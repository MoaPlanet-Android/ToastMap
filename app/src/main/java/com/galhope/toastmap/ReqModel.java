package com.galhope.toastmap;

import com.google.gson.annotations.SerializedName;

public class ReqModel {

    @SerializedName("appKey")
    private String appKey = "eNk4hnsnzctcFy8T";

    @SerializedName("startX")
    private String startX;

    @SerializedName("startY")
    private String startY;

    @SerializedName("endX")
    private String endX;

    @SerializedName("endY")
    private String endY;

    @SerializedName("option")
    private String option = "motorcycle";

    @SerializedName("coordType")
    private String coordType = "wgs84";

    public void setStartX(String startX) {
        this.startX = startX;
    }

    public void setStartY(String startY) {
        this.startY = startY;
    }

    public void setEndX(String endX) {
        this.endX = endX;
    }

    public void setEndY(String endY) {
        this.endY = endY;
    }

    public String getAppKey() {
        return appKey;
    }

    public String getCoordType() {
        return coordType;
    }

    public String getEndX() {
        return endX;
    }

    public String getEndY() {
        return endY;
    }

    public String getOption() {
        return option;
    }

    public String getStartX() {
        return startX;
    }

    public String getStartY() {
        return startY;
    }

}
