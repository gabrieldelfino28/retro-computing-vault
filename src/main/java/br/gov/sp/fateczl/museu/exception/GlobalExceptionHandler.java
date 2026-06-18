package br.gov.sp.fateczl.museu.exception;

import br.gov.sp.fateczl.museu.util.controller.ControllerSupport;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler implements ControllerSupport {

    @ExceptionHandler(BusinessRuleException.class)
    public String handleBusinessRule(BusinessRuleException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("err", ex.getMessage());
        return "redirect:/erro/negocio";
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public String handleNoResourceFound(NoResourceFoundException ex, Model model) {
        model.addAttribute("mensagem", "A página solicitada não foi encontrada.");
        breadcrumb(model, "Página não encontrada");
        return "error/404";
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleNotFound(ResourceNotFoundException ex, Model model) {
        model.addAttribute("mensagem", ex.getMessage());
        return "error/404";
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneral(Exception ex, Model model) {
        model.addAttribute("mensagem", ex.getMessage());
        return "error/500";
    }
}

