package br.gov.sp.fateczl.museu.controller;

import br.gov.sp.fateczl.museu.controller.template.HardwareControllerTemplate;
import br.gov.sp.fateczl.museu.domain.entity.Computador;
import br.gov.sp.fateczl.museu.domain.enums.TipoComputador;
import br.gov.sp.fateczl.museu.service.ComputadorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/computador")
public class ComputadorController extends HardwareControllerTemplate<Computador, ComputadorService> {

    private final ComputadorService service;

    public ComputadorController(ComputadorService service) {
        this.service = service;
    }

    @Override
    protected ComputadorService getService() {
        return service;
    }

    @Override
    protected String getViewPrefix() {
        return "computador";
    }

    @Override
    protected String getModelName() {
        return "computador";
    }

    @Override
    protected String entityName() {
        return "Computador";
    }

    @Override
    protected Computador newInstance() {
        return new Computador();
    }

    @ModelAttribute("tipos")
    public TipoComputador[] getTipos() {
        return TipoComputador.values();
    }
}

