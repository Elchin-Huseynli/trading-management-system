package express.az.tms.controller;

import express.az.tms.model.dto.request.UserRequest;
import express.az.tms.model.dto.response.GlobalResponse;
import express.az.tms.service.IEmailService;
import express.az.tms.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tms/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final IUserService userService;
    private final IEmailService emailService;

    @PostMapping("/registration")
    public GlobalResponse<?> registerUser(@RequestBody UserRequest user) {
        log.info("Registering user: {}", user.getName());

        return userService.signUp(user);
    }

    @PostMapping("/login")
    public GlobalResponse<?> login(@RequestBody UserRequest user) {
        log.info("Login user: {}", user.getName());

        return userService.login(user);
    }


    @GetMapping("/refresh-token")
    public GlobalResponse<?> refreshToken(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String token) {
        return userService.refreshToken(token);
    }

    @RequestMapping(value="/confirmation-account", method= {RequestMethod.GET, RequestMethod.POST})
    public GlobalResponse<?> confirmUserAccount(@RequestParam("token") String confirmationToken) {
        log.info("Confirming user account with token: {}", confirmationToken);
        return emailService.confirmEmail(confirmationToken);
    }

}
