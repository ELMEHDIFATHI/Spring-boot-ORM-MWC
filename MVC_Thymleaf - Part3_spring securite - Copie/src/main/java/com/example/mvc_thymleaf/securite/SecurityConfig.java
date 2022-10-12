package com.example.mvc_thymleaf.securite;

import com.example.mvc_thymleaf.securite.services.UserDetailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailServices userDetailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //ldap

        PasswordEncoder passwordEncoder=passwordEncoder();

         /*
        String encodePwd=passwordEncoder.encode("1234");
        auth.inMemoryAuthentication().
                withUser("user1").password(encodePwd).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder.encode("12345")).roles("USER","ADMIN");

         */


        /*
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username as principal, password as credentials, active from users where username=?")
                .authoritiesByUsernameQuery("select username as principal, role as role from users_role where username=?")
                .rolePrefix("ROLE_")
                .passwordEncoder(passwordEncoder);

        */

        auth.userDetailsService(userDetailService);




    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").permitAll();;
        //http.formLogin();
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/user/**").hasAuthority("USER");
        http.authorizeRequests().antMatchers("/admin/**").hasAuthority("ADMIN");
        //http.authorizeRequests().anyRequest().authenticated();
        http.exceptionHandling().accessDeniedPage("/403");

        // les mot pas sont hash



    }
    @Bean
        PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
        }

}
