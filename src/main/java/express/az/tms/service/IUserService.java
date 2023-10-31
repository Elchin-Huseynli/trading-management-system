package express.az.tms.service;

import express.az.tms.model.dto.request.UserRequest;
import express.az.tms.model.dto.response.AuthenticationResponse;
import express.az.tms.model.dto.response.GlobalResponse;


public interface IUserService {
    GlobalResponse<AuthenticationResponse> signUp(UserRequest userRequest);
    GlobalResponse<AuthenticationResponse> login(UserRequest userRequest);
    GlobalResponse<AuthenticationResponse> refreshToken(String authHeader);

}
