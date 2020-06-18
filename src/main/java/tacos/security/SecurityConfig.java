package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

//인메모리 사용자 스토어, JDBC기반 사용자 스토어, LDAP 기반 사용자 스토어, 커스텀 사용자 명세 서비스
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //보안을 구성하는 메서드
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/design", "/orders").access("hasRole('ROLE_USER')")
                .antMatchers("/", "/**").access("permitAll")
            .and()
                .httpBasic();
    }

    //사용자 인증 정보를 구성하는 메서드
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        //사용자 정보를 직접 지정
        auth.inMemoryAuthentication()
                .withUser("user1")
                .password("{noop}password1")
                .authorities("ROLE_USER")
            .and()
                .withUser("user2")
                .password("{noop}password2")
                .authorities("ROLE_USER");
    }
}
