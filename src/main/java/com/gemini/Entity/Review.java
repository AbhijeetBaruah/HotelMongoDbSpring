package com.gemini.Entity;

public class Review {
    private String userName;
    private int rating;
    private boolean approved;


    public String getUserName() {
        return userName;
    }

    public int getRating() {
        return rating;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public boolean isApproved() {
        return approved;
    }
}
