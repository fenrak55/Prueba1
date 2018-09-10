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

public class AdapterCategoria extends RecyclerView.Adapter<AdapterCategoria.AdapterHolderCategoria> implements View.OnClickListener{

    //Creo los objetos
    private Context context;
    private List<Categoria> categoriasList;
    private View.OnClickListener listener;

    public AdapterCategoria(Context context, List<Categoria> categoriasList) {
        this.context = context;
        this.categoriasList = categoriasList;
    }

    @NonNull
    @Override
    public AdapterCategoria.AdapterHolderCategoria onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(context).inflate(R.layout.item_list_principal, parent, false);

        vista.setOnClickListener(this);

        return new AdapterHolderCategoria(vista);
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null){
            listener.onClick(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCategoria.AdapterHolderCategoria holder, int position) {
        holder.imagenPrincipal.setImageResource(categoriasList.get(position).getImagePrincipal());
        holder.iconoPrincipal.setImageResource(categoriasList.get(position).getIconoPrincipal());
        holder.nombreCategoria.setText(categoriasList.get(position).getNombreCategoria());
    }

    @Override
    public int getItemCount() {
        return categoriasList.size();
    }

    public class AdapterHolderCategoria extends RecyclerView.ViewHolder {

        //Creo los objetos
        ImageView imagenPrincipal, iconoPrincipal;
        TextView nombreCategoria;

        public AdapterHolderCategoria(View itemView) {
            super(itemView);

            //Creo las referencias
            imagenPrincipal = (ImageView) itemView.findViewById(R.id.image_principal);
            iconoPrincipal = (ImageView) itemView.findViewById(R.id.icono_principal);
            nombreCategoria = (TextView) itemView.findViewById(R.id.nombre_categoria_principal);
        }
    }
}
