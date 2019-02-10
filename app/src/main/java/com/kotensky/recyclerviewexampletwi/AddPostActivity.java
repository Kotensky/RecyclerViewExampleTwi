package com.kotensky.recyclerviewexampletwi;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class AddPostActivity extends AppCompatActivity {

    public static final String NEW_ITEM_INTENT_KEY = "new_item_intent_key";

    private EditText authorNameEdt;
    private EditText avatarImgEdt;
    private EditText textEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        authorNameEdt = findViewById(R.id.author_name_edt);
        avatarImgEdt = findViewById(R.id.avatar_img_edt);
        textEdt = findViewById(R.id.text_edt);

        setupToolbar();
        setupFabListener();
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

                String authorName = authorNameEdt.getText().toString();
                String text = textEdt.getText().toString();
                String avatarLink = avatarImgEdt.getText().toString();

                PostEntity postEntity = new PostEntity(authorName, text, avatarLink);

                Intent intent = new Intent();
                intent.putExtra(NEW_ITEM_INTENT_KEY, postEntity);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
