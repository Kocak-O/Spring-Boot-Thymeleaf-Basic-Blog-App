package net.osk.springbootbasicblogapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterDto {

    private Long id;

    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty(message = "Mail cannot be empty")
    @Email
    private String email;
    @NotEmpty(message = "Password should not be empty")
    private String password;
}
