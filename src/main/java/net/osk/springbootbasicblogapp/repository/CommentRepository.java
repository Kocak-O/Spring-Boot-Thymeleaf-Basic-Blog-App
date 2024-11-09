package net.osk.springbootbasicblogapp.repository;

import net.osk.springbootbasicblogapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {


}
