package kr.withbooks.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("profile")
public class ProfileController {
    
    // @Autowired
    // private ProfileService profileService;

    @GetMapping("edit")
    public String edit(){

        return "profile/edit";
    }
}