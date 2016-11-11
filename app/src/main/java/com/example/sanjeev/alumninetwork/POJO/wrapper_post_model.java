package com.example.sanjeev.alumninetwork.POJO;


import java.util.ArrayList;
import java.util.List;

public class wrapper_post_model
{
    private List<post_model> result = new ArrayList<post_model>();
    public List<post_model> getprojects() {
        return result;
    }
    public void setprojects(List<post_model> post) {
        this.result = post;}
}
