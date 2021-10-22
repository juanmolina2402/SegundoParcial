package com.example.segundoparcial.adapterMC;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.segundoparcial.AddContactMCActivity;
import com.example.segundoparcial.R;
import com.example.segundoparcial.daoMC.ContactsMCDao;
import com.example.segundoparcial.modelMC.ContactsMC;
import java.util.List;

public class ContactsMCAdapter extends RecyclerView.Adapter<ContactsMCAdapter.ViewHolder>{
    private List<ContactsMC> contacts;
    private Context context;
    private ContactsMCDao dao;

    public static class ViewHolder extends RecyclerView.ViewHolder  {
        private TextView tvId, tvNombre, tvNumero, tvPropietario;
        private Button btnModificar, btnEliminar;
        private Context context;

        public ViewHolder(View view, Context context){
            super(view);
            tvId = view.findViewById(R.id.tvId);
            tvNombre = view.findViewById(R.id.tvNombre);
            tvNumero = view.findViewById(R.id.tvNumero);
            tvPropietario = view.findViewById(R.id.tvPropietario);
            btnModificar = view.findViewById(R.id.btnModificar);
            btnEliminar = view.findViewById(R.id.btnEliminar);
            this.context=context;
        }
    }

    public ContactsMCAdapter(List<ContactsMC> contacts, Context context, ContactsMCDao dao) {
        this.context = context;
        this.contacts = contacts;
        this.dao = dao;
    }

    @NonNull
    @Override
    public ContactsMCAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mc_contacts,parent,false);
        ViewHolder vh = new ViewHolder(v, context);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsMCAdapter.ViewHolder holder, int position) {
        ContactsMC p = contacts.get(position);
        holder.tvId.setText(""+p.getId());
        holder.tvNombre.setText(p.getNombre());
        holder.tvNumero.setText(p.getNumero());
        holder.tvPropietario.setText(p.getPropietario());

        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao.delete(p);
                contacts = dao.getAll();
                notifyDataSetChanged();
                Toast.makeText(context.getApplicationContext(),"ELiminado",Toast.LENGTH_SHORT).show();
            }
        });

        holder.btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddContactMCActivity.class);
                intent.putExtra("estado",1);
                intent.putExtra("contacto", p);
                intent.putExtra("numero", p);
                intent.putExtra("propietario", p);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
}
