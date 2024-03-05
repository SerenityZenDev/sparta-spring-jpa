package org.example.thread;

import java.util.Set;
import org.example.channel.Channel;
import org.example.channel.ChannelRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(false)
class ThreadRepositoryTest {

    @Autowired
    private ThreadRepository threadRepository;

    @Autowired
    private ChannelRepository channelRepository;

    @Test
    void insertSelectThreadTest() {
        // given
        var newChannel = Channel.builder().name("new-group").build();
        var newThread = Thread.builder().message("new-message").build();
        var newThread2 = Thread.builder().message("new2-message").build();
        newThread.setChannel(newChannel);
        newThread2.setChannel(newChannel);

        // when
        var saveChannel = channelRepository.save(newChannel);
        var saveThread = threadRepository.insertChannel(newThread);
        var saveThread2 = threadRepository.insertChannel(newThread2);

        // then
        var foundChannel = channelRepository.findById(saveChannel.getId());
        assert foundChannel.get().getThreads().containsAll(Set.of(saveThread, saveThread2));
    }

    @Test
    void deleteThreadbyOrphanRemovalTest() {
        // given
        var newChannel = Channel.builder().name("new-group").build();
        var newThread = Thread.builder().message("new-message").build();
        var newThread2 = Thread.builder().message("new2-message").build();
        newThread.setChannel(newChannel);
        newThread2.setChannel(newChannel);
        var saveChannel = channelRepository.save(newChannel);
        var saveThread = threadRepository.insertChannel(newThread);
        var saveThread2 = threadRepository.insertChannel(newThread2);
        // when
        var foundChannel = channelRepository.findById(saveChannel.getId());
        foundChannel.get().getThreads().remove(saveThread);

        // then
//        var foundChannel = channelRepository.selectChannel(saveChannel.getId());
//        assert foundChannel.getThreads().containsAll(Set.of(saveThread, saveThread2));
    }


}