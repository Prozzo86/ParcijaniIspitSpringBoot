package algebra.spring_boot.user;

import javax.swing.text.html.Option;

public interface UserService {

    User findById(Long id);
    User findByUsername(String username);
}
