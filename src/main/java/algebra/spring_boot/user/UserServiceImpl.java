package algebra.spring_boot.user;

import algebra.spring_boot.auth.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()){
            throw new RuntimeException("User not found.");
        }

        return user.get();
    }

    @Override
    public User findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()){
            throw new RuntimeException("User not found.");
        }

        return user.get();
    }
}
