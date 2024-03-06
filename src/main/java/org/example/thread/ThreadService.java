package org.example.thread;

import java.util.List;
import org.example.channel.Channel;
import org.example.common.PageDTO;
import org.example.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.jaxb.SpringDataJaxb.PageDto;

public interface ThreadService {

    List<Thread> getMentionedThreadList(User user);

    List<Thread> selectNotEmptyThreadList(Channel channel);

    Thread insert(Thread thread);

    Page<Thread> selectMentionedThreadList(Long userId, PageDTO pageDto);
}
