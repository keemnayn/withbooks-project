package kr.withbooks.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("font")
public class FontController {
    
    @GetMapping("list")
    public String list(){

        return "font/list";
    }
}
