package com.example.teamtilt.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.teamtilt.databinding.ItemQuickLinkBinding;
import com.example.teamtilt.models.QuickLink;

import java.util.ArrayList;
import java.util.List;

public class QuickLinkAdapter extends RecyclerView.Adapter<QuickLinkAdapter.QuickLinkViewHolder> {
    private List<QuickLink> quickLinks = new ArrayList<>();
    private OnQuickLinkClickListener listener;

    public interface OnQuickLinkClickListener {
        void onQuickLinkClick(QuickLink quickLink);
    }

    public QuickLinkAdapter(OnQuickLinkClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public QuickLinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemQuickLinkBinding binding = ItemQuickLinkBinding.inflate(
            LayoutInflater.from(parent.getContext()), parent, false);
        return new QuickLinkViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull QuickLinkViewHolder holder, int position) {
        holder.bind(quickLinks.get(position));
    }

    @Override
    public int getItemCount() {
        return quickLinks.size();
    }

    public void setQuickLinks(List<QuickLink> quickLinks) {
        this.quickLinks = quickLinks;
        notifyDataSetChanged();
    }

    class QuickLinkViewHolder extends RecyclerView.ViewHolder {
        private final ItemQuickLinkBinding binding;

        QuickLinkViewHolder(ItemQuickLinkBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(QuickLink quickLink) {
            binding.quickLinkTitle.setText(quickLink.getTitle());
            binding.quickLinkDescription.setText(quickLink.getDescription());

            // Load icon using Glide
            Glide.with(binding.getRoot())
                .load(quickLink.getIconUrl())
                .into(binding.quickLinkIcon);

            // Set click listener
            binding.getRoot().setOnClickListener(v -> {
                if (listener != null) {
                    listener.onQuickLinkClick(quickLink);
                }
            });
        }
    }
} 