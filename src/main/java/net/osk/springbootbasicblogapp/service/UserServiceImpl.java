package net.osk.springbootbasicblogapp.service;

import net.osk.springbootbasicblogapp.dto.RegisterDto;
import net.osk.springbootbasicblogapp.entity.Role;
import net.osk.springbootbasicblogapp.entity.User;
import net.osk.springbootbasicblogapp.repository.RoleRepository;
import net.osk.springbootbasicblogapp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void saveUser(RegisterDto registerDto) {
        User user = new User();
        user.setName(registerDto.getFirstName() + " " + registerDto.getLastName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_GUEST");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public User findByMail(String email) {
        return userRepository.findByEmail(email);
    }
}
