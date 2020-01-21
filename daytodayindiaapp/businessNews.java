package firbase.app.daytodayindiaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class businessNews extends AppCompatActivity {



    private RecyclerView recyclerView;
    ProgressBar progressBar;

    FirebaseDatabase db= FirebaseDatabase.getInstance();
    DatabaseReference postre;
    private List<NewsData> examplelist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_news);
        recyclerView=findViewById(R.id.bussinesnewslist);
        progressBar =findViewById(R.id.busiprogresbar);
        postre = FirebaseDatabase.getInstance().getReference().child("New record").child("Business news");
        recyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(1, LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        DateMethod();
    }
    private  void DateMethod()
    {
        postre.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressBar .setVisibility(View.VISIBLE);
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    NewsData post=dataSnapshot1.getValue(NewsData.class);
                    examplelist.add(post);


                }
                NewsAdapter adapter=new NewsAdapter(businessNews.this,examplelist);

                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(businessNews.this, "data not found", Toast.LENGTH_SHORT).show();

            }
        });
    }
    }

