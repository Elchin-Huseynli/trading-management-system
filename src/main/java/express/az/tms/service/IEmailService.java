package express.az.tms.service;

import express.az.tms.model.dto.request.EmailRequest;
import express.az.tms.model.dto.response.EmailResponse;
import express.az.tms.model.dto.response.GlobalResponse;


public interface IEmailService {
    void sendEmail(EmailRequest emailDTO);
    GlobalResponse<EmailResponse> confirmEmail(String token);
}
