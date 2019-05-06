package com.bluekjg.wxapp.model.wxApp20;

public class WxAiRequest {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String openId;
    private Integer isDel;
    //创建日期
    private String createTime;
    //修改日期
    private String LastModifiedTime;
    //备注
    private String note;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastModifiedTime() {
        return LastModifiedTime;
    }

    public void setLastModifiedTime(String lastModifiedTime) {
        LastModifiedTime = lastModifiedTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "WxAiRequest{" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", isDel=" + isDel +
                ", createTime='" + createTime + '\'' +
                ", LastModifiedTime='" + LastModifiedTime + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
