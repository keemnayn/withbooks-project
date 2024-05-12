package kr.withbooks.web.controller.with;


import kr.withbooks.web.entity.WithView;
import kr.withbooks.web.service.MyWithService;
import kr.withbooks.web.service.WithMemberService;
import kr.withbooks.web.service.WithService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/mywith")
@Controller
public class MyWithController {

    @Autowired
    private MyWithService service;

    @Autowired
    private WithService withService;
    @GetMapping("/list")
    public  String list(Model model){
    Long userId = 1L;

    List<Long>  withIdList =   service.getListByUserId(userId);


     List<WithView> withList = withService.getListByWithIds(withIdList);

        System.out.println("비타민 = "  +withList);

        model.addAttribute("withList",withList);



        return  "mywith/list";
    }
}