package Daniela.TodoGamersApp.Controller;


import Daniela.TodoGamersApp.Model.Game;
import Daniela.TodoGamersApp.Model.Platform;
import Daniela.TodoGamersApp.Repository.GameRepository;
import Daniela.TodoGamersApp.Repository.PlatformRepository;
import Daniela.TodoGamersApp.Service.AlmacenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private GameRepository gameRepositorio;

	@Autowired
	private PlatformRepository platformRepository;

	@Autowired
	private AlmacenServiceImpl servicio;

	
	@GetMapping("")
	public ModelAndView verPaginaDeInicio(@PageableDefault(sort = "titulo", size = 5) Pageable pageable) {
		Page<Game> games = gameRepositorio.findAll(pageable);
		return new ModelAndView("adminindex").addObject("games", games);
	}

	@GetMapping("/juego/nuevo")
	public ModelAndView mostrarFormularioDeNuevoJuego() {
		List<Platform> platform = platformRepository.findAll(Sort.by("titulo"));
		return new ModelAndView("nuevo-juego")
				.addObject("game", new Game())
				.addObject("platforms", platform);
	}
	
	@PostMapping("/juego/nuevo")
	public ModelAndView registrarJuego(@Validated Game game,BindingResult bindingResult) {
		if(bindingResult.hasErrors() || game.getPortada().isEmpty()) {
			if(game.getPortada().isEmpty()) {
				bindingResult.rejectValue("portada","MultipartNotEmpty");
			}
			
			List<Platform> platforms = platformRepository.findAll(Sort.by("titulo"));
			return new ModelAndView("nuevo-juego")
					.addObject("game", game)
					.addObject("platforms", platforms);
		}
		
		String rutaPortada = servicio.almacenarArchivo(game.getPortada());
		game.setRutaPortada(rutaPortada);
		
		gameRepositorio.save(game);
		return new ModelAndView("redirect:/admin");
	}
	
	@GetMapping("/juego/{id}/editar")
	public ModelAndView mostrarFormilarioDeEditarJuego(@PathVariable Integer id) {
		Game game = gameRepositorio.getOne(id);
		List<Platform> platforms = platformRepository.findAll(Sort.by("titulo"));
		
		return new ModelAndView("editar-juego")
				.addObject("game", game)
				.addObject("platforms", platforms);
	}
	
	@PostMapping("/juego/{id}/editar")
	public ModelAndView actualizarJuego(@PathVariable Integer id,@Validated Game game,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			List<Platform> platforms = platformRepository.findAll(Sort.by("titulo"));
			return new ModelAndView("editar-juego")
					.addObject("game", game)
					.addObject("platforms", platforms);
		}
		
		Game gameDB = gameRepositorio.getOne(id);
		gameDB.setTitulo(game.getTitulo());
		gameDB.setDescripcion(game.getDescripcion());
		gameDB.setPersonajes(game.getPersonajes());
		gameDB.setFechaEstreno(game.getFechaEstreno());
		gameDB.setYoutubeTrailerId(game.getYoutubeTrailerId());
		gameDB.setPlataformas(game.getPlataformas());
		
		if(!game.getPortada().isEmpty()) {
			servicio.eliminarArchivo(gameDB.getRutaPortada());
			String rutaPortada = servicio.almacenarArchivo(game.getPortada());
			gameDB.setRutaPortada(rutaPortada);
		}

		gameRepositorio.save(gameDB);
		return new ModelAndView("redirect:/admin");
	}
	
	@PostMapping("/juego/{id}/eliminar")
	public String eliminarJuego(@PathVariable Integer id) {
		Game game = gameRepositorio.getOne(id);
		gameRepositorio.delete(game);
		servicio.eliminarArchivo(game.getRutaPortada());
		
		return "redirect:/admin";
	}
}
