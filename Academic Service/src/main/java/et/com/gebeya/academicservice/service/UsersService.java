package et.com.gebeya.academicservice.service;

import et.com.gebeya.academicservice.model.Users;
import et.com.gebeya.academicservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    UserRepository userRepository;

    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username)  {
                return userRepository.findFirstByUserName(username).orElseThrow(()-> new UsernameNotFoundException("user not found"));
            }
        };
    }
    public Users createAdmin(Users user){

        return userRepository.save(user);
    }
    public Users createUser(Users user) {
        return  userRepository.save(user);
    }
    public Users loadUserByUsername(String userName) {
        return userRepository.findFirstByUserName(userName).orElseThrow(() -> new IllegalArgumentException("Invalid user name or password"));
    }
}