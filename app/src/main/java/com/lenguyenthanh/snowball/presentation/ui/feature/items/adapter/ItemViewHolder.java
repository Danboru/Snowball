package com.lenguyenthanh.snowball.presentation.ui.feature.items.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.lenguyenthanh.snowball.R;
import com.lenguyenthanh.snowball.presentation.model.ItemModel;
import com.lenguyenthanh.snowball.presentation.ui.network.SnowballImageLoader;

class ItemViewHolder extends RecyclerView.ViewHolder {

  @NonNull private final SnowballImageLoader picasso;

  private final ImageView imageView;

  private final TextView titleTextView;

  private final TextView shortDescriptionTextView;

  ItemViewHolder(@NonNull View itemView, @NonNull SnowballImageLoader picasso) {
    super(itemView);
    this.picasso = picasso;
    imageView = (ImageView) itemView.findViewById(R.id.list_item_image_view);
    titleTextView = (TextView) itemView.findViewById(R.id.list_item_title_text_view);
    shortDescriptionTextView =
        (TextView) itemView.findViewById(R.id.list_item_short_description_text_view);
  }

  public void bind(@NonNull ItemModel item) {
    picasso.downloadInto(item.thumbnail(), imageView);
    titleTextView.setText(item.name());
    shortDescriptionTextView.setText(item.name());
  }
}
