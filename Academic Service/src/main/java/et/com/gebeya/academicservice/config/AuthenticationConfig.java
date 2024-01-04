package et.com.gebeya.academicservice.config;


import et.com.gebeya.academicservice.model.Enumeration.Role;
import et.com.gebeya.academicservice.model.Users;
import et.com.gebeya.academicservice.service.UsersService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AuthenticationConfig {

    public static final String ADMIN = "ADMIN";
    public static final String TEACHER = "TEACHER";
    public static final String STUDENT = "STUDENT";
    public static final String GUARDIAN = "GUARDIAN";
    public static final String[] UNAUTHORIZED_MATCHERS =
            {
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/api/auth/log_in"
            };
    public static final String[] ADMIN_MATCHERS =
            {
                    "/asquala/**"
            };
    public static final String[] STUDENT_MATCHERS =
            {
                    "asquala/student/getStudent/{id}",
                    "asquala/attendance/getAttendance/{id}",
                    "asquala/subject/getSubject",
                    "asquala/teacher/getTeacher"
            };
    public static final String[] TEACHER_MATCHERS =
            {
                    "/asquala/teacher/getTeacher/{id}",
                    "/asquala/attendance/addAttendance",
                    "/asquala/attendance/updateAttendance",
                    "/asquala/attendance/getAttendance",
                    "asquala/subject/getSubject"
            };
    public static final String[] GUARDIAN_MATCHERS =
            {
                    "/asquala/guardian/getGuardian/{id}",
                    "/asquala/attendance/getAttendance/{id}",

            };
    private  final JwtAuthenticationFilter jwtAuthenticationFilter;
    private  final UsersService usersService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request.requestMatchers(UNAUTHORIZED_MATCHERS).permitAll())
                .authorizeHttpRequests(request -> request.requestMatchers(ADMIN_MATCHERS).hasAuthority(Role.ADMIN.name()))
                .authorizeHttpRequests(request -> request.requestMatchers(TEACHER_MATCHERS).hasAuthority(Role.TEACHER.name()))
                .authorizeHttpRequests(request -> request.requestMatchers(STUDENT_MATCHERS).hasAuthority(Role.STUDENT.name()))
                .authorizeHttpRequests(request -> request.requestMatchers(GUARDIAN_MATCHERS).hasAuthority(Role.GUARDIAN.name()))
                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                .exceptionHandling(handling -> handling.authenticationEntryPoint(unauthorizedEntryPoint()))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public AuthenticationEntryPoint unauthorizedEntryPoint() {
        return (request, response, authException) ->
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(usersService.userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        createAdminUser();
        return authProvider;
    }

    private void createAdminUser() {
        try {
            Users users = usersService.loadUserByUsername("admin");
        } catch (Exception e) {
            Users users = Users.builder()
                    .name("Admin Admin")
                    .isActive(true)
                    .role(Role.ADMIN)
                    .userName("admin")
                    .password(passwordEncoder().encode("password"))
                    .build();
            usersService.createAdmin(users);
        }
    }

    private Users userPasswordEncoder(Users user){
        Users users = Users.builder()
                .name(user.getName())
                .userName(user.getUsername())
                .role(user.getRole())
                .password(passwordEncoder().encode(user.getPassword()))
                .isActive(user.getIsActive()).build();
             return  usersService.createUser(users);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }
}
