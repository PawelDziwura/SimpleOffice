package com.example.pawel.simpleoffice;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SimpleOffice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_office);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
        {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    public void click(View view) {
        Intent intent;
        switch (view.getId())
        {
            case R.id.lets_go:
                intent=new Intent(SimpleOffice.this, LoginActivity.class);
                startActivity(intent);
                break;

            case R.id.Continue_without_registration:
                intent=new Intent(SimpleOffice.this, Welcome.class);
                startActivity(intent);
                break;
        }
    }
}
