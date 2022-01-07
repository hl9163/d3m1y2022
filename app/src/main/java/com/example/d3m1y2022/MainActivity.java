package com.example.d3m1y2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * @author		Harel Leibovich <address @ example.com>
 * @version	1.0 (current version number of program)
 * @since		08/01/2022 (the date of the package the class was added)
 * main activity
 */
public class MainActivity extends AppCompatActivity {
    TextView showText;
    EditText input;
    String text, data, savedData;
    boolean isSaved = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = (EditText) findViewById(R.id.edittextInpot);
        showText = (TextView) findViewById(R.id.showTextField);

    }
    /**
     * read from the file "savedData.txt" the information from the last time the
     * user opened the app.
     * In the end all the information is written in the textView window
     * <p>
     *
     */
    @Override
    protected void onStart() {
        super.onStart();
        try{
            FileInputStream fis= openFileInput("savedData.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer sb = new StringBuffer();
            String line = br.readLine();
            while (line != null) {
                sb.append(line+'\n');
                line = br.readLine();
            }
            text=sb.toString();
            isr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showText.setText(text);
        data = text;
    }
    /**
     * save the data that included in the "data" valuable
     * <p>
     *
     */
    public void saveData(){
        data =  data+ input.getText().toString();
        input.setText("");
        input.setHint("enter text");
        try{
            FileOutputStream fos = openFileOutput("savedData.txt",MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(data);
            bw.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * save the info into the file.
     * <p>
     *
     * @param	view Description: Save button
     *
     */

    public void saveInfo(View view) {
        saveData();
        showText.setText(data);
    }
    /**
     * reset the data and the text in screen
     * <p>
     *
     * @param	view Description: Reset button
     *
     */
    public void resetText(View view) {
        data ="";
        saveData();
        showText.setText("");
    }
    /**
     * exit the application and save the info into the file if needed.
     * <p>
     *
     * @param	view Description: Exit button
     *
     */
    public void exit(View view) {
        String checkInputField = input.getText().toString();
        if (checkInputField.length() != 0){
            saveData();
        }
        finish();
    }
    /**
     * create the menu.
     * <p>
     *
     */
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    /**
     * move to the credits activity.
     * <p>
     *
     */
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.cridits1){
            Intent si = new Intent(this, activity_credits.class);
            startActivity(si);
        }
        return true;
    }
}