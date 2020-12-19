package spring.security.issue9293;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringSecurityIssue9293Application extends SpringBootServletInitializer {

    public static void main(final String[] args) {
        SpringApplication.run(SpringSecurityIssue9293Application.class, args);
    }

}
