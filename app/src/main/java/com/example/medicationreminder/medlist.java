
package com.example.medicationreminder;

public class medlist {
    String medName, medUnit, startDate, endDate,userId,medId,medTakenUnit;
    int numOfTimes;

    public medlist()
    {}
    public medlist(String medName, String medUnit, String startDate, String endDate, String userId, String medId, int numOfTimes) {
        this.medName = medName;
        this.medUnit = medUnit;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId=userId;
        this.medId=medId;
        this.numOfTimes = numOfTimes;
        // this.numoftimes = numoftimes;
    }

    public medlist(String medName, String medunit) {
        this.medName = medName;
        this.medUnit=medunit;
    }
    public medlist(String medName, int numOfTimes) {
        this.medName = medName;
        this.numOfTimes=numOfTimes;
    }

    public medlist(String enddate, String medname, String medunit, int numoftimes, String startdate) {
        this.medName = medName;
        this.medUnit = medUnit;
        this.startDate = startDate;
        this.endDate = endDate;

        // this.medId=medId;
        this.numOfTimes = numOfTimes;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public String getMedUnit() {
        return medUnit;
    }

    public void setMedUnit(String medUnit) {
        this.medUnit = medUnit;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMedId() {
        return medId;
    }

    public void setMedId(String medId) {
        this.medId = medId;
    }

    public String getMedTakenUnit() {
        return medTakenUnit;
    }

    public void setMedTakenUnit(String medTakenUnit) {
        this.medTakenUnit = medTakenUnit;
    }

    public int getNumOfTimes() {
        return numOfTimes;
    }

    public void setNumOfTimes(int numOfTimes) {
        this.numOfTimes = numOfTimes;
    }
    /*   public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }*/


  /*  public int getNumoftimes() {
        return numoftimes;
    }

    public void setNumoftimes(int numoftimes) {
        this.numoftimes = numoftimes;
    }*/

   /* public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }*/
}
