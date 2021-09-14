package com.xyxy.coversafetyapp.entity.request;


import java.io.Serializable;

/**
 * @author phcbest
 * @email phcbest2017@outlook.com
 * @date 2021-08-17 17:30:43
 */

public class CoverErrorEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;
    /**
     * 逻辑外键
     */
    private Integer exid;
    /**
     * uid
     */
    private String uid;
    /**
     * 井盖的id
     */
    private String coverUid;
    /**
     * 错误的描述
     */
    private String errorDepict;
    /**
     * 错误的时间
     */
    private Long errorTime;
    /**
     * 错误的图片
     */
    private String errorImage;
    /**
     * 该条错误的状态，用于逻辑删除
     */
    private Integer errorStatus;

    /**
     * 错误的等级，1-5 ，数字越大等级越高
     */
    private Integer errorLevel;

    @Override
    public String toString() {
        return "CoverErrorEntity{" +
                "id=" + id +
                ", exid=" + exid +
                ", uid='" + uid + '\'' +
                ", coverUid='" + coverUid + '\'' +
                ", errorDepict='" + errorDepict + '\'' +
                ", errorTime=" + errorTime +
                ", errorImage='" + errorImage + '\'' +
                ", errorStatus=" + errorStatus +
                ", errorLevel=" + errorLevel +
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

    public Long getErrorTime() {
        return errorTime;
    }

    public void setErrorTime(Long errorTime) {
        this.errorTime = errorTime;
    }

    public String getErrorImage() {
        return errorImage;
    }

    public void setErrorImage(String errorImage) {
        this.errorImage = errorImage;
    }

    public Integer getErrorStatus() {
        return errorStatus;
    }

    public void setErrorStatus(Integer errorStatus) {
        this.errorStatus = errorStatus;
    }

    public Integer getErrorLevel() {
        return errorLevel;
    }

    public void setErrorLevel(Integer errorLevel) {
        this.errorLevel = errorLevel;
    }
}
