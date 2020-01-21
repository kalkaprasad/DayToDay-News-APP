package firbase.app.daytodayindiaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class DistroyActivity extends AppCompatActivity {
    EditText title, discriptions;
  ImageView myimg;
    String keynode;
    String parentnode;
    FirebaseDatabase mdb;
    DatabaseReference mpostref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distroy);
        title=findViewById(R.id.up_title);
        discriptions=findViewById(R.id.up_discription);
        myimg=findViewById(R.id.delimg);
        title.setText(getIntent().getStringExtra("title"));
        discriptions.setText(getIntent().getStringExtra("desc"));
        Picasso.get().load(getIntent().getStringExtra("imgurl")).into(myimg);
        keynode=getIntent().getExtras().get("myid").toString();
        parentnode=getIntent().getExtras().get("parent").toString();
        mdb=FirebaseDatabase.getInstance();
        mpostref=mdb.getReference().child("News Record").child(parentnode).child(keynode);

    }
    public  void delete(View view)
    {
        mpostref.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
           if(task.isSuccessful())
           {
               Toast.makeText(DistroyActivity.this, "Succesful delete", Toast.LENGTH_SHORT).show();
               Intent i = new Intent(DistroyActivity.this,modifydata.class);
               startActivity(i);
               finish();
           }
            }
        });
    }
    public  void update(View view)
    {
        String mytitle=title.getText().toString();
        String mydesc=discriptions.getText().toString();
        mpostref.child("title").setValue(mytitle).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(DistroyActivity.this, " Title Update succesfully", Toast.LENGTH_SHORT).show();
            }
        });
        mpostref.child("discription").setValue(mydesc).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(DistroyActivity.this, "discription updated", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
