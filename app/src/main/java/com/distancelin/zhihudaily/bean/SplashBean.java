package com.distancelin.zhihudaily.bean;

import java.util.List;

/**
 * Created by distancelin on 2017/5/14.
 */

public class SplashBean {

    private List<CreativesBean> creatives;

    public List<CreativesBean> getCreatives() {
        return creatives;
    }

    public void setCreatives(List<CreativesBean> creatives) {
        this.creatives = creatives;
    }

    public static class CreativesBean {
        /**
         * url : https://pic2.zhimg.com/v2-337d33a455bd5512f8445e1acbbbb15d.jpg
         * start_time : 1494743024
         * impression_tracks : ["https://sugar.zhihu.com/track?vs=1&ai=4301&ut=&cg=2&ts=1494743024.71&si=57ef1fe928d047d3880246dbf01d39b6&lu=0&hn=ad-engine.ad-engine.ccba6e08&at=impression&pf=PC&az=11&sg=ce65be60884d16d4b22ced4d1e276cca"]
         * type : 0
         * id : 4301
         */

        private String url;
        private int start_time;
        private int type;
        private String id;
        private List<String> impression_tracks;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getStart_time() {
            return start_time;
        }

        public void setStart_time(int start_time) {
            this.start_time = start_time;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<String> getImpression_tracks() {
            return impression_tracks;
        }

        public void setImpression_tracks(List<String> impression_tracks) {
            this.impression_tracks = impression_tracks;
        }
    }
}
