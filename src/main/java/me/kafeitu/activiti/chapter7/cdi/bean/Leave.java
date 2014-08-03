package me.kafeitu.activiti.chapter7.cdi.bean;

import java.io.Serializable;

/**
 * @author: Henry Yan
 */
public class Leave implements Serializable {

    private String startTime;
    private String endTime;
    private String reason;

    private boolean deptLeaderApproved;
    private boolean hrApproved;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean isDeptLeaderApproved() {
        return deptLeaderApproved;
    }

    public void setDeptLeaderApproved(boolean deptLeaderApproved) {
        this.deptLeaderApproved = deptLeaderApproved;
    }

    public boolean isHrApproved() {
        return hrApproved;
    }

    public void setHrApproved(boolean hrApproved) {
        this.hrApproved = hrApproved;
    }
}
