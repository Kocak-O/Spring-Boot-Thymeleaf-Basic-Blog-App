package net.osk.springbootbasicblogapp.repository;

import net.osk.springbootbasicblogapp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);


}
