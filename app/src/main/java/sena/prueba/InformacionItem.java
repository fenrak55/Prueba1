package sena.prueba;

import android.content.Intent;
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
    private TextView viewNombre, viewDescripcion, viewSitioweb, viewDireccion, viewEmail, viewTelefono;
    private ImageButton btnWeb, btnMensaje, btnTelefono;
    private SQLiteDatabase objDb;
    private String sitioWeb, email, telefono;
    private static int idC = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_item);

        //Creo las referencias
        toolbar = (Toolbar) findViewById(R.id.toolbar_item);
        imagen = (ImageView) findViewById(R.id.logo_item);
        viewNombre = (TextView) findViewById(R.id.nombre_item);
        viewDescripcion = (TextView) findViewById(R.id.descripcion_item);
        viewSitioweb = (TextView) findViewById(R.id.sitio_web_item);
        viewDireccion = (TextView) findViewById(R.id.direccion_item);
        viewEmail = (TextView) findViewById(R.id.email_item);
        viewTelefono = (TextView) findViewById(R.id.telefono_item);
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

        //Recibimos el id de MainActivity
        if (getIntent().getIntExtra(Utilidades.ID_TBL_LUGAR, 0) != 0){
            idC = getIntent().getIntExtra(Utilidades.ID_TBL_LUGAR, 0);
            consulta(idC);
        }else {
            consulta(idC);
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
                    //Asigno los valores
                    imagen.setImageResource(objCursor.getInt(1));
                    viewNombre.setText(objCursor.getString(2));
                    viewDescripcion.setText(objCursor.getString(3));
                    viewDireccion.setText(objCursor.getString(4));
                    telefono = objCursor.getString(5);
                    viewTelefono.setText(telefono);
                    sitioWeb = objCursor.getString(6);
                    viewSitioweb.setText(sitioWeb);
                    email = objCursor.getString(7);
                    viewEmail.setText(email);
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
        Intent objInten = null;
        switch (id){
            case R.id.menu_registrarse:
                //Cambiamos a la actividad de registro
                objInten = new Intent(InformacionItem.this, Registrarse.class);
                startActivity(objInten);
                break;
            case R.id.menu_configuracion:
                //Cambiamos a la actividad configuracion
                objInten = new Intent(InformacionItem.this, Configuracion.class);
                startActivity(objInten);
                break;
            case R.id.menu_ayuda:
                //Cambiamos a la actividad de ayuda
                objInten = new Intent(InformacionItem.this, Ayuda.class);
                startActivity(objInten);
                break;
            case R.id.menu_salir:
                //Salimos de la aplicacion
                objInten=new Intent(Intent.ACTION_MAIN);
                objInten.addCategory(Intent.CATEGORY_HOME);
                objInten.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(objInten);
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
                mensaje = sitioWeb;
                break;
            case R.id.btn_image_mensaje:
                mensaje = email;
                break;
            case R.id.btn_image_telefono:
                mensaje = telefono;
                break;
        }
        Toast.makeText(this, "" + mensaje, Toast.LENGTH_SHORT).show();
    }
}
