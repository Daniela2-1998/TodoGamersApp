package Daniela.TodoGamersApp.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Platform {

    @Id
    @Column(name = "id_platform")
    private Integer id;

    private String titulo;


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


    public Platform() {
    }

    public Platform(Integer id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }
}
