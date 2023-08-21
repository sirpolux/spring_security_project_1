package com.luv2code.springboot.cruddemo.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;

import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfiguration {

    // add support for JDBC
//    @Bean
//    public UserDetailsManager userDetailsManager(DataSource dataSource){
//        return new JdbcUserDetailsManager(dataSource);
//    }
    @Bean
     public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

         //define query to retrieve a user by username
         jdbcUserDetailsManager.setUsersByUsernameQuery(
                 "select user_id,pw,enabled from members where user_id=?");
        // define query to retrieve the authorities by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_id, roles from roles where user_id=?");
        return  jdbcUserDetailsManager;

     }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer->
                configurer
                        .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
                );
        //USE Http Basic authentication
        http.httpBasic(Customizer.withDefaults());
        //disable cross site request forgery
        //in general, not required for stateless REST APis that use POST, PUT, DELETE ad/or PATCH

        http.csrf(csrf->csrf.disable());

        return http.build();
    }

        /* HARD CODED USER ACCOUNT
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123")
                .roles("EMPLOYEE").build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("EMPLOYEE","MANAGER").build();

        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("EMPLOYEE","MANAGER","ADMIN").build();

        return new InMemoryUserDetailsManager(john,mary,susan);
    }
*/
}
