package net.osk.springbootbasicblogapp.service;

import net.osk.springbootbasicblogapp.dto.RegisterDto;
import net.osk.springbootbasicblogapp.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public void saveUser(RegisterDto registerDto);

    User findByMail(String email);
}
