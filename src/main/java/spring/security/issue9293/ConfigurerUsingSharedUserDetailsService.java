package spring.security.issue9293;

import java.util.Objects;

import javax.servlet.Filter;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;

public class ConfigurerUsingSharedUserDetailsService
        extends AbstractHttpConfigurer<ConfigurerUsingSharedUserDetailsService, HttpSecurity> {

    /** {@inheritDoc} */
    @Override
    public void configure(final HttpSecurity httpSecurity) {
        // get shared UserDetailsService
        final UserDetailsService sharedUserDetailsService = httpSecurity.getSharedObject(UserDetailsService.class);
        // add a filter using shared UserDetailsService when testSharedUserDetailsService parameter is true
        Filter filter = (request, response, chain) -> {
            if (Objects.equals(request.getParameter("testSharedUserDetailsService"), "true")) {
                sharedUserDetailsService.loadUserByUsername("user");
            }
            chain.doFilter(request, response);
        };
        filter = this.postProcess(filter);
        httpSecurity.addFilterBefore(filter, RememberMeAuthenticationFilter.class);
    }
}
