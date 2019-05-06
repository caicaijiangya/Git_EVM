package com.bluekjg.wxapp.model.wxApp20;

import com.bluekjg.wxapp.model.WxGoods;

import java.util.List;

public class ShoppingCart {

    private String openId;


    private List<WxGoods> goodsList;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
    public List<WxGoods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<WxGoods> goodsList) {
        this.goodsList = goodsList;
    }



}
