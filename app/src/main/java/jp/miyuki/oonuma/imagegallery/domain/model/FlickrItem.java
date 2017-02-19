package jp.miyuki.oonuma.imagegallery.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 */
public class FlickrItem implements Parcelable{

    private String title;
    private String link;
    private String dateTaken;
    private String description;
    private String published;
    private String author;
    private String authorId;
    private String tags;
    private String pictureUrl;

    /**
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return link
     */
    public String getLink() {
        return link;
    }

    /**
     *
     * @param link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     *
     * @return dateTaken
     */
    public String getDateTaken() {
        return dateTaken;
    }

    /**
     *
     * @param dateTaken
     */
    public void setDateTaken(String dateTaken) {
        this.dateTaken = dateTaken;
    }

    /**
     *
     * @retur description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return published
     */
    public String getPublished() {
        return published;
    }

    /**
     *
     * @param published
     */
    public void setPublished(String published) {
        this.published = published;
    }

    /**
     *
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    /**
     *
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     *
     * @return authorId
     */
    public String getAuthorId() {
        return authorId;
    }

    /**
     *
     * @param authorId
     */
    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    /**
     *
     * @return
     *     The tags
     */
    public String getTags() {
        return tags;
    }

    /**
     *
     * @param tags
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.link);
        dest.writeString(this.dateTaken);
        dest.writeString(this.description);
        dest.writeString(this.published);
        dest.writeString(this.author);
        dest.writeString(this.authorId);
        dest.writeString(this.tags);
        dest.writeString(this.pictureUrl);
    }

    public FlickrItem() {
    }

    private FlickrItem(Parcel in) {
        this.title = in.readString();
        this.link = in.readString();
        this.dateTaken = in.readString();
        this.description = in.readString();
        this.published = in.readString();
        this.author = in.readString();
        this.authorId = in.readString();
        this.tags = in.readString();
    }

}
