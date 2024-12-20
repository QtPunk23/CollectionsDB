package gitling.studio.app.DataLayer;

public class Disc {
    private long discId;
    private String title;
    private String description;
    private String mediaTypeName;
    private String categoryName;

    public Disc(long discId, String title, String description, String mediaTypeName, String categoryName) {
        this.discId = discId;
        this.title = title;
        this.description = description;
        this.mediaTypeName = mediaTypeName;
        this.categoryName = categoryName;
    }

    public long getDiscId() {
        return discId;
    }

    public void setDiscId(long discId) {
        this.discId = discId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMediaTypeName() {
        return mediaTypeName;
    }

    public void setMediaTypeName(String mediaTypeName) {
        this.mediaTypeName = mediaTypeName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "DiscId: " + discId + ", Title: " + title + ", MediaType: " + mediaTypeName + ", Category: " + categoryName + ", Description: " + description;
    }
}
