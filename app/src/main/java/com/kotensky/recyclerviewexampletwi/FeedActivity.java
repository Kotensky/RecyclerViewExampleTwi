package com.kotensky.recyclerviewexampletwi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

public class FeedActivity extends AppCompatActivity implements FeedAdapter.OnFeedClickListener {

    private static final int ADD_POST_ACTIVITY_REQUEST_CODE = 12;

    private RecyclerView feedRecycler;

    private ArrayList<PostEntity> postItems = new ArrayList<>();
    private RecyclerView.Adapter feedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        setupToolbar();
        setupFabListener();
        setupRecycler();

    }

    private void setupRecycler() {
        fillPostItems();

        feedRecycler = findViewById(R.id.feed_recycler);
        feedAdapter = new FeedAdapter(postItems, this);
        feedRecycler.setAdapter(feedAdapter);
        feedRecycler.setLayoutManager(new LinearLayoutManager(this));
        feedRecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private void fillPostItems() {
        postItems.add(new PostEntity("Артем Андрощук", "Test text 1", ""));
        postItems.add(new PostEntity("Владислав Вапнярук", "Hello there!", ""));
        postItems.add(new PostEntity("Владислав Чоботок", "Post example text", ""));
        postItems.add(new PostEntity("Назар Миргородський", "Hello World!", ""));
        postItems.add(new PostEntity("Владислава Мороз", "Text text text", ""));
        postItems.add(new PostEntity("Ростислав Фотюк", "Twitter na minimalkah", "https://lh3.googleusercontent.com/a-/AAuE7mAW0Lkz0wNH84xd9GDAiUt5xlyOrHQ-3E3yFOOX=s32"));
        postItems.add(new PostEntity("Сергій Пірхал", "Will you show me app?", ""));
        postItems.add(new PostEntity("Володимир Кривонос", "Test text 2", ""));
        postItems.add(new PostEntity("Максим Іваніцький", "ping ping ping", "https://lh3.googleusercontent.com/a-/AAuE7mCsuxhnzZ43_KdIi-BA2lcbpQ_lHb4fVUPQVjUK=s32"));
        postItems.add(new PostEntity("Павло Головін", "pong pong pong", "https://lh3.googleusercontent.com/a-/AAuE7mAA0UoqBIigtYY09xPMK63lShZzsc-vop7JzV1FLQ=s32"));
        postItems.add(new PostEntity("Даниїл Андрійченко", "Sample text", ""));
        postItems.add(new PostEntity("Анастасія Нескородєва", "text text text text text", "https://lh3.googleusercontent.com/a-/AAuE7mDS0OuwC-WWE13CNVB3SH4izKj2qFqstHKvgff0=s32"));
        postItems.add(new PostEntity("Денис Томчук", "recycler example text", "https://lh3.googleusercontent.com/a-/AAuE7mDE3JH09vrRHEKDa8qn1zW10E_udVTMT9W9hlOhwA=s32"));
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setupFabListener() {
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FeedActivity.this, AddPostActivity.class);
                startActivityForResult(intent, ADD_POST_ACTIVITY_REQUEST_CODE);
            }
        });
    }


    private void addItemToList(PostEntity newPostItem) {
        if (newPostItem != null) {
            postItems.add(newPostItem);
            feedAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ADD_POST_ACTIVITY_REQUEST_CODE:
                    if (data != null && data.hasExtra(AddPostActivity.NEW_ITEM_INTENT_KEY)) {
                        PostEntity newPostItem = (PostEntity) data.getSerializableExtra(AddPostActivity.NEW_ITEM_INTENT_KEY) ;
                        addItemToList(newPostItem);
                    }
                    break;
            }
        }
    }


    @Override
    public void onFeedItemClick(int position) {
        String authorName = getString(R.string.message, postItems.get(position).getAuthorName());
        Snackbar.make(feedRecycler, authorName, Snackbar.LENGTH_LONG)
                .show();
    }

}
