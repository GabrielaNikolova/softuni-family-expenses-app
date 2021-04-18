package com.familyapp.config;

import com.familyapp.services.impl.UserDetailsAppService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {


    private final UserDetailsAppService userDetailsAppService;
    private final PasswordEncoder passwordEncoder;


    public ApplicationSecurityConfiguration(UserDetailsAppService userDetailsAppService, PasswordEncoder passwordEncoder) {
        this.userDetailsAppService = userDetailsAppService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests().
                // allow access to static resources to anyone
                        requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
                // allow access to index, family login and registration, user login and registration to anyone
                        antMatchers("/", "/families/register", "/families/confirm", "/users/login", "/users/register", "/users/login-error").permitAll().
                //protect admin page
                        antMatchers("/admin/**").hasRole("ADMIN").
                // protect all other pages
                        antMatchers("/**").authenticated().
                anyRequest().authenticated().
                and().
                // configure login with HTML form
                        formLogin().
                // our login page will be served by the controller with mapping /users/login
                        loginPage("/users/login").
                // the name of the user name input field in OUR login form is username (optional)
                // usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
                        usernameParameter("username").
                // the name of the user password input field in OUR login form is password (optional)
                // passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
                        passwordParameter("password").
                // on login success redirect here
                        defaultSuccessUrl("/users/dashboard").
                // on login failure redirect here
                        failureForwardUrl("/users/login-error").
                and().

                logout().
                // which endpoint performs logout, e.g. http://localhost:8080/logout (!this should be POST request)
                        logoutUrl("/logout").
                //custom success handler
                        logoutSuccessHandler(logoutSuccessHandler()).
                // where to land after logout
                        logoutSuccessUrl("/").permitAll().
                // remove the session from the server
                        invalidateHttpSession(true).
                // delete the session cookie
                        deleteCookies("JSESSIONID");//bye! :-)
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsAppService)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new LogoutCustomHandler();
    }

}
