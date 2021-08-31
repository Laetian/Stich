package es.ifp.stich;

public class Nota {

    protected int id = 0;
    protected String titulo;
    protected String comment;

    public Nota(int id, String titulo, String comment) {
        this.id = id;
        this.titulo = titulo;
        this.comment = comment;

    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getComment() {


        return this.comment;

    }

    public void setComment(String comment) {
        this.comment = comment;
    }



}

