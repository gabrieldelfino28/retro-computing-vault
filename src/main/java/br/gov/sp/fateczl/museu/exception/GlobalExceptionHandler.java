package br.gov.sp.fateczl.museu.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessRuleException.class)
    public String handleBusinessRule(BusinessRuleException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("err", ex.getMessage());
        return "redirect:/erro/negocio";
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneral(Exception ex, Model model) {
        model.addAttribute("mensagem", ex.getMessage());
        return "error/500";
    }
}
