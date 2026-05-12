package br.gov.sp.fateczl.museu.service;

import br.gov.sp.fateczl.museu.domain.entity.Usuario;
import br.gov.sp.fateczl.museu.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private Validator validator;

    @Autowired
    private UsuarioRepository userRepository;

    public List<Usuario> findAll() {
        return userRepository.findAll();
    }

    
}
