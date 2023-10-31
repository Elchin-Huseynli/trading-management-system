package express.az.tms.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import static express.az.tms.model.constants.Constants.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailRequest {

    @NotBlank(message = EMAIL_IS_URGENT)
    @Email(message = EMAIL_IS_NOT_VALID)
    private String to;

    @NotBlank(message = SUBJECT_IS_URGENT)
    @Size(max = 100, message = SUBJECT_REGEX)
    private String subject;

    @NotBlank(message = BODY_IS_URGENT)
    private String text;


    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
