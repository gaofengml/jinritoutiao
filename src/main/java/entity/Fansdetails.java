 package entity;

public class Fansdetails {
    private Integer id;

    private Integer admin_id;

    private String time;

    private Integer fansNum;

    private Integer fansReadNum;

    private String fansMoney;

    private Integer delfocus;

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public Integer getFansNum() {
        return fansNum;
    }

    public void setFansNum(Integer fansNum) {
        this.fansNum = fansNum;
    }

    public Integer getFansReadNum() {
        return fansReadNum;
    }

    public void setFansReadNum(Integer fansReadNum) {
        this.fansReadNum = fansReadNum;
    }

    public String getFansMoney() {
        return fansMoney;
    }

    public void setFansMoney(String fansMoney) {
        this.fansMoney = fansMoney == null ? null : fansMoney.trim();
    }

    public Integer getDelfocus() {
        return delfocus;
    }

    public void setDelfocus(Integer delfocus) {
        this.delfocus = delfocus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", admin_id=").append(admin_id);
        sb.append(", time=").append(time);
        sb.append(", fansNum=").append(fansNum);
        sb.append(", fansReadNum=").append(fansReadNum);
        sb.append(", fansMoney=").append(fansMoney);
        sb.append(", delfocus=").append(delfocus);
        sb.append("]");
        return sb.toString();
    }
}