package com.bluekjg.wxapp.utils;

import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.service.IWxIndexService;

import java.util.List;

public class GetRecommendUtil {

    private IWxIndexService indexService;
    //个性推荐
    public String getRecommendByDimension(String dimension, PagingDto page){
        // todo 设置问题严重程度
        String severity = "普通";//问题严重程度
        if (severity.equals("普通")){
            Integer orderCount = indexService.queryOrderCountByOpenId(page);
            if (orderCount!=0){
                //看订单历史中有没有这个商品
                List<Integer> goodsIdList = indexService.queryGoodsByOpenId(page);
                Integer goodsId = 0;
                for (int i = 0;i<goodsIdList.size();i++){
                    goodsId = goodsIdList.get(i);
                    if (goodsId == 1){
                        //用过1
                        if (goodsId == 2){
                            //用过2
                            if (goodsId == 3){
                                //用过1X
                                if (goodsId == 4){
                                    //用过2x
                                    return "周期1推荐1x，周期2推荐2x";
                                }else {
                                    return "周期1推荐2x,周期2推荐1x";
                                }
                            }else {
                                return "周期1推荐1x，周期2推荐2x";
                            }
                        }else {
                            return "周期1推荐2,周期2推荐1x";
                        }

                    }else{
                        return "周期1推荐1，周期2推荐2";
                    }
                }
            }else {
                return "周期1推荐1，周期2推荐2";
            }

        }else if (severity.equals("严重")){
            if ("维度里面包含AAEFGH" !=null){
                Integer orderCount = indexService.queryOrderCountByOpenId(page);
                if (orderCount!=0){
                    //看订单历史中有没有这个商品
                    List<Integer> goodsIdList = indexService.queryGoodsByOpenId(page);
                    Integer goodsId;
                    for (int i = 0;i<goodsIdList.size();i++){
                        goodsId = goodsIdList.get(i);
                        if (goodsId == 1){
                            //用过1
                            if (goodsId == 2){
                                //用过2
                                if (goodsId == 3){
                                    //用过1X
                                    if (goodsId == 4){
                                        //用过2x
                                        return "周期1推荐1x，周期2推荐2x";
                                    }else {
                                        return "周期1推荐2x,周期2推荐1x";
                                    }
                                }else {
                                    return "周期1推荐1x，周期2推荐2x";
                                }
                            }else {
                                return "周期1推荐2,周期2推荐1x";
                            }

                        }else{
                            return "周期1推荐1，周期2推荐2";
                        }
                    }
                }else {
                    return "周期1推荐1x，周期2推荐2x";
                }
            }else{
                Integer orderCount = indexService.queryOrderCountByOpenId(page);
                if (orderCount!=0){
                    //看订单历史中有没有这个商品
                    List<Integer> goodsIdList = indexService.queryGoodsByOpenId(page);
                    Integer goodsId;
                    for (int i = 0;i<goodsIdList.size();i++){
                        goodsId = goodsIdList.get(i);
                        if (goodsId == 3){
                            //用过1X
                            if (goodsId == 4){
                                //用过2x
                                return "周期1推荐1x，周期2推荐2x";
                            }else {
                                return "周期1推荐2x,周期2推荐1x";
                            }
                        }else {
                            return "周期1推荐1x，周期2推荐2x";
                        }
                    }
                }else {
                    return "周期1推荐1，周期2推荐2";
                }
            }
        }
        return null;
    }
}
