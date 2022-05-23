package com.atguigu.security.entity;

/**
 * @author ChenCheng
 * @create 2022-05-23 15:45
 */
public class Admin {
    private Integer id;
    private  String loginacct;
    private String username;
    private String userpswd;
    private String email;
    private String createtime;

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", loginacct='" + loginacct + '\'' +
                ", username='" + username + '\'' +
                ", userpswd='" + userpswd + '\'' +
                ", email='" + email + '\'' +
                ", createtime='" + createtime + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginacct() {
        return loginacct;
    }

    public void setLoginacct(String loginacct) {
        this.loginacct = loginacct;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpswd() {
        return userpswd;
    }

    public void setUserpswd(String userpswd) {
        this.userpswd = userpswd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Admin(Integer id, String loginacct, String username, String userpswd, String email, String createtime) {
        this.id = id;
        this.loginacct = loginacct;
        this.username = username;
        this.userpswd = userpswd;
        this.email = email;
        this.createtime = createtime;
    }

    public Admin() {
    }
}
