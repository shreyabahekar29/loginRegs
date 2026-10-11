
package com.example.loginRegs.model;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class user_controller {

    private final user_service userService;

    public user_controller(user_service userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam String fullName,
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            RedirectAttributes redirectAttributes) {

        String result = userService.registerUser(
                fullName, username, email, password, confirmPassword);

        if ("SUCCESS".equals(result)) {
            redirectAttributes.addFlashAttribute(
                    "success", "Registration successful! Please log in.");
            return "redirect:/login";
        }

        redirectAttributes.addFlashAttribute("error", result);
        return "redirect:/register";
    }

    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }
}
