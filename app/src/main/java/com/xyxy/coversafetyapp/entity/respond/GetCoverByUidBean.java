package com.xyxy.coversafetyapp.entity.respond;

public class GetCoverByUidBean {

    /**
     * msg : success
     * code : 0
     * coverInfo : {"id":2,"exid":1,"uid":"9bfa28c3-d47f-477c-9acf-58930b0c0c2f","deleteStatus":0,"coverRoad":"新余xyxyxyx","coverSensorStatus":0,"coverLoastime":1631331348161,"coverLongLat":"114.99968262848277-27.855947370214256"}
     */

    private String msg;
    private int code;
    private CoverInfoBean coverInfo;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public CoverInfoBean getCoverInfo() {
        return coverInfo;
    }

    public void setCoverInfo(CoverInfoBean coverInfo) {
        this.coverInfo = coverInfo;
    }

    public static class CoverInfoBean {
        /**
         * id : 2
         * exid : 1
         * uid : 9bfa28c3-d47f-477c-9acf-58930b0c0c2f
         * deleteStatus : 0
         * coverRoad : 新余xyxyxyx
         * coverSensorStatus : 0
         * coverLoastime : 1631331348161
         * coverLongLat : 114.99968262848277-27.855947370214256
         */

        private int id;
        private int exid;
        private String uid;
        private int deleteStatus;
        private String coverRoad;
        private int coverSensorStatus;
        private long coverLoastime;
        private String coverLongLat;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getExid() {
            return exid;
        }

        public void setExid(int exid) {
            this.exid = exid;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public int getDeleteStatus() {
            return deleteStatus;
        }

        public void setDeleteStatus(int deleteStatus) {
            this.deleteStatus = deleteStatus;
        }

        public String getCoverRoad() {
            return coverRoad;
        }

        public void setCoverRoad(String coverRoad) {
            this.coverRoad = coverRoad;
        }

        public int getCoverSensorStatus() {
            return coverSensorStatus;
        }

        public void setCoverSensorStatus(int coverSensorStatus) {
            this.coverSensorStatus = coverSensorStatus;
        }

        public long getCoverLoastime() {
            return coverLoastime;
        }

        public void setCoverLoastime(long coverLoastime) {
            this.coverLoastime = coverLoastime;
        }

        public String getCoverLongLat() {
            return coverLongLat;
        }

        public void setCoverLongLat(String coverLongLat) {
            this.coverLongLat = coverLongLat;
        }
    }
}
