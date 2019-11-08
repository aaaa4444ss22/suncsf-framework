package cn.suncsf.framework.core.utils;

import cn.suncsf.framework.core.business.AbaseBusiness;
import cn.suncsf.framework.core.entity.EntityResult;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author sunchao
 * @version 1.0.0
 * @date 2019/11/8
 * @create 2019/11/8
 * @description
 */
public class MailUtil extends AbaseBusiness {
    public final static String HTML_CONTENT_TYPE = "text/html;charset=UTF-8";
    private Builder builder;

    private MailUtil(Builder builder) {
        this.builder = builder;
    }


    public <T extends MimeMessage> EntityResult send(Function<MimeMessage,T> function) throws Exception{
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");//指定邮件发送的协议，参数是规范规定的
        properties.setProperty("mail.host", builder.mailServerHost);//指定发件服务器的地址，参数是规范规定的
        properties.setProperty("mail.smtp.auth", "true");//请求服务器进行身份认证。参数与具体的JavaMail实现有关
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.starttls.required", "true");
        if(builder.properties != null){
            for (Object item: properties.keySet()) {
                String key = (String) item;
                if(properties.getProperty(key) == null){
                    properties.setProperty(key,builder.properties.getProperty((String) key));
                }
            }
        }

        Session session = Session.getInstance(properties);
        session.setDebug(true);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(builder.fromAddress
                ,StringUtils.isNotBlank(builder.name)?builder.name:(builder.fromAddress.split("@")[0])
                ,"UTF-8"));

        message.setRecipients(Message.RecipientType.TO
                , ArrayUtil.createArray(builder.toMoreAddress,InternetAddress.class));

        message.setContent(builder.content
                ,StringUtils.isNotBlank(builder.contentType)?builder.contentType:HTML_CONTENT_TYPE);
        message.setSubject(builder.subject);

        message.saveChanges();
        T entity = function.apply(message);
        Transport transport = session.getTransport();
        transport.connect(builder.userName,builder.password);
        transport.sendMessage(entity,entity.getAllRecipients());
        return getResult(1);
    }

    public static class Builder {

        public MailUtil build() {
            return new MailUtil(this);
        }

        private Properties properties;
        /**
         * 邮件服务主机地址
         */
        private String mailServerHost;

        /**
         * 发件人
         */
        private String userName;

        /**
         * 发件人姓名
         */
        private String name;

        /**
         * 发件人密码
         */
        private String password;
        /**
         * 发送账号
         */
        private String fromAddress;
//        /**
//         * 接收人账号
//         */
       private String contentType;
        /**
         * 接收人账号
         */
        private List<Address> toMoreAddress = new ArrayList<>();
        /**
         * 标题
         */
        private String subject;

        /**
         * 内容
         */
        private String content;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setToMoreAddress(Address[] toMoreAddress) {
            if(toMoreAddress != null && toMoreAddress.length > 0){
                this.toMoreAddress.addAll(Arrays.asList(toMoreAddress));
            }
            return this;
        }

        public Builder setToMoreStrAddress(String toMoreStrAddress,String regex) throws AddressException {
            if(StringUtils.isNotBlank(toMoreStrAddress)){
                if(StringUtils.isBlank(regex)){
                    String[] array = new String[1];
                    array[0] = toMoreStrAddress;
                    return this.setToMoreArrayStrAddress(array);
                }
               return this.setToMoreArrayStrAddress(toMoreStrAddress.split(regex));
            }
            return this;
        }

        public Builder setToMoreArrayStrAddress(String [] arrayAddress) throws AddressException {
            if(arrayAddress != null && arrayAddress.length > 0){
                for (String item:arrayAddress) {
                    if(StringUtils.isNotBlank(item) && item.indexOf('@') > -1){
                        toMoreAddress.add(new InternetAddress(item));
                    }
                }
            }
            return this;
        }

        public Builder setContentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        public Builder setMailServerHost(String mailServerHost) {
            this.mailServerHost = mailServerHost;
            return this;
        }

        public Builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setFromAddress(String fromAddress) {
            this.fromAddress = fromAddress;
            return this;
        }

        public Builder setToAddress(String toAddress)  throws AddressException{
            return setToMoreStrAddress(toAddress,null);
        }

        public Builder setSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setProperties(Properties properties) {
            this.properties = properties;
            return this;
        }
    }

}
