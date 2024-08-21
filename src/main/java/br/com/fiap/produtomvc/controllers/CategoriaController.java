package br.com.fiap.produtomvc.controllers;

import br.com.fiap.produtomvc.dto.CategoriaDTO;
import br.com.fiap.produtomvc.services.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping("/form")
    public String loadFormCategoria(Model model) {
        model.addAttribute("categoriaDTO", new CategoriaDTO());
        return "categoria/nova-categoria";
    }

    @PostMapping()
    public String insert(@Valid CategoriaDTO categoriaDTO,
                         BindingResult result,
                         RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "categoria/nova-categoria";
        }
        categoriaDTO = service.insert(categoriaDTO);
        attributes.addFlashAttribute("mensagem", "Categoria salva com sucesso!");
        return "redirect:/categorias";
    }

    @GetMapping()
    public String findAll(Model model) {
        List<CategoriaDTO> categoriasDTO = service.findAll();
        model.addAttribute("categoriasDTO", categoriasDTO);
        return "/categoria/listar-categorias";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        CategoriaDTO categoriaDTO = service.findById(id);
        model.addAttribute("categoriaDTO", categoriaDTO);
        return "/categoria/editar-categoria";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id,
                         @Valid CategoriaDTO categoriaDTO,
                         BindingResult result) {
        if (result.hasErrors()) {
            categoriaDTO.setId(id);
            return "/categoria/editar-categoria";
        }
        service.update(id, categoriaDTO);
        return "redirect:/categorias";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        service.delete(id);
        return "redirect:/categorias";
    }

}










