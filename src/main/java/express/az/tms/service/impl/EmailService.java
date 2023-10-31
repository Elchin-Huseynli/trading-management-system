package express.az.tms.service.impl;

import express.az.tms.exception.ApplicationException;
import express.az.tms.model.constants.Constants;
import express.az.tms.model.dto.request.EmailRequest;
import express.az.tms.model.dto.response.EmailResponse;
import express.az.tms.model.dto.response.GlobalResponse;
import express.az.tms.model.entity.ConfirmationToken;
import express.az.tms.model.entity.User;
import express.az.tms.model.enums.Exceptions;
import express.az.tms.repository.ConfirmationTokenRepository;
import express.az.tms.repository.UserRepository;
import express.az.tms.service.IEmailService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.mail.internet.MimeMessage;


@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService implements IEmailService {

    private final JavaMailSender mailSender;
    private final UserRepository userRepository;
    private final ConfirmationTokenRepository confirmationRepo;

    @SneakyThrows
    public void sendEmail(EmailRequest emailRequest) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(emailRequest.getTo());
        mimeMessageHelper.setSubject(emailRequest.getSubject());
        mimeMessageHelper.setText(emailRequest.getText(), true);

        mailSender.send(mimeMessage);
    }

    public GlobalResponse<EmailResponse> confirmEmail(String confirmationToken) {
        ConfirmationToken token = confirmationRepo.findByToken(confirmationToken);

        log.info("Confirmation token: " + confirmationToken);

        if (token != null) {
            User user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("Username nor found " + token.getUser().getEmail()));

            log.info("Find email: " + token.getUser().getEmail());
            user.setEnable(true);
            userRepository.save(user);
            log.info("Email verified successfully!");

            return GlobalResponse.of(Constants.USER_ACTIVATION_SUCCESSFULLY, HttpStatus.ACCEPTED,
                    EmailResponse.builder()
                    .to(user.getEmail())
                            .build());
        }

        throw new ApplicationException(Exceptions.TOKEN_IS_UNREACHABLE);
    }

}
