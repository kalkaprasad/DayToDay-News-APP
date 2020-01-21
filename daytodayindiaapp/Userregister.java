package firbase.app.daytodayindiaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Userregister extends AppCompatActivity {
 EditText name,email;
 ProgressBar progressBar;
 FirebaseUser firebaseUser;
 FirebaseUser mob =FirebaseAuth.getInstance().getCurrentUser();

 DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userregister);
        name=(EditText) findViewById(R.id.user);
        email=(EditText) findViewById(R.id.pass);
        databaseReference=FirebaseDatabase.getInstance().getReference();
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        progressBar=(ProgressBar) findViewById(R.id.process);

    }


public  void signup(View view) {

    String myname = name.getText().toString().trim();
    String myemail = email.getText().toString().trim();

    final String key=databaseReference.child("user").push().getKey();
    HashMap hp = new HashMap();
    hp.put("name",myname);
    hp.put("email",myemail);
    hp.put("number",mob);
    hp.put("key",key);
    databaseReference.child("user").child(firebaseUser.getUid()).child(key).updateChildren(hp).addOnCompleteListener(new OnCompleteListener() {
        @Override
        public void onComplete(@NonNull Task task) {

            if(task.isSuccessful())
                {
                    Toast.makeText(Userregister.this, "Data  inserted", Toast.LENGTH_SHORT).show();
                   Intent i = new Intent(Userregister.this,WelcomeActivity.class).putExtra("key",key);
                    startActivity(i);
                }
            else
            {
                Toast.makeText(Userregister.this, "failled", Toast.LENGTH_SHORT).show();
            }

        }
    });

}

    }

