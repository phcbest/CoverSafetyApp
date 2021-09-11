package com.xyxy.coversafetyapp.entity.respond;

import java.util.List;

public class AllCoverBean {

    /**
     * msg : success
     * code : 0
     * page : {"totalCount":0,"pageSize":10,"totalPage":0,"currPage":1,"list":[{"id":2,"exid":1,"uid":"9bfa28c3-d47f-477c-9acf-58930b0c0c2f","deleteStatus":0,"coverRoad":"新余学院","coverSensorStatus":0,"coverLoastime":1629277311159,"coverLongLat":"115.00190875347438-25.853637790183306"},{"id":3,"exid":2,"uid":"d04b71cb-b313-4610-8a41-90cfe4e4d1fa","deleteStatus":0,"coverRoad":"xyxy","coverSensorStatus":0,"coverLoastime":1629333999312,"coverLongLat":"115.00190875347438-27.853637790183306"},{"id":4,"exid":3,"uid":"2c35bbb0-8cf9-4adb-9de3-0f47298dca19","deleteStatus":0,"coverRoad":"xyxy","coverSensorStatus":0,"coverLoastime":1629334121775,"coverLongLat":"114.00190875347438-28.853637790183306"},{"id":5,"exid":4,"uid":"fe27f705-133b-446b-9f37-a75f72e73608","deleteStatus":0,"coverRoad":"xyxy","coverSensorStatus":0,"coverLoastime":1629882458696,"coverLongLat":"115.00190875347438-26.853637790183306"}]}
     */


    private String msg;
    private int code;
    private PageBean page;

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

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public static class PageBean {
        /**
         * totalCount : 0
         * pageSize : 10
         * totalPage : 0
         * currPage : 1
         * list : [{"id":2,"exid":1,"uid":"9bfa28c3-d47f-477c-9acf-58930b0c0c2f","deleteStatus":0,"coverRoad":"新余学院","coverSensorStatus":0,"coverLoastime":1629277311159,"coverLongLat":"115.00190875347438-25.853637790183306"},{"id":3,"exid":2,"uid":"d04b71cb-b313-4610-8a41-90cfe4e4d1fa","deleteStatus":0,"coverRoad":"xyxy","coverSensorStatus":0,"coverLoastime":1629333999312,"coverLongLat":"115.00190875347438-27.853637790183306"},{"id":4,"exid":3,"uid":"2c35bbb0-8cf9-4adb-9de3-0f47298dca19","deleteStatus":0,"coverRoad":"xyxy","coverSensorStatus":0,"coverLoastime":1629334121775,"coverLongLat":"114.00190875347438-28.853637790183306"},{"id":5,"exid":4,"uid":"fe27f705-133b-446b-9f37-a75f72e73608","deleteStatus":0,"coverRoad":"xyxy","coverSensorStatus":0,"coverLoastime":1629882458696,"coverLongLat":"115.00190875347438-26.853637790183306"}]
         */

        private int totalCount;
        private int pageSize;
        private int totalPage;
        private int currPage;
        private List<ListBean> list;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getCurrPage() {
            return currPage;
        }

        public void setCurrPage(int currPage) {
            this.currPage = currPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 2
             * exid : 1
             * uid : 9bfa28c3-d47f-477c-9acf-58930b0c0c2f
             * deleteStatus : 0
             * coverRoad : 新余学院
             * coverSensorStatus : 0
             * coverLoastime : 1629277311159
             * coverLongLat : 115.00190875347438-25.853637790183306
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
}
