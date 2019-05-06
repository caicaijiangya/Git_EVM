package com.bluekjg.wxapp.model.wxApp20;

import java.util.List;

public class WxGoodsGXTJ {
    private String id;
    private String name;

    private List<WxGoodsJCTJ> item;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WxGoodsJCTJ> getItem() {
        return item;
    }
    public void setItem(List<WxGoodsJCTJ> item) {
        this.item = item;
    }

}
