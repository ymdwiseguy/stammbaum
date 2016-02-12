package com.ymdwiseguy;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FamilyTreeController {

    @RequestMapping(value = "/greeting")
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
//        return "greeting";
        FamilyTreeService familyTreeService = new FamilyTreeService();
        return familyTreeService.render();
    }
}
