package spring.security.issue9293;

import java.util.Objects;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    /** {@inheritDoc} */
    @Override
    public UserDetails loadUserByUsername(final String username) {
        if (Objects.equals(username, "user")) {
            return User.withUsername(username).password("{noop}pwd").roles("USER").build();
        }
        throw new UsernameNotFoundException("user not found :" + username);
    }
}
