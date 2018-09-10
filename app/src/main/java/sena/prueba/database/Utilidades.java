package sena.prueba.database;

import sena.prueba.R;

public class Utilidades {


    //CREACION DE TABLAS

    //Campos para la tabla Categorias
    public static final String TBL_CATEGORIA = "Categoria";
    public static final String ID_TBL_CATEGORIA = "Id";
    public static final String IMAGEN_TBL_CATEGORIA = "Imagen";
    public static final String ICONO_TBL_CATEGORIA = "Icono";
    public static final String NOMBRE_TBL_CATEGORIA = "Nombre";
    public static final String CREA_TBL_CATEGORIA = "CREATE TABLE " + TBL_CATEGORIA + " (" +
            ID_TBL_CATEGORIA + " INTEGER PRIMARY KEY, " +
            IMAGEN_TBL_CATEGORIA + " INTEGER NOT NULL, " +
            ICONO_TBL_CATEGORIA + " INTEGER NOT NULL, " +
            NOMBRE_TBL_CATEGORIA + " TEXT NOT NULL)";

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
    public static final String IMAGEN_LUGAR = "Imagen";
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
            IMAGEN_LUGAR + " INTEGER NOT NULL, " +
            NOMBRE_TBL_LUGAR + " TEXT NOT NULL, " +
            DESCRIPCION_TBL_LUGAR + " TEXT NOT NULL, " +
            DIRECCION_TBL_LUGAR + " TEXT NOT NULL, " +
            TELEFONO_TBL_LUGAR + " TEXT NOT NULL, " +
            SITIO_WEB_TBL_LUGAR + " TEXT NOT NULL, " +
            EMAIL_TBL_LUGAR + " TEXT NOT NULL, " +
            ID_CATEGORIA_TBL_LUGAR + " INTEGER NOT NULL, " +
            ID_TIPO_ITEM_TBL_LUGAR + " INTEGER NOT NULL, " +
            "FOREIGN KEY(" + ID_CATEGORIA_TBL_LUGAR + ") REFERENCES " + TBL_CATEGORIA + " (" + ID_TBL_CATEGORIA + "), " +
            "FOREIGN KEY(" + ID_TIPO_ITEM_TBL_LUGAR + ") REFERENCES " + TBL_TIPO_ITEM + " (" + ID_TBL_TIPO_ITEM + "))";

    //Campos para la tabla Usuario
    public static final String TBL_USUARIO = "Usuario";
    public static final String ID_TBL_USUARIO = "Id";
    public static final String NOMBRE_TBL_USUARIO = "Nombre";
    public static final String TELEFONO_TBL_USUARIO = "Telefono";
    public static final String EMAIL_TBL_USUARIO = "Email";
    public static final String CREA_TBL_USUARIO = "CREATE TABLE " + TBL_USUARIO + " (" +
            ID_TBL_USUARIO + " INTEGER PRIMARY KEY, "+
            NOMBRE_TBL_USUARIO + " TEXT NOT NULL, " +
            TELEFONO_TBL_USUARIO + " TEXT NOT NULL, " +
            EMAIL_TBL_USUARIO + " TEXT NOT NULL)";


    //INSERCION EN LAS TABLAS

    //Inseta en la tabla Categorias
    public static final String INSERTA_TBL_CATEGORIA = "INSERT INTO " + TBL_CATEGORIA +
            "(" + IMAGEN_TBL_CATEGORIA + ", " + ICONO_TBL_CATEGORIA + ", " + NOMBRE_TBL_CATEGORIA + ") VALUES " +
            "(" + R.drawable.que_visitar + ", " + R.drawable.que_visitar + ", 'Que visitar'), " +
            "(" + R.drawable.que_comer + ", " + R.drawable.que_comer + ", 'Donde comer'), " +
            "(" + R.drawable.que_hacer + ", " + R.drawable.que_hacer + ", 'Que hacer'), " +
            "(" + R.drawable.sitio_turisticos + ", " + R.drawable.sitio_turisticos + ", 'Servicios turisticos'), " +
            "(" + R.drawable.llamadas_urgentes + ", " + R.drawable.llamadas_urgentes + ", 'Llamadas urgentes')";

    //Inserta en la tabla Tipo_item
    public static final String INSERTA_TBL_TIPO_ITEM = "INSERT INTO " + TBL_TIPO_ITEM +
            " (" + NOMBRE_TBL_TIPO_ITEM + ") VALUES " +
            "('Museos')," +
            "('Monumentos')," +
            "('Parques')," +
            "('Zona de camping')," +
            "('Hoteles')," +
            "('Heladerias')," +
            "('Restaurantes')," +
            "('Guias turisticos')," +
            "('Transporte')," +
            "('Drogueria')," +
            "('Policia')";

    //Inserta en la tabla Lugar
    public static final String INSERTA_TBL_LUGAR = "INSERT INTO " + TBL_LUGAR + " (" +
            IMAGEN_LUGAR + ", " + NOMBRE_TBL_LUGAR + ", " + DESCRIPCION_TBL_LUGAR + ", " + DIRECCION_TBL_LUGAR + ", " + TELEFONO_TBL_LUGAR + ", " +
            SITIO_WEB_TBL_LUGAR + ", " + EMAIL_TBL_LUGAR + ", " + ID_CATEGORIA_TBL_LUGAR + ", " + ID_TIPO_ITEM_TBL_LUGAR + ") VALUES " +
            "(" + R.drawable.museo_nacional + ", 'Museo nacional', 'Un lugar al cual visitar', 'Bogota centro', '321321', 'www.museonacional.com', 'museo@nacional.com', 1, 1)," +
            "(" + R.drawable.parque_simon_bolivar + ", 'Parque simon bolivar', 'Un lugar para recrear', 'Av. Calle 53 y Av. Esmeralda', '546131354', 'www.simonbolivar.com', 'sbolivar@nacional.com', 1, 1)," +
            "(" + R.drawable.salitre_magico + ", 'Salitre magico', 'Parque de diversiones', 'Avenida 68', '68765342', 'www.salitre.com', 'salitre@gmail.com', 3, 3)";

    //Inserta en la tabla Usuario
    public static final String INSERTA_TBL_USUARIO = "INSERT INTO " + TBL_USUARIO + " (" +
            NOMBRE_TBL_USUARIO + ", " + TELEFONO_TBL_USUARIO + ", " + EMAIL_TBL_USUARIO + ") VALUES " +
            "('Faiber', '65634344', 'faiber@gmail.com'), " +
            "('Andres', '35687588', 'andres@hotmail.com')";
}
