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

import java.util.List;

import sena.prueba.EditaLugar;
import sena.prueba.R;
import sena.prueba.database.Utilidades;

public class AdapterEditaItem extends RecyclerView.Adapter<AdapterEditaItem.EditaItemHolder>{

    private Context context;
    private List<Item> listaItem;

    public AdapterEditaItem(Context context, List<Item> listaItem) {
        this.context = context;
        this.listaItem = listaItem;
    }

    @NonNull
    @Override
    public EditaItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(context).inflate(R.layout.item_list_edita_item, parent, false);
        return new EditaItemHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull EditaItemHolder holder, int position) {
        holder.id = listaItem.get(position).getId();
        holder.imagenLugar.setImageResource(listaItem.get(position).getImagen());
        holder.nombreLugar.setText(listaItem.get(position).getNombre());
        holder.descripcionLugar.setText(listaItem.get(position).getDescripcion());
    }

    @Override
    public int getItemCount() {
        return listaItem.size();
    }

    public class EditaItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imagenLugar;
        TextView nombreLugar, descripcionLugar;
        ImageButton imageBtnEdita;
        int id;

        public EditaItemHolder(View itemView) {
            super(itemView);

            imagenLugar = (ImageView) itemView.findViewById(R.id.image_edita);
            nombreLugar = (TextView) itemView.findViewById(R.id.view_nombre_edita);
            descripcionLugar = (TextView) itemView.findViewById(R.id.view_descripcion_edita);
            imageBtnEdita = (ImageButton) itemView.findViewById(R.id.image_btn_edita);

            imageBtnEdita.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent objIntent = new Intent(context, EditaLugar.class);
            objIntent.putExtra(Utilidades.ID_TBL_LUGAR, id);
            context.startActivity(objIntent);
        }
    }
}
