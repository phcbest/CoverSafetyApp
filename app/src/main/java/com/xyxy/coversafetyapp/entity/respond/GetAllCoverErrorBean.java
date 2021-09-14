package com.xyxy.coversafetyapp.entity.respond;

import java.util.List;

public class GetAllCoverErrorBean {


    /**
     * msg : success
     * code : 0
     * page : {"records":[{"id":4,"exid":1,"uid":"37b562f5-7d28-427b-b168-63629b5ffced","coverUid":"2c35bbb0-8cf9-4adb-9de3-0f47298dca19","errorDepict":"asdasdasd","errorTime":1631550597346,"errorImage":"string","errorStatus":0},{"id":5,"exid":2,"uid":"d744bcc9-25ac-4f33-ae24-d269533ee2b2","coverUid":"2c35bbb0-8cf9-4adb-9de3-0f47298dca19","errorDepict":"asdasdasd","errorTime":1631550610224,"errorImage":"string","errorStatus":0}],"total":0,"size":1,"current":1,"orders":[],"hitCount":false,"searchCount":true,"pages":0}
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
         * records : [{"id":4,"exid":1,"uid":"37b562f5-7d28-427b-b168-63629b5ffced","coverUid":"2c35bbb0-8cf9-4adb-9de3-0f47298dca19","errorDepict":"asdasdasd","errorTime":1631550597346,"errorImage":"string","errorStatus":0},{"id":5,"exid":2,"uid":"d744bcc9-25ac-4f33-ae24-d269533ee2b2","coverUid":"2c35bbb0-8cf9-4adb-9de3-0f47298dca19","errorDepict":"asdasdasd","errorTime":1631550610224,"errorImage":"string","errorStatus":0}]
         * total : 0
         * size : 1
         * current : 1
         * orders : []
         * hitCount : false
         * searchCount : true
         * pages : 0
         */

        private int total;
        private int size;
        private int current;
        private boolean hitCount;
        private boolean searchCount;
        private int pages;
        private List<RecordsBean> records;
        private List<?> orders;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }

        public boolean isHitCount() {
            return hitCount;
        }

        public void setHitCount(boolean hitCount) {
            this.hitCount = hitCount;
        }

        public boolean isSearchCount() {
            return searchCount;
        }

        public void setSearchCount(boolean searchCount) {
            this.searchCount = searchCount;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public List<RecordsBean> getRecords() {
            return records;
        }

        public void setRecords(List<RecordsBean> records) {
            this.records = records;
        }

        public List<?> getOrders() {
            return orders;
        }

        public void setOrders(List<?> orders) {
            this.orders = orders;
        }

        public static class RecordsBean {
            /**
             * id : 4
             * exid : 1
             * uid : 37b562f5-7d28-427b-b168-63629b5ffced
             * coverUid : 2c35bbb0-8cf9-4adb-9de3-0f47298dca19
             * errorDepict : asdasdasd
             * errorTime : 1631550597346
             * errorImage : string
             * errorStatus : 0
             */

            private int id;
            private int exid;
            private String uid;
            private String coverUid;
            private String errorDepict;
            private long errorTime;
            private String errorImage;
            private int errorStatus;
            private int errorLevel;

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

            public String getCoverUid() {
                return coverUid;
            }

            public void setCoverUid(String coverUid) {
                this.coverUid = coverUid;
            }

            public String getErrorDepict() {
                return errorDepict;
            }

            public void setErrorDepict(String errorDepict) {
                this.errorDepict = errorDepict;
            }

            public long getErrorTime() {
                return errorTime;
            }

            public void setErrorTime(long errorTime) {
                this.errorTime = errorTime;
            }

            public String getErrorImage() {
                return errorImage;
            }

            public void setErrorImage(String errorImage) {
                this.errorImage = errorImage;
            }

            public int getErrorStatus() {
                return errorStatus;
            }

            public void setErrorStatus(int errorStatus) {
                this.errorStatus = errorStatus;
            }

            public int getErrorLevel() {
                return errorLevel;
            }

            public void setErrorLevel(int errorLevel) {
                this.errorLevel = errorLevel;
            }
        }
    }
}
