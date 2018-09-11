package sena.prueba;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sena.prueba.database.ConexionDb;
import sena.prueba.database.Utilidades;

public class Registrarse extends AppCompatActivity implements View.OnClickListener{

    private EditText campoNombre, campoEmail, campoTelefono;
    private Toolbar toolbarRegistro;
    private ActionBar actionBar;
    private Button btnEnvia;
    private SQLiteDatabase objDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //Creamos las referencias
        campoNombre = (EditText) findViewById(R.id.txt_nombre);
        campoEmail = (EditText) findViewById(R.id.txt_email);
        campoTelefono = (EditText) findViewById(R.id.txt_celular);
        toolbarRegistro = (Toolbar) findViewById(R.id.toolbar_registro);
        btnEnvia = (Button) findViewById(R.id.btn_envia_usu);

        btnEnvia.setOnClickListener(this);

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
        Intent objIntent = null;
        switch (id){
            case R.id.menu_configuracion:
                //Cambiamos a la actividad configuracion
                objIntent = new Intent(Registrarse.this, Configuracion.class);
                startActivity(objIntent);
                break;
            case R.id.menu_ayuda:
                //Cambiamos a la actividad ayuda
                objIntent = new Intent(Registrarse.this, Ayuda.class);
                startActivity(objIntent);
                break;
            case R.id.menu_salir:
                //Salimos de la aplicacion
                objIntent=new Intent(Intent.ACTION_MAIN);
                objIntent.addCategory(Intent.CATEGORY_HOME);
                objIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(objIntent);
                break;
            }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btn_envia_usu:
                insertaRegistro();
                break;
        }
    }

    public void insertaRegistro(){
        try{
            //Conectamos con la base de datos
            objDb = conecta();

            //Rescatamos los datos de los campos de texto
            String nombre = campoNombre.getText().toString();
            String email = campoEmail.getText().toString();
            String telefono = campoTelefono.getText().toString();

            //Verificamos si los campos estan o no vacios
            if (!nombre.isEmpty() && !email.isEmpty() && !telefono.isEmpty()){
                //Hacemos una consulta para comparar si ya existen coincidencias
                Cursor objCursor = objDb.rawQuery("SELECT Telefono, Email FROM Usuario", null);
                //Contara el numero de coincidencias que existan
                int contadorTelefono = 0;
                int contadorEmail = 0;
                //Si obtengo registros
                if (objCursor.moveToFirst()){
                    do {
                        //Comparamos los valores de telefono
                        if (telefono.equals(objCursor.getString(0))){
                            contadorTelefono++;
                        }
                        //Comparamos los valores de email
                        if (email.equals(objCursor.getString(1))){
                            contadorEmail++;
                        }
                    }while (objCursor.moveToNext());
                }

                //Si hay coincidencias
                if (contadorTelefono > 0 || contadorEmail > 0) {
                    if (contadorTelefono > 0) {
                        Toast.makeText(this, "Este Telefono ya existe.", Toast.LENGTH_SHORT).show();
                    }
                    if (contadorEmail > 0){
                        Toast.makeText(this, "Este Email ya existe.", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    //Listamos los datos a insertar
                    ContentValues objContent = new ContentValues();
                    objContent.put(Utilidades.NOMBRE_TBL_USUARIO, nombre);
                    objContent.put(Utilidades.TELEFONO_TBL_USUARIO, telefono);
                    objContent.put(Utilidades.EMAIL_TBL_USUARIO, email);

                    //Insertamos los valores en la base de datos
                    long n = objDb.insert(Utilidades.TBL_USUARIO, null, objContent);
                    if (n != 0) {
                        Toast.makeText(this, "Datos guardados.", Toast.LENGTH_SHORT).show();

                        //Limpiamos todos los campos
                        campoNombre.setText("");
                        campoTelefono.setText("");
                        campoEmail.setText("");
                    } else {
                        Toast.makeText(this, "Fallo al guardar.", Toast.LENGTH_SHORT).show();
                    }
                }
            }else {
                //Creamos un array con los valores de los campos
                String[] valores = new String[]{nombre, email, telefono};
                //Este contador contara el numero de campos que no sean nulos
                int contador = 0;
                //Almacena el unico valor del array que no sea null
                String valor = "";
                for (int i=0; i<valores.length; i++){
                    if (!valores[i].isEmpty()){
                        contador++;
                        valor = valores[i];
                    }
                }
                //Validamos que haya solo un campo no null
                if (contador == 1){
                    //Hacemos la consulta de usuarios
                    String[] args = new String[]{valor, valor, valor};
                    Cursor objCursor = objDb.rawQuery("SELECT * FROM Usuario WHERE Nombre = ? OR Telefono = ? OR Email = ?", args);
                    //Si se obtienen registros
                    if (objCursor.moveToFirst()){
                        do {
                            campoNombre.setText(objCursor.getString(1));
                            campoTelefono.setText(objCursor.getString(2));
                            campoEmail.setText(objCursor.getString(3));
                        }while (objCursor.moveToNext());
                    }else{
                        Toast.makeText(this, "Ninguna coincidencia.", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "Utilice solo un campo para realizar la busqueda.", Toast.LENGTH_SHORT).show();
                }
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
