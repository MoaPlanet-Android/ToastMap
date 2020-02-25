package com.galhope.toastmap;

import com.google.gson.annotations.SerializedName;

public class ResModel {

    @SerializedName("route")
    public RouteModel reoutModel;

    public class RouteModel {

        @SerializedName("data")
        public DataModel dataModel;

    }

    public class DataModel {

        // 소요시간 - 초
        @SerializedName("spend_time")
        public int time;

        @SerializedName("distance")
        public int distance;

    }
}
