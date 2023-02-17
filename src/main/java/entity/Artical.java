package entity;

public class Artical {
    private Integer id;

    private Integer admin_id;

    private String articalTitle;

    private Integer readNum;

    private Integer fansReadNum;

    private Integer collectNum;

    private Integer upvoteNum;

    private Integer fansUpvoteNum;

    private String sendTime;

    private String imageUrl;

    private Integer pd_id;

    private String status;

    private String articalText;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Integer admin_id) {
        this.admin_id = admin_id;
    }

    public String getArticalTitle() {
        return articalTitle;
    }

    public void setArticalTitle(String articalTitle) {
        this.articalTitle = articalTitle == null ? null : articalTitle.trim();
    }

    public Integer getReadNum() {
        return readNum;
    }

    public void setReadNum(Integer readNum) {
        this.readNum = readNum;
    }

    public Integer getFansReadNum() {
        return fansReadNum;
    }

    public void setFansReadNum(Integer fansReadNum) {
        this.fansReadNum = fansReadNum;
    }

    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }

    public Integer getUpvoteNum() {
        return upvoteNum;
    }

    public void setUpvoteNum(Integer upvoteNum) {
        this.upvoteNum = upvoteNum;
    }

    public Integer getFansUpvoteNum() {
        return fansUpvoteNum;
    }

    public void setFansUpvoteNum(Integer fansUpvoteNum) {
        this.fansUpvoteNum = fansUpvoteNum;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime == null ? null : sendTime.trim();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public Integer getPd_id() {
        return pd_id;
    }

    public void setPd_id(Integer pd_id) {
        this.pd_id = pd_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getArticalText() {
        return articalText;
    }

    public void setArticalText(String articalText) {
        this.articalText = articalText == null ? null : articalText.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", admin_id=").append(admin_id);
        sb.append(", articalTitle=").append(articalTitle);
        sb.append(", readNum=").append(readNum);
        sb.append(", fansReadNum=").append(fansReadNum);
        sb.append(", collectNum=").append(collectNum);
        sb.append(", upvoteNum=").append(upvoteNum);
        sb.append(", fansUpvoteNum=").append(fansUpvoteNum);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", imageUrl=").append(imageUrl);
        sb.append(", pd_id=").append(pd_id);
        sb.append(", status=").append(status);
        sb.append(", articalText=").append(articalText);
        sb.append("]");
        return sb.toString();
    }
}