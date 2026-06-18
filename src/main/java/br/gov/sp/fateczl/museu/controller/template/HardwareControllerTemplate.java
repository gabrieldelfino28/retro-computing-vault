package br.gov.sp.fateczl.museu.controller.template;

import br.gov.sp.fateczl.museu.domain.entity.Hardware;
import br.gov.sp.fateczl.museu.service.HardwareService;
import br.gov.sp.fateczl.museu.util.controller.ControllerSupport;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public abstract class HardwareControllerTemplate<Type extends Hardware, Service extends HardwareService<Type>> implements ControllerSupport {

    protected abstract Service getService();
    protected abstract String getViewPrefix();
    protected abstract String getModelName();
    protected abstract String entityName();
    protected abstract Type newInstance();

    @GetMapping
    public String root() {
        return "redirect:/" + getViewPrefix() + "/listar";
    }
    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("itens", getService().getAll());
        breadcrumb(model, entityName());
        return getViewPrefix() + "/lista";
    }

    @GetMapping("/detalhe/{id}")
    public String detalhe(@PathVariable Long id, Model model) {
        var hardware = getService().searchById(id);
        model.addAttribute("item",hardware);
        breadcrumb(model,  entityName() + " / " + hardware.getModelo());
        return getViewPrefix() + "/detalhe";
    }

    @GetMapping("/novo")
    public String formNovo(Model model) {
        model.addAttribute(getModelName(), newInstance());
        breadcrumb(model, entityName() + " / Novo");
        return getViewPrefix() + "/form";
    }

    @PostMapping("/novo")
    public String inserir(@ModelAttribute Type hardware, BindingResult result, RedirectAttributes attrs) {
        if (result.hasErrors()) {
            getService().log().err(result.getAllErrors().toString());
            return getViewPrefix() + "/form";
        }

        if (hardware.getImagens() != null) {
            hardware.getImagens()
                    .forEach(img -> img.setHardware(hardware));
        }

        getService().insert(hardware);

        attrs.addFlashAttribute("sucesso", "Cadastrado com sucesso!");
        return "redirect:/" + getViewPrefix() + "/listar";
    }

    @PostMapping("/excluir/{id}")
    public String deletar(@PathVariable Long id, RedirectAttributes attrs) {
        getService().deleteById(id);
        attrs.addFlashAttribute("sucesso", "Removido com sucesso!");
        return "redirect:/" + getViewPrefix() + "/listar";
    }
}
