package gr.aueb.mvcfilmbro.controllers;

import gr.aueb.mvcfilmbro.model.User;
import gr.aueb.mvcfilmbro.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {

        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        try {
            User user = userService.authenticate(username, password);
            session.setAttribute("loggedInUser", user); // Store user in session
            return "redirect:/profile"; // Redirect to profile page
        } catch (Exception e) {
            model.addAttribute("error", "Invalid username or password");
            return "index"; // Return back to index page with an error message
        }
    }

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/index"; // Redirect to index if not authenticated/logged in
        }

        model.addAttribute("user", user);
        return "profile"; // This should be your profile page
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Destroy session
        return "redirect:/index"; // Redirect to login page
    }
}
