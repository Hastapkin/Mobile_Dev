package com.example.lab1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    public interface OnCartChangedListener {
        void onChanged();
    }

    private final List<CartItem> cartItems;
    private final OnCartChangedListener listener;

    public CartAdapter(List<CartItem> cartItems, OnCartChangedListener listener) {
        this.cartItems = cartItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartItem item = cartItems.get(position);
        holder.tvName.setText(item.getProduct().getName());
        holder.tvQty.setText(String.valueOf(item.getQuantity()));
        holder.tvSubtotal.setText(String.format("$%.2f", item.getTotalPrice()));

        holder.btnIncrease.setOnClickListener(v -> {
            item.setQuantity(item.getQuantity() + 1);
            notifyItemChanged(holder.getAdapterPosition());
            listener.onChanged();
        });

        holder.btnDecrease.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (item.getQuantity() > 1) {
                item.setQuantity(item.getQuantity() - 1);
                notifyItemChanged(pos);
            } else {
                CartManager.getInstance().removeItem(pos);
                notifyItemRemoved(pos);
            }
            listener.onChanged();
        });

        holder.btnRemove.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            CartManager.getInstance().removeItem(pos);
            notifyItemRemoved(pos);
            listener.onChanged();
        });
    }

    @Override
    public int getItemCount() { return cartItems.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvQty, tvSubtotal;
        Button btnIncrease, btnDecrease, btnRemove;

        ViewHolder(View v) {
            super(v);
            tvName      = v.findViewById(R.id.tvCartProductName);
            tvQty       = v.findViewById(R.id.tvQuantity);
            tvSubtotal  = v.findViewById(R.id.tvSubtotal);
            btnIncrease = v.findViewById(R.id.btnIncrease);
            btnDecrease = v.findViewById(R.id.btnDecrease);
            btnRemove   = v.findViewById(R.id.btnRemove);
        }
    }
}
