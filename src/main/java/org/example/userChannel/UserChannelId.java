package org.example.userChannel;

import java.io.Serializable;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserChannelId implements Serializable {
    private Long user;   // UserChannel 의 user 필드명과 동일해야함
    private Long channel; // UserChannel 의 channel 필드명과 동일해야함

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserChannelId userChannelId = (UserChannelId) o;
        return Objects.equals(getUser(), userChannelId.getUser()) && Objects.equals(getChannel(), userChannelId.getChannel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getChannel());
    }
}
