package pl.connectis.cinemareservationsapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.connectis.cinemareservationsapp.model.Role;
import pl.connectis.cinemareservationsapp.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserPrincipalDetailsService userPrincipalDetailsService;
    private UserRepository userRepository;

    public SecurityConfiguration(UserPrincipalDetailsService userPrincipalDetailsService, UserRepository userRepository) {
        this.userPrincipalDetailsService = userPrincipalDetailsService;
        this.userRepository = userRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf()
                .disable()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilter(new JwtAuthenticationFilter(authenticationManager()))
            .addFilter(new JwtAuthorizationFilter(authenticationManager(), this.userRepository))
            .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/", "/movie", "/session").permitAll()
                .antMatchers("/signup", "/login").anonymous()
                .antMatchers("/register").hasRole(String.valueOf(Role.EMPLOYEE))
                .anyRequest().fullyAuthenticated()
//                .antMatchers("/mytickets").hasRole(String.valueOf(Role.CLIENT))
//                .antMatchers("/movie/**", "/room/**", "/session/**", "/ticket/**").hasRole(String.valueOf(Role.EMPLOYEE))
//                .antMatchers("/room/**").hasAnyRole("EMPLOYEE", "ADMIN")
//                .antMatchers("/client/**").permitAll()
//                .antMatchers("/room/**").hasAnyRole("EMPLOYEE", "ADMIN")
//                .antMatchers("/movie/**").hasAnyRole("EMPLOYEE", "ADMIN")
//                .antMatchers("/session/**").hasAnyRole("EMPLOYEE", "ADMIN") //Todo for further settings
//                .antMatchers("/ticket/**").hasAnyRole("CLIENT", "EMPLOYEE", "ADMIN") //Todo for further settings
            .and()
                .logout()
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .permitAll();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() throws Exception {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);
        return daoAuthenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
