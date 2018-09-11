package sena.prueba.Adaptadores;

public class Item {
    private int id;
    private int imagen;
    private String nombre;
    private String descripcion;

    public Item(int id, int imagen, String nombre, String descripcion) {
        this.id = id;
        this.imagen = imagen;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public int getImagen() {
        return imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
