package de.hawlandshut.pluto20.model;

public class Post {
    private String uuid;
    private String author;
    private String title;
    private String body;
    private long timestamp;

    public Post() {}

    public Post(String uuid, String author, String title, String body, long timestamp) {
        this.uuid = uuid;
        this.author = author;
        this.title = title;
        this.body = body;
        this.timestamp = timestamp;
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
}
