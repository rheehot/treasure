package hello.board.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/introduce/list").permitAll()
                .antMatchers("/board/list").hasRole("ADMIN");

        http.formLogin(); //인증 문제시 로그인 화면 뿌림.
        http.csrf().disable(); //CSRF 토큰 비활성화.
        http.logout();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //사용자 계정은 admin
        auth.inMemoryAuthentication().withUser("admin")
                // 1234 패스워드 인코딩 결과
                .password("$2a$10$uiRTiJ.drvv2OdjESeJh6uwUijdQHn69b2yAbQpN5fiv.E1HI8HIq")
                .roles("ADMIN");
    }
}
