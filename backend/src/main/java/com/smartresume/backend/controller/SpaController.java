package com.smartresume.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpaController {

    // Forwards all paths that do not contain a dot (files) to index.html
    // This allows Angular routes like /dashboard to be handled by the frontend
    @RequestMapping(value = "/{path:[^\\.]*}")
    public String redirect() {
        return "forward:/index.html";
    }
}
