package net.osk.springbootbasicblogapp.util;

import net.osk.springbootbasicblogapp.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {


    public static User getCurrentUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof User){
            return (User) principal;
        }
        return null;
    }


}
