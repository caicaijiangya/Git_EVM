package com.bluekjg.wxapp.model.wxApp20;

import java.util.List;

public class WxQuestionDto {

    private String title;
    private String type;
    private Integer page;
    private String icon;
    private String name;
    private String note;
    private String value;
    private String sort;
    private String comment;
    private Integer score;
    private boolean warn;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setItem(List<WxQuestionDetailDto> item) {
        this.item = item;
    }

    private List<WxQuestionDetailDto> item;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean getWarn() {
        return warn;
    }

    public void setWarn(boolean warn) {
        this.warn = warn;
    }

    public List<WxQuestionDetailDto> getItem() {
        return item;
    }

    public void setDetailList(List<WxQuestionDetailDto> item) {
        this.item = item;
    }



}
