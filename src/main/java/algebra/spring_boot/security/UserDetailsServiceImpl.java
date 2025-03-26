package algebra.spring_boot.security;

import algebra.spring_boot.user.User;
import algebra.spring_boot.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired // drugi nacin dependency injectiona
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()){
            throw new UsernameNotFoundException("Wrong credentials");
        }

        // return new CustomUserDetails(user.get().getUsername(), user.get().getPassword());
        return new CustomUserDetails(user.get());
    }
}
