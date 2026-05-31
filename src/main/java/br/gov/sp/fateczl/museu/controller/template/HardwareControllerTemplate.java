package br.gov.sp.fateczl.museu.controller.template;

import br.gov.sp.fateczl.museu.domain.entity.Hardware;
import br.gov.sp.fateczl.museu.domain.entity.Imagem;
import br.gov.sp.fateczl.museu.service.template.HardwareServiceTemplate;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class HardwareControllerTemplate<Type extends Hardware, Service extends HardwareServiceTemplate<Type, ?>> {

    protected abstract Service getService();
    protected abstract String getViewPrefix();
    protected abstract Type newInstance();

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("itens", getService().getAll());
        return getViewPrefix() + "/lista";
    }

    @GetMapping("/{id}")
    public String detalhe(@PathVariable Long id, Model model) {
        model.addAttribute("item", getService().searchById(id));
        return getViewPrefix() + "/detalhe";
    }

    @GetMapping("/novo")
    public String formNovo(Model model) {
        model.addAttribute("hardware", newInstance()); // Thymeleaf precisa do objeto vazio
        return getViewPrefix() + "/form";
    }

    @PostMapping("/novo")
    public String inserir(
            @ModelAttribute("hardware") Type hardware,
            @RequestParam(value = "arquivos", required = false) List<MultipartFile> arquivos,
            BindingResult result,
            RedirectAttributes attrs) {

        if (result.hasErrors()) return getViewPrefix() + "/form";
        Set<Imagem> imagens = processarImagens(arquivos); // método utilitário
        getService().insert(hardware, imagens);
        attrs.addFlashAttribute("sucesso", "Cadastrado com sucesso!");
        return "redirect:/" + getViewPrefix();
    }

    @PostMapping("/{id}/deletar")
    public String deletar(@PathVariable Long id, RedirectAttributes attrs) {
        getService().deleteById(id);
        attrs.addFlashAttribute("sucesso", "Removido com sucesso!");
        return "redirect:/" + getViewPrefix();
    }

}
