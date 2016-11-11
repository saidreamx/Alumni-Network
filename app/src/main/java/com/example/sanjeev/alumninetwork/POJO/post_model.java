package com.example.sanjeev.alumninetwork.POJO;

public class post_model
{
    private String name;
    private String time;
    private String post;
    private String comments;



    public String getName() {
        return name;
    }

    public String getComments() {
        return comments;
    }

    public String getPost() {
        return post;
    }

    public String getTime() {
        return time;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
