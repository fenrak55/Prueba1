package sena.prueba.Adaptadores;

public class DatosCategoria {

    //Creamos los campos necesarios
    private int imagen;
    private String nombre;
    private String direccion;

    public DatosCategoria(int imagen, String nombre, String direccion) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public int getImagen() {
        return imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }
}
