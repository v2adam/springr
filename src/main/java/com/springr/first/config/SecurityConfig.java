package com.springr.first.config;

import com.springr.first.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userService;

    @Autowired
    public void setUserService(@Qualifier("userServiceImpl") UserDetailsService userService) {
        this.userService = userService;
    }

    // a security menjen a UserDetailsService alapján
    // usernév alapján menjen a dolog
    // TODO: LDAP alapján user jogosultságok
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // jelzem neki, hogy mi alapján menjen a jelszó
        auth.userDetailsService(userService).passwordEncoder(User.PASSWORD_ENCODER);
    }


    // oldalak láthatósága
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/registration").permitAll()
                .antMatchers("/reg").permitAll()
                .antMatchers("/hello_world.xhtml").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().logoutSuccessUrl("/login?logout").permitAll()
                .and()
                .csrf().disable();

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring().antMatchers("/v2/api-docs", "/configuration/ui/**", "/swagger-resources/**", "/configuration/security/**", "/swagger-ui.html", "/webjars/**");
                //.and()
                //.ignoring().antMatchers("/js/**");
    }

}