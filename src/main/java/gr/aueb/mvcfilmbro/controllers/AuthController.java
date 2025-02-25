package gr.aueb.mvcfilmbro.controllers;

import gr.aueb.mvcfilmbro.model.User;
import gr.aueb.mvcfilmbro.model.Country;
import gr.aueb.mvcfilmbro.service.CountryService;
import gr.aueb.mvcfilmbro.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
public class AuthController {

    private final UserService userService;
    private final CountryService countryService;

    @Autowired
    public AuthController(UserService userService, CountryService countryService) {

        this.userService = userService;
        this.countryService = countryService;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model,
                        RedirectAttributes redirectAttributes) {
        try {
            User user = userService.authenticate(username, password);

            if(user == null) {
                redirectAttributes.addFlashAttribute("error", "Wrong username or password");
                return "/index";
            } else {
                session.setAttribute("loggedInUser", user); // Store user in session
                return "redirect:/profile"; // Redirect to profile page
            }
        } catch (Exception e) {
            model.addAttribute("error", "Wrong username or password");
            return "/index"; // Return back to login page with an error message
        }
    }

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "You must be logged in to see your profile.");
            return "redirect:/index"; // Redirect to login if not authenticated
        }

        model.addAttribute("user", user);
        return "profile"; // This should be your profile page
    }

    @GetMapping("/editmyprofile")
    public String editProfile(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "You must be logged in to edit your profile.");
            return "redirect:/index"; // Redirect to index if not authenticated
        }

        model.addAttribute("user", loggedInUser);

        //Fetch the list of countries from CountryService
        List<Country> countries = countryService.getAllCountries();
        model.addAttribute("countries", countries);

        // Get country name from the country code
        String selectedCountryName = countryService.getCountryNameByCode(loggedInUser.getCountry());
        model.addAttribute("selectedCountryName", selectedCountryName);

        return "editmyprofile"; // This should match your Thymeleaf template name
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Destroy session
        return "redirect:/index"; // Redirect to login page
    }
}