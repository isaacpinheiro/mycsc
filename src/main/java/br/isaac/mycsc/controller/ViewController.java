package br.isaac.mycsc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @RequestMapping("/perfil")
    public String perfil() {
        return "perfil";
    }

    @RequestMapping("/cadastrarempresa")
    public String cadastrarEmpresa() {
        return "/cadastrarempresa";
    }

}