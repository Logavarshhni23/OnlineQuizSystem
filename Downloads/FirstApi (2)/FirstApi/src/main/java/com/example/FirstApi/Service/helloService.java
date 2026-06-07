package com.example.FirstApi.Service;

import org.springframework.stereotype.Service;

@Service
public class helloService {

    public String greeting() {
        return "Hello World";
    }

    public String aboutme() {
        return "This is the about me";
    }

    public String lecture() {
        return "This is the lecture";
    }
}
