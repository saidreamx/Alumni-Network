package com.example.sanjeev.alumninetwork.POJO;

public class projects_model
{
    private String ptitle;
    private String pmentor;
    private String pdescription;
    private String pduration;
    private int sid;

    public int getSid() {
        return sid;
    }

    public String getPdescription() {
        return pdescription;
    }

    public String getPtitle() {
        return ptitle;
    }

    public String getPmentor() {
        return pmentor;
    }

    public String getPduration() {
        return pduration;
    }

    public void setPdescription(String pdescription) {
        this.pdescription = pdescription;
    }

    public void setPduration(String pduration) {
        this.pduration = pduration;
    }

    public void setPmentor(String pmentor) {
        this.pmentor = pmentor;
    }

    public void setPtitle(String ptitle) {
        this.ptitle = ptitle;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }
}
