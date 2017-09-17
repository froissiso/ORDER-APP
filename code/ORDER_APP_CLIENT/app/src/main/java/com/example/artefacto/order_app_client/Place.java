package com.example.artefacto.order_app_client;

/**
 * Created by fjrois on 15/11/16.
 */

public class Place {
    private String name;
    private String idDrawable;
    private int id;

    public Place() {}

    public Place(String name, String idDrawable) {
        super();
        this.name = name;
        this.idDrawable = idDrawable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdDrawable(){
        return idDrawable;
    }

    public void setIdDrawable(String idDrawable) {
        this.idDrawable = idDrawable;
    }

    public int getId() {
        //return name.hashCode();// ??
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Place [id=" + Integer.toString(id) + ", name="+name+", idDrawable=" + idDrawable +" ]";
    }

}
