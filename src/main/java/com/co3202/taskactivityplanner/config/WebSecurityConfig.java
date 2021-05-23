package com.co3202.taskactivityplanner.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    // Adapted from https://www.baeldung.com/spring-security-jdbc-authentication
    private DataSource dataSource;

    public WebSecurityConfig(DataSource dataSource) {
        // Spring Data connects to database
        this.dataSource = dataSource;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT email, password, true FROM user where email=?")
                .authoritiesByUsernameQuery("SELECT email, role FROM user u INNER JOIN user_role ur ON(u.user_id=ur.user_id) INNER JOIN role r ON(ur.role_id=r.role_id) WHERE u.email=?")
                .passwordEncoder(passwordEncoder())
                .rolePrefix("ROLE_");}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/login", "/css/**")
                .permitAll()
                .antMatchers("/account/**", "/tasks/**", "/add-task/**", "/edit-task/**",
                        "/urgent-tasks", "/normal-tasks", "/trivial-tasks", "/finished-tasks")
                .hasAnyRole("USER, ADMIN")
                .and().formLogin().loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/tasks")
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login");

//        http.csrf().ignoringAntMatchers("/h2-console/**");
//        http.headers().frameOptions().sameOrigin();
    }
}
