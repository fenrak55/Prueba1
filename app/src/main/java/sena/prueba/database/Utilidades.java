package sena.prueba.database;

import sena.prueba.R;

public class Utilidades {


    //Campos para la tabla Categorias
    public static final String TBL_CATEGORIA = "Categoria";
    public static final String ID_TBL_CATEGORIA = "Id";
    public static final String IMAGEN_TBL_CATEGORIA = "Imagen";
    public static final String ICONO_TBL_CATEGORIA = "Icono";
    public static final String NOMBRE_TBL_CATEGORIA = "Nombre_cat";
    public static final String CREA_TBL_CATEGORIA = "CREATE TABLE " + TBL_CATEGORIA + " (" +
            ID_TBL_CATEGORIA + " INTEGER PRIMARY KEY, " +
            IMAGEN_TBL_CATEGORIA + " INTEGER NOT NULL, " +
            ICONO_TBL_CATEGORIA + " INTEGER NOT NULL, " +
            NOMBRE_TBL_CATEGORIA + " TEXT NOT NULL)";
    public static final String INSERTA_TBL_CATEGORIA = "INSERT INTO " + TBL_CATEGORIA +
            "(" + IMAGEN_TBL_CATEGORIA + ", " + ICONO_TBL_CATEGORIA + ", " + NOMBRE_TBL_CATEGORIA + ") VALUES " +
            "(" + R.drawable.que_visitar + ", " + R.drawable.que_visitar + ", 'Que visitar'), " +
            "(" + R.drawable.que_comer + ", " + R.drawable.que_comer + ", 'Donde comer'), " +
            "(" + R.drawable.que_hacer + ", " + R.drawable.que_hacer + ", 'Que hacer'), " +
            "(" + R.drawable.sitio_turisticos + ", " + R.drawable.sitio_turisticos + ", 'Servicios turisticos'), " +
            "(" + R.drawable.llamadas_urgentes + ", " + R.drawable.llamadas_urgentes + ", 'Llamadas urgentes')";

    /*
    //Campos para la  tabla tipo_item
    public static final String TBL_TIPO_ITEM = "Tipo_item";
    public static final String ID_TBL_TIPO_ITEM = "Id";
    public static final String NOMBRE_TBL_TIPO_ITEM = "Nombre";
    public static final String CREA_TBL_TIPO_ITEM = "CREATE TABLE " + TBL_TIPO_ITEM + " (" +
            ID_TBL_TIPO_ITEM + " INTEGER PRIMARY KEY, " +
            NOMBRE_TBL_TIPO_ITEM + " TEXT NOT NULL)";


    //Campos para la tabla lugar
    public static final String TBL_LUGAR = "Lugar";
    public static final String ID_TBL_LUGAR = "Id";
    public static final String NOMBRE_TBL_LUGAR = "Nombre";
    public static final String DESCRIPCION_TBL_LUGAR = "Descripcion";
    public static final String DIRECCION_TBL_LUGAR = "Direccion";
    public static final String TELEFONO_TBL_LUGAR = "Telefono";
    public static final String SITIO_WEB_TBL_LUGAR = "Sitio_web";
    public static final String EMAIL_TBL_LUGAR = "Email";
    public static final String ID_CATEGORIA_TBL_LUGAR = "Id_categoria";
    public static final String ID_TIPO_ITEM_TBL_LUGAR = "Id_tipo_item";
    public static final String CREA_TBL_LUGAR = "CREATE TABLE " + TBL_LUGAR + " (" +
            ID_TBL_LUGAR + " INTEGER PRIMARY KEY, " +
            NOMBRE_TBL_LUGAR + " TEXT NOT NULL, " +
            DESCRIPCION_TBL_LUGAR + " TEXT NOT NULL, " +
            DIRECCION_TBL_LUGAR + " TEXT NOT NULL, " +
            TELEFONO_TBL_LUGAR + " TEXT NOT NULL, " +
            SITIO_WEB_TBL_LUGAR + " TEXT NOT NULL, " +
            EMAIL_TBL_LUGAR + " TEXT NOT NULL, " +
            ID_CATEGORIA_TBL_LUGAR + " INTEGER NOT NULL, " +
            ID_TIPO_ITEM_TBL_LUGAR + " INTEGER NOT NULL)";
    //Creo las relaciones
    public static final String CREA_RELACION_TBL_LUGAR = "ALTER TABLE " + TBL_LUGAR + " ADD CONSTRAINT 'fk_categoria' FOREIGN KEY ('" +
            ID_TBL_LUGAR + "') REFERENCES '" + TBL_CATEGORIA + "' ('" + ID_TBL_CATEGORIA + "') ON DELETE SET NULL ON UPDATE CASCADE, " +
            "ADD CONSTRAINT 'fk_tipo_item' FOREIGN KEY ('" + ID_TIPO_ITEM_TBL_LUGAR + "') REFERENCES '" + TBL_TIPO_ITEM + "' ('" + ID_TBL_TIPO_ITEM + "') ON DELETE SET NULL ON UPDATE CASCADE";
    */
}
