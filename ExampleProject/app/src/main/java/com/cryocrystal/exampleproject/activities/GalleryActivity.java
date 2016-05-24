package com.cryocrystal.exampleproject.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.cryocrystal.exampleproject.R;

/**
 * Created by Thierry on 17/05/2016.
 */
public class GalleryActivity extends AppCompatActivity implements View.OnClickListener{
    // Just a key for every bundle maps.
    public static final String IMAGE_URI_KEY = "image_uri_key";
    private static final int IMAGE_REQUEST_CODE = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        Button fetchFromGallery = (Button) findViewById(R.id.button_to_gallery);
        if (fetchFromGallery != null){
            fetchFromGallery.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        Intent imageIntent = new Intent();
        imageIntent.setType("image/*");
        imageIntent.setAction(Intent.ACTION_GET_CONTENT);
        imageIntent.addCategory(Intent.CATEGORY_OPENABLE);

        // Launch an activity capable of handling images
        startActivityForResult(imageIntent, IMAGE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case IMAGE_REQUEST_CODE:
                if (resultCode == RESULT_OK) { // Everything went fine
                    // the imageUri can be obtained from the os gallery with     data.getData()
                    finishWithImage(data.getData());
                } else { // error
                    finishWithoutImage();
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void finishWithImage(Uri imageUri){
        Intent imageData = new Intent();
        imageData.putExtra(IMAGE_URI_KEY, imageUri);
        // Sets the result code and intent (data) that will be transmit to the caller
        setResult(RESULT_OK, imageData);
        finish();
    }

    private void finishWithoutImage(){
        setResult(RESULT_CANCELED);
        finish();
    }
}
