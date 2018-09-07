package sena.prueba;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.nio.file.FileSystemNotFoundException;


public class Ayuda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);

        String title="Ayuda";
        setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_informacion_item,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Metodo para pulsar el overflow menu


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_registrarse:
                Intent intent;
                startActivity(intent= new Intent(Ayuda.this,Registrarse.class));
                break;
            case R.id.menu_salir:
                /*android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);*/
                Intent intent1=new Intent(Intent.ACTION_MAIN);
                intent1.addCategory(Intent.CATEGORY_HOME);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);

        }


        return super.onOptionsItemSelected(item);
    }
}
