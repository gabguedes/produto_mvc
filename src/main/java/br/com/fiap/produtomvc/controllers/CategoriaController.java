package br.com.fiap.produtomvc.controllers;

import br.com.fiap.produtomvc.models.Categoria;
import br.com.fiap.produtomvc.repository.CategoriaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository repository;

    @GetMapping("form")
    public String loadForm(Model model){
        model.addAttribute("categoria", new Categoria());
        return "categoria/nova-categoria";
    }

    @PostMapping()
    @Transactional
    public String insert(@Valid Categoria categoria,
                         BindingResult br,
                         RedirectAttributes attributes){
        if(br.hasErrors()){
            return "categoria/nova-categoria";
        }
        repository.save(categoria);
        attributes.addFlashAttribute("mensagem", "Categoria salva com sucesso!");
        return "redirect:/categorias";
    }

    @GetMapping()
    @Transactional(readOnly = true)
    public String findAll(Model model){
        model.addAttribute("categorias", repository.findAll());
        return "categoria/listar-categoria";
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public String findById(@PathVariable ("id") Long id, Model model){
        Categoria categoria = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Categoria Inválida - id: " + id)
        );
        model.addAttribute("categoria", categoria);
        return "categoria/editar-categoria";
    }

    @PutMapping("/{id}")
    @Transactional
    public String update(@PathVariable("id") Long id,
                         @Valid Categoria categoria,
                         BindingResult br){
        if(br.hasErrors()){
            categoria.setId(id);
            return "/categoria/editar-categoria";
        }
        repository.save(categoria);
        return "redirect:/categorias";
    }

    @DeleteMapping("/{id}")
    @Transactional
    public String delete (@PathVariable("id") Long id, Model model){
        if(!repository.existsById(id)){
            throw new IllegalArgumentException("Recurso inválido - id: " + id);
        }
        try{
          repository.deleteById(id);
        }catch (Exception e){
            throw new IllegalArgumentException("Falha na integridade referencial - id: " + id);
        }

        return "redirect:/categorias";
    }
}
