package firbase.app.daytodayindiaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class signup extends AppCompatActivity {
  EditText email, pass;
  Button btn;
  TextView already;

    public FirebaseAuth mAuth; // firbase  class variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        email=findViewById(R.id.useremailid);
        pass=findViewById(R.id.userpassword);
    }

    public void google(View view)
    {
        Intent i= new Intent(signup.this,WelcomeActivity.class);
        startActivity(i);
    }

    public void Loginuser(View view) {

    if(email.getText().toString().isEmpty())
    {
        email.requestFocus();
        email.setError("Email id Empty");
    }
    if (pass.getText().toString().isEmpty())
    {
        pass.requestFocus();
        pass.setError("Password is Empty");
    }
    else
    {
String emails=email.getText().toString().trim();
String password=pass.getText().toString().trim();
        mAuth.signInWithEmailAndPassword(emails, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithEmail:success");
                            ///FirebaseUser user = mAuth.getCurrentUser();
                            Intent i= new Intent(signup.this,WelcomeActivity.class);
                            startActivity(i);
                            Toast.makeText(signup.this, "Successfully Login", Toast.LENGTH_SHORT).show();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithEmail:failure", task.getException());
                            //Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                         //           Toast.LENGTH_SHORT).show();
                           // updateUI(null);
                            Toast.makeText(signup.this, "Successfully Login", Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }




    }
}
