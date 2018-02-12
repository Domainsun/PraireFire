package com.praire.fire.utils.version;

/**
 * Created by lyp
 * on 2018/2/11.
 */

public class VersionInfo {

    /**
     * code : 1
     * version : {"id":"4","version":"v2.1.2","versioncode":"2","downurl":"www.lygyxh.cn","desc":"<ol class=\" list-paddingleft-2\" style=\"list-style-type: decimal;\"><li><p>功能一更新<\/p><\/li><li><p>功能二更新<\/p><\/li><li><p>功能三更新<br/><\/p><\/li><\/ol>","create_time":"1518336857","update_time":"0"}
     */

    private int code;
    private VersionBean version;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public VersionBean getVersion() {
        return version;
    }

    public void setVersion(VersionBean version) {
        this.version = version;
    }

    public static class VersionBean {
        /**
         * id : 4
         * version : v2.1.2
         * versioncode : 2
         * downurl : www.lygyxh.cn
         * desc : <ol class=" list-paddingleft-2" style="list-style-type: decimal;"><li><p>功能一更新</p></li><li><p>功能二更新</p></li><li><p>功能三更新<br/></p></li></ol>
         * create_time : 1518336857
         * update_time : 0
         */

        private String id;
        private String version;
        private String versioncode;
        private String downurl;
        private String desc;
        private String create_time;
        private String update_time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getVersioncode() {
            return versioncode;
        }

        public void setVersioncode(String versioncode) {
            this.versioncode = versioncode;
        }

        public String getDownurl() {
            return downurl;
        }

        public void setDownurl(String downurl) {
            this.downurl = downurl;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }
    }
}
