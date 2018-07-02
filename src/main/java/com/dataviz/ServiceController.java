package com.dataviz;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ServiceController {
    @GetMapping("/services/dummyRequest")
    public ModelAndView success(){
        return new ModelAndView("test");
    }
}
