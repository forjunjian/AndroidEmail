package com.forjun.email;


import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 实现参考：http://blog.csdn.net/qqgrid/article/details/40402287
 */
public class SimpleAuthenticator extends Authenticator {
    String userName = null;
    String password = null;

    public SimpleAuthenticator() {
    }

    public SimpleAuthenticator(String username, String password) {
        this.userName = username;
        this.password = password;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
    }
}
