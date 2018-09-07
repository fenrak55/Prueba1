package sena.prueba.Adaptadores;

public class Categoria {

    private int imagePrincipal;
    private int iconoPrincipal;
    private String nombreCategoria;

    public Categoria(int imagePrincipal, int iconoPrincipal, String nombreCategoria) {
        this.imagePrincipal = imagePrincipal;
        this.iconoPrincipal = iconoPrincipal;
        this.nombreCategoria = nombreCategoria;
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
