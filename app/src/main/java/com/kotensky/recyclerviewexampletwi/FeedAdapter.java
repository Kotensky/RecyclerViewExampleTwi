package com.kotensky.recyclerviewexampletwi;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {

    private ArrayList<PostEntity> postItems;
    private OnFeedClickListener listener;
    private RequestOptions glideRequestOptions;


    public FeedAdapter(ArrayList<PostEntity> postItems, OnFeedClickListener listener) {
        this.postItems = postItems;
        this.listener = listener;
        glideRequestOptions = RequestOptions.circleCropTransform()
                .placeholder(R.drawable.ic_account_circle);
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int itemType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_recycler_item, parent, false);
        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder feedViewHolder, int position) {
        PostEntity postEntity = postItems.get(position);

        feedViewHolder.authorNameTxt.setText(postEntity.getAuthorName());
        feedViewHolder.postTxt.setText(postEntity.getText());

        Glide.with(feedViewHolder.avatarImg.getContext())
                .load(postEntity.getAvatarLink())
                .apply(glideRequestOptions)
                .into(feedViewHolder.avatarImg);


        final int finalPosition = position;
        feedViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onFeedItemClick(finalPosition);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return postItems == null ? 0 : postItems.size();
    }

/*    @Override
    public int getItemCount() {
        if ( postItems == null) {
            return 0;
        }
        return postItems.size();
    }
    */

    public class FeedViewHolder extends RecyclerView.ViewHolder {
        public ImageView avatarImg;
        public TextView authorNameTxt;
        public TextView postTxt;

        public FeedViewHolder(@NonNull View itemView) {
            super(itemView);
            avatarImg = itemView.findViewById(R.id.avatar_img);
            authorNameTxt = itemView.findViewById(R.id.author_name_txt);
            postTxt = itemView.findViewById(R.id.post_txt);
        }
    }


    public interface OnFeedClickListener {

        void onFeedItemClick(int position);

    }
}
