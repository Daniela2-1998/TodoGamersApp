package Daniela.TodoGamersApp.Controller;


import Daniela.TodoGamersApp.Model.Game;
import Daniela.TodoGamersApp.Model.Platform;
import Daniela.TodoGamersApp.Repository.GameRepository;
import Daniela.TodoGamersApp.Repository.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(path = "inicio")
public class MainController {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private PlatformRepository platformRepository;

    @GetMapping(value = "")
    public ModelAndView paginaInicio(){

        return new ModelAndView("index");
    }


    @GetMapping(value = "inicio")
    public ModelAndView verPaginaDeInicio(@PageableDefault(sort = "titulo", size = 5) Pageable pageable) {
        Page<Game> ultimosJuegos = gameRepository.findAll(pageable);
        return new ModelAndView("index").addObject("ultimosJuegos", ultimosJuegos);
    }

    @GetMapping("juego/nuevo")
    public ModelAndView mostrarFormularioDeNuevoJuego() {
        List<Platform> platforms = platformRepository.findAll(Sort.by("titulo"));
        return new ModelAndView("nuevo-juego")
                .addObject("game", new Game())
                .addObject("platforms", platforms);
    }

    @PostMapping(path = "/agregar")
    public @ResponseBody String agregarJuego(@RequestParam String titulo, @RequestParam String descripcion, @RequestParam String personajes,
                                             @RequestParam LocalDate fechaEstreno, @RequestParam String youtubeTrailerId,
                                             @RequestParam String rutaPortada, @RequestParam List<Platform> plataformas,
                                             @RequestParam MultipartFile portada){
        Game game = new Game();
        game.setTitulo(titulo);
        game.setDescripcion(descripcion);
        game.setPersonajes(personajes);
        game.setFechaEstreno(fechaEstreno);
        game.setYoutubeTrailerId(youtubeTrailerId);
        game.setRutaPortada(rutaPortada);
        game.setPlataformas(plataformas);
        game.setPortada(portada);
        gameRepository.save(game);

        return "Guardado";
    }

    @GetMapping("/juegos")
    public ModelAndView listarJuegos(@PageableDefault(sort = "fechaEstreno",direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Game> games = gameRepository.findAll(pageable);
        return new ModelAndView("juegos")
                .addObject("games", games);
    }

    @GetMapping("/juego/{id}")
    public ModelAndView mostrarDetallesDeJuego(@PathVariable Integer id) {
        Game game = gameRepository.getOne(id);
        return new ModelAndView("game").addObject("game", game);
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Game> getAllGames() {
        // This returns a JSON or XML with the users
        return gameRepository.findAll();
    }
}
