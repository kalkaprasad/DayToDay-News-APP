package firbase.app.daytodayindiaapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class businessNewsAdapter extends RecyclerView.Adapter<businessNewsAdapter.ExamviewHolder> {
    public List<NewsData> emExampleList;
    public Context mcontext;



    public businessNewsAdapter(Context context, List<NewsData> examplelist) {

          mcontext=context;
          emExampleList=examplelist;

    }

    public static class ExamviewHolder extends  RecyclerView.ViewHolder {  //  here  we are   excletends the  method the Recycle view..

          public ImageView imageView;
          public TextView tv_date,tv_time,tv_title;
        public LinearLayout myclick;
          public ExamviewHolder(@NonNull View itemView) {
              super(itemView);
              imageView=itemView.findViewById(R.id.l_image);
              tv_date=itemView.findViewById(R.id.l_date);
              tv_time=itemView.findViewById(R.id.l_time);
              tv_title=itemView.findViewById(R.id.l_title);
              myclick=itemView.findViewById(R.id.listitemclick);
          }
      }

      @NonNull
      @Override
      public ExamviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newslayout,parent,false);  // infalter are use  mfor the  layput connect to anoteht layout
        ExamviewHolder examviewHolder=new ExamviewHolder(view);
        return examviewHolder;
      }

      @Override
      public void onBindViewHolder(@NonNull ExamviewHolder holder, int position) {
          final NewsData currentitem=emExampleList.get(position);  // it is  use the  hold the data and set the data to text view or id ..
          holder.tv_time.setText(currentitem.getTime());
          holder.tv_date.setText(currentitem.getTime());
          holder.tv_title.setText(currentitem.getTitle());// holder
          Picasso.get().load(currentitem.getImgurl()).into(holder.imageView); // picasso are library   use for the load image from url..
          holder.myclick.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  Intent i= new Intent(mcontext,businessNews.class);
                  i.putExtra("title",currentitem.getTitle());
                  i.putExtra("desc",currentitem.getDiscription());
                  i.putExtra("time",currentitem.getTime());
                  i.putExtra("date",currentitem.getDate());
                  i.putExtra("imgurl",currentitem.getImgurl());
                  mcontext.startActivity(i);

              }
          });

      }

      @Override
      public int getItemCount() {
          return emExampleList.size();
      }



}
