package com.example.FirstApi.Controller;


import com.example.FirstApi.Service.helloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloController {

    @Autowired
    helloService s;

    @GetMapping("/")
    public String greet(){
        return s.greeting();
    }

    @GetMapping("/about")
    public String about(){
        return s.aboutme();
    }

    @GetMapping("/about/contect")
    public String lecture(){
        return s.lecture();
    }


//   //http://localhost:8080/
//    @GetMapping("/")
//    public String greet(){
//        return "Hello World";
//    }
//
//    //http://localhost:8080/about
//    @GetMapping("/about")
//    public String about(){
//        return "This is the about page";
//    }
//
//    //http://localhost:8080/about/lecture
//    @GetMapping("/about/contect")
//    public String lecture(){
//        return "This is the contect page";
//    }

}
