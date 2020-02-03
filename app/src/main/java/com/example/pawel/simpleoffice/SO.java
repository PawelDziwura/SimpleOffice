package com.example.pawel.simpleoffice;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SO extends AppCompatActivity {

    final private int MULTIPLE_PERMISSION = 12345;
    private ListView listView;
    private String[] activities;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_so);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (ListView) findViewById(R.id.ToolsListView);
        initResources();
        initActivitiesListView();
    }

    private void initResources()
    {
        Resources resources = getResources();
        activities = resources.getStringArray(R.array.activities);
    }

    private void initActivitiesListView()
    {
        listView.setAdapter(new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                activities
        ));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                click(position);
            }
        });
    }

    public void click(int pos) {
        Intent intent = null;
        switch (pos)
        {
            case 0:
                intent=new Intent(SO.this, Calendar.class);
                startActivity(intent);
                break;

            case 1:
                intent=new Intent(SO.this, Clock.class);
                startActivity(intent);
                break;

            case 2:
                intent=new Intent(SO.this, Calculator.class);
                startActivity(intent);
                break;

            case 3:
                intent=new Intent(SO.this, NoteBook.class);
                startActivity(intent);
                break;

            case 4:
                intent=new Intent(SO.this, Maps.class);
                startActivity(intent);
                break;

            case 5:
                intent=new Intent(SO.this, Painting.class);
                startActivity(intent);
                break;

            case 6:
                intent=new Intent(SO.this, Spirit_level.class);
                startActivity(intent);
                break;

            case 7:
                intent=new Intent(SO.this, CameraActivity.class);
                startActivity(intent);
                break;

            case 8:
                intent=new Intent(SO.this, GamesActivity.class);
                startActivity(intent);
                break;
        }
    }

}
