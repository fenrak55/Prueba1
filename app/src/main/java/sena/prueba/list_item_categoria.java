package sena.prueba;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import sena.prueba.Adaptadores.AdapterListCategoria;
import sena.prueba.Adaptadores.DatosCategoria;

public class list_item_categoria extends AppCompatActivity {

    //Creamos los objetos
    RecyclerView recyclerListItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item_categoria);

        //Creamos las referencias
        recyclerListItem = (RecyclerView) findViewById(R.id.recycler_list_item_categoria);

        recyclerListItem.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        List<DatosCategoria> listaCategoria = new ArrayList<>();
        listaCategoria.add(new DatosCategoria(R.drawable.ic_launcher_background, "Primer Categoria", "Bogota"));

        //Creamos una instancia del adaptador
        AdapterListCategoria adapter = new AdapterListCategoria(this, listaCategoria);
        recyclerListItem.setAdapter(adapter);
    }
}
