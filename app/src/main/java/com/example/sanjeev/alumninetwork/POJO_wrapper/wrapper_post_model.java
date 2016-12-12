package com.example.sanjeev.alumninetwork.POJO_wrapper;


import com.example.sanjeev.alumninetwork.POJO.post_model;

import java.util.ArrayList;
import java.util.List;

public class wrapper_post_model
{
    private List<post_model> result = new ArrayList<post_model>();
    public List<post_model> getpost() {
        return result;
    }
    public void setpost(List<post_model> post) {
        this.result = post;}
}
