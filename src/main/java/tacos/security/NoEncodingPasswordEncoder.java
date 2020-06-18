package tacos.security;

import org.springframework.security.crypto.password.PasswordEncoder;

public class NoEncodingPasswordEncoder implements PasswordEncoder {

    //로그인 대화상자에서 입력된 비밀번호(rawPwd)를 암호화하지 않고 String으로 반환
    @Override
    public String encode(CharSequence rawPwd){
        return rawPwd.toString();
    }
    @Override
    public boolean matches(CharSequence rawPwd, String encodedPwd){
        return rawPwd.toString().equals(encodedPwd);
    }
}
