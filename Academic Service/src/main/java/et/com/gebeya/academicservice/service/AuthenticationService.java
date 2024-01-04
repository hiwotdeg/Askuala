package et.com.gebeya.academicservice.service;

import et.com.gebeya.academicservice.dto.AuthenticationResponse;
import et.com.gebeya.academicservice.dto.UsersCredential;
import et.com.gebeya.academicservice.model.Users;
import et.com.gebeya.academicservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse signIn(UsersCredential usersCredential)  {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usersCredential.getUserName(), usersCredential.getPassword()));
        Users user = userRepository.findFirstByUserName(usersCredential.getUserName())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user name or password"));
        String jwt = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwt).role(user.getRole()).build();
    }

}
