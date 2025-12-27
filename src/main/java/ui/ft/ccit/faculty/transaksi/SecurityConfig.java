package ui.ft.ccit.faculty.transaksi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // ========== GET API publik (Bisa dibaca semua orang) ==========
                        .requestMatchers(HttpMethod.GET, "/api/**").permitAll()

                        // ========== Write API WAJIB Token (POST, PUT, DELETE) ==========
                        .requestMatchers(HttpMethod.POST, "/api/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/**").authenticated()

                        // Endpoint umum & Swagger tanpa auth
                        .requestMatchers("/", "/error", "/actuator/health", "/swagger-ui/**", "/v3/api-docs/**").permitAll()

                        // Selain yang di atas → butuh auth
                        .anyRequest().authenticated())

                // Resource server JWT (Validasi Token Google)
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

        return http.build();
    }
}