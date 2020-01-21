package firbase.app.daytodayindiaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class Phonenumber extends AppCompatActivity {


    private Button sendverifactioncode,verifybutton;
    EditText inputphonenumber,inputvercode;
    String phonenumb,phonenumbe;
    ProgressDialog loadingbar;
    FirebaseAuth mauth; // firbase auth is use for  connnect to the Firebase Aunthications..
    DatabaseReference rootfef= FirebaseDatabase.getInstance().getReference().child("User Data"); // it give the refrence the database
    FirebaseAuth.AuthStateListener authStateListener;
    private  PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    private  String  mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonenumber);

        inputphonenumber=findViewById(R.id.phone);
        inputvercode=findViewById(R.id.otpsend);
        mAuth=FirebaseAuth.getInstance();
        loadingbar=new ProgressDialog(this);
        sendverifactioncode=findViewById(R.id.send);
        verifybutton=findViewById(R.id.otp);


        sendverifactioncode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                phonenumb=inputphonenumber.getText().toString().trim();
                if(phonenumb.isEmpty())
                {
                    inputphonenumber.setError("Enter the number");
                }
                else
                {

                    loadingbar.setMessage("Sending Code on Your Number");
                    loadingbar.setCanceledOnTouchOutside(false);
                    loadingbar.show();
                    phonenumbe="+91"+phonenumb;
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            phonenumbe,
                            60,
                            TimeUnit.SECONDS,
                            Phonenumber.this,
                            callbacks);
                }

            }
        });
        verifybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String varcode=inputvercode.getText().toString();

                if(varcode.isEmpty())
                {
                    inputvercode.setError("Enter The Verification Code");
                }
                else
                {

                    loadingbar.setMessage("Verifying Your Number.. ");
                    loadingbar.setCanceledOnTouchOutside(false);
                    loadingbar.show();
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId,varcode );// PhoneAuthCredential are use for credentioal permsstion..

                    signInWithPhoneAuthCredential(credential);

                }
            }
        });

        callbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential)
            {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                inputphonenumber.setVisibility(View.VISIBLE);
                sendverifactioncode .setVisibility(View.VISIBLE);
                inputvercode.setVisibility(View.INVISIBLE);
                verifybutton.setVisibility(View.INVISIBLE);

                Toast.makeText(Phonenumber.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                loadingbar.dismiss();
            }


            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                inputphonenumber.setVisibility(View.INVISIBLE);
                sendverifactioncode .setVisibility(View.INVISIBLE);
                inputvercode.setVisibility(View.VISIBLE);
                loadingbar.dismiss();
                myalert();
                verifybutton.setVisibility(View.VISIBLE);
                mVerificationId = verificationId;
                mResendToken = token;
                Toast.makeText(Phonenumber.this, "Verification Code has Been Sent On Your Number", Toast.LENGTH_SHORT).show();

            }

        };

    }



    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {


                            loadingbar.dismiss();
                             Toast.makeText(Phonenumber.this, "Verification Completed", Toast.LENGTH_SHORT).show();
                             Intent i = new Intent(Phonenumber.this,Userregister.class);
                             startActivity(i);




                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }

    private void myalert(){


        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage(" Keep Seat Back and Relax We Automatically Detect Your Phone Otp  ");
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });



        AlertDialog alert11 = builder1.create();
        alert11.show();
    }


}
