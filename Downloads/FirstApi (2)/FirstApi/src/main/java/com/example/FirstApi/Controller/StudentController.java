package com.example.FirstApi.Controller;


import com.example.FirstApi.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    @Autowired
    StudentService s;

    @PostMapping("/students/s1")
    public String s1(){
        return "Name:"+s.std()+"\n"+
               "Age:"+s.age()+"\n"+
                "Class:"+s.cl()+"\n"+
                "Year:"+s.year();

    }
    @PostMapping("/students/s2")
    public  String S2(){
        return "Name:"+s.std1()+"\n"+
                "Age:"+s.age1()+"\n"+
                "Class:"+s.cl1()+"\n"+
                "Year:"+s.year1();

    }
    @DeleteMapping("/students/s2")
    public  String S3(){
        return s.std2();

    }
    @PutMapping("/students/s2")
    public  String S4(){
        return "Name:"+s.std4()+"\n"+
                "Age:"+s.age()+"\n"+
                "Class:"+s.cl1()+"\n"+
                "Year:"+s.year1();

    }
}
