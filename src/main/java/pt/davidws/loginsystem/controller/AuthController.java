package pt.davidws.loginsystem.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pt.davidws.loginsystem.service.UserService; // Importa o teu service

@Controller
public class AuthController {

    private final UserService userService; // 1. Declara o service

    // 2. Construtor para injetar o service automaticamente
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/home")
    public String homePage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("email", userDetails.getUsername());
        return "home";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String name,
                               @RequestParam String email,
                               @RequestParam String password,
                               Model model) {
        try {
            // 3. Chamada real ao service que criámos
            userService.registerUser(name, email, password);

            model.addAttribute("success", "[OK] OPERADOR REGISTADO! EFETUE O LOGIN AGORA.");
            return "register";
        } catch (Exception e) {
            // Se o email já existir ou outro erro ocorrer, o catch captura e mostra na UI
            model.addAttribute("error", "[ERRO] " + e.getMessage());
            return "register";
        }
    }
}