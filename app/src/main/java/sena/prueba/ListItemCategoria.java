package sena.prueba;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Surface;
import android.view.WindowManager;
import android.support.v7.widget.Toolbar;
import java.util.ArrayList;
import java.util.List;

import sena.prueba.Adaptadores.AdapterListCategoria;
import sena.prueba.Adaptadores.DatosCategoria;

public class list_item_categoria extends AppCompatActivity {

    //Creamos los objetos
    RecyclerView recyclerListItem;
    Toolbar toolbar;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item_categoria);

        //Creamos las referencias
        recyclerListItem = (RecyclerView) findViewById(R.id.recycler_list_item_categoria);
        toolbar = (Toolbar) findViewById(R.id.toolbar_list_categorias);

        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        if (getRotation().equals("Horizontal") || getRotation().equals("Horizontal_inversa")){
            recyclerListItem.setLayoutManager(new GridLayoutManager(this, 2));
        }

        recyclerListItem.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        List<DatosCategoria> listaCategoria = new ArrayList<>();
        listaCategoria.add(new DatosCategoria(R.drawable.ic_launcher_background, "Primer Categoria", "Bogota"));

        //Creamos una instancia del adaptador
        AdapterListCategoria adapter = new AdapterListCategoria(this, listaCategoria);
        recyclerListItem.setAdapter(adapter);
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
