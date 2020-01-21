package firbase.app.daytodayindiaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Deletelist extends AppCompatActivity {
int id;
 private RecyclerView recyclerView;
 DatabaseReference dbref;
    ProgressBar progressBar;
    private List<NewsData> examplelist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletelist);
        recyclerView=(RecyclerView) findViewById(R.id.delrecycleview);
        progressBar=(ProgressBar) findViewById(R.id.delprogresbar);
        id=getIntent().getIntExtra("id",0);
        if(id==1)
        {
            dbref = FirebaseDatabase.getInstance().getReference().child("New record").child("Tranding news");
        }
        if(id==2)
        {
            dbref = FirebaseDatabase.getInstance().getReference().child("New record").child("Breaking  news");
        }
        if(id==3)
        {
            dbref = FirebaseDatabase.getInstance().getReference().child("New record").child("world news");
        }
        if(id==4)
        {
            dbref = FirebaseDatabase.getInstance().getReference().child("New record").child("tech  news");
        }
        if(id==5)
        {
            dbref = FirebaseDatabase.getInstance().getReference().child("New record").child("Business news");
        }
        if(id==6)
        {

            dbref = FirebaseDatabase.getInstance().getReference().child("New record").child("Crime news");

        }


        recyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(1, LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        DateMethod();
    }
    private  void DateMethod()
    {

       dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressBar .setVisibility(View.GONE);
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    NewsData post=dataSnapshot1.getValue(NewsData.class);
                    examplelist.add(post);
                }
                DeleteAdapter adapter=new DeleteAdapter(Deletelist.this,examplelist);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(Deletelist.this, "data not found", Toast.LENGTH_SHORT).show();

            }
        });
    }


        }


