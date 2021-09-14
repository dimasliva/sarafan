package sarafan.sarafan.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import sarafan.sarafan.domain.User;
import sarafan.sarafan.repo.UserDetailsRepo;

import java.time.LocalDateTime;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .mvcMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }

    @Bean
    public PrincipalExtractor principalExtractor(UserDetailsRepo userDetailsRepo) {
        return  map -> {
            String id = (String) map.get("sub");
            User user = userDetailsRepo.findById(id).orElseGet(() -> {
                User newUser = new User();

                newUser.setId(id);
                newUser.setEmail((String) map.get("email"));
                newUser.setName((String) map.get("name"));
                newUser.setGender((String) map.get("gender"));
                newUser.setUserpic((String) map.get("picture"));
                newUser.setLocale((String) map.get("locale"));

                return newUser;
            });

            user.setLastVisit(LocalDateTime.now());
            return userDetailsRepo.save(user);
        };
    }
}
