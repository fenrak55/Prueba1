package sena.prueba;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sena.prueba.database.ConexionDb;
import sena.prueba.database.Utilidades;

public class EditaLugar extends AppCompatActivity {

    private EditText newNombre, newDescripcion, newDireccion, newSitio, newTelefono, newEmail;
    private Button btnActualiza;
    private int id = 0;
    private Toolbar toolbarEdita;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edita_lugar);

        //Creo las referencias
        newNombre = (EditText) findViewById(R.id.txt_new_nombre);
        newDescripcion = (EditText) findViewById(R.id.txt_new_descripcion);
        newDireccion = (EditText) findViewById(R.id.txt_new_direccion);
        newSitio = (EditText) findViewById(R.id.txt_new_sitio);
        newTelefono = (EditText) findViewById(R.id.txt_new_telefono);
        newEmail = (EditText) findViewById(R.id.txt_new_email);
        btnActualiza = (Button) findViewById(R.id.btn_actualiza_item);
        toolbarEdita = (Toolbar) findViewById(R.id.toolbar_edita_item);

        toolbarEdita.setTitle("Edita item");
        setSupportActionBar(toolbarEdita);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        btnActualiza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizaItem();
            }
        });

        //Si recibo el id de AdapterEditeItem
        if (getIntent().getIntExtra(Utilidades.ID_TBL_LUGAR, 0) != 0){
            id = getIntent().getIntExtra(Utilidades.ID_TBL_LUGAR, 0);
            consulta(id);
        }
    }

    public void actualizaItem() {
        try{
            //Rescatamos los valores de los campos
            String nombre = newNombre.getText().toString();
            String descripcion = newDescripcion.getText().toString();
            String direccion = newDireccion.getText().toString();
            String sitio = newSitio.getText().toString();
            String telefono = newTelefono.getText().toString();
            String email = newEmail.getText().toString();

            //Conecta con la base de datos
            SQLiteDatabase objDb = conecta();

            //Listamos los valores a actualizar
            ContentValues objContent = new ContentValues();
            objContent.put(Utilidades.NOMBRE_TBL_LUGAR, nombre);
            objContent.put(Utilidades.DESCRIPCION_TBL_LUGAR, descripcion);
            objContent.put(Utilidades.DIRECCION_TBL_LUGAR, direccion);
            objContent.put(Utilidades.SITIO_WEB_TBL_LUGAR, sitio);
            objContent.put(Utilidades.TELEFONO_TBL_LUGAR, telefono);
            objContent.put(Utilidades.EMAIL_TBL_LUGAR, email);

            //Actualizamos la tabla Lugar
            String[] args = new String[]{String.valueOf(id)};
            int n = objDb.update(Utilidades.TBL_LUGAR, objContent, "Id = ?", args);
            if (n != 0){
                Toast.makeText(this, "Actualizado correctamente.", Toast.LENGTH_SHORT).show();

                //Invalidamos todos los campos
                newNombre.setEnabled(false);
                newDescripcion.setEnabled(false);
                newDireccion.setEnabled(false);
                newSitio.setEnabled(false);
                newTelefono.setEnabled(false);
                newEmail.setEnabled(false);
            }else {
                Toast.makeText(this, "Fallo al actualizar.", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            e.getStackTrace();
        }
    }

    public void consulta(int id) {
        try{
            //Conectamos con la base de datos
            SQLiteDatabase objDb = conecta();

            //Creamos la consulta
            String[] args = new String[]{String.valueOf(id)};
            Cursor objCursor = objDb.rawQuery("SELECT Nombre, Descripcion, Direccion, Telefono, Sitio_web, Email FROM Lugar WHERE Id = ?", args);
            //Si obtengo registros
            if (objCursor.moveToFirst()){
                do {
                    newNombre.setText(objCursor.getString(0));
                    newDescripcion.setText(objCursor.getString(1));
                    newDireccion.setText(objCursor.getString(2));
                    newTelefono.setText(objCursor.getString(3));
                    newSitio.setText(objCursor.getString(4));
                    newEmail.setText(objCursor.getString(5));
                }while (objCursor.moveToNext());
            }
        }catch (Exception e){
            e.getStackTrace();
        }
    }

    public SQLiteDatabase conecta(){
        ConexionDb objConecta = new ConexionDb(this, "sitios", null, 1);
        return objConecta.getWritableDatabase();
    }
}
