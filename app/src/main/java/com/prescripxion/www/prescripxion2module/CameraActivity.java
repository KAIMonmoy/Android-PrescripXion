package com.prescripxion.www.prescripxion2module;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CameraActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1888;
    public static final int MY_CAMERA_PERMISSION = 5;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        imageView = findViewById(R.id.imageView);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                invokeCamera();
            } else{
                String[] requestedPermission = {Manifest.permission.CAMERA};
                requestPermissions(requestedPermission, MY_CAMERA_PERMISSION);
            }
        }
        else{
            invokeCamera();
        }

        Button openCameraButton = (Button) findViewById(R.id.camera);

        openCameraButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                            invokeCamera();
                        } else{
                            String[] requestedPermission = {Manifest.permission.CAMERA};
                            requestPermissions(requestedPermission, MY_CAMERA_PERMISSION);
                        }
                    }
                    else{
                        invokeCamera();
                    }
                }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Now user should be able to use camera
                invokeCamera();
            } else {
                // App will not have this permission. Turn off all functions
                Toast.makeText(this, "Camera permission not granted.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void invokeCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        String pictureName = getPictureName();

        File path = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/PrescripXion");
        if (!path.exists()) {
            path.mkdirs();
        }
        File image = new File(path, pictureName);
        Uri pictureUri = Uri.fromFile(image);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }


    private String getPictureName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timeStamp = sdf.format(new Date());
        return "PresImg" + timeStamp + ".jpg";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap cameraImage = (Bitmap) extras.get("data");
            imageView.setImageBitmap(cameraImage);
        }
    }
}