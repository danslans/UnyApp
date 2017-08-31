package com.darkcode.unyapp.model;

/**
 * Created by daniel.gomez on 31/08/2017.
 */

public class University {
    private int id;
    private String name;
    private String information;
    private String url;
    private String logo;

    public University() {
    }

    public University(int id, String name, String information, String url, String logo) {
        this.id = id;
        this.name = name;
        this.information = information;
        this.url = url;
        this.logo = logo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
