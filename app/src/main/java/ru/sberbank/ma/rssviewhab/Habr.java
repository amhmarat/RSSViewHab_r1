package ru.sberbank.ma.rssviewhab;

import android.graphics.Bitmap;

import java.util.UUID;

/**
 * Created by internet on 04.02.2018.
 */

public class Habr {
    private String mTitle;
    private String mLink;
    private String mDesc;
    private String mCategory;
    private String mPubDate;
    private String mImageUrl;
    private Bitmap mImage;
    private UUID mID;
    private String mCreater;

    Habr() {
        mID = UUID.randomUUID();
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getLinkToFullPost() {
        return mLink;
    }

    public void setLinkToFullPost(String linkToFullPost) {
        mLink = linkToFullPost;
    }

    public String getDescription() {
        return mDesc;
    }

    public void setDescription(String description) {
        mDesc = description;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        mCategory = category;
    }

    public String getPubDate() {
        return mPubDate;
    }

    public void setPubDate(String pubDate) {
        mPubDate = pubDate;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public Bitmap getImage() {
        return mImage;
    }

    public void setImage(Bitmap image) {
        mImage = image;
    }

    public UUID getUUID() {
        return mID;
    }

    public String getCreater() {
        return mCreater;
    }

    public void setCreater(String description) {
        mCreater = description;
    }
}
