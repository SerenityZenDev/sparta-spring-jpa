package org.example.my;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.example.user.User;
import org.example.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(false)
public class MyUserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void myUserRepositoryDeleteTest() {
        // given
        var newUser = User.builder()
            .username("newUser")
            .password("newPassword")
            .build();
        userRepository.save(newUser);

        // when
        userRepository.delete(newUser);
    }

    @Test
    void myUserRepositoryFindNameAllTest() {
        // given
        var newUser1 = User.builder().username("newUser1").password("newPassword1").build();
        var newUser2 = User.builder().username("newUser2").password("newPassword2").build();
        userRepository.save(newUser1);
        userRepository.save(newUser2);

        // when
        var userNameList = userRepository.findNameAll();

        // then
        Assertions.assertThat(
            userNameList.containsAll(List.of(newUser1.getUsername(), newUser2.getUsername())));
    }

}
