package security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import security.models.Visitor;
import security.repositories.VisitorRepository;
import security.services.VisitorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {
    private final VisitorService visitorService;

    public AdminController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @Autowired
    private VisitorRepository visitorRepository;

    @GetMapping("/admin")
    public String admin() {
        return "admin/admin_panel";
    }

    @GetMapping("/admin/addVisitor")
    public String adminAddVisitor() {
        return "admin/addVisitor";
    }

    @PostMapping("/admin/addVisitor")
    public String addVisitor(@RequestParam String visitorname, @RequestParam String role, @RequestParam String purpose, @RequestParam String hour, @RequestParam String minute){

        List<Visitor> visitors = visitorRepository.findAll();
        for(Visitor i:visitors){
            if(i.getVisitorname().equals(visitorname)){
                return "admin/errorAdminName";
            }
        }

        for(Visitor i:visitors){
            if(role.equals("Инкассация")&& i.getRole().equals(role)){
                return "admin/errorAdminRole";
            }
        }

        hour = hour.replaceAll("[^0-9]", "");
        minute = minute.replaceAll("[^0-9]", "");
        if(Integer.parseInt(hour)>23||Integer.parseInt(hour)<0||Integer.parseInt(minute)>59||Integer.parseInt(minute)<0){
            return "/admin/errorAdmin";
        }
        Visitor visitor = new Visitor(visitorname, role, purpose, hour, minute);
        visitorService.save(visitor);
        return "redirect:/admin";
    }

    @GetMapping("/admin/showAllVisitors")
    public String adminShowAllVisitors(Model model) {
        Iterable<Visitor> visitors = visitorService.findAll();
        model.addAttribute("visitors", visitors);
        return "admin/showAllVisitors";
    }

    @RequestMapping(value = "/admin/showAllVisitors/{id}")
    public String visitorInfo(@PathVariable long id, Model model) {
        Optional<Visitor> visitor = visitorService.findById(id);
        ArrayList<Visitor> res = new ArrayList<>();
        visitor.ifPresent(res::add);
        model.addAttribute("visitor", res);
        return "admin/visitorDetails";
    }

    @RequestMapping("/admin/searchByName")
    public String searchByName(@RequestParam(name = "name") String name, Model model) {
        model.addAttribute("visitor", visitorService.findByVisitorname(name));
        return "admin/searchByName";
    }

    @RequestMapping("/admin/searchByRole")
    public String searchByRole(@RequestParam(name = "role") String role, Model model) {
        model.addAttribute("visitor", visitorService.findByRole(role));
        return "admin/searchByRole";
    }

    @RequestMapping(value = "/admin/showAllVisitors/{id}/edit")
    public String visitorEdit(@PathVariable long id, Model model) {
        Optional<Visitor> visitor = visitorService.findById(id);
        ArrayList<Visitor> res = new ArrayList<>();
        visitor.ifPresent(res::add);
        model.addAttribute("visitor", res);
        return "admin/visitorEdit";
    }

    @PostMapping("/admin/showAllVisitors/{id}/edit")
    public String visitorUpdate(@PathVariable long id,@RequestParam String visitorname, @RequestParam String role, @RequestParam String purpose, @RequestParam String hour, @RequestParam String minute){

        List<Visitor> visitors = visitorRepository.findAll();
        for(Visitor i:visitors){
            if(role.equals("Инкассация")&& i.getRole().equals(role)){
                return "admin/errorAdminRole";
            }
        }

        hour = hour.replaceAll("[^0-9]", "");
        minute = minute.replaceAll("[^0-9]", "");
        if(Integer.parseInt(hour)>23||Integer.parseInt(hour)<0||Integer.parseInt(minute)>59||Integer.parseInt(minute)<1){
            return "/admin/errorAdmin";
        }

        Visitor visitor = visitorRepository.findById(id).orElseThrow();
        visitor.setVisitorname(visitorname);
        visitor.setRole(role);
        visitor.setPurpose(purpose);
        visitor.setHour(hour);
        visitor.setMinute(minute);
        if(visitor.getRole().equals("Сотрудник")||visitor.getRole().equals("Инкассация")){
            visitor.setPurpose("");
        }
        visitorRepository.save(visitor);
        return "redirect:/admin/showAllVisitors";
    }

    @PostMapping("/admin/showAllVisitors/{id}/delete")
    public String visitorDelete(@PathVariable long id, Model model){
        Visitor visitor = visitorRepository.findById(id).orElseThrow();
        visitorRepository.delete(visitor);
        return "redirect:/admin/showAllVisitors";
    }

}
