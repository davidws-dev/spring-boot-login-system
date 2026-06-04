package pt.davidws.loginsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Criptografia padrão de mercado (nunca guardar passwords em texto limpo)
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/forgot-password", "/reset-password", "/h2-console/**").permitAll() // Páginas públicas
                        .anyRequest().authenticated() // Tudo o resto exige login
                )
                .formLogin(form -> form
                        .loginPage("/login") // O URL da nossa página personalizada de login
                        .defaultSuccessUrl("/home", true) // Para onde vai após o login com sucesso
                        .usernameParameter("email") // O nome do campo do formulário HTML
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        // ATENÇÃO: Estas duas linhas abaixo servem APENAS para podermos aceder à consola do H2 no browser.
        // Em produção, isto nunca deve ser feito desta forma.
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"));
        http.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

        return http.build();
    }
}