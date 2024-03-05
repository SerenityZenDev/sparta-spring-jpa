package org.example.user;

import org.example.my.MyRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, MyRepository<User> {


}
