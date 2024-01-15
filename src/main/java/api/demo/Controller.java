package api.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import api.demo.models.User;
import api.demo.models.UserDto;

@RestController
public class Controller {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String home() {
        return "<center><h1>Welcome!</h1>";
    }

    @GetMapping("/user")
    public String user() {
        return "<center><h1>Welcome User!</h1>";
    }

    @GetMapping("/admin")
    public String admin() {
        return "<center><h1>Welcome Admin!</h1>";
    }

    @PostMapping("/register") 
    public String registerUser(@RequestBody UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setActive(true);
        user.setRole("ADMIN");
        try {
            userRepository.save(user);
            return "User Registered !";
        } catch (Exception e) {
            return "Something Went Wrong !";
        }
    }

}
