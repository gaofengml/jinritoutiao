package entity;

import java.util.Date;

public class Admin {
    private Integer admin_id;

    private String admin_name;

    private String admin_password;

    private String email;

    private String status;

    private Date entry_time;

    public Integer getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Integer admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name == null ? null : admin_name.trim();
    }

    public String getAdmin_password() {
        return admin_password;
    }

    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password == null ? null : admin_password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getEntry_time() {
        return entry_time;
    }

    public void setEntry_time(Date entry_time) {
        this.entry_time = entry_time;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", admin_id=").append(admin_id);
        sb.append(", admin_name=").append(admin_name);
        sb.append(", admin_password=").append(admin_password);
        sb.append(", email=").append(email);
        sb.append(", status=").append(status);
        sb.append(", entry_time=").append(entry_time);
        sb.append("]");
        return sb.toString();
    }
}