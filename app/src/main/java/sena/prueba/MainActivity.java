package sena.prueba;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sena.prueba.Adaptadores.AdapterCategoria;
import sena.prueba.Adaptadores.Categoria;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerCategorias;
    private List<Categoria> categoriaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creamos las referencias
        recyclerCategorias = (RecyclerView) findViewById(R.id.recycler_principal);

        if (getRotation().equals("Horizontal") || getRotation().equals("Horizontal_inversa")){
            recyclerCategorias.setLayoutManager(new GridLayoutManager(this, 2));
        }

        recyclerCategorias.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        categoriaList = new ArrayList<>();
        categoriaList.add(new Categoria(R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, "Este es mi titulo"));

        //Creamos una instancia del adaptador de Categorias
        AdapterCategoria adapter = new AdapterCategoria(this, categoriaList);
        recyclerCategorias.setAdapter(adapter);

        recyclerCategorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Tocaste un item de categoria.", Toast.LENGTH_SHORT).show();
            }
        });
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
