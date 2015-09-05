package com.clc.sitesee;

import android.app.Activity;
import android.os.AsyncTask;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.View;

import com.ibm.watson.developer_cloud.visual_recognition.v1.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v1.model.RecognizedImage;

import java.io.File;
import java.io.IOException;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActionBar().hide();

        // for replacing container on main layout with camera fragment
        if (null == savedInstanceState) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, CameraFragment.newInstance())
                    .commit();
        }
    }

    private class recognizeImage extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground (Void...params){
            VisualRecognition service = new VisualRecognition();
            service.setUsernameAndPassword("lucyyu1996@hotmail.com", "SFBNY16S");

            File image = new File("src/main/res/drawable/car.jpg");

            RecognizedImage recognizedImage = service.recognize(image);
            System.out.println(recognizedImage);
            return null;

        }
    }
}
