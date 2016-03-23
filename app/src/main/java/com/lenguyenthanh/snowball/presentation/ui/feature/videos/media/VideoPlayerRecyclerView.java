package com.lenguyenthanh.snowball.presentation.ui.feature.videos.media;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.AbsListView;
import com.lenguyenthanh.snowball.presentation.model.VideoModel;
import com.lenguyenthanh.snowball.presentation.ui.feature.videos.VideoListComponent;
import com.lenguyenthanh.snowball.presentation.ui.network.NetworkBitmapClient;
import com.volokh.danylo.video_player_manager.manager.SingleVideoPlayerManager;
import com.volokh.danylo.video_player_manager.manager.VideoPlayerManager;
import com.volokh.danylo.video_player_manager.meta.MetaData;
import com.volokh.danylo.visibility_utils.calculator.DefaultSingleItemCalculatorCallback;
import com.volokh.danylo.visibility_utils.calculator.ListItemsVisibilityCalculator;
import com.volokh.danylo.visibility_utils.calculator.SingleListViewItemActiveCalculator;
import com.volokh.danylo.visibility_utils.scroll_utils.ItemsPositionGetter;
import com.volokh.danylo.visibility_utils.scroll_utils.RecyclerViewItemPositionGetter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

public class VideoPlayerRecyclerView extends RecyclerView {

  private ItemsPositionGetter mItemsPositionGetter;
  private LinearLayoutManager mLayoutManager;
  private VideoPlayerManager<MetaData> mVideoPlayerManager;
  private ListItemsVisibilityCalculator mVideoVisibilityCalculator;
  private final List<BaseVideoItem> videoItems = new ArrayList<>();
  private VideoRecyclerViewAdapter videoRecyclerViewAdapter;

  @Inject
  NetworkBitmapClient bitmapClient;

  private int mScrollState = AbsListView.OnScrollListener.SCROLL_STATE_IDLE;

  public VideoPlayerRecyclerView(final Context context) {
    this(context, null);
  }

  public VideoPlayerRecyclerView(final Context context, final AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public VideoPlayerRecyclerView(final Context context, final AttributeSet attrs,
      final int defStyle) {
    super(context, attrs, defStyle);
  }

  public void initialize(VideoListComponent component) {
    component.inject(this);
    mLayoutManager = new LinearLayoutManager(getContext());
    mItemsPositionGetter = new RecyclerViewItemPositionGetter(mLayoutManager, this);
    this.setLayoutManager(mLayoutManager);
    mVideoPlayerManager = new SingleVideoPlayerManager(metaData -> {});
    mVideoVisibilityCalculator =
        new SingleListViewItemActiveCalculator(new DefaultSingleItemCalculatorCallback(),
            videoItems);
    videoRecyclerViewAdapter =
        new VideoRecyclerViewAdapter(mVideoPlayerManager, getContext(), videoItems);
    this.setAdapter(videoRecyclerViewAdapter);
    this.setHasFixedSize(true);

    setScrollerListener();
  }

  public void setVideoItems(final Collection<VideoModel> videoItems) {
    this.videoItems.clear();
    this.videoItems.addAll(transform(videoItems));
    videoRecyclerViewAdapter.notifyDataSetChanged();
    onResume();
  }

  private void setScrollerListener() {
    this.addOnScrollListener(new OnScrollListener() {

      @Override
      public void onScrollStateChanged(RecyclerView recyclerView, int scrollState) {
        mScrollState = scrollState;
        if (scrollState == RecyclerView.SCROLL_STATE_IDLE && !videoItems.isEmpty()) {

          mVideoVisibilityCalculator.onScrollStateIdle(mItemsPositionGetter,
              mLayoutManager.findFirstVisibleItemPosition(),
              mLayoutManager.findLastVisibleItemPosition());
        }
      }

      @Override
      public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (!videoItems.isEmpty()) {
          mVideoVisibilityCalculator.onScroll(mItemsPositionGetter,
              mLayoutManager.findFirstVisibleItemPosition(),
              mLayoutManager.findLastVisibleItemPosition()
                  - mLayoutManager.findFirstVisibleItemPosition() + 1, mScrollState);
        }
      }
    });
  }

  private List<BaseVideoItem> transform(final Collection<VideoModel> videoItems) {
    List<BaseVideoItem> items = new ArrayList<>(videoItems.size());
    for (VideoModel videoModel : videoItems) {
      BaseVideoItem item = DirectLinkVideoItem.from(videoModel, mVideoPlayerManager, bitmapClient);
      items.add(item);
    }
    return items;
  }

  public void onResume() {
    if (!videoItems.isEmpty()) {
      // need to call this method from list view handler in order to have filled list

      this.post(() -> mVideoVisibilityCalculator.onScrollStateIdle(mItemsPositionGetter,
          mLayoutManager.findFirstVisibleItemPosition(),
          mLayoutManager.findLastVisibleItemPosition()));
    }
  }

  public void onStop() {
    mVideoPlayerManager.resetMediaPlayer();
  }
}
