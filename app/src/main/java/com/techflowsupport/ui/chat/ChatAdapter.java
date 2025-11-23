package com.techflowsupport.ui.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.techflowsupport.R;
import com.techflowsupport.data.model.Message;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private List<Message> mensagens;
    private Context context;

    public ChatAdapter(Context context, List<Message> mensagens) {
        this.context = context;
        this.mensagens = mensagens;
    }

    @Override
    public int getItemViewType(int position) {
        return mensagens.get(position).isUser() ? 1 : 0;
    }

    @NonNull
    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout = (viewType == 1)
                ? R.layout.item_message_user
                : R.layout.item_message_tecnico;

        View view = LayoutInflater.from(context).inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ViewHolder holder, int position) {
        holder.txtMsg.setText(mensagens.get(position).getTexto());
    }

    @Override
    public int getItemCount() {
        return mensagens.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMsg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMsg = itemView.findViewById(R.id.txtMsg);
        }
    }
}
