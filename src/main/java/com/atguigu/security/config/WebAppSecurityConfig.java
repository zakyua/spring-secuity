package com.atguigu.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ChenCheng
 * @create 2022-05-23 14:20
 */
// 注意！这个类一定要放在自动扫描的包下，否则所有配置都不会生效！
@Configuration  // 表明这是一个注解类
@EnableWebSecurity  // 是一个WebSecurity
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
    @Autowired
    private MyUserDetailsService userDetailsService;

    // 这个方法是来设置登录系统的账号密码(权限，角色)
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {

        // 1.基于内存登录的方式来设置
       /* builder.inMemoryAuthentication()
                .withUser("tom").password("123123").roles("ADMIN")  //设置账号和密码以及角色
                .and()
                .withUser("jerry").password("456456").authorities("SAVE","EDIT"); // 设置账号和密码以及权限
*/
        // 2.基于数据库查询的方式来设置
        builder.userDetailsService(userDetailsService);
    }

    // 这个方法是用来保护资源的，对一些资源需要什么样的权限(角色)都在这里设置
    @Override
    protected void configure(HttpSecurity security) throws Exception {

        security.authorizeRequests()                            // 对请求进行授权
                .antMatchers("/index.jsp")         // 针对index.jsp路径进行设置
                .permitAll()                                   // 全部放行
                .antMatchers("/layui/**")         // 针对layui下的资源
//                .hasRole("")       这就是对当前资源需要什么样的权限或者需要什么样的角色才能访问
//                .hasAuthority("")   这是对当前资源需要什么样的权限才能访问
                .permitAll()                                   // 全部放行
                .and()
                .authorizeRequests()                            // 对请求进行授权
                .anyRequest()                                   // 对任意的请求
                .authenticated()                                // 都需要拥有权限
                 .and()
                .formLogin()                                    // 使用表单形式提交
                .loginPage("/index.jsp")                        // 指定登录页面
                .loginProcessingUrl("/do/login.html")         // 指定提交表单的地址
                .usernameParameter("loginAcct")               // 设置表单发来的用户名
                .passwordParameter("userPswd")                // 设置表单发来的密码
                .defaultSuccessUrl("/main.html")             // 登录成功后要去的页面
                .and()
                // 这个退出的请求如果不关闭csrf功能，那么退出的按钮必须的是一个"post"请求
                // 关闭csrf功能   .and.csrf().disable()
                .logout()                                     // 退出程序
                .logoutUrl("/do/logout.html")                 // 处理退出程序的url
                .logoutSuccessUrl("/index.jsp")             // 退出程序后需要去的主页面
                .and()                                      // 这个是来设置如果你没有响应的权限会让你去那个页面
                .exceptionHandling()
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
                        request.setAttribute("message","抱歉！您无法访问这个资源！☆☆☆");
                        request.getRequestDispatcher("/WEB-INF/views/no_auth.jsp").forward(request,response);
                    }
                });




    }




}
