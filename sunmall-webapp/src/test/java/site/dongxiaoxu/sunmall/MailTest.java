package site.dongxiaoxu.sunmall;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import site.dongxiaoxu.sunmall.framework.utils.Mail;
import site.dongxiaoxu.sunmall.framework.utils.MailUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTest {

    @Autowired
    private MailUtil mailSender;

    @Test
    public void test() {
        Mail mail = new Mail();
        mail.setReceivers(new String[] {"dongxiaoxu.email@gmail.com"});
        mail.setSubjectText("测试发送邮件");
        mail.setContent("这是一封测试邮件");
        mail.setIsHtml(false);
        boolean result;
        result = mailSender.sendMail(mail);
        System.out.println(result);
    }
}
