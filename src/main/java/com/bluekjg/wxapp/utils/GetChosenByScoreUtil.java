package com.bluekjg.wxapp.utils;

import org.apache.taglibs.standard.lang.jstl.ELEvaluator;

public class GetChosenByScoreUtil {
    public String getOptionByScore(Integer score){
        if (score == 95){
            return "E";
        }else if(score == 80){
            return "D";
        }else if(score == 60){
            return "C";
        }else if(score == 40){
            return "B";
        }else if(score == 10){
            return "A";
        }else {
            return null;
        }
    }
    //通过选项拿到得分
    public Integer getScoreByOption(String option){

        if (option.equals("A")){
            return 10;
        }else if (option.equals("B")){
            return 40;
        }else if (option.equals("C")){
            return 60;
        }else if (option.equals("D")){
            return 80;
        }else if (option.equals("E")){
            return 95;
        }else {
            return 0;
        }

    }
    //通过选项序列拿到选项

    public String getOptionBySeq(Integer seq) {
        if (seq == 1){
            return "A";
        }else if(seq == 2){
            return "B";
        }else if(seq == 3){
            return "C";
        }else if(seq == 4){
            return "D";
        }else if(seq == 5){
            return "E";
        }else {
            return "false";
        }

    }
}
