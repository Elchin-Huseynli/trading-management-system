package express.az.tms.helper;

import express.az.tms.model.dto.request.EmailRequest;
import express.az.tms.model.entity.ConfirmationToken;
import express.az.tms.model.entity.User;
import express.az.tms.repository.ConfirmationTokenRepository;
import express.az.tms.service.IEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class EmailServiceHelper {

    private final IEmailService emailService;
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void confirmationToken(User user) {
        ConfirmationToken confirmationToken = confirmationTokenBuild(user);
        confirmationTokenRepository.save(confirmationToken);
        confirmationTokenRepository.save(confirmationToken);

        emailService.sendEmail(confirmationTokenSendEmail(user, confirmationToken));
    }


    public EmailRequest confirmationTokenSendEmail(User user, ConfirmationToken confirmationToken){
        String url = "http://localhost:8080/tms/user/confirmation-account?token=" + confirmationToken.getToken();
        return EmailRequest.builder()
                .to(user.getEmail())
                .subject("Registration")
                .text("<p> Hi, " + user.getName() + ", <p>" +
                        "<p>Thank you for registering with us," + "" +
                        "Please, follow the link below to complete your registration.<p>" +
                        "<a href=\"" + url + "\">Verify your email to active your account<a>" +
                        "<p> Thank you <br> Users Registration Portal Service")
                .build();
    }

    public ConfirmationToken confirmationTokenBuild (User user){
        String token = UUID.randomUUID().toString();

        return ConfirmationToken
                .builder()
                .confirm(true)
                .token(token)
                .user(user)
                .confirmedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .build();


    }

}
