package br.gov.sp.fateczl.museu.util.controller;

import org.springframework.ui.Model;

import java.util.List;

public interface ControllerSupport {
    default void breadcrumb(Model model, String value) {
        model.addAttribute("breadcrumb", value);
    }

    default void breabdcrumbs(Model model, List<String> items) {
        model.addAttribute("breadcrumbs", items);
    }
}
