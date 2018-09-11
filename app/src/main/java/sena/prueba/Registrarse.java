package sena.prueba;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class Registrarse extends AppCompatActivity {

    private EditText campoNombre, campoEmail, campoCelular;
    private Toolbar toolbarRegistro;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //Creamos las referencias
        campoNombre = (EditText) findViewById(R.id.txt_nombre);
        campoEmail = (EditText) findViewById(R.id.txt_email);
        campoCelular = (EditText) findViewById(R.id.txt_celular);
        toolbarRegistro = (Toolbar) findViewById(R.id.toolbar_registro);

        toolbarRegistro.setTitle("Registro");
        setSupportActionBar(toolbarRegistro);

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
        Intent intent = null;
        switch (id){
            case R.id.menu_ayuda:
                intent = new Intent(Registrarse.this, Ayuda.class);
                startActivity(intent);
                break;
            case R.id.menu_salir:
                intent=new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            }
        return super.onOptionsItemSelected(item);
    }
}
