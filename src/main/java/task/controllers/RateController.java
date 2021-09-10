package task.controllers;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import task.modelsDAO.RateDAO;

@Component
@Controller
@RequestMapping("/rate")
public class RateController {

    private final RateDAO rateDAO;


    public RateController(RateDAO rateDAO) {
        this.rateDAO = rateDAO;
    }

    @GetMapping()
    @ResponseBody
    public String mainPage(Model model) {

        double rate = rateDAO.getRate();
        model.addAttribute("rate", rate);

        return "{\"rate\":"+rate+"}";
        //return "rate";

    }

}
