package algebra.spring_boot.security;

import algebra.spring_boot.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private String username;

    private String password;


    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public CustomUserDetails (User user){
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

    public CustomUserDetails (String username, String password){
        this.username =username;
        this.password =password;
    }
}
