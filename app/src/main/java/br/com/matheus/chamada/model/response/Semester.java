package br.com.matheus.chamada.model.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Semester implements Serializable {

    @SerializedName("code")
    private String code;

    @SerializedName("startDate")
    private Date startDate;

    @SerializedName("endDate")
    private Date endDate;

    @SerializedName("schoolDays")
    private List<Date> schoolDays;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Date> getSchoolDays() {
        return schoolDays;
    }

    public void setSchoolDays(List<Date> schoolDays) {
        this.schoolDays = schoolDays;
    }
}
