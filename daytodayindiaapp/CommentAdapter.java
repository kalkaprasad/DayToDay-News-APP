package firbase.app.daytodayindiaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ExamviewHolder> {
    public List<CommentData> emExampleList;
    public Context mcontext;

    public static class ExamviewHolder extends  RecyclerView.ViewHolder{


        public TextView comment;


        public ExamviewHolder(@NonNull View itemView) {
            super(itemView);
            comment=itemView.findViewById(R.id.customusercomment);

        }
    }
    public CommentAdapter(Context context, List<CommentData> examplelist)
    {
        mcontext=context;
        emExampleList=examplelist;

    }
    @NonNull
    @Override
    public ExamviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_comment,viewGroup,false);
        ExamviewHolder exampleivwholer=new ExamviewHolder(view);
        return  exampleivwholer;



    }

    @Override
    public void onBindViewHolder(@NonNull ExamviewHolder exampleivwholer, int i) {
        final CommentData currentitem=emExampleList.get(i);
        exampleivwholer.comment.setText(currentitem.getComment());


    }

    @Override
    public int getItemCount() {
        return emExampleList.size();
    }


}