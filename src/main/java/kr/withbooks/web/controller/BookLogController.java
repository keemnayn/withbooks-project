package kr.withbooks.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("booklog")
public class BookLogController {
    
    // @Autowired
    // private BooklogService;

    @GetMapping("list")
    public String list(){
        return "booklog/list";
    }
    
    @GetMapping("detail")
    public String detail(){
        return "booklog/detail";
    }
    
}