package com.xyxy.coversafetyapp.entity.request;


import java.io.Serializable;

/**
 * @author phcbest
 * @email phcbest2017@outlook.com
 * @date 2021-08-17 17:30:43
 */
public class CoverLogEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * log内容
     */
    private String log;
    /**
     * 主键
     */
    private Integer id;
    /**
     * log上传时间
     */
    private Integer logTime;
    /**
     * 井盖uid
     */
    private String coverUid;

    @Override
    public String toString() {
        return "CoverLogEntity{" +
                "log='" + log + '\'' +
                ", id=" + id +
                ", logTime=" + logTime +
                ", coverUid='" + coverUid + '\'' +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLogTime() {
        return logTime;
    }

    public void setLogTime(Integer logTime) {
        this.logTime = logTime;
    }

    public String getCoverUid() {
        return coverUid;
    }

    public void setCoverUid(String coverUid) {
        this.coverUid = coverUid;
    }
}
