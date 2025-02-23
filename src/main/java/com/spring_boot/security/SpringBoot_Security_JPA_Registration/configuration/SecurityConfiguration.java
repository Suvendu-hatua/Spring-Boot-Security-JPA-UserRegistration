package com.spring_boot.security.SpringBoot_Security_JPA_Registration.configuration;

import com.spring_boot.security.SpringBoot_Security_JPA_Registration.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService){
        DaoAuthenticationProvider auth=new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer->
                configurer
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/resume/**").hasRole("ADMIN")
                        .requestMatchers("/security-meeting").hasAnyRole("ADMIN","TEACHER","STUDENT")
                        .requestMatchers(
                                "/css/**","/img/**","/lib/**","/js/**","/admin/img/**"
                        ).permitAll()
                        .requestMatchers("/register/**","/",
                                "/about",
                                "/classes",
                                "/contact",
                                "/school-facility",
                                "/popular-teachers",
                                "/become-teacher",
                                "/testimonial",
                                "/teacher-appointment",
                                "/submit-job-application",
                                "/reset-password",
                                "/reset-user-password"
                                ).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(formlogin->
                        formlogin
                                .loginPage("/show-login")
                                .loginProcessingUrl("/authenticateTheUser")
                                .defaultSuccessUrl("/dashboard")
                                .permitAll()
                )
                .logout(logout->logout.permitAll()  //logout endpoint is enabled.
                ).exceptionHandling(configurer->
                        configurer.accessDeniedPage("/access-denied")
                );
        return http.build();
    }
}
