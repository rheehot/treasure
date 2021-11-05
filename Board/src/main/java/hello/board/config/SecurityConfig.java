package hello.board.config;

import hello.board.security.handler.LoginSuccessHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers("/introduce/list").permitAll()
                .antMatchers("/introduce/*").hasRole("USER")
                .antMatchers("/board/*").hasRole("USER")
                .antMatchers("/movie/*").hasRole("USER")
                .antMatchers("/introduce/*").hasRole("USER");
        http.formLogin(); //인증 문제시 로그인 화면 뿌림.
        http.csrf().disable(); //CSRF 토큰 비활성화.
//        http.logout();
        http.oauth2Login().successHandler(successHandler());
        http.rememberMe().tokenValiditySeconds(3600)// 1hours
                .userDetailsService(userDetailsService);
    }

    @Bean
    public LoginSuccessHandler successHandler() {
        return new LoginSuccessHandler(passwordEncoder());
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //사용자 계정은 admin
//        auth.inMemoryAuthentication().withUser("admin")
//                // 1234 패스워드 인코딩 결과
//                .password("$2a$10$uiRTiJ.drvv2OdjESeJh6uwUijdQHn69b2yAbQpN5fiv.E1HI8HIq")
//                .roles("ADMIN");
//    }
}
