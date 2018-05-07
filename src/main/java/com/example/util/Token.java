package com.example.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by DL-NG-ZJ-0019 on 2017/11/29.
 */
public class Token {
    private static final Logger logger = LoggerFactory.getLogger(Token.class);

    private static final String TOEKRN_STR="token";
    private static String getToken(HttpSession session) {
        String obj = String.valueOf(session.getAttribute(TOEKRN_STR));
        if (obj != null) {
            return  obj;
        } else {
            return "";
        }
    }

    private static void saveTokenString(String tokenStr, HttpSession session) {
        session.setAttribute(TOEKRN_STR,tokenStr);
    }

    private static String generateTokenString(String username){
        String newstr="";
        try {
            MessageDigest md5=MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            String str = username + new Date();
            //加密后的字符串
            newstr = base64en.encode(md5.digest(username.getBytes("utf-8")));
        }catch (Exception e){
            logger.error(e.toString());
        }

        return newstr;
    }

/** *//**
     * Generate a token string, and save the string in session, then return the token string.
     * @param  session
     * @return a token string used for enforcing a single request for a particular transaction.
     */

    public static String getTokenString(String username,HttpSession session) {
        String tokenStr = generateTokenString(username);
        saveTokenString(tokenStr,session);
        return tokenStr;
    }

/** *//**

     * check whether token string is valid. if session contains the token string, return true.
     * otherwise, return false.
     * @param  tokenStr
     * @param  session
     * @return true: session contains tokenStr; false: session is null or tokenStr is id not in session
     */

    public static boolean isTokenStringValid(String tokenStr, HttpSession session) {
        boolean valid = true;
        if(session != null){
            String username = getToken(session);
            if (username==""||username==null) {
                valid = false;
            }
        }
        return valid;
    }
}
