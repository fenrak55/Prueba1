package sena.prueba;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import sena.prueba.Adaptadores.AdapterCategoria;
import sena.prueba.Adaptadores.Categoria;
import sena.prueba.database.ConexionDb;
import sena.prueba.database.Utilidades;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerCategorias;
    private List<Categoria> listaCategoria;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creamos las referencias
        recyclerCategorias = (RecyclerView) findViewById(R.id.recycler_principal);
        toolbar = (Toolbar) findViewById(R.id.toolbar_categorias);

        toolbar.setTitle("Categorias");

        setSupportActionBar(toolbar);

        if (getRotation().equals("Horizontal") || getRotation().equals("Horizontal_inversa")){
            recyclerCategorias.setLayoutManager(new GridLayoutManager(this, 2));
        }else{
            recyclerCategorias.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        }

        llenaRecycler();

        if (listaCategoria != null) {
            //Creamos una instancia del adaptador de Categorias
            AdapterCategoria adapter = new AdapterCategoria(this, listaCategoria);
            adapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Rescato el id de la categoria
                    int id = listaCategoria.get(recyclerCategorias.getChildAdapterPosition(v)).getId();
                    evento(id);
                }
            });
            recyclerCategorias.setAdapter(adapter);
        }else{
            Toast.makeText(this, "La lista de categorias esta vacia.", Toast.LENGTH_SHORT).show();
        }
    }

    public void evento(int id){
        Intent objIntent = new Intent(MainActivity.this, ListItemCategoria.class);
        objIntent.putExtra(Utilidades.ID_TBL_CATEGORIA, id);
        startActivity(objIntent);
    }

    public void llenaRecycler(){
        try{
            //Conectamos con la base de datos
            ConexionDb objConecta = new ConexionDb(this, "sitios", null, 1);
            SQLiteDatabase objDb = objConecta.getWritableDatabase();
            //Creamos la consulta a la base de datos
            Cursor objCursor = objDb.rawQuery("SELECT * FROM " + Utilidades.TBL_CATEGORIA, null);
            //si obtengo registros
            if (objCursor.moveToFirst()){
                listaCategoria = new ArrayList<>();
                do {
                    listaCategoria.add(new Categoria(objCursor.getInt(0), objCursor.getInt(1), objCursor.getInt(2), objCursor.getString(3)));
                }while (objCursor.moveToNext());
                objCursor.close();
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
}
