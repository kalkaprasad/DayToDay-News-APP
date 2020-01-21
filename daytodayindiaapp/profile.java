package firbase.app.daytodayindiaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    public void updateprofile(View view)
    {
        Toast.makeText(this, "Profile updated", Toast.LENGTH_SHORT).show();
    }
}
