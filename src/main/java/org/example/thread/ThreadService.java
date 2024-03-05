package org.example.thread;

import java.util.List;
import org.example.channel.Channel;
import org.example.user.User;

public interface ThreadService {

    List<Thread> getMentionedThreadList(User user);

    List<Thread> selectNotEmptyThreadList(Channel channel);

    Thread insert(Thread thread);
}
