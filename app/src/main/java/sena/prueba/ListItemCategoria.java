package sena.prueba;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Surface;
import android.view.WindowManager;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sena.prueba.Adaptadores.AdapterListCategoria;
import sena.prueba.Adaptadores.DatosCategoria;
import sena.prueba.database.ConexionDb;
import sena.prueba.database.Utilidades;

public class ListItemCategoria extends AppCompatActivity {

    //Creamos los objetos
    private RecyclerView recyclerListItem;
    private List<DatosCategoria> listDatosCategoria;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private SQLiteDatabase objDb;
    public static int idC = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item_categoria);

        //Creamos las referencias
        recyclerListItem = (RecyclerView) findViewById(R.id.recycler_list_item_categoria);
        toolbar = (Toolbar) findViewById(R.id.toolbar_list_categorias);
        toolbar.setTitle("Lista de items");

        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        if (getRotation().equals("Horizontal") || getRotation().equals("Horizontal_inversa")){
            recyclerListItem.setLayoutManager(new GridLayoutManager(this, 2));
        }else{
            recyclerListItem.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        }

        //Recibimos el id de MainActivity
        if (getIntent().getIntExtra(Utilidades.ID_TBL_CATEGORIA, 0) != 0){
            idC = getIntent().getIntExtra(Utilidades.ID_TBL_CATEGORIA, 0);
            llenaRecycler(idC);
        }else {
            llenaRecycler(idC);
        }
        //Creamos una instancia del adaptador
        AdapterListCategoria adapter = new AdapterListCategoria(this, listDatosCategoria);
        recyclerListItem.setAdapter(adapter);
    }

    private void llenaRecycler(int id) {
        try{
            //Conecto con la base de datos
            objDb = conecta();
            //Creo la consulta a la base de datos
            String[] args = new String[]{String.valueOf(id)};
            Cursor objCursor = objDb.rawQuery("SELECT Lugar.Id, Lugar.Imagen, Lugar.Nombre, Lugar.Direccion, Lugar.Email, Lugar.Telefono FROM Lugar INNER JOIN Categoria ON Lugar.Id_categoria = Categoria.Id WHERE Lugar.Id_categoria = ?", args);
            //Si hay registros
            listDatosCategoria = new ArrayList<>();
            if (objCursor.moveToFirst()){
                do {
                    listDatosCategoria.add(new DatosCategoria(objCursor.getInt(0), objCursor.getInt(1), objCursor.getString(2), objCursor.getString(3),
                            objCursor.getString(4), objCursor.getString(5)));
                }while (objCursor.moveToNext());
            }
        }catch (Exception e){
            e.getStackTrace();
        }
    }

    public String getRotation() {
        final int rotation = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRotation();
        String mensaje = null;
        switch (rotation){
            case Surface.ROTATION_0:
                mensaje = "Vertical";
                break;
            case Surface.ROTATION_90:
                mensaje = "Horizontal";
                break;
            case Surface.ROTATION_180:
                mensaje = "Vertical_inversa";
                break;
            case Surface.ROTATION_270:
                mensaje = "Horizontal_inversa";
                break;
        }
        return mensaje;
    }

    public SQLiteDatabase conecta(){
        ConexionDb conexion = new ConexionDb(this, "sitios", null, 1);
        return conexion.getWritableDatabase();
    }
}
