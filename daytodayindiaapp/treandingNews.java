package firbase.app.daytodayindiaapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.MediaRouteButton;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class treandingNews extends AppCompatActivity {
    EditText comment;
 TextView date,time,discription,title;
 private RecyclerView recyclerView;
 ImageView imgview;
 FirebaseAuth auth; // FirebaseAuth are use for the make auntication to Datanase
 DatabaseReference commetref; // it is use for the make the refrance the database
 ImageView fb,whatsapp,instra;
  DatabaseReference postre;
    String myuser;
  DatabaseReference dbref;
   String current_user_id;
   String myuniqekey;

   RecyclerView commentlist;


ProgressBar progressBar;
    private List<NewsData> examplelist = new ArrayList<>();
    private List<CommentData> examplelist2=new ArrayList<>();// it is the List  use for the  store the list from the database.

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treanding_news);
        date=findViewById(R.id.f_date);
        commentlist=findViewById(R.id.commentrv);
        commentlist.setHasFixedSize(true); //commentlist fixed
        commentlist.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        commentlist.setLayoutManager(layoutManager);
        commentlist.smoothScrollToPosition(0);

         comment=(EditText) findViewById(R.id.et_comment);
        time=findViewById(R.id.f_time);

        dbref=FirebaseDatabase.getInstance().getReference().child("user").child("comment");
        title=findViewById(R.id.f_title);
        discription=findViewById(R.id.f_desc);
        imgview=findViewById(R.id.f_image);
         fb=findViewById(R.id.fb);
         instra=findViewById(R.id.whatsapp);

        FirebaseUser mob = FirebaseAuth.getInstance().getCurrentUser();
        date.setText(getIntent().getStringExtra("date"));
        time.setText(getIntent().getStringExtra("time"));
        discription.setText(getIntent().getStringExtra("desc"));
        title.setText(getIntent().getStringExtra("title"));
        Picasso.get().load(getIntent().getStringExtra("imgurl")).into(imgview);
        auth=FirebaseAuth.getInstance();
       // current_user_id=auth.getCurrentUser().getUid();
        recyclerView=findViewById(R.id.mytrendnewslist);
       // progressBar =findViewById(R.id.progresbar);
        recyclerView=findViewById(R.id.mytrendnewslist);
        postre = FirebaseDatabase.getInstance().getReference().child("News record").child("Tranding news");
        commetref=FirebaseDatabase.getInstance().getReference().child("comment").child("Tranding news");
        recyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager layoutManager2=new StaggeredGridLayoutManager(1, LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(layoutManager2);
        DateMethod();
        DateMethod2();
dbref=FirebaseDatabase.getInstance().getReference().child("users").child("hjgvb");

        ////  share with the facebook
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String [] email={"anykor6@gmail.com"};
                //Intent kp=new Intent(Inte)
               Intent kp= new Intent(Intent.ACTION_SEND);
                kp.setType("Text/plain");
                kp.putExtra(Intent.EXTRA_SUBJECT,getIntent().getStringExtra("Title"));
                kp.putExtra(Intent.EXTRA_EMAIL,email);
                kp.putExtra(Intent.EXTRA_TEXT,getIntent().getStringExtra("discription"));
                startActivity(kp);


            }
        });
        instra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{

                Intent kp= new Intent(Intent.ACTION_SEND);

                kp.setType("text/plain");
                kp.putExtra(Intent.EXTRA_TEXT,"*"+getIntent().getStringExtra("title")+"*"+"  "+getIntent().getStringExtra("discription"));
                kp.setPackage("com.whatsapp");
                startActivity(kp);
                }
                catch (ActivityNotFoundException e)
                {
                    Toast.makeText(treandingNews.this, "Please install ", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
    private  void DateMethod()
    {
        postre.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //progressBar .setVisibility(View.VISIBLE);
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    NewsData post=dataSnapshot1.getValue(NewsData.class);
                    examplelist.add(post);

                }
                NewsAdapter adapter=new NewsAdapter(treandingNews.this,examplelist);
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //progressBar.setVisibility(View.GONE);
                Toast.makeText(treandingNews.this, "data not found", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public  void  submit(View view)
    {
        String mycomment=comment.getText().toString().trim();

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    if (dataSnapshot.exists()) {
                        myuser = dataSnapshot.child("name").getValue(String.class);
                        Toast.makeText(treandingNews.this, "" + myuser, Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){}
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
       // FirebaseDatabase.getInstance().getReference().child("user").setValue("Hamimi");
        try {

            commetref.child(myuniqekey).child("name").setValue(myuser);
            commetref.child(myuniqekey).child("comment").setValue(myuser);
            commetref.child(myuniqekey).child("uniqid").setValue(myuser);
            commetref.child(myuniqekey).child("userid").setValue(myuser);

            HashMap hp = new HashMap();
            hp.put("name", myuser);
            hp.put("comment", mycomment);
            hp.put("uniqid", myuniqekey);
            hp.put("userid", current_user_id);
            commetref.child(myuniqekey).updateChildren(hp).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()) {

                        Toast.makeText(treandingNews.this, "comment added", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(treandingNews.this, "Something went  wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }catch (Exception e){
            Toast.makeText(this, "Comment added ", Toast.LENGTH_SHORT).show();
        }


    }
    private  void DateMethod2()
    {

        commetref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //progressBar .setVisibility(View.VISIBLE);
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    CommentData post=dataSnapshot1.getValue(CommentData.class);
                    examplelist2.add(post);

                }
                CommentAdapter adapter=new CommentAdapter(treandingNews.this,examplelist2);
                commentlist.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //progressBar.setVisibility(View.GONE);
                Toast.makeText(treandingNews.this, "data not found", Toast.LENGTH_SHORT).show();

            }
        });
    }



    }


