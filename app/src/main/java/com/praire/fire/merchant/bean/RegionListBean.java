package com.praire.fire.merchant.bean;

import java.util.List;

/**
 * Created by sunlo on 2018/1/6.
 */

public class RegionListBean {


    /**
     * code : 1
     * list : [{"id":"100","pid":"0","name":"江西省","son":[{"id":"101","pid":"100","name":"赣州市","son":[{"id":"102","pid":"101","name":"章贡区"},{"id":"103","pid":"101","name":"南康区"},{"id":"104","pid":"101","name":"赣县"},{"id":"105","pid":"101","name":"信丰县"},{"id":"106","pid":"101","name":"大余县"},{"id":"107","pid":"101","name":"上犹县"},{"id":"108","pid":"101","name":"崇义县"},{"id":"109","pid":"101","name":"安远县"},{"id":"110","pid":"101","name":"龙南县"},{"id":"111","pid":"101","name":"定南县"},{"id":"112","pid":"101","name":"全南县"},{"id":"113","pid":"101","name":"宁都县"},{"id":"114","pid":"101","name":"于都县"},{"id":"115","pid":"101","name":"兴国县"},{"id":"116","pid":"101","name":"会昌县"},{"id":"117","pid":"101","name":"寻乌县"},{"id":"118","pid":"101","name":"石城县"},{"id":"119","pid":"101","name":"瑞金市"}]}]}]
     */

    private int code;
    private List<ListBean> list;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 100
         * pid : 0
         * name : 江西省
         * son : [{"id":"101","pid":"100","name":"赣州市","son":[{"id":"102","pid":"101","name":"章贡区"},{"id":"103","pid":"101","name":"南康区"},{"id":"104","pid":"101","name":"赣县"},{"id":"105","pid":"101","name":"信丰县"},{"id":"106","pid":"101","name":"大余县"},{"id":"107","pid":"101","name":"上犹县"},{"id":"108","pid":"101","name":"崇义县"},{"id":"109","pid":"101","name":"安远县"},{"id":"110","pid":"101","name":"龙南县"},{"id":"111","pid":"101","name":"定南县"},{"id":"112","pid":"101","name":"全南县"},{"id":"113","pid":"101","name":"宁都县"},{"id":"114","pid":"101","name":"于都县"},{"id":"115","pid":"101","name":"兴国县"},{"id":"116","pid":"101","name":"会昌县"},{"id":"117","pid":"101","name":"寻乌县"},{"id":"118","pid":"101","name":"石城县"},{"id":"119","pid":"101","name":"瑞金市"}]}]
         */

        private String id;
        private String pid;
        private String name;
        private List<SonBeanX> son;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<SonBeanX> getSon() {
            return son;
        }

        public void setSon(List<SonBeanX> son) {
            this.son = son;
        }

        public static class SonBeanX {
            /**
             * id : 101
             * pid : 100
             * name : 赣州市
             * son : [{"id":"102","pid":"101","name":"章贡区"},{"id":"103","pid":"101","name":"南康区"},{"id":"104","pid":"101","name":"赣县"},{"id":"105","pid":"101","name":"信丰县"},{"id":"106","pid":"101","name":"大余县"},{"id":"107","pid":"101","name":"上犹县"},{"id":"108","pid":"101","name":"崇义县"},{"id":"109","pid":"101","name":"安远县"},{"id":"110","pid":"101","name":"龙南县"},{"id":"111","pid":"101","name":"定南县"},{"id":"112","pid":"101","name":"全南县"},{"id":"113","pid":"101","name":"宁都县"},{"id":"114","pid":"101","name":"于都县"},{"id":"115","pid":"101","name":"兴国县"},{"id":"116","pid":"101","name":"会昌县"},{"id":"117","pid":"101","name":"寻乌县"},{"id":"118","pid":"101","name":"石城县"},{"id":"119","pid":"101","name":"瑞金市"}]
             */

            private String id;
            private String pid;
            private String name;
            private List<SonBean> son;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<SonBean> getSon() {
                return son;
            }

            public void setSon(List<SonBean> son) {
                this.son = son;
            }

            public static class SonBean {
                /**
                 * id : 102
                 * pid : 101
                 * name : 章贡区
                 */

                private String id;
                private String pid;
                private String name;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getPid() {
                    return pid;
                }

                public void setPid(String pid) {
                    this.pid = pid;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }
    }
}
