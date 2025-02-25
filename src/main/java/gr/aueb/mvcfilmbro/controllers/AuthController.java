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

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.ModelAttribute;


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
            return "redirect:/index"; // Redirect to index if not authenticated
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

//    @PostMapping("/updateProfile")
//    public String updateProfile(@RequestParam int userId,
//                                @RequestParam String name,
//                                @RequestParam String userEmail,
//                                @RequestParam String country,
//                                @RequestParam String bio,
//                                @RequestParam String avatarUrl,
//                                @RequestParam int profileVisibility, // accountStatus
//                                HttpSession session,
//                                RedirectAttributes redirectAttributes) {
//
//        User currentUser = (User) session.getAttribute("loggedInUser");
//
//        if (currentUser == null) {
//            redirectAttributes.addFlashAttribute("errorMessage", "You must be logged in to edit your profile.");
//            return "redirect:/index"; // Redirect to login page if not authenticated
//        }
//
//        try {
//            // Map profileVisibility to accountStatus
//            int accountStatus = profileVisibility; // If profileVisibility should map to accountStatus
//
//            // Create the updated User object (using the constructor that matches the User class)
//            User updatedUser = new User(userId, name, currentUser.getPassWord(), userEmail, country, avatarUrl, accountStatus, bio);
//
//            // Update the user details in the database using UserService
//            userService.updateUser(updatedUser);
//
//            // Save the updated user back to the session
//            session.setAttribute("loggedInUser", updatedUser);
//
//            redirectAttributes.addFlashAttribute("successMessage", "Profile updated successfully!");
//            return "redirect:/profile"; // Redirect to the profile page
//
//        } catch (Exception e) {
//            redirectAttributes.addFlashAttribute("errorMessage", "An error occurred: " + e.getMessage());
//            return "redirect:/error"; // Redirect to the custom error page
//        }
//    }
@PostMapping("/updateProfile")
public String updateProfile(@ModelAttribute("user") User user, Model model) {
    System.out.println("Received user update request: " + user);
    System.out.println("Received User ID: " + user.getUserId());

    try {
        userService.updateUser(user);
        return "profile";
    } catch (Exception e) {
        model.addAttribute("error", e.getMessage());
        return "error";
    }
}




//    @PostMapping("/editmyprofile")
//    public String updateUserProfile(@RequestParam String name,
//                                    @RequestParam String userEmail,
//                                    @RequestParam String country,
//                                    @RequestParam String bio,
//                                    @RequestParam String avatarUrl,
//                                    @RequestParam int accountStatus,
//                                    HttpSession session,
//                                    RedirectAttributes redirectAttributes) {
//
//        // Retrieve the logged-in user from session
//        User loggedInUser = (User) session.getAttribute("loggedInUser");
//
//        if (loggedInUser == null) {
//            redirectAttributes.addFlashAttribute("errorMessage", "You must be logged in to edit your profile.");
//            return "redirect:/index"; // Redirect to index if not authenticated
//        }
//
//        try {
//            // Update the user object with the form data
//            loggedInUser.setUserName(name);
//            loggedInUser.setEmail(userEmail);
//            loggedInUser.setCountry(country);
//            loggedInUser.setBio(bio);
//            loggedInUser.setProfilePic(avatarUrl);
//            loggedInUser.setAccountStatus(accountStatus); // Set the account status
//
//            // Call the service method to update the user in the database
//            userService.updateUser(loggedInUser);
//
//            // Save the updated user back in the session
//            session.setAttribute("loggedInUser", loggedInUser);
//
//            // Redirect to profile page after successful update
//            redirectAttributes.addFlashAttribute("successMessage", "Profile updated successfully!");
//            return "redirect:/profile"; // Redirect to profile page
//
//        } catch (Exception e) {
//            // Handle errors (e.g., DB issues)
//            redirectAttributes.addFlashAttribute("errorMessage", "Error updating profile: " + e.getMessage());
//            return "redirect:/editmyprofile"; // Stay on the edit page if there's an error
//        }
//    }



    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Destroy session
        return "redirect:/index"; // Redirect to login page
    }
}