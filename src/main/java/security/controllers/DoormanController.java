package security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import security.models.Visitor;
import security.services.VisitorService;
import security.repositories.VisitorRepository;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class DoormanController {
    private final VisitorService visitorService;

    public DoormanController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @GetMapping("/doorman")
    public String doorman() {
        return "doorman/doorman_panel";
    }

    @Autowired
    private VisitorRepository visitorRepository;

    @GetMapping("/show_visitors")
    public String show_visitors(Model model) {
        Iterable<Visitor> visitors = visitorRepository.findAll();
        model.addAttribute("visitors", visitors);
        return "doorman/show_visitors";
    }

    @RequestMapping("/show-role")
    public String show_role(@RequestParam(name = "role") String role, Model model) {
        model.addAttribute("visitor", visitorService.findByRole(role));
        return "doorman/show-role";
    }

    @RequestMapping("/show-name")
    public String show_name(@RequestParam(name = "name") String name, Model model) {
        model.addAttribute("visitor", visitorService.findByVisitorname(name));
        return "doorman/show-name";
    }

    @RequestMapping(value = "/show_visitors/{id}")
    public String visitorInfo(@PathVariable long id, Model model) {
        Optional<Visitor> visitor = visitorRepository.findById(id);
        ArrayList<Visitor> res = new ArrayList<>();
        visitor.ifPresent(res::add);
        model.addAttribute("visitor", res);
        return "doorman/visitor-details";
    }
}