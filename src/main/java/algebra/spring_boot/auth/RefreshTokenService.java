package algebra.spring_boot.auth;

import java.util.Optional;

public interface RefreshTokenService {

    RefreshToken findByUserId(Long userId);

    RefreshToken generateRefreshToken(Long userId);

    RefreshToken findByToken (String token);
}
