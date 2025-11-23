package com.techflowsupport.ui.chamados;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.techflowsupport.R;
import com.techflowsupport.data.model.Chamado;

import java.util.List;

public class ChamadosAdapter extends RecyclerView.Adapter<ChamadosAdapter.ViewHolder> {

    private Context context;
    private List<Chamado> lista;

    public ChamadosAdapter(Context context, List<Chamado> lista) {
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_chamado, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Chamado c = lista.get(position);

        holder.txtTitulo.setText(c.getTitulo());
        holder.txtDescricao.setText(c.getDescricao());
        holder.txtStatus.setText("Status: " + c.getStatus());
        holder.txtPrioridade.setText("Prioridade: " + c.getPrioridade());

        switch (c.getStatus().toLowerCase()) {
            case "aberto":
                holder.txtStatus.setTextColor(Color.parseColor("#007BFF")); // azul
                break;
            case "em andamento":
                holder.txtStatus.setTextColor(Color.parseColor("#FF9800")); // laranja
                break;
            case "finalizado":
            case "resolvido":
                holder.txtStatus.setTextColor(Color.parseColor("#4CAF50")); // verde
                break;
            default:
                holder.txtStatus.setTextColor(Color.BLACK);
        }

        switch (c.getPrioridade().toLowerCase()) {
            case "alta":
                holder.txtPrioridade.setTextColor(Color.parseColor("#FF0000")); // vermelho
                break;
            case "média":
            case "media":
                holder.txtPrioridade.setTextColor(Color.parseColor("#FFC107")); // amarelo
                break;
            case "baixa":
                holder.txtPrioridade.setTextColor(Color.parseColor("#8FFF00")); // verde neon
                break;
            default:
                holder.txtPrioridade.setTextColor(Color.BLACK);
        }

        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(context, DetalhesChamadoActivity.class);

            intent.putExtra("index", position);

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitulo, txtDescricao, txtStatus, txtPrioridade;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitulo = itemView.findViewById(R.id.txtTituloChamado);
            txtDescricao = itemView.findViewById(R.id.txtDescricaoChamado);
            txtStatus = itemView.findViewById(R.id.txtStatusChamado);
            txtPrioridade = itemView.findViewById(R.id.txtPrioridadeChamado);
        }
    }
}
