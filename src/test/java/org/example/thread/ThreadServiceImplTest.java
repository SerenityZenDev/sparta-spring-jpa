package org.example.thread;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.example.channel.Channel;
import org.example.channel.Channel.Type;
import org.example.channel.ChannelRepository;
import org.example.mention.Mention;
import org.example.user.User;
import org.example.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ThreadServiceImplTest {

    @Autowired
    UserRepository userRepository;


    @Autowired
    ChannelRepository channelRepository;

    @Autowired
    ThreadService threadService;

    @Test
    void getMentionedThreadList() {
        // QuerydslPredicateExecutor만으로는 join이 불가능하여 실패하는게 정상
        // given
        var newUser = User.builder().username("new").password("1").build();
        var savedUser = userRepository.save(newUser);
        var newThread = Thread.builder().message("message").build();
        newThread.addMention(savedUser);
        threadService.insert(newThread);

        var newThread2 = Thread.builder().message("message2").build();
        newThread2.addMention(savedUser);
        threadService.insert(newThread2);

        // when
        // 모든 채널에서 내가 멘션된 쓰레드 목록 조회 기능
        var mentionedThreads = savedUser.getMentions().stream().map(Mention::getThread).toList();

        // then
        assert mentionedThreads.containsAll(List.of(newThread, newThread2));
    }

    @Test
    void getNotEmptyThreadList() {
        // given
        var newChannel = Channel.builder().name("c1").type(Type.PULBIC).build();
        var savedChannel = channelRepository.save(newChannel);
        var newThread = Thread.builder().message("message").build();
        newThread.setChannel(savedChannel);
        threadService.insert(newThread);

        var newThread2 = Thread.builder().message("").build();
        newThread2.setChannel(savedChannel);
        threadService.insert(newThread2);

        // when
        var notEmptyThreads = threadService.selectNotEmptyThreadList(savedChannel);

        // then
        assert !notEmptyThreads.contains(newThread2);
    }
}