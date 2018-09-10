package sena.prueba.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionDb extends SQLiteOpenHelper{

    public ConexionDb(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //CREO LAS TABLAS
        db.execSQL(Utilidades.CREA_TBL_CATEGORIA);
        db.execSQL(Utilidades.CREA_TBL_TIPO_ITEM);
        db.execSQL(Utilidades.CREA_TBL_LUGAR);
        db.execSQL(Utilidades.CREA_TBL_USUARIO);

        //INSERTO EN LAS TABLAS
        db.execSQL(Utilidades.INSERTA_TBL_CATEGORIA);
        db.execSQL(Utilidades.INSERTA_TBL_TIPO_ITEM);
        db.execSQL(Utilidades.INSERTA_TBL_LUGAR);
        db.execSQL(Utilidades.INSERTA_TBL_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
