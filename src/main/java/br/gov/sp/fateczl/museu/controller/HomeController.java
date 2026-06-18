package br.gov.sp.fateczl.museu.controller;

import br.gov.sp.fateczl.museu.domain.entity.Computador;
import br.gov.sp.fateczl.museu.service.ComputadorService;
import br.gov.sp.fateczl.museu.util.controller.ControllerSupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController implements ControllerSupport {

	private final ComputadorService service;

    public HomeController(ComputadorService service) {
        this.service = service;
    }

    @GetMapping("/")
	public String home(@RequestParam(defaultValue = "0") int page, Model model) {
		//Pageable pageable = PageRequest.of(page, 8);
		//Page<Computador> computadores = service.toPage(service.getAll(), pageable);
		//model.addAttribute("pagina", computadores);
		breadcrumb(model, null);
		return "index";
	}

	@GetMapping("/quem-somos")
	public String sobreFatec(Model model) {
		breadcrumb(model, "A Fatec Zona Leste");
		return "sobre/fatec";
	}

	@GetMapping("/sobre-projeto")
	public String sobreProjeto(Model model) {
		breadcrumb(model, "O Projeto");
		return "sobre/projeto";
	}
}
