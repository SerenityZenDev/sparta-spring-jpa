package org.example.channel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.common.Timestamp;
import org.example.thread.Thread;
import org.example.user.User;
import org.example.userChannel.UserChannel;

// lombok
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

// jpa
@Entity
public class Channel extends Timestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 컬럼 - 연관관계 컬럼을 제외한 컬럼을 정의합니다.
     */
    private String name;

    @Enumerated(EnumType.STRING)
    private Type type;


    public enum Type {
        PULBIC, PRIVATE
    }


    /**
     * 생성자 - 약속된 형태로만 생성가능하도록 합니다.
     */
    @Builder
    public Channel(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    /**
     * 연관관계 - Foreign Key 값을 따로 컬럼으로 정의하지 않고 연관 관계로 정의합니다.
     */
    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Thread> threads = new LinkedHashSet<>();

    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserChannel> userChannels = new LinkedHashSet<>();

    /**
     * 연관관계 편의 메소드 - 반대쪽에는 연관관계 편의 메소드가 없도록 주의합니다.
     */
    public void addThread(Thread thread) {
        this.threads.add(thread);
    }

    public UserChannel joinUser(User user) {
        var userChannel = UserChannel.builder().user(user).channel(this).build();
        this.userChannels.add(userChannel);
        user.getUserChannels().add(userChannel);
        return userChannel;

    }

    /**
     * 서비스 메소드 - 외부에서 엔티티를 수정할 메소드를 정의합니다. (단일 책임을 가지도록 주의합니다.)
     */

    /**
     * 라이프 사이클 메소드
     */



    /*
    과제 : ContextHolder + 엔티티 라이프 사이클 이벤트(@PrePersist, @PreUpdate)
    를 사용해서 createdBy, modifiedBy를 구현
     */

}
