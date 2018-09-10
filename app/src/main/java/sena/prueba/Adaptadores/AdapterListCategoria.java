package sena.prueba.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import sena.prueba.InformacionItem;
import sena.prueba.R;
import sena.prueba.database.Utilidades;

public class AdapterListCategoria extends RecyclerView.Adapter<AdapterListCategoria.AdapterHolder>{

    private Context context;
    private List<DatosCategoria> datosCategoriaList;

    //Creamos el constructor
    public AdapterListCategoria(Context context, List<DatosCategoria> datosCategoriaList) {
        this.context = context;
        this.datosCategoriaList = datosCategoriaList;
    }

    @NonNull
    @Override
    public AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(context).inflate(R.layout.item_list_categoria, parent, false);

        return new AdapterHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHolder holder, int position) {
        holder.imageView.setImageResource(datosCategoriaList.get(position).getImagen());
        holder.viewNombre.setText(datosCategoriaList.get(position).getNombre());
        holder.viewDireccion.setText(datosCategoriaList.get(position).getDireccion());
        holder.email = datosCategoriaList.get(position).getEmail();
        holder.telefono = datosCategoriaList.get(position).getTelefono();
        holder.id = datosCategoriaList.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return datosCategoriaList.size();
    }

    public class AdapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //Creamos los objetos
        ImageView imageView;
        TextView viewNombre;
        TextView viewDireccion;
        ImageButton btnView, btnMessage, btnTelefono;
        String email, telefono;
        int id;

        public AdapterHolder(View itemView) {
            super(itemView);

            //Creamos las referencias
            imageView = (ImageView) itemView.findViewById(R.id.logo_list_categoria);
            viewNombre = (TextView) itemView.findViewById(R.id.nombre_list_categoria);
            viewDireccion = (TextView) itemView.findViewById(R.id.direccion_list_categoria);
            btnMessage = (ImageButton) itemView.findViewById(R.id.btn_image_message);
            btnView = (ImageButton) itemView.findViewById(R.id.btn_image_view);
            btnTelefono = (ImageButton) itemView.findViewById(R.id.btn_image_numero);

            btnMessage.setOnClickListener(this);
            btnTelefono.setOnClickListener(this);
            btnView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int id = v.getId();
            String mensaje = "";
            switch (id){
                case R.id.btn_image_message:
                    Toast.makeText(context, "" + email, Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_image_view:
                    cambiaInformacion();
                    break;
                case R.id.btn_image_numero:
                    Toast.makeText(context, "" + telefono, Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        public void cambiaInformacion(){
            Intent objIntent = new Intent(context, InformacionItem.class);
            objIntent.putExtra(Utilidades.ID_TBL_LUGAR, id);
            context.startActivity(objIntent);
        }
    }
}
