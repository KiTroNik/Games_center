package pl.studia.ecommerence.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import pl.studia.ecommerence.filter.CustomAuthenticationFilter;
import pl.studia.ecommerence.filter.CustomAuthorizationFilter;


import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/token");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers(POST, "/api/users").permitAll();
        http.authorizeRequests().antMatchers(POST, "/api/token/**").permitAll();
        http.authorizeRequests().antMatchers(GET, "/api/products/all").hasAnyAuthority( "ROLE_ADMIN");
        http.authorizeRequests().antMatchers(POST, "/api/products").hasAnyAuthority( "ROLE_ADMIN");
        http.authorizeRequests().antMatchers(DELETE, "/api/products/**").hasAnyAuthority( "ROLE_ADMIN");
        http.authorizeRequests().antMatchers(PUT, "/api/products/**").hasAnyAuthority( "ROLE_ADMIN");
        http.authorizeRequests().antMatchers(GET, "/api/orders").hasAnyAuthority( "ROLE_USER");
        http.authorizeRequests().antMatchers(GET, "/api/orders/all").hasAnyAuthority( "ROLE_ADMIN");
        http.authorizeRequests().antMatchers(GET, "/api/orders/**").hasAnyAuthority( "ROLE_ADMIN");
        http.authorizeRequests().antMatchers(PATCH, "/api/orders/**").hasAnyAuthority( "ROLE_ADMIN");
        http.authorizeRequests().antMatchers(GET, "/api/products/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN");
        http.authorizeRequests().antMatchers(GET, "/api/cart/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN");
        http.cors().configurationSource(corsConfigurationSource());
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource()
    {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
