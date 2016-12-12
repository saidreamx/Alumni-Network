package com.example.sanjeev.alumninetwork.POJO_wrapper;

import com.example.sanjeev.alumninetwork.POJO.comment_model;

import java.util.ArrayList;
import java.util.List;

public class wrapper_comment_model
{
    private List<comment_model> result = new ArrayList<comment_model>();
    public List<comment_model> getcomment() {
        return result;
    }
    public void setpost(List<comment_model> post) {
        this.result = post;}

}
