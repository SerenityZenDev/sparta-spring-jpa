package org.example.userChannel;

import static org.junit.jupiter.api.Assertions.*;

import org.example.channel.Channel;
import org.example.channel.ChannelRepository;
import org.example.thread.ThreadRepository;
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
class UserChannelRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private UserChannelRepository userChannelRepository;

    @Test
    void userJoinChannelTest(){
        // given
        var newChannel = Channel.builder().name("new-group").build();
        var newUser = User.builder().username("new-user").password("new-pass").build();
        var newUserChannel = newChannel.joinUser(newUser);

        // when
        var saveChannel = channelRepository.insertChannel(newChannel);
        var savedUser = userRepository.insertUser(newUser);
        var savedUserChannel = userChannelRepository.insertUserChannel(newUserChannel);

        // then
        var foundChannel = channelRepository.selectChannel(saveChannel.getId());
        assert foundChannel.getUserChannels().stream()
            .map(UserChannel::getChannel)
            .anyMatch(name -> name.equals(newChannel.getName()));
    }
}