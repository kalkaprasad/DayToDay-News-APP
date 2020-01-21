package firbase.app.daytodayindiaapp;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;



public class modifydata extends AppCompatActivity {

    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifydata);

    }

    public void treand(View view)
    {
  id=1;
        //Toast.makeText(this, "You are clicked on the Treanding news", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this,Deletelist.class);
        i.putExtra("id",id);
        startActivity(i);
        //Toast.makeText(this, "tranding news on", Toast.LENGTH_SHORT).show();
    }
    public  void business(View view)
    {
  id=2;
        Intent i = new Intent(this,Deletelist.class);
        i.putExtra("id",id);
        startActivity(i);
        //Toast.makeText(this, " you are clicked on the Business News", Toast.LENGTH_SHORT).show();
    }
    public void tech(View view)
    {
        id=3;
        Intent i= new Intent(this,Deletelist.class);
        startActivity(i);
        i.putExtra("id",id);
        // Toast.makeText(this, "You are clicked on the Teach news", Toast.LENGTH_SHORT).show();
    }
    public  void  world(View view)
    {
        id=4;
        Intent i = new Intent(this,Deletelist.class);
        i.putExtra("id",id);
        startActivity(i);
        // Toast.makeText(this, " You are clicked on the World news", Toast.LENGTH_SHORT).show();
    }
    public  void   breaking(View view)
    {
        id=5;
        Intent i = new Intent(this,Deletelist.class);
        i.putExtra("id",id);
        startActivity(i);
        // Toast.makeText(this, " You are clicked on the Breaking News", Toast.LENGTH_SHORT).show();
    }
    public  void  crime(View view)
    {
        id=6;
        Intent i= new Intent(this,Deletelist.class);
        i.putExtra("id",id);
        startActivity(i);
        // Toast.makeText(this, "You are clicked on the Crime news", Toast.LENGTH_SHORT).show();
    }
}
