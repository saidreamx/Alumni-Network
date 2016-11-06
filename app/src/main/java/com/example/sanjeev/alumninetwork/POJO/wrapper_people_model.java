package com.example.sanjeev.alumninetwork.POJO;


import java.util.ArrayList;
import java.util.List;

public class wrapper_people_model
{
    private List<people_list_model> result = new ArrayList<people_list_model>();
    public List<people_list_model> getpeople() {
        return result;
    }
    public void setpeople(List<people_list_model> people) {
        this.result = people;}
}
