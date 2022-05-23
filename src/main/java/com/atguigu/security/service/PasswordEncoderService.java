package com.atguigu.security.service;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author ChenCheng
 * @create 2022-05-23 16:25
 */
public class PasswordEncoderService implements PasswordEncoder {


    // 这个方法是加密的
    @Override
    public String encode(CharSequence rawPassword) {

        return null;
    }

    // 这个方法是比较的
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return false;
    }
}
