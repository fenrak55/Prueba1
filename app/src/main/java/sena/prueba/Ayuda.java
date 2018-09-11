package sena.prueba;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.nio.file.FileSystemNotFoundException;


public class Ayuda extends AppCompatActivity {

    private Toolbar toolbarAyuda;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);

        toolbarAyuda = (Toolbar) findViewById(R.id.toolbar_ayuda);
        toolbarAyuda.setTitle("Ayuda");
        setSupportActionBar(toolbarAyuda);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_informacion_item,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent objIntent = null;
        switch (id){
            case R.id.menu_registrarse:
                objIntent = new Intent(Ayuda.this, Registrarse.class);
                startActivity(objIntent);
                break;
            case R.id.menu_configuracion:
                //Cambiamos a la actividad configuracion
                objIntent = new Intent(Ayuda.this, Configuracion.class);
                startActivity(objIntent);
                break;
            case R.id.menu_salir:
                Intent intent1=new Intent(Intent.ACTION_MAIN);
                intent1.addCategory(Intent.CATEGORY_HOME);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
