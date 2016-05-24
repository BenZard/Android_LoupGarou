package com.cryocrystal.exampleproject.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cryocrystal.exampleproject.R;

/**
 * Created by Thierry on 17/05/2016.
 */
public class FetchImageActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int IMAGE_REQUEST_CODE = 1;
    private Button buttonToGallery;
    private Button buttonToDisplay;
    private Uri imageUri = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_image);

        buttonToGallery = (Button) findViewById(R.id.button_to_gallery_activity);
        if (buttonToGallery != null) {
            buttonToGallery.setOnClickListener(this);
        }

        buttonToDisplay = (Button) findViewById(R.id.button_recreate_display_image);
        if (buttonToDisplay != null) {
            buttonToDisplay.setOnClickListener(this);
        }
    }


    // We declared that we implement View.OnClickListener so we need this fonction
    @Override
    public void onClick(View v) {
        if (v == buttonToGallery){
            Intent galleryForImage = new Intent(this, GalleryActivity.class);
            startActivityForResult(galleryForImage, IMAGE_REQUEST_CODE);
        } else if (v == buttonToDisplay){
            Intent displayIntent = new Intent(this, DisplayImageActivity.class);
            displayIntent.putExtra(GalleryActivity.IMAGE_URI_KEY, imageUri);
            startActivity(displayIntent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case IMAGE_REQUEST_CODE:
                if (resultCode == RESULT_OK){
                    imageUri = data.getParcelableExtra(GalleryActivity.IMAGE_URI_KEY);
                } else {
                    Toast.makeText(this, "Gallery Activity sucks, gave me no image !!", Toast.LENGTH_LONG).show();
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
