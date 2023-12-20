package com.edutech.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    /*
    //등록 후 실행하면 로그인 창에 입력한 비밀번호가 암호화 되어야 있어야 하므로 암호화된 비밀번호를 입력하여야 로그인이 이루어진다.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    */

    /*
    //등록 후 실행하면 로그인 창을 찾지 못하므로 view(jsp나 Thymeleaf)를 작성한 후 formLogin() 메소드로 반드시 로그인 창을 지정하여 설정하여야 한다.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.build();
    }
    */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable());
        http
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        .requestMatchers(new AntPathRequestMatcher("/**")).permitAll().requestMatchers(new AntPathRequestMatcher("/board/read")).hasRole("USER").anyRequest().authenticated());
        http.formLogin((formLogin) -> formLogin.loginPage("/member/login").loginProcessingUrl("/member/loginPro").usernameParameter("username").passwordParameter("password").defaultSuccessUrl("/").failureUrl("/member/login"));
        http.logout((logout) -> logout.logoutUrl("/member/logout").logoutSuccessUrl("/"));
        //http.logout(Customizer.withDefaults());
        return http.build();
    }

}
