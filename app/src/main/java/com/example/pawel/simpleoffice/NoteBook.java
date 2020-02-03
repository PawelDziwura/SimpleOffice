package com.example.pawel.simpleoffice;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceFragment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.jar.Manifest;

public class NoteBook extends AppCompatActivity {

    EditText et;
    Bundle bundle = new Bundle();
    private String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Directory/SimpleOffice";
    private final int MEMORY_ACCESS = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("aktywność", "onCreate");
        setContentView(R.layout.activity_note_book);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        et = (EditText) findViewById(R.id.editText);
        et.setText(bundle.getString("et"));
        if(ActivityCompat.shouldShowRequestPermissionRationale(NoteBook.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

        }
        else
        {
            ActivityCompat.requestPermissions(NoteBook.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, MEMORY_ACCESS);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case MEMORY_ACCESS:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Jeśli nie zostanie wyrażona zgoda na dostęp do pamięci nie będzie możliwości zapisania pliku", Toast.LENGTH_LONG).show();
                }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {
            createDir();
            createFile();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        Log.d("aktywność", "onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("aktywność", "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("aktywność", "onPause");
        bundle.putString("et", et.getText().toString());
        super.onPause();
    }

    @Override
    protected void onRestart() {
        Log.d("aktywność", "onRestart");
        super.onRestart();
    }

    @Override
    protected void onStop() {
        Log.d("aktywność", "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("aktywność", "onDestroy");
        super.onDestroy();
    }

    public void createDir()
    {
        File folder = new File(path);
        if(!folder.exists())
        {
            try
            {
                folder.mkdirs();
            }
            catch (Exception e)
            {
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    public void createFile()
    {
        File file = new File(path+"/"+System.currentTimeMillis()+".txt");
        FileOutputStream fOut;
        OutputStreamWriter myOutWriter;
        try{
            fOut = new FileOutputStream(file);
            myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append(et.getText());
            myOutWriter.close();
            fOut.close();
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
