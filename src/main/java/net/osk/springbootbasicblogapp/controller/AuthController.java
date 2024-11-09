package net.osk.springbootbasicblogapp.controller;

import jakarta.validation.Valid;
import net.osk.springbootbasicblogapp.dto.RegisterDto;
import net.osk.springbootbasicblogapp.entity.User;
import net.osk.springbootbasicblogapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        RegisterDto user = new RegisterDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") RegisterDto registerDto, BindingResult result, Model model){
        User existingUser = userService.findByMail(registerDto.getEmail());
        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null, "Already registered!");
        }
        if (result.hasErrors()){
            model.addAttribute("user", registerDto);
            return "register";
        }
        userService.saveUser(registerDto);
        return "redirect:/register?success";
    }
}

