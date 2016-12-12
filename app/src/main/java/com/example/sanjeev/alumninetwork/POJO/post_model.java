package com.example.sanjeev.alumninetwork.POJO;

public class post_model {
    private int post_id;
    private String post_data;
    private String post_name;
    private String post_time;

    public String getPost_data() {
        return post_data;
    }

    public int getPost_id() {
        return post_id;
    }


    public String getPost_time() {
        return post_time;
    }

    public void setPost_data(String post_data) {
        this.post_data = post_data;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getPost_name() {
        return post_name;
    }

    public void setPost_name(String post_name) {
        this.post_name = post_name;
    }

    public void setPost_time(String post_time) {
        this.post_time = post_time;
    }
}
