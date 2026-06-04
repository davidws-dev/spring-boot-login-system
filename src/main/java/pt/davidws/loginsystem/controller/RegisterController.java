package pt.davidws.loginsystem.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pt.davidws.loginsystem.domain.User;
import pt.davidws.loginsystem.repository.UserRepository;

@Controller
public class RegisterController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // O Spring injecta automaticamente o teu repositório e o encoder de password
    public RegisterController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 1. Apresenta a página de registo ao utilizador
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    // 2. Processa os dados enviados pelo formulário brutalista
    @PostMapping("/register")
    public String registerNewOperator(@RequestParam("email") String email,
                                      @RequestParam("password") String password) {

        // Cria a entidade do utilizador com os dados recebidos
        User newOperator = new User();
        newOperator.setEmail(email);

        // CRUCIAL: Encriptar a password antes de salvar na base de dados
        newOperator.setPassword(passwordEncoder.encode(password));

        // Grava fisicamente no H2 database
        userRepository.save(newOperator);

        System.out.println(">>> [REGISTO] Novo operador adicionado à infraestrutura: " + email);

        // Redireciona de volta para o login com uma mensagem de sucesso
        return "redirect:/login?logout";
    }
}