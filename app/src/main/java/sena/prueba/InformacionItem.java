package sena.prueba;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import sena.prueba.database.ConexionDb;
import sena.prueba.database.Utilidades;

public class InformacionItem extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private ActionBar actionBar;
    private ImageView imagen;
    private TextView nombre, descripcion, sitioWeb, direccion, email, telefono;
    private ImageButton btnWeb, btnMensaje, btnTelefono;
    private SQLiteDatabase objDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_item);

        //Creo las referencias
        toolbar = (Toolbar) findViewById(R.id.toolbar_item);
        imagen = (ImageView) findViewById(R.id.logo_item);
        nombre = (TextView) findViewById(R.id.nombre_item);
        descripcion = (TextView) findViewById(R.id.descripcion_item);
        sitioWeb = (TextView) findViewById(R.id.sitio_web_item);
        direccion = (TextView) findViewById(R.id.direccion_item);
        email = (TextView) findViewById(R.id.email_item);
        telefono = (TextView) findViewById(R.id.telefono_item);
        btnWeb = (ImageButton) findViewById(R.id.btn_image_web);
        btnMensaje = (ImageButton) findViewById(R.id.btn_image_mensaje);
        btnTelefono = (ImageButton) findViewById(R.id.btn_image_telefono);

        //Creamos los escuchadores
        btnWeb.setOnClickListener(this);
        btnMensaje.setOnClickListener(this);
        btnTelefono.setOnClickListener(this);

        toolbar.setTitle("Informacion");
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Recibo el id del AdapterListCategoria
        int id = getIntent().getIntExtra(Utilidades.ID_TBL_LUGAR, 0);
        if (id != 0){
            consulta(id);
        }
    }

    private void consulta(int id) {
        try {
            //Conectamos con la base de datos
            objDb = conecta();
            //Hacemos la consulta
            String[] args = new String[]{String.valueOf(id)};
            Cursor objCursor = objDb.rawQuery("SELECT * FROM Lugar WHERE Id = ?", args);
            //Si obtenemos registros
            if (objCursor.moveToFirst()){
                do {
                    imagen.setImageResource(objCursor.getInt(1));
                    nombre.setText(objCursor.getString(2));
                    descripcion.setText(objCursor.getString(3));
                    direccion.setText(objCursor.getString(4));
                    telefono.setText(objCursor.getString(5));
                    sitioWeb.setText(objCursor.getString(6));
                    email.setText(objCursor.getString(7));
                }while (objCursor.moveToNext());
            }
        }catch (Exception e){
            e.getStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_informacion_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_registrarse:
                break;
            case R.id.menu_ayuda:
                break;
            case R.id.menu_salir:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public SQLiteDatabase conecta(){
        ConexionDb objConecta = new ConexionDb(this, "sitios", null, 1);
        return objConecta.getWritableDatabase();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        String mensaje = "";
        switch (id){
            case R.id.btn_image_web:
                mensaje = "Sitio web";
                break;
            case R.id.btn_image_mensaje:
                mensaje = "Mensaje";
                break;
            case R.id.btn_image_telefono:
                mensaje = "Telefono";
                break;
        }
        Toast.makeText(this, "" + mensaje, Toast.LENGTH_SHORT).show();
    }
}
