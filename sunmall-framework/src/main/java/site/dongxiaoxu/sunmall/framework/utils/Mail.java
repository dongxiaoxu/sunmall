package site.dongxiaoxu.sunmall.framework.utils;

import lombok.*;

import java.io.File;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mail {

    @NonNull
    private String[] receivers;

    @NonNull
    private String subjectText;

    @NonNull
    private String content;

    private Attachment[] attachments;

    @NonNull
    private Boolean isHtml;

    @Data
    public class Attachment {

        private String fileName;

        private File file;
    }

}
