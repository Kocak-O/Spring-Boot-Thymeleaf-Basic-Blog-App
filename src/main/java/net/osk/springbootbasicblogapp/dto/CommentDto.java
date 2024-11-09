package net.osk.springbootbasicblogapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.osk.springbootbasicblogapp.entity.Post;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CommentDto {

    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty(message = "Message should not be empty")
    private String content;
    private LocalDateTime createdOn;
    private LocalDateTime updateOn;
    @NotEmpty(message = "E-Mail should not be empty")
    @Email
    private String email;
    private Post post;


}
