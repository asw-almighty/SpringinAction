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
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*
         * 사용자 정보를 찾을 때, 스프링 시큐리티의 내부 코드에서는 기본적으로 다음 쿼리를 수행한다.
         * select username,password,enabled from users where username=? 사용자의 이름, 비밀번호, 활성화 여부 검색(사용자 인증에 사용)
         * select username,authority from authorities where username=? 해당 사용자에게 부여된 권한 검색
         * select g.id, g.group_name, ga.authority from authorities g, group_members gm, group_authorities ga
         *       where gm.username=? and g.id=ga.group_id and g.id=gm.group_id 해당 사용자가 속한 그룹과 권한 그룹 검색
         */
        auth
            .jdbcAuthentication()
            .dataSource(dataSource) //data를 액세스하는 방법을 알려주는 코드, schema.sql과 data.sql을 확인하자.
//          users 테이블이 아닌 다른 테이블을 사용할 때! custom sql을 사용한다.
//          .usersByUserNameQuery("select username,password,enabled from **mans where username=?")
//          .authoritiesByUsernameQuery("select username,authority from **auths where username=?");
//          .passwordEncoder(new BCryptPasswordEncoder());
            .passwordEncoder(new NoEncodingPasswordEncoder());
    }


}
