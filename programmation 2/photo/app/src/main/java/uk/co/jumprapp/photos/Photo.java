package uk.co.jumprapp.photos;

import java.io.Serializable;

public class Photo implements Serializable {

    private String file;
    private boolean selected;
    private boolean goodPhoto;

    public Photo(String file) {
        this.file = file;
        this.selected = false;
        this.goodPhoto = getRandomBoolean();
    }

    public static boolean getRandomBoolean() {
        return Math.random() < 0.5;
        //I tried another approaches here, still the same result

    }

    public boolean isGoodPhoto() {
        return goodPhoto;
    }

    public void setGoodPhoto(boolean goodPhoto) {
        this.goodPhoto = goodPhoto;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getFile() {
        return file;
    }

}
