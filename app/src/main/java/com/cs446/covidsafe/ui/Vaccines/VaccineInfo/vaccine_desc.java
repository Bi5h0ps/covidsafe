package com.cs446.covidsafe.ui.Vaccines.VaccineInfo;

public class vaccine_desc {
    private String v_type, v_desc;
    private Boolean expandable;

    public Boolean getExpandable() {
        return expandable;
    }

    public void setExpandable(Boolean expandable) {
        this.expandable = expandable;
    }

    public vaccine_desc(String v_type, String v_desc) {
        this.v_type = v_type;
        this.v_desc = v_desc;
        this.expandable = false;
    }

    public String getV_type() {
        return v_type;
    }

    public String getV_desc() {
        return v_desc;
    }

    public void setV_type(String v_type) {
        this.v_type = v_type;
    }

    public void setV_desc(String v_desc) {
        this.v_desc = v_desc;
    }

    @Override
    public String toString() {
        return "vaccine_desc{" +
                "v_type='" + v_type + '\'' +
                ", v_desc='" + v_desc + '\'' +
                '}';
    }
}
