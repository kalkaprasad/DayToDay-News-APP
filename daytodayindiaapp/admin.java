package firbase.app.daytodayindiaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class admin extends AppCompatActivity {
 EditText  email,pass;
 FirebaseAuth auth;
 TextView forgetpass;
 String myemail="anykor6@gmail.com";
 ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        email=(EditText) findViewById(R.id.email);
        pass=(EditText) findViewById(R.id.pass);
        forgetpass=(TextView) findViewById(R.id.forgetpass);
        auth=FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("Sending  reset Link...");
                progressDialog.show();
                auth.sendPasswordResetEmail(myemail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            progressDialog.dismiss();
                            Toast.makeText(admin.this, " Reset link sent on your email.", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(admin.this, "Something  went  wrong.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

}

public void submit(View view)
{
    if(email.getText().toString().isEmpty())
    {
        email.setError("error");
        email.requestFocus();

    }

    if( pass.getText().toString().isEmpty())
    {
        pass.setError("error");
        pass.requestFocus();

    }
    else
    {
           progressDialog.setMessage("Loding..");
           progressDialog.show();
           progressDialog.setCanceledOnTouchOutside(false);
        String emails=email.getText().toString().trim();
        String passs=pass.getText().toString().trim();
        auth.signInWithEmailAndPassword(emails,passs).addOnCompleteListener(admin.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {

                    progressDialog.dismiss();
                    Intent i = new Intent(admin.this,welcomeadmin.class);
                    startActivity(i);
                    //Intent p new Intent(admin.this,modifydata.class);
                    //startActivity(p);
                    Toast.makeText(admin.this, "valid user", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //progressDialog.dismiss();
                    Toast.makeText(admin.this, "Invalid user", Toast.LENGTH_SHORT).show();
                }
            }

        });


    }
}}

