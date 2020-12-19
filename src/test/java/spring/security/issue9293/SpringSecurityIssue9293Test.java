package spring.security.issue9293;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
@Import({ SecurityConfig.class, UserDetailsServiceImpl.class })
public class SpringSecurityIssue9293Test {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testLogin() throws Exception {
        this.mockMvc.perform(post("/login").param("username", "user").param("password", "pwd").with(csrf())) //
                .andExpect(status().is3xxRedirection()) //
                .andExpect(redirectedUrl("/"));
        // Login OK
    }

    @WithMockUser(value = "user")
    @Test
    public void testHome() throws Exception {
        this.mockMvc.perform(get("/")) //
                .andExpect(status().isOk()) //
                .andExpect(content().string("Home page"));
        // Home page OK
    }

    @WithMockUser(value = "user")
    @Test
    public void testUsingSharedUserDetailsService() throws Exception {
        this.mockMvc.perform(get("/").param("testSharedUserDetailsService", "true")) //
                .andExpect(status().isOk()) //
                .andExpect(content().string("Home page"));
        // java.lang.IllegalStateException: UserDetailsService is required. at
        // org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter$UserDetailsServiceDelegator.loadUserByUsername(WebSecurityConfigurerAdapter.java:485)
    }
}
