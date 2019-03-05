package site.dongxiaoxu.sunmall.framework.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
@Slf4j
public class MailUtil {

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Value("${spring.mail.username}")
    private String sendUser;

    public boolean sendMail(Mail mail) {
        this.checkArguments(mail);
        if (mail.getAttachments() == null || mail.getAttachments().length == 0) {
            return this.sendMailWithNoAttachment(mail);
        } else {
            return this.sendMailWithAttachment(mail);
        }
    }

    private void checkArguments(Mail mail) {
        if (mail == null) {
            throw new NullPointerException("参数不允许为null！");
        } else if (mail.getReceivers() == null || mail.getReceivers().length == 0) {
            throw new IllegalArgumentException("未配置接收人！");
        } else if (StringUtils.isEmpty(mail.getSubjectText())) {
            throw new IllegalArgumentException("未配置发送主题！");
        } else if (StringUtils.isEmpty(mail.getContent())) {
            throw new IllegalArgumentException("未配置发送内容！");
        } else if (mail.getIsHtml() == null) {
            throw new IllegalArgumentException("未配置是否显示为HTML格式！");
        }
    }

    private boolean sendMailWithNoAttachment(Mail mail) {
        SimpleMailMessage message;
        message = new SimpleMailMessage();
        try {
            message.setTo(mail.getReceivers());
            message.setSubject(mail.getSubjectText());
            message.setText(mail.getContent());
            //发送人必须设置与配置文件一致，不然无法发送
            message.setFrom(sendUser);
            javaMailSender.send(message);
        } catch (MailException e) {
            log.error("邮件发送失败！", e);
            return false;
        }
        return true;
    }

    private boolean sendMailWithAttachment(Mail mail) {
        MimeMessage message;
        message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(mail.getReceivers());
            helper.setSubject(mail.getSubjectText());
            helper.setFrom(sendUser);
            if (mail.getIsHtml()) {
                helper.setText(mail.getContent(), true);
            } else {
                helper.setText(mail.getContent());
            }
            for (Mail.Attachment attachment : mail.getAttachments()) {
                helper.addAttachment(attachment.getFileName(), attachment.getFile());
            }
            javaMailSender.send(message);
        } catch (MessagingException | MailException e) {
            log.error("邮件发送失败！", e);
            return false;
        }
        return true;
    }
}
