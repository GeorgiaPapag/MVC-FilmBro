package gr.aueb.mvcfilmbro.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class CustomErrorController implements ErrorController {

    // This method will handle errors and redirect to a custom error page
    @RequestMapping("/error")
    public String handleError(Model model) {

        model.addAttribute("error", "An unexpected error occurred!");
        // Return the error page error.html
        return "error"; // Replace with the name of your error page template
    }
}
