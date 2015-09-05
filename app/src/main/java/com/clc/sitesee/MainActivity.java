package com.clc.sitesee;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.ibm.watson.developer_cloud.visual_recognition.v1.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v1.model.RecognizedImage;
import com.ibm.watson.developer_cloud.visual_recognition.v1.model.LabelSet;
import java.io.File;
import java.util.List;
import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;

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
        VisualRecognition service = new VisualRecognition();
        service.setUsernameAndPassword("lucyyu1996@hotmail.com", "SFBNY16S");

        File image = new File("src/main/res/drawable/car.jpg");

        RecognizedImage recognizedImage = service.recognize(image);
        System.out.println(recognizedImage);

        /*
        TextToSpeech service = new TextToSpeech();
        List<Voice> voices = service.getVoices();
        System.out.println(voices);
        */
    }
}
