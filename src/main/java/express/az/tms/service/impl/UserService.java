package express.az.tms.service.impl;

import express.az.tms.exception.ApplicationException;
import express.az.tms.helper.EmailServiceHelper;
import express.az.tms.helper.SecurityHelper;
import express.az.tms.helper.UserServiceHelper;
import express.az.tms.mapper.UserMapper;
import express.az.tms.model.constants.Constants;
import express.az.tms.model.dto.request.UserRequest;
import express.az.tms.model.dto.response.AuthenticationResponse;
import express.az.tms.model.dto.response.GlobalResponse;
import express.az.tms.model.entity.User;
import express.az.tms.model.enums.Exceptions;
import express.az.tms.repository.UserRepository;
import express.az.tms.service.IJwtService;
import express.az.tms.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository appUserRepository;
    private final EmailServiceHelper emailService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final IJwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserServiceHelper userServiceHelper;
    private final SecurityHelper securityHelper;

    @SneakyThrows
    @Override
    public GlobalResponse<AuthenticationResponse> signUp(UserRequest userRequest) {
        User user = userMapper.userRequestDtoToUser(userRequest);

        if (appUserRepository.existsByEmail(user.getEmail())) {
            throw new ApplicationException(Exceptions.USER_IS_ALREADY_EXISTS);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        appUserRepository.save(user);
        emailService.confirmationToken(user);

        String accessToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        userServiceHelper.saveUserToken(user, accessToken);

        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        return GlobalResponse.of(Constants.USER_REGISTER_SUCCESSFULLY, HttpStatus.CREATED, authenticationResponse);

    }

    @Override
    public GlobalResponse<AuthenticationResponse> login(UserRequest userRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRequest.getEmail(), userRequest.getPassword()));

        User user = appUserRepository.findByEmailIgnoreCase(userRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found " + userRequest.getEmail()));

        return userServiceHelper.getAuthenticationResponseGlobalResponse(user);

    }

    @Override
    public GlobalResponse<AuthenticationResponse> refreshToken(String authHeader) {
        if (!securityHelper.authHeaderIsValid(authHeader)) {
            throw new ApplicationException(Exceptions.TOKEN_IS_INVALID_EXCEPTION);
        }

        String jwt = authHeader.substring(7);
        String username = jwtService.extractUsername(jwt);

        if (username != null) {
            User user = appUserRepository.findByEmailIgnoreCase(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Username doesn't exist: " + username));

            if (jwtService.isTokenValid(jwt, user)) {
                return userServiceHelper.getAuthenticationResponseGlobalResponse(user);
            }
        }

        throw new ApplicationException(Exceptions.TOKEN_IS_INVALID_EXCEPTION);
    }

}
