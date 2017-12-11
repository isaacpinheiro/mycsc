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

    @RequestMapping("/login")
    public String login() {
        return "/login";
    }

    @RequestMapping("/edashboard")
    public String enterpriseDashboard() {
        return "edashboard";
    }

    @RequestMapping("/eperfil")
    public String enterprisePerfil() {
        return "eperfil";
    }

    @RequestMapping("/eprodutos")
    public String enterpriseProdutos() {
        return "eprodutos";
    }

    @RequestMapping("/adicionarproduto")
    public String adicionarProduto() {
        return "adicionarproduto";
    }

    @RequestMapping("/alterarproduto")
    public String alterarProduto() {
        return "alterarproduto";
    }

    @RequestMapping("/mensagemempresa")
    public String mensagemEmpresa() {
        return "mensagemempresa";
    }

    @RequestMapping("/mensagemproduto")
    public String mensagemProduto() {
        return "mensagemproduto";
    }

}