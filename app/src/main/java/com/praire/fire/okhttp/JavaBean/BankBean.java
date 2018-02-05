package com.praire.fire.okhttp.JavaBean;

import java.util.List;

/**
 * Created by sunlo on 2018/2/3.
 */

public class BankBean {


    /**
     * code : 1
     * banklist : [{"id":"2","name":"工商银行","bank_pic":"upload/banklist/201712/ffaa602766a2acce95721bb164399d5d.jpg","ossbankpic":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/upload/banklist/201712/ffaa602766a2acce95721bb164399d5d.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1517649236&Signature=M%2Fkanx%2F2Fjs1Mh%2F6iRTqpMYELxM%3D"},{"id":"3","name":"农业银行","bank_pic":"upload/banklist/201712/f7f0f1709aabd31aba92a5224b2bc6b7.jpg","ossbankpic":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/upload/banklist/201712/f7f0f1709aabd31aba92a5224b2bc6b7.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1517649236&Signature=KYnHxU5IghQEgneq9aF8YLafOmI%3D"},{"id":"4","name":"中国银行","bank_pic":"upload/banklist/201712/c4a539cd8c3464fc7db7bfbb3c81296a.jpg","ossbankpic":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/upload/banklist/201712/c4a539cd8c3464fc7db7bfbb3c81296a.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1517649236&Signature=pb%2F4dpasWspoeIabr83njoG2gBo%3D"},{"id":"5","name":"建设银行","bank_pic":"upload/banklist/201712/8f649fe1d02c272feaf4e89b03cfae03.jpg","ossbankpic":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/upload/banklist/201712/8f649fe1d02c272feaf4e89b03cfae03.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1517649236&Signature=q8NpLGB3EC1FGh2sFdee5Tf516w%3D"},{"id":"6","name":"交通银行","bank_pic":"upload/banklist/201712/e5062909fd8ace107b84b2a2ba58a104.jpg","ossbankpic":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/upload/banklist/201712/e5062909fd8ace107b84b2a2ba58a104.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1517649236&Signature=JpQw5akp9RVEBmmuqBapSG1FT%2Bs%3D"},{"id":"7","name":"邮政储蓄","bank_pic":"upload/banklist/201712/88203b5978eb693f13a22cbb4ef5c638.jpg","ossbankpic":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/upload/banklist/201712/88203b5978eb693f13a22cbb4ef5c638.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1517649236&Signature=rSAkTFWn%2BsAEx3QRZjbyVnptJ4s%3D"},{"id":"8","name":"中信银行","bank_pic":"upload/banklist/201712/c8a021b339a070780d5f8c1a7da8b72d.jpg","ossbankpic":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/upload/banklist/201712/c8a021b339a070780d5f8c1a7da8b72d.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1517649236&Signature=fFm7Inmd%2F6JsqqUXn5jjy0tvIjc%3D"},{"id":"9","name":"招商银行","bank_pic":"upload/banklist/201712/509a1ca53170a21a9f16a234b64867d9.jpg","ossbankpic":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/upload/banklist/201712/509a1ca53170a21a9f16a234b64867d9.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1517649236&Signature=WpNywv8uyDI7%2BsWs2ub8gEeBDUc%3D"},{"id":"10","name":"兴业银行","bank_pic":"upload/banklist/201712/f549ef27f34121c60f0bdd3772e30b81.jpg","ossbankpic":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/upload/banklist/201712/f549ef27f34121c60f0bdd3772e30b81.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1517649236&Signature=elsNPXtYNVB1n42YiB1B8Oh7ZOs%3D"},{"id":"11","name":"民生银行","bank_pic":"upload/banklist/201712/7e0f9d3c5380e6cbe9a79159fd59a222.jpg","ossbankpic":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/upload/banklist/201712/7e0f9d3c5380e6cbe9a79159fd59a222.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1517649236&Signature=ndBIbZt6M2UnlWs0fYXY2bZ6nro%3D"},{"id":"12","name":"浦发银行","bank_pic":"upload/banklist/201712/b3ba4a056ee9f29566a95a869b54110c.jpg","ossbankpic":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/upload/banklist/201712/b3ba4a056ee9f29566a95a869b54110c.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1517649236&Signature=fg3UAggVe091q%2FF3QYMDBBQ3JHA%3D"},{"id":"13","name":"光大银行","bank_pic":"upload/banklist/201712/c9c2c1f9d09bf619c31330a99369b154.jpg","ossbankpic":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/upload/banklist/201712/c9c2c1f9d09bf619c31330a99369b154.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1517649236&Signature=gnJpypX79xhEEAv6r6rD6YVMn%2FI%3D"},{"id":"14","name":"平安银行","bank_pic":"upload/banklist/201712/e11746ad845cd430b0dc9adfa6b47277.jpg","ossbankpic":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/upload/banklist/201712/e11746ad845cd430b0dc9adfa6b47277.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1517649236&Signature=N8T7GZR%2F9Xqz9egBR1spz%2FMTrGg%3D"}]
     */

    private int code;
    private List<BanklistBean> banklist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<BanklistBean> getBanklist() {
        return banklist;
    }

    public void setBanklist(List<BanklistBean> banklist) {
        this.banklist = banklist;
    }

    public static class BanklistBean {
        /**
         * id : 2
         * name : 工商银行
         * bank_pic : upload/banklist/201712/ffaa602766a2acce95721bb164399d5d.jpg
         * ossbankpic : http://lysh-upload.oss-cn-shanghai.aliyuncs.com/upload/banklist/201712/ffaa602766a2acce95721bb164399d5d.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1517649236&Signature=M%2Fkanx%2F2Fjs1Mh%2F6iRTqpMYELxM%3D
         */

        private String id;
        private String name;
        private String bank_pic;
        private String ossbankpic;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBank_pic() {
            return bank_pic;
        }

        public void setBank_pic(String bank_pic) {
            this.bank_pic = bank_pic;
        }

        public String getOssbankpic() {
            return ossbankpic;
        }

        public void setOssbankpic(String ossbankpic) {
            this.ossbankpic = ossbankpic;
        }
    }
}
