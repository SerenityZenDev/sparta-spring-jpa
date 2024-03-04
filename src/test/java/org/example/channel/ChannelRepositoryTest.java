package org.example.channel;

import static org.junit.jupiter.api.Assertions.*;

import org.example.user.User;
import org.example.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(value = false)
class ChannelRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChannelRepository channelRepository;

    @Test
    void insertSelectGroupTest(){
        // given
        var newChannel = Channel.builder().name("new-group").build();

        // when
        var saveChannel = channelRepository.insertChannel(newChannel);

        // then
        var foundChannel = channelRepository.selectChannel(saveChannel.getId());
        assert foundChannel.getId().equals(saveChannel.getId());
    }

}