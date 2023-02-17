package entity;

public class ReadFrom {
    private Integer id;

    private String tuijian;

    private String pindao;

    private String qita;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTuijian() {
        return tuijian;
    }

    public void setTuijian(String tuijian) {
        this.tuijian = tuijian == null ? null : tuijian.trim();
    }

    public String getPindao() {
        return pindao;
    }

    public void setPindao(String pindao) {
        this.pindao = pindao == null ? null : pindao.trim();
    }

    public String getQita() {
        return qita;
    }

    public void setQita(String qita) {
        this.qita = qita == null ? null : qita.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", tuijian=").append(tuijian);
        sb.append(", pindao=").append(pindao);
        sb.append(", qita=").append(qita);
        sb.append("]");
        return sb.toString();
    }
}