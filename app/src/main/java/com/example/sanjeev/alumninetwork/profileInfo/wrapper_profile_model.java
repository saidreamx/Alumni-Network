package com.example.sanjeev.alumninetwork.profileInfo;

import java.util.ArrayList;
import java.util.List;

public class wrapper_profile_model
{
    private List<profile_model> result = new ArrayList<profile_model>();
    public List<profile_model> getprofile() {
        return result;
    }
    public void setprofile(List<profile_model> profile) {
        this.result = profile;
    }
}
