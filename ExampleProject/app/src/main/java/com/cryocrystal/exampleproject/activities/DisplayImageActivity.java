package com.cryocrystal.exampleproject.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cryocrystal.exampleproject.R;

public class DisplayImageActivity extends AppCompatActivity {

    private Uri imageUri = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_image);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            imageUri = extras.getParcelable(GalleryActivity.IMAGE_URI_KEY);
        }

        // Get a string
        String s = getString(R.string.app_name);

        initFetchButton();
        initImage();
    }

    private void initFetchButton() {
        Button buttonToFecth = (Button) findViewById(R.id.button_to_b_activity);
        if (buttonToFecth == null) {
            return;
        }

        // If we have image information, we'll hide this button
        if (imageUri != null) {
            buttonToFecth.setVisibility(View.GONE);
        }

        buttonToFecth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Normally inside an activity new Intent(this, ...) should work but we are inside an annonyme class of OnClickListener so we need to explicitely specify the scope
                Intent intentFetchActivity = new Intent(DisplayImageActivity.this, FetchImageActivity.class);
                startActivity(intentFetchActivity);

                finish();
            }
        });
    }

    private void initImage() {
        ImageView imageView = (ImageView) findViewById(R.id.my_image);
        if (imageView == null || imageUri == null) {
            return;
        }

        // We display the image from the uri we got
        imageView.setImageURI(imageUri);
        // Make the image visible
        imageView.setVisibility(View.VISIBLE);
    }
}
