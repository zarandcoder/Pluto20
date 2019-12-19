package de.hawlandshut.pluto20.model;

import com.google.firebase.database.DataSnapshot;

public class Post {
    private String uuid;
    private String author;
    private String title;
    private String body;
    private long timestamp;
    private String firebaseKey;

    public Post() {}

    public Post(String uuid, String author, String title, String body, long timestamp, String firebaseKey) {
        this.uuid = uuid;
        this.author = author;
        this.title = title;
        this.body = body;
        this.timestamp = timestamp;
        this.firebaseKey = firebaseKey;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getFirebaseKey() { return firebaseKey; }


    public static Post fromSnapShot(DataSnapshot dataSnapshot) {
        String uid = (String) dataSnapshot.child("uid").getValue();
        String title = (String) dataSnapshot.child("title").getValue();
        String body = (String) dataSnapshot.child("body").getValue();
        String author = (String) dataSnapshot.child("author").getValue();

        Post p = new Post(uid, author, title, body, 0, dataSnapshot.getKey());

        return p;
    }
}
