package org.example.userChannel;

import org.example.channel.Channel;
import org.example.channel.ChannelRepository;
import org.example.user.User;
import org.example.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(false)
class UserChannelRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private UserChannelRepository userChannelRepository;

    @Test
    void userJoinChannelWithCascadeTest() {
        // given
        var newChannel = Channel.builder().name("new-group").build();
        var newUser = User.builder().username("new-user").password("new-pass").build();
        newChannel.joinUser(newUser);

        // when
        var saveChannel = channelRepository.insertChannel(newChannel);
        // var savedUser = userRepository.findByUsername(newUser.getUsername());

        // then
        var foundChannel = channelRepository.selectChannel(saveChannel.getId());
        assert foundChannel.getUserChannels().stream()
            .map(UserChannel::getChannel)
            .anyMatch(name -> name.equals(newChannel.getName()));
    }

    @Test
    void userCustonFieldSortingTest() {
        // given
        var newUser1 = User.builder().username("new_user").password("newPassword1").build();
        var newUser2 = User.builder().username("new_user").password("newPassword2").build();
        userRepository.save(newUser1);
        userRepository.save(newUser2);

        // when
        var users = userRepository.findByUsername("new_user", Sort.by("customField"));
        var users2 = userRepository.findByUsername("new_user", Sort.by("customField").descending());

        // then
        assert users.get(0).getPassword().equals(newUser1.getPassword());
        assert users.get(1).getPassword().equals(newUser2.getPassword());
    }

}