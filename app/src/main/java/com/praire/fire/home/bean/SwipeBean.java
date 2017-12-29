package com.praire.fire.home.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/29.
 */

public class SwipeBean {
    /**
     * code : 1
     * swipelist : [{"id":"36","cover":"upload/article/201712/68a46bfddf1120cbd76fca52b547cc50.jpg","url":"#","ossclientcover":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/upload/article/201712/68a46bfddf1120cbd76fca52b547cc50.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1514513596&Signature=KWzidVgAdbbW%2BH54oi6tZ3OmX34%3D"},{"id":"37","cover":"upload/article/201712/f40d3c942385a108b5c642a4662dbaed.jpg","url":"#","ossclientcover":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/upload/article/201712/f40d3c942385a108b5c642a4662dbaed.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1514513596&Signature=aveIN0euCVOLRxw4hsAd730IGpU%3D"},{"id":"38","cover":"upload/article/201712/2e680b1ff9c63fdf658451f67083fd1a.jpg","url":"#","ossclientcover":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/upload/article/201712/2e680b1ff9c63fdf658451f67083fd1a.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1514513596&Signature=%2Ftv3dyLA8ck2Nqx%2BlMF2lHsSriA%3D"},{"id":"74","cover":"upload/article/201712/e1dcd0ec8ccb1981289d1e517dbdb812.jpg","url":"#","ossclientcover":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/upload/article/201712/e1dcd0ec8ccb1981289d1e517dbdb812.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1514513596&Signature=n91CtuGUM%2BG%2BsS7ir3GIgbfLMF8%3D"}]
     */

    private int code;
    private List<SwipelistBean> swipelist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<SwipelistBean> getSwipelist() {
        return swipelist;
    }

    public void setSwipelist(List<SwipelistBean> swipelist) {
        this.swipelist = swipelist;
    }

    public static class SwipelistBean {
        /**
         * id : 36
         * cover : upload/article/201712/68a46bfddf1120cbd76fca52b547cc50.jpg
         * url : #
         * ossclientcover : http://lysh-upload.oss-cn-shanghai.aliyuncs.com/upload/article/201712/68a46bfddf1120cbd76fca52b547cc50.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1514513596&Signature=KWzidVgAdbbW%2BH54oi6tZ3OmX34%3D
         */

        private String id;
        private String cover;
        private String url;
        private String ossclientcover;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getOssclientcover() {
            return ossclientcover;
        }

        public void setOssclientcover(String ossclientcover) {
            this.ossclientcover = ossclientcover;
        }
    }
}
