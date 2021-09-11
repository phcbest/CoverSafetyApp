package com.xyxy.coversafetyapp.entity.request;

import java.io.Serializable;

/**
 * @author phcbest
 * @email phcbest2017@outlook.com
 * @date 2021-08-17 17:30:43
 */

public class CoverInfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;
    /**
     * 外键
     */
    private Integer exid;
    /**
     * 唯一id
     */
    private String uid;
    /**
     * 逻辑删除
     */
    private Integer deleteStatus;
    /**
     * 道路位置
     */
    private String coverRoad;
    /**
     * 传感器状态(倾斜角度)
     */
    private Integer coverSensorStatus;
    /**
     * 上次维护时间
     */
    private Long coverLoastime;
    /**
     * 井盖的经纬度
     */
    private String coverLongLat;

    @Override
    public String toString() {
        return "CoverInfoEntity{" +
                "id=" + id +
                ", exid=" + exid +
                ", uid='" + uid + '\'' +
                ", deleteStatus=" + deleteStatus +
                ", coverRoad='" + coverRoad + '\'' +
                ", coverSensorStatus=" + coverSensorStatus +
                ", coverLoastime=" + coverLoastime +
                ", coverLongLat='" + coverLongLat + '\'' +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExid() {
        return exid;
    }

    public void setExid(Integer exid) {
        this.exid = exid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public String getCoverRoad() {
        return coverRoad;
    }

    public void setCoverRoad(String coverRoad) {
        this.coverRoad = coverRoad;
    }

    public Integer getCoverSensorStatus() {
        return coverSensorStatus;
    }

    public void setCoverSensorStatus(Integer coverSensorStatus) {
        this.coverSensorStatus = coverSensorStatus;
    }

    public Long getCoverLoastime() {
        return coverLoastime;
    }

    public void setCoverLoastime(Long coverLoastime) {
        this.coverLoastime = coverLoastime;
    }

    public String getCoverLongLat() {
        return coverLongLat;
    }

    public void setCoverLongLat(String coverLongLat) {
        this.coverLongLat = coverLongLat;
    }
}
