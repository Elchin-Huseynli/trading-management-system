package express.az.tms.helper;

import express.az.tms.model.constants.Constants;
import express.az.tms.model.dto.response.AuthenticationResponse;
import express.az.tms.model.dto.response.GlobalResponse;
import express.az.tms.model.entity.Token;
import express.az.tms.model.entity.User;
import express.az.tms.repository.TokenRepository;
import express.az.tms.service.IJwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceHelper {

    private final TokenRepository tokenRepository;
    private final IJwtService jwtService;

    public void saveUserToken(User user, String jwtToken) {
        Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .revoked(false)
                .expired(false)
                .build();

        tokenRepository.save(token);
    }

    public void revokedAllUserTokens(User user) {
        List<Token> validUserTokens = tokenRepository.findAllValidTokensByUser(user.getId());

        if (validUserTokens.isEmpty()) return;

        validUserTokens
                .forEach(t -> {t.setExpired(true); t.setRevoked(true);});

        tokenRepository.saveAll(validUserTokens);
    }

    public GlobalResponse<AuthenticationResponse> getAuthenticationResponseGlobalResponse(User user) {
        String accessToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        revokedAllUserTokens(user);
        saveUserToken(user, accessToken);

        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        return GlobalResponse.of(Constants.USER_LOGIN_SUCCESSFULLY, HttpStatus.OK, authenticationResponse);
    }

}
