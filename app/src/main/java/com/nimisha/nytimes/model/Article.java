package com.nimisha.nytimes.model;

import java.util.List;

import static com.nimisha.nytimes.constants.AppConstant.NYTIMES_BASE_URI;

public class Article {

    private String source;

    public List<Multimedia> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<Multimedia> multimedia) {
        this.multimedia = multimedia;
    }

    private List<Multimedia> multimedia = null;

    public String getLead_paragraph() {
        return lead_paragraph;
    }

    public void setLead_paragraph(String lead_paragraph) {
        this.lead_paragraph = lead_paragraph;
    }

    private String lead_paragraph;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getArticleThumbnailUrl() {
        String thumbnailUrl = "";

        for (Multimedia m : this.getMultimedia()) {
            if (m.getType().equals("image") && m.getSubtype().equals("xlarge")) {
                thumbnailUrl = NYTIMES_BASE_URI + m.getUrl();
                break;
            }
        }
        return thumbnailUrl;
    }
}
