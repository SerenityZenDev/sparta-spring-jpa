package org.example.mention;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.common.Timestamp;
import org.example.thread.Thread;
import org.example.user.User;

// lombok
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

// jpa
@Entity
public class ThreadMention extends Timestamp {

    /**
     * 컬럼 - 연관관계 컬럼을 제외한 컬럼을 정의합니다.
     */
    @EmbeddedId
    private ThreadMentionId threadMentionId;


    /**
     * 생성자 - 약속된 형태로만 생성가능하도록 합니다.
     */
    @Builder
    public ThreadMention(User user, Thread thread) {
        this.user = user;
        this.thread = thread;
        this.threadMentionId = getThreadMentionId(user, thread);
    }

    private static ThreadMentionId getThreadMentionId(User user, Thread thread) {
        var id = new ThreadMentionId();
        id.setThreadId(thread.getId());
        id.setUserId(user.getId());
        return id;
    }

    /**
     * 연관관계 - Foreign Key 값을 따로 컬럼으로 정의하지 않고 연관 관계로 정의합니다.
     */
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("threadId")
    @JoinColumn(name = "thread_id")
    Thread thread;

    /**
     * 연관관계 편의 메소드 - 반대쪽에는 연관관계 편의 메소드가 없도록 주의합니다.
     */

    /**
     * 서비스 메소드 - 외부에서 엔티티를 수정할 메소드를 정의합니다. (단일 책임을 가지도록 주의합니다.)
     */

}
