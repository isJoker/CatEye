package com.wjc.cateye.moive.bean;

import java.util.List;

/**
 * Created by ${万嘉诚} on 2016/12/1.
 * WeChat：wjc398556712
 * Function：全球电影奖Bean类
 */

public class MoiveAwardsBean {

    /**
     * festivals : [{"festivalId":117,"festivalName":"奥斯卡金像奖"},{"festivalId":116,"festivalName":"戛纳电影节"},{"festivalId":115,"festivalName":"威尼斯电影节"},{"festivalId":114,"festivalName":"柏林国际电影节"},{"festivalId":113,"festivalName":"金球奖"},{"festivalId":112,"festivalName":"香港金像奖"},{"festivalId":111,"festivalName":"台湾金马奖"},{"festivalId":110,"festivalName":"东京国际电影节"},{"festivalId":83,"festivalName":"中国电影金鸡奖"},{"festivalId":109,"festivalName":"釜山国际电影节"}]
     * region : 热门
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String region;
        /**
         * festivalId : 117
         * festivalName : 奥斯卡金像奖
         */

        private List<FestivalsBean> festivals;

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public List<FestivalsBean> getFestivals() {
            return festivals;
        }

        public void setFestivals(List<FestivalsBean> festivals) {
            this.festivals = festivals;
        }

        public static class FestivalsBean {
            private int festivalId;
            private String festivalName;

            public int getFestivalId() {
                return festivalId;
            }

            public void setFestivalId(int festivalId) {
                this.festivalId = festivalId;
            }

            public String getFestivalName() {
                return festivalName;
            }

            public void setFestivalName(String festivalName) {
                this.festivalName = festivalName;
            }
        }
    }
}
