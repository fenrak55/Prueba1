package sena.prueba;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sena.prueba.Adaptadores.AdapterEditaItem;
import sena.prueba.Adaptadores.Item;
import sena.prueba.database.ConexionDb;

public class Configuracion extends AppCompatActivity {

    private Spinner spinnerCategorias, spinnerTipoItem;
    private RecyclerView recyclerConfiguracion;
    private SQLiteDatabase objDb;
    private Toolbar toolbarConfiguracion;
    private ActionBar actionBar;
    private ArrayList<String> spinnerListCategorias;
    private ArrayList<String> spinnerListTipoItem;
    private ArrayAdapter<String> objAdapter;
    private AdapterEditaItem adapterEditaItem;
    private List<Item> listaItem;
    private static String seleccionSpinnerCategoria = "";
    private static String seleccionSpinnerItem = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        //Creamos las referencias
        spinnerCategorias = (Spinner) findViewById(R.id.spinner_categoria);
        spinnerTipoItem = (Spinner) findViewById(R.id.spinner_tipo_item);
        recyclerConfiguracion = (RecyclerView) findViewById(R.id.recycler_configuracion);
        toolbarConfiguracion = (Toolbar) findViewById(R.id.toolbar_configuracion);

        recyclerConfiguracion.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        toolbarConfiguracion.setTitle("Configuracion");
        setSupportActionBar(toolbarConfiguracion);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        llenaSpinnerCategoria();
        llenaSpinnerTipoItem();
        
        //Asignamos los eventos a los spinner
        spinnerCategorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                seleccionSpinnerCategoria = parent.getSelectedItem().toString();
                consulta();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerTipoItem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                seleccionSpinnerItem = parent.getSelectedItem().toString();
                consulta();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Se ejecuta cuando las variables estaticas no este vacias
        if (!seleccionSpinnerCategoria.isEmpty() && !seleccionSpinnerItem.isEmpty()){
            for (int i=0; i<spinnerListCategorias.size(); i++){
                if (spinnerListCategorias.get(i).equals(seleccionSpinnerCategoria)){
                    spinnerCategorias.setSelection(i);
                }
            }
            for (int i=0; i<spinnerListTipoItem.size(); i++){
                if (spinnerListTipoItem.get(i).equals(seleccionSpinnerItem)){
                    spinnerTipoItem.setSelection(i);
                }
            }
            consulta();
        }
    }

    public void llenaSpinnerCategoria(){
        try{
            //Conectamos con la base de datos
            SQLiteDatabase objDb = conecta();
            //Hacemos una consulta para asignarla al spinnerCategoria
            Cursor objCursor = objDb.rawQuery("SELECT Nombre FROM Categoria", null);
            //Si obtengo registros
            if(objCursor.moveToFirst()){
                spinnerListCategorias = new ArrayList<>();
                do {
                    spinnerListCategorias.add(objCursor.getString(0));
                }while (objCursor.moveToNext());
            }
            objAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, spinnerListCategorias);
            spinnerCategorias.setAdapter(objAdapter);
            objCursor.close();
            objDb.close();
        }catch (Exception e){
            e.getStackTrace();
        }
    }

    public void llenaSpinnerTipoItem(){
        try{
            //Conectamos con la base de datos
            SQLiteDatabase objDb = conecta();
            //Hacemos una consulta para asignarla al spinnerCategoria
            Cursor objCursor = objDb.rawQuery("SELECT Nombre FROM Tipo_item", null);
            //Si obtengo registros
            if(objCursor.moveToFirst()){
                spinnerListTipoItem = new ArrayList<>();
                do {
                    spinnerListTipoItem.add(objCursor.getString(0));
                }while (objCursor.moveToNext());
            }
            objAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, spinnerListTipoItem);
            spinnerTipoItem.setAdapter(objAdapter);
            objCursor.close();
            objDb.close();
        }catch (Exception e){
            e.getStackTrace();
        }
    }

    public void consulta(){
        try{
            //Conectamos con la base de datos
            SQLiteDatabase objDb = conecta();

            //Rescatamos el valor seleccionado de los spinner
            String categoria = spinnerCategorias.getSelectedItem().toString();
            String tipoItem = spinnerTipoItem.getSelectedItem().toString();

            //Hacemos la consulta
            String[] args = new String[]{categoria, tipoItem};
            Cursor objCursor = objDb.rawQuery("SELECT Lugar.Id, Lugar.Imagen, Lugar.Nombre, Lugar.Descripcion " +
                    "FROM Lugar INNER JOIN Categoria ON Lugar.Id_categoria = Categoria.Id " +
                    "INNER JOIN Tipo_item ON Lugar.Id_tipo_item = Tipo_item.Id WHERE Categoria.Nombre = ? AND Tipo_item.Nombre = ?", args);
            //Si obtengo registros
            if (objCursor.moveToFirst()){
                listaItem = new ArrayList<>();
                do {
                    listaItem.add(new Item(objCursor.getInt(0), objCursor.getInt(1), objCursor.getString(2), objCursor.getString(3)));
                }while (objCursor.moveToNext());
                adapterEditaItem = new AdapterEditaItem(this, listaItem);
                recyclerConfiguracion.setAdapter(adapterEditaItem);
            }else {
                Toast.makeText(this, "Ninguna coincidencia.", Toast.LENGTH_SHORT).show();
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
                objInten = new Intent(Configuracion.this, Registrarse.class);
                startActivity(objInten);
                break;
            case R.id.menu_ayuda:
                //Cambiamos a la actividad de ayuda
                objInten = new Intent(Configuracion.this, Ayuda.class);
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
}
