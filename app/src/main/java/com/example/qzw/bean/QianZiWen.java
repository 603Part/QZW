package com.example.qzw.bean;

import java.util.List;

public class QianZiWen {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * content : 天地玄黄，宇宙洪荒。日月盈昃，辰宿列张。
         * translate : 天是青黑色的，地是黄色的，宇宙形成于混沌蒙昧的状态中。太阳正了又斜，月亮圆了又缺，星辰布满在无边的太空中。
         * tips : ①洪荒：无边无际、混沌蒙昧的状态，指远古时代。洪，洪大、辽阔。荒，空洞、荒芜。②盈昃（zè）：盈：月光圆满。昃（zè）：太阳西斜。③宿（xiù）〈古〉中国天文学家将天空中某些星的集合体叫做“宿”。
         */

        private String content;
        private String translate;
        private String tips;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTranslate() {
            return translate;
        }

        public void setTranslate(String translate) {
            this.translate = translate;
        }

        public String getTips() {
            return tips;
        }

        public void setTips(String tips) {
            this.tips = tips;
        }
    }
}
