package com.example.artefacto.order_app_client;

/**
 * Created by fjrois on 16/11/16.
 */

public class Title {
    private String title;
    private int idDrawable;

    public Title(String title, int idDrawable) {
        this.title = title;
        this.idDrawable = idDrawable;
    }

    public String getName() {
        return title;
    }

    public int getIdDrawable() {
        return idDrawable;
    }

    public int getId() {
        return title.hashCode();// ??
    }

    public static Title[] ITEMS = {
            new Title("Find places", R.drawable.nearme1),
            //new Title("Find places near me 2", R.drawable.nearme2),
            new Title("Mates",R.drawable.contacts)
    };

    /**
     * Obtain item based on its identifier
     *
     * @param id identifier
     * @return Place
     */
    public static Title getItem(int id) {
        for (Title item : ITEMS) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
}