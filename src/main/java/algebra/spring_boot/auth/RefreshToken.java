package algebra.spring_boot.auth;

import algebra.spring_boot.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String refreshToken;

    private Long userId;

    private Date expiresAt;

    public RefreshToken(String refreshToken, Long userId, Date expiresAt) {
        this.refreshToken = refreshToken;
        this.userId = userId;
        this.expiresAt = expiresAt;
    }
}
