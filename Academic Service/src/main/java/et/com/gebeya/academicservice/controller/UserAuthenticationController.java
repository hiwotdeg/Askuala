package et.com.gebeya.academicservice.controller;

import et.com.gebeya.academicservice.dto.AuthenticationResponse;
import et.com.gebeya.academicservice.dto.UsersCredential;
import et.com.gebeya.academicservice.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class UserAuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/log_in")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody UsersCredential usersCredential) {
        return ResponseEntity.ok(authenticationService.signIn(usersCredential));
    }
}
