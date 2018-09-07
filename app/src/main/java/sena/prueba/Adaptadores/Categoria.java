package sena.prueba.Adaptadores;

public class Categoria {

    private int id;
    private int imagePrincipal;
    private int iconoPrincipal;
    private String nombreCategoria;

    public Categoria(int id, int imagePrincipal, int iconoPrincipal, String nombreCategoria) {
        this.id = id;
        this.imagePrincipal = imagePrincipal;
        this.iconoPrincipal = iconoPrincipal;
        this.nombreCategoria = nombreCategoria;
    }

    public int getId() {
        return id;
    }

    public int getImagePrincipal() {
        return imagePrincipal;
    }

    public int getIconoPrincipal() {
        return iconoPrincipal;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }
}
