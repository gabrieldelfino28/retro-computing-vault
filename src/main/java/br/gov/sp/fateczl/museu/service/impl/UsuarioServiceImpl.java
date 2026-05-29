package br.gov.sp.fateczl.museu.service.impl;

import br.gov.sp.fateczl.museu.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

@Service
public class UsuarioServiceImpl {

    @Autowired
    private Validator validator;

    @Autowired
    private UsuarioRepository userRepository;



    
}
