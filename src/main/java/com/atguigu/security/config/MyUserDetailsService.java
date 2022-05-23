package com.atguigu.security.config;

import com.atguigu.security.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenCheng
 * @create 2022-05-23 15:42
 */
@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 这个方法就是我们自己来设置从数据库里来查询用户和密码
    @Override
    public UserDetails loadUserByUsername(
            // 这个username 就是从前端发来的账号的名称
            String username

    ) throws UsernameNotFoundException {

        // 1.根据表单发来的username去数据库查询这个角色
        String sql = "SELECT id,loginacct,userpswd,username,email FROM t_admin  WHERE loginacct = ?";
        List<Admin> adminList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Admin.class), username);
        Admin admin = adminList.get(0);

        // 2.给admin设置角色和权限(创建权限的列表)
        List<GrantedAuthority> authorities = new ArrayList<>();

        // 3.给admin分配权限
        // 分配角色的时候需要加上ROLE_
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        // 分配权限
        authorities.add(new SimpleGrantedAuthority("UPDATE"));

        // 4.封装把admin对象和authorities封装到UserDetails中
        return  new User(username,admin.getUserpswd(),authorities);
    }
}
