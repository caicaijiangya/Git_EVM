package com.bluekjg.wxapp.model.wxApp20;

import java.util.List;

public class WxSkinRecommend {
    private String type;//1:个性化定制方案。0：基础定制方案
    private String name;
    private boolean selected;
    private List<WxGoodsJCTJ> itemsJC;
    private List<WxGoodsGXTJ> itemsGX;



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public List<WxGoodsJCTJ> getItemsJC() {
        return itemsJC;
    }

    public void setItemsJC(List<WxGoodsJCTJ> itemsJC) {
        this.itemsJC = itemsJC;
    }

    public List<WxGoodsGXTJ> getItemsGX() {
        return itemsGX;
    }

    public void setItemsGX(List<WxGoodsGXTJ> itemsGX) {
        this.itemsGX = itemsGX;
    }
}
