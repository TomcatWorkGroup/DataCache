package com.itdreamworks.datacacheoutput.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.servlet.http.Cookie;

@Service
public class TokenService {
    @Value("${token.user}")
    String userTokenName;

    public Cookie getUserToken(String userId){
        return new Cookie(userTokenName,userId);
    }

    public boolean verifyUserToken(Cookie tokenCookie){
        if(null == tokenCookie)
            return false;
        return true;
    }

}
