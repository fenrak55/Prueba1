package sena.prueba;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {
    ImageView visitar,dormir,comer,hacer,servicios,llamada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        visitar=findViewById(R.id.visitaImagen);
        dormir=findViewById(R.id.dormirImagen);
        comer=findViewById(R.id.comerImagen);
        hacer=findViewById(R.id.hacerImagen);
        servicios=findViewById(R.id.servicioImagen);
        llamada=findViewById(R.id.llamadaImagen);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.visitaImagen:
                startActivity(new Intent(MainActivity.this,list_item_categoria.class));
        }
    }




}
