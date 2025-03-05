package com.example.demo.security;
import com.example.demo.repository.RuoloRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
    private final AuthenticationProvider authenticationProvider;
    private final JwtFilter jwtAuthFilter;
    private final RuoloRepository roleRepository;
    public SecurityConfig(AuthenticationProvider authenticationProvider, JwtFilter jwtAuthFilter, RuoloRepository roleRepository1){
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthFilter = jwtAuthFilter;
        this.roleRepository = roleRepository1;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        var admin = roleRepository.findByNome("ADMIN")
                .orElseThrow(() -> new IllegalStateException("not found"));
        var user = roleRepository.findByNome("USER")
                .orElseThrow(() -> new IllegalStateException("not found"));
        http
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(
                                        "/auth/**"
                                ).permitAll()
                                .requestMatchers(HttpMethod.GET, "**").hasAnyAuthority(user.getNome(), admin.getNome())
                                .requestMatchers(HttpMethod.POST, "**").hasAnyAuthority(admin.getNome())
                                .requestMatchers(HttpMethod.DELETE, "**").hasAnyAuthority(admin.getNome())
                                .requestMatchers(HttpMethod.PUT, "**").hasAnyAuthority(admin.getNome())
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session-> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
