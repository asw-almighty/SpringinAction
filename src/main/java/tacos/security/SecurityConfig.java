package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

//인메모리 사용자 스토어, JDBC기반 사용자 스토어, LDAP 기반 사용자 스토어, 커스텀 사용자 명세 서비스
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

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
    //ldap는 사용자가 직접 ldap 서버에서 인증받도록 하는 것.
    //ldap 디렉토리에 입력된 비밀번호를 전송 후, 사용자의 비밀번호 속성 값(userPassword)과 비교하도록 ldap 서버에 요청.
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println(new BCryptPasswordEncoder().encode("password1"));
        auth
                .ldapAuthentication()
                .userSearchBase("ou=people") //사용자를 찾기 위한 기준점 쿼리를 제공한다. 여기서는 people 구성 단위부터 검색이 시작.
                .userSearchFilter("(uid={0})")
                .groupSearchBase("ou=groups")
                .groupSearchFilter("member={0}")
//                .contextSource().url("ldap://tacocloud.com:389/dc=tacocloud,dc=com")//외부 ldap 서버의 위치 지정
                .contextSource() //내장 ldap 서버 설정
                .root("dc=tacocloud,dc=com")
                .ldif("classpath:users.ldif") //ldif 파일을 찾을 수 있는 경로 지정
            .and()
                .passwordCompare()
                .passwordEncoder(new BCryptPasswordEncoder())
                .passwordAttribute("userPasscode"); // 사용자의 비밀번호 속성 값이 userPassword가 아닐 경우!

    }


}
