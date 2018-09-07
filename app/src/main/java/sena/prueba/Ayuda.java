package sena.prueba;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;



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
}
