package com.nimisha.nytimes.model;

import java.util.List;

public class TopStories {

    private String title;
    private String url;
    private String section;
    private String subsection;

    public String getSubsection() {
        return subsection;
    }

    public void setSubsection(String subsection) {
        this.subsection = subsection;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    private List<Multimedia> multimedia = null;

    public List<Multimedia> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<Multimedia> multimedia) {
        this.multimedia = multimedia;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticleThumbnailUrl() {
        String thumbnailUrl = "";

        if (this.getMultimedia() != null) {
            for (Multimedia m : this.getMultimedia()) {
                //  if (m.getType().equals("image") && m.getSubtype().equals("photo")&&getSection().equals("arts")&&getSubsection().equals("television")){

                thumbnailUrl = m.getUrl();
                break;
            }
            //  }
            return thumbnailUrl;
        }
        return thumbnailUrl;
    }


}
