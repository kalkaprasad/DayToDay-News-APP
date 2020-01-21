package firbase.app.daytodayindiaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class welcomeadmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomeadmin);
    }

    public void update(View view)
    {
        Intent i= new Intent(welcomeadmin.this,Addpost.class);
        startActivity(i);
    }
    public  void  modify(View view)
    {
        Intent i= new Intent(welcomeadmin.this,modifydata.class);
        startActivity(i);
    }
}
