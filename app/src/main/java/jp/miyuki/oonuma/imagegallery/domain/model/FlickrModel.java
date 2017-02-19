package jp.miyuki.oonuma.imagegallery.domain.model;

/**
 * Json feed as Flickr
 */
public class FlickrModel {

    private String title;
    private String link;
    private String id;
    private String published;
    private String updated;
    private String dateTaken;
    private String description;
    private String author;
    private String authorId;
    private String tags;

    public FlickrModel(
            String title,
            String link,
            String id,
            String published,
            String updated,
            String dateTaken,
            String description,
            String author,
            String authorId,
            String tags) {
        this.title = title;
        this.link = link;
        this.id = id;
        this.published = published;
        this.updated = updated;
        this.dateTaken = dateTaken;
        this.description = description;
        this.author = author;
        this.authorId = authorId;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getId() {
        return id;
    }

    public String getPublished() {
        return published;
    }

    public String getUpdated() {
        return updated;
    }

    public String getDateTaken() {
        return dateTaken;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getTags() {
        return tags;
    }
}
