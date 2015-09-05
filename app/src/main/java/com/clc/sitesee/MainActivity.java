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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void sitesee(View view) {
        recognizeImage ri = new recognizeImage();
        ri.execute();

        // Lines below automatically open camera app -- we want to avoid this so the code below attempts that
        //Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        //startActivityForResult(intent,0);

        Camera backCamera = Camera.open();
        SurfaceView v = new SurfaceView(this);
        try{
            backCamera.setPreviewDisplay(v.getHolder());
        } catch (IOException e){
            // NOP
        }
        backCamera.startPreview();
        // backCamera.takePicture(shutterCallback, rawPictureCallback, jpegPictureCallback);

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
