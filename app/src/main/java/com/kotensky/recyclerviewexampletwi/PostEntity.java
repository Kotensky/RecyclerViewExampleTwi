package com.kotensky.recyclerviewexampletwi;

import java.io.Serializable;

public class PostEntity implements Serializable {

    private String authorName;
    private String text;
    private String avatarLink;


    public PostEntity(String authorName, String text, String avatarLink) {
        this.authorName = authorName;
        this.text = text;
        this.avatarLink = avatarLink;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAvatarLink() {
        return avatarLink;
    }

    public void setAvatarLink(String avatarLink) {
        this.avatarLink = avatarLink;
    }
}
