package com.exelatech.mrad.microauth.security;

import com.exelatech.mrad.microauth.service.MyUserDetailsService;
import com.exelatech.mrad.microauthfilter.filters.JWTAuthFilter;
import com.exelatech.mrad.microauthfilter.filters.RestExceptionHandlerFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JWTAuthFilter jwtAuthFilter;

    @Autowired
    private RestExceptionHandlerFilter exceptionHandlerFilter;

    @Override
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth.userDetailsService(myUserDetailsService);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .cors().and()
        .csrf().disable()
        .authorizeRequests()
        // .antMatchers(HttpMethod.POST, "/authenticate").permitAll()
        // .antMatchers(HttpMethod.DELETE, "/authenticate").hasAnyAuthority("ADMIN")
        // .antMatchers("/publickey").permitAll()
        // .antMatchers(HttpMethod.POST, "/user").permitAll()
        // .antMatchers(HttpMethod.DELETE, "/user").hasAnyAuthority("ADMIN")
        // .antMatchers("/user").hasAnyAuthority("ADMIN")
        // .antMatchers("/user/**").hasAnyAuthority("ADMIN") // uradi preko parametara /user?username=root&...
        // .anyRequest().authenticated()
        .anyRequest().permitAll()
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(exceptionHandlerFilter, JWTAuthFilter.class); // must be first
    }

    // @Bean
    // CorsConfigurationSource corsConfigurationSource() {
    //   final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //   source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
    //   return source;
    // }

}
