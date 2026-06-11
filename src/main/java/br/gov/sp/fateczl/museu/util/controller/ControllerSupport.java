package br.gov.sp.fateczl.museu.util.controller;

import org.springframework.ui.Model;

public interface ControllerSupport {
    default void breadcrumb(
            Model model,
            String value) {

        model.addAttribute(
                "breadcrumb",
                value
        );
    }
}
