package com.xyxy.coversafetyapp.entity.respond;

import java.util.List;

public class AllCoverLogBean {

    /**
     * msg : success
     * code : 0
     * page : {"records":[{"log":"this is log","id":1,"logTime":1631330372894,"coverUid":"9bfa28c3-d47f-477c-9acf-58930b0c0c2f","deleteStatus":0},{"log":"this is log","id":2,"logTime":1631330885589,"coverUid":"9bfa28c3-d47f-477c-9acf-58930b0c0c2f","deleteStatus":0},{"log":"this is log","id":3,"logTime":1631339634434,"coverUid":"9bfa28c3-d47f-477c-9acf-58930b0c0c2f","deleteStatus":0}],"total":0,"size":123,"current":123,"orders":[],"hitCount":false,"searchCount":true,"pages":0}
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
         * records : [{"log":"this is log","id":1,"logTime":1631330372894,"coverUid":"9bfa28c3-d47f-477c-9acf-58930b0c0c2f","deleteStatus":0},{"log":"this is log","id":2,"logTime":1631330885589,"coverUid":"9bfa28c3-d47f-477c-9acf-58930b0c0c2f","deleteStatus":0},{"log":"this is log","id":3,"logTime":1631339634434,"coverUid":"9bfa28c3-d47f-477c-9acf-58930b0c0c2f","deleteStatus":0}]
         * total : 0
         * size : 123
         * current : 123
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
             * log : this is log
             * id : 1
             * logTime : 1631330372894
             * coverUid : 9bfa28c3-d47f-477c-9acf-58930b0c0c2f
             * deleteStatus : 0
             */

            private String log;
            private int id;
            private long logTime;
            private String coverUid;
            private int deleteStatus;

            public String getLog() {
                return log;
            }

            public void setLog(String log) {
                this.log = log;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public long getLogTime() {
                return logTime;
            }

            public void setLogTime(long logTime) {
                this.logTime = logTime;
            }

            public String getCoverUid() {
                return coverUid;
            }

            public void setCoverUid(String coverUid) {
                this.coverUid = coverUid;
            }

            public int getDeleteStatus() {
                return deleteStatus;
            }

            public void setDeleteStatus(int deleteStatus) {
                this.deleteStatus = deleteStatus;
            }
        }
    }
}
