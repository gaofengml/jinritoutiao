package entity;

public class UserPictures {
    private Integer id;

    private Integer all_id;

    private String imageUrl;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAll_id() {
        return all_id;
    }

    public void setAll_id(Integer all_id) {
        this.all_id = all_id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", all_id=").append(all_id);
        sb.append(", imageUrl=").append(imageUrl);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
}