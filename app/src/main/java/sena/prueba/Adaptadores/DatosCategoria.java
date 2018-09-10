package sena.prueba.Adaptadores;

public class DatosCategoria {

    //Creamos los campos necesarios
    private int id;
    private int imagen;
    private String nombre;
    private String direccion;
    private String email;
    private String telefono;

    public DatosCategoria(int id, int imagen, String nombre, String direccion, String email, String telefono) {
        this.id = id;
        this.imagen = imagen;
        this.nombre = nombre;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
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

    public String getDireccion() {
        return direccion;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }
}
