package org.example.channel;

import com.querydsl.core.types.Predicate;
import java.util.Optional;
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
    void insertSelectGroupTest() {
        // given
        var newChannel = Channel.builder().name("new-group").build();

        // when
        var saveChannel = channelRepository.save(newChannel);

        // then
        var foundChannel = channelRepository.findById(saveChannel.getId());
        assert foundChannel.get().getId().equals(saveChannel.getId());
    }

    @Test
    void queryDslTest() {
        // given
        var newChannel = Channel.builder().name("melue").build();
        channelRepository.save(newChannel);

        Predicate predicate = QChannel.channel
            .name.equalsIgnoreCase("melue");

        // when
        Optional<Channel> optional = channelRepository.findOne(predicate);

        // then
        assert optional.get().getName().equals(newChannel.getName());
    }
}