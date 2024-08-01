package security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import security.models.Visitor;
import security.repositories.VisitorRepository;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private VisitorRepository visitorRepository;

    @GetMapping("/")
    public String home() {
        return "user/home";
    }

    @PostMapping("/")
    public String visitorAdd(@RequestParam String visitorname, @RequestParam String role, @RequestParam String purpose, @RequestParam String hour, @RequestParam String minute){

        List<Visitor> visitors = visitorRepository.findAll();
        for(Visitor i:visitors){
            if(i.getVisitorname().equals(visitorname)){
                return "user/errorUserName";
            }
        }

        for(Visitor i:visitors){
            if(role.equals("Инкассация")&& i.getRole().equals(role)){
                return "user/errorUserRole";
            }
        }

        hour = hour.replaceAll("[^0-9]", "");
        minute = minute.replaceAll("[^0-9]", "");
        if(Integer.parseInt(hour)>23||Integer.parseInt(hour)<0||Integer.parseInt(minute)>59||Integer.parseInt(minute)<0){
            return "user/errorUser";
        }
        Visitor visitor = new Visitor(visitorname, role, purpose, hour, minute);
        visitorRepository.save(visitor);
        return "redirect:/";
    }
}
