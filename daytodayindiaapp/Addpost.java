package firbase.app.daytodayindiaapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Addpost extends AppCompatActivity {
    Spinner spinner;
    String download;
    EditText newstype,description;
    ImageView opencameras;
    ProgressDialog progressDialog;
    ImageView camera,gallerys;
    String [] catgery= {"Tranding news","Breaking news","Crime news","world news","tech news","Business news"};
    DatabaseReference dbref;
    String title,descriptions,date,time;
    ArrayAdapter Ad1;
    String mychoice;
    Bitmap bitmaps;
    StorageReference refrence;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpost);
        newstype=(EditText) findViewById(R.id.newstype);
        spinner =(Spinner) findViewById(R.id.spinners);
        description=(EditText) findViewById(R.id.descri);
        opencameras=(ImageView) findViewById(R.id.opencamera);
        refrence= FirebaseStorage.getInstance().getReference();

      progressDialog=new ProgressDialog(this); // progress dialog box are use the waiting time of the any operation
        Ad1=new ArrayAdapter( this,android.R.layout.select_dialog_item,catgery); // context is pass the corrent object
        spinner.setAdapter(Ad1);
        checkmyvalue();
        opencameras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(Addpost.this, "camera will open", Toast.LENGTH_SHORT).show();
                showmyDailog();  // here method are created and initilize in   the outside the setonClicklistner method..


            }
        });


    }

    private void showmyDailog() {

        AlertDialog.Builder dailog=new AlertDialog.Builder(this); // it  is  use for Conformation dialog box...
        LayoutInflater inflater=this.getLayoutInflater(); // Layout inflater are use the show the custom box of the conformation box..
        View dailogview=inflater.inflate(R.layout.customcamera,null);
      // AlertDialog.Builder dailog=new AlertDialog.Builder(this);
        //LayoutInflater inflater1=this.getLayoutInflater();
       //View dailogviews=inflater.inflate(R.layout.customcamera,null);
       //dailog.setView(dailogview);
        dailog.setView(dailogview);
        final AlertDialog alertDialog=dailog.create();
        alertDialog.show();

        camera=alertDialog.findViewById(R.id.camera);
        gallerys=alertDialog.findViewById(R.id.gallery);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);
                //alertDialog.dismiss();
            }
        });
        gallerys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  takeimage = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(takeimage,1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0 &&resultCode==RESULT_OK)
        {
             bitmaps=(Bitmap) data.getExtras().get("data");
            opencameras.setImageBitmap(bitmaps);

        }
        else if(requestCode==1&&resultCode==RESULT_OK&&data.getData()!=null)
        {
            Uri uri=data.getData();
            try {
                bitmaps = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            opencameras.setImageBitmap(bitmaps);
        }
    }
    public  void Post(View view) {

        title=newstype.getText().toString().trim();
        descriptions=description.getText().toString().trim();
        progressDialog.setMessage("uploading");
        progressDialog.setCanceledOnTouchOutside(false);
        uploadimage();
        progressDialog.show();

    }

    private void uploadimage() {

   // it is use for the image compress and upload image.
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmaps.compress(Bitmap.CompressFormat.JPEG,50,baos);
        byte[] finalimg=baos.toByteArray();
        final  StorageReference filepath = refrence.child("news images").child(finalimg+"jpg");

        final UploadTask uploadTask=filepath.putBytes(finalimg);
        uploadTask.addOnCompleteListener(Addpost.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful())
                {
                  //  Toast.makeText(Addpost.this, "Task is complete", Toast.LENGTH_SHORT).show();

                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    download=String.valueOf(uri);
                                    insertdata();
                                    //Toast.makeText(Addpost.this,download, Toast.LENGTH_SHORT).show();
                                    Toast.makeText(Addpost.this, " sucessful", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                else
                {
                    Toast.makeText(Addpost.this, " task is failed ", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void  insertdata()
    {
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat currentdate=new SimpleDateFormat("dd-mmmm-yyyy");
        date=currentdate.format(calendar.getTime());

        Calendar calfortime= Calendar.getInstance();
        SimpleDateFormat currenttime= new SimpleDateFormat("hh-mm");
        time=currenttime.format(calfortime.getTime());
        final String myuniquekey=dbref.push().getKey();
        HashMap hp= new HashMap();
        hp.put("time",title);
        hp.put("date",date);
        hp.put("title",title);
        hp.put("imgurl",download);
        hp.put("discription",descriptions);
        hp.put("myid",myuniquekey);
        hp.put("category",mychoice);
        dbref.child(myuniquekey).updateChildren(hp).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful())
                {
                    progressDialog.dismiss();
                    Toast.makeText(Addpost.this, "Data Inserted succesfully", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    progressDialog.dismiss();
                    Toast.makeText(Addpost.this, "Faild to insert to data ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
public  void checkmyvalue()
{
    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            // here we  take the postion of the spiiner data
            // object item= addapterView.getItemPostion(i);
          //  Object item=adapterView.getItemAtPosition(i);
            Object item = adapterView.getItemAtPosition(i);
            if(item.toString().equals("select category"))
            {

            }
            else if(item.toString().equals("Tranding news"))
            {
                dbref = FirebaseDatabase.getInstance().getReference().child("New record").child("Tranding news");
                mychoice="Tranding news";
                //Tranding news","Breaking news","Crime news","world news","tech news","Business news"};
            } else if (item.toString().equals("Breaking news")) {
                dbref = FirebaseDatabase.getInstance().getReference().child("New record").child("Breaking  news");
                mychoice="Breaking news";
            } else if (item.toString().equals("world news")) {
                dbref = FirebaseDatabase.getInstance().getReference().child("New record").child("world news");
                mychoice="world news";
            } else if (item.toString().equals("tech  news")) {
                dbref = FirebaseDatabase.getInstance().getReference().child("New record").child("tech  news");
                mychoice="tech news";
            } else if (item.toString().equals("Business news")) {
                dbref = FirebaseDatabase.getInstance().getReference().child("New record").child("Business news");
                        mychoice="Business news";
            }
            else if (item.toString().equals("Crime news")) {
                dbref = FirebaseDatabase.getInstance().getReference().child("New record").child("Crime news");
                mychoice="Crime news";
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    });

}
}
