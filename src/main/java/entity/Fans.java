package entity;

import java.util.Date;

public class Fans {
    private Integer id;

    private Integer admin_id;

    private Integer fans_id;

    private String fans_name;

    private String fans_headImgUrl;

    private Integer fans_status;

    private Date focus_time;

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

    public Integer getFans_id() {
        return fans_id;
    }

    public void setFans_id(Integer fans_id) {
        this.fans_id = fans_id;
    }

    public String getFans_name() {
        return fans_name;
    }

    public void setFans_name(String fans_name) {
        this.fans_name = fans_name == null ? null : fans_name.trim();
    }

    public String getFans_headImgUrl() {
        return fans_headImgUrl;
    }

    public void setFans_headImgUrl(String fans_headImgUrl) {
        this.fans_headImgUrl = fans_headImgUrl == null ? null : fans_headImgUrl.trim();
    }

    public Integer getFans_status() {
        return fans_status;
    }

    public void setFans_status(Integer fans_status) {
        this.fans_status = fans_status;
    }

    public Date getFocus_time() {
        return focus_time;
    }

    public void setFocus_time(Date focus_time) {
        this.focus_time = focus_time;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", admin_id=").append(admin_id);
        sb.append(", fans_id=").append(fans_id);
        sb.append(", fans_name=").append(fans_name);
        sb.append(", fans_headImgUrl=").append(fans_headImgUrl);
        sb.append(", fans_status=").append(fans_status);
        sb.append(", focus_time=").append(focus_time);
        sb.append("]");
        return sb.toString();
    }
}