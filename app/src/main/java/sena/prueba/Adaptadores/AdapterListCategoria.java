package sena.prueba.Adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import sena.prueba.R;

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
    }

    @Override
    public int getItemCount() {
        return datosCategoriaList.size();
    }

    public class AdapterHolder extends RecyclerView.ViewHolder {

        //Creamos los objetos
        ImageView imageView;
        TextView viewNombre;
        TextView viewDireccion;

        public AdapterHolder(View itemView) {
            super(itemView);

            //Creamos las referencias
            imageView = (ImageView) itemView.findViewById(R.id.logo_list_categoria);
            viewNombre = (TextView) itemView.findViewById(R.id.nombre_list_categoria);
            viewDireccion = (TextView) itemView.findViewById(R.id.direccion_list_categoria);
        }
    }
}
