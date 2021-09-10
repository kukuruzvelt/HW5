package task.controllers;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import task.users.BTCtoUSDRate;
import task.users.User;
import task.usersDAO.UserDAO;


@Component
@Controller
@RequestMapping("/user")
public class FirstController {

    private final UserDAO userDAO;

    private final BTCtoUSDRate btCtoUSDRate;

    public FirstController(UserDAO userDAO, BTCtoUSDRate btCtoUSDRate) {
        this.userDAO = userDAO;
        this.btCtoUSDRate= btCtoUSDRate;
    }

    @GetMapping()
    public String mainPage() {
        return "main";
    }

    @GetMapping("/new")
    public String registerPage(@ModelAttribute("user") User user) {
        return "register";
    }

    @GetMapping("/login")
    public String loginPage(@ModelAttribute("user") User user) {
        return "login";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") User user) throws Exception {
        userDAO.save(user);
        return "successfulCreation";
    }

    @PostMapping("/check")
    public String checkUser(@ModelAttribute("user") User user) throws Exception {
        if (userDAO.check(user)) {
            return "successfulLogin";
        } else return "unSuccessfulLogin";
    }

    @GetMapping("/BTC")
    public String BTCPage(Model model) {
        double result = btCtoUSDRate.returnRate();
        model.addAttribute("result", result);
        return "BTC";
    }
}
