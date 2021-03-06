package com.example.thomas.mygames.bean.custom;

import java.util.List;


public class Festival {

    /**
     * classid : 561
     * name : 场景
     * parentid : 0
     * list : [{"classid":"574","name":"春节","parentid":"561"},{"classid":"575","name":"情人节","parentid":"561"},{"classid":"576","name":"元宵节","parentid":"561"},{"classid":"577","name":"二月二","parentid":"561"},{"classid":"578","name":"复活节","parentid":"561"},{"classid":"579","name":"愚人节","parentid":"561"},{"classid":"580","name":"寒食节","parentid":"561"},{"classid":"581","name":"清明节","parentid":"561"},{"classid":"582","name":"三月三","parentid":"561"},{"classid":"583","name":"母亲节","parentid":"561"},{"classid":"584","name":"儿童节","parentid":"561"},{"classid":"585","name":"端午节","parentid":"561"},{"classid":"586","name":"父亲节","parentid":"561"},{"classid":"587","name":"六月六","parentid":"561"},{"classid":"588","name":"七夕节","parentid":"561"},{"classid":"589","name":"中元节","parentid":"561"},{"classid":"590","name":"中秋节","parentid":"561"},{"classid":"591","name":"重阳节","parentid":"561"},{"classid":"592","name":"万圣节","parentid":"561"},{"classid":"593","name":"感恩节","parentid":"561"},{"classid":"594","name":"圣诞节","parentid":"561"},{"classid":"595","name":"腊八节","parentid":"561"},{"classid":"596","name":"小年","parentid":"561"},{"classid":"597","name":"年夜饭","parentid":"561"},{"classid":"598","name":"春季","parentid":"561"},{"classid":"599","name":"夏季","parentid":"561"},{"classid":"600","name":"秋季","parentid":"561"},{"classid":"601","name":"冬季","parentid":"561"}]
     */

    private String classid;
    private String name;
    private String parentid;
    private List<ListBean> list;

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * classid : 574
         * name : 春节
         * parentid : 561
         */

        private String classid;
        private String name;
        private String parentid;

        public String getClassid() {
            return classid;
        }

        public void setClassid(String classid) {
            this.classid = classid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getParentid() {
            return parentid;
        }

        public void setParentid(String parentid) {
            this.parentid = parentid;
        }
    }
}
