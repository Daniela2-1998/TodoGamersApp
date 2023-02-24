package Daniela.TodoGamersApp.Model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id_game")
    private Integer id;

    private String titulo;

    private String descripcion;

    private String personajes;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaEstreno;

    private String youtubeTrailerId;

    private String rutaPortada;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "plataforma_juego",
            joinColumns = @JoinColumn(name = "id_juego"),
            inverseJoinColumns = @JoinColumn(name = "id_plataforma"))
    private List<Platform> plataformas;


    @Transient
    private MultipartFile portada;




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPersonajes() {
        return personajes;
    }

    public void setPersonajes(String personajes) {
        this.personajes = personajes;
    }

    public LocalDate getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(LocalDate fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public String getYoutubeTrailerId() {
        return youtubeTrailerId;
    }

    public void setYoutubeTrailerId(String youtubeTrailerId) {
        this.youtubeTrailerId = youtubeTrailerId;
    }

    public String getRutaPortada() {
        return rutaPortada;
    }

    public void setRutaPortada(String rutaPortada) {
        this.rutaPortada = rutaPortada;
    }

    public List<Platform> getPlataformas() {
        return plataformas;
    }

    public void setPlataformas(List<Platform> plataformas) {
        this.plataformas = plataformas;
    }

    public MultipartFile getPortada() {
        return portada;
    }

    public void setPortada(MultipartFile portada) {
        this.portada = portada;
    }



    public Game() {
    }

    public Game(Integer id, String titulo, String descripcion, String personajes, LocalDate fechaEstreno, String youtubeTrailerId, String rutaPortada, List<Platform> plataformas, MultipartFile portada) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.personajes = personajes;
        this.fechaEstreno = fechaEstreno;
        this.youtubeTrailerId = youtubeTrailerId;
        this.rutaPortada = rutaPortada;
        this.plataformas = plataformas;
        this.portada = portada;
    }
}
