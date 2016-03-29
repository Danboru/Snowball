package com.lenguyenthanh.snowball.presentation.ui.feature.videos.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.lenguyenthanh.snowball.R;
import com.lenguyenthanh.snowball.presentation.model.VideoModel;
import com.lenguyenthanh.snowball.presentation.ui.network.NetworkBitmapClient;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.unmodifiableList;

public class ItemsAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    @NonNull
    private final LayoutInflater layoutInflater;

    @NonNull
    private final NetworkBitmapClient bitmapClient;

    @NonNull
    private List<VideoModel> items = emptyList();

    public ItemsAdapter(@NonNull LayoutInflater layoutInflater, @NonNull NetworkBitmapClient bitmapClient) {
        this.layoutInflater = layoutInflater;
        this.bitmapClient = bitmapClient;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setData(@NonNull List<VideoModel> items) {
        this.items = unmodifiableList(items); // Prevent possible side-effects.
        notifyDataSetChanged();
    }

    @Override @NonNull
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(layoutInflater.inflate(R.layout.list_item, parent, false),
            bitmapClient);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder viewHolder, int position) {
        viewHolder.bind(items.get(position));
    }

}
