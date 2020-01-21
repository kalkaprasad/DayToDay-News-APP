package firbase.app.daytodayindiaapp;

public class NewsData {


 public NewsData()
 {


 }


    private String date,time,title,imgurl,discription,myid,category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getMyid() {
        return myid;
    }

    public void setMyid(String myid) {
        this.myid = myid;
    }

    public NewsData(String date, String time, String title, String imgurl, String discription, String myid,String category) {
        this.date = date;
        this.time = time;
        this.title = title;
        this.imgurl = imgurl;
        this.discription = discription;
        this.myid = myid;
        this.category=category;
    }
}
