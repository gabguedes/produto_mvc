package br.com.fiap.produtomvc.controllers;

import br.com.fiap.produtomvc.dto.CategoriaDTO;
import br.com.fiap.produtomvc.dto.LojaDTO;
import br.com.fiap.produtomvc.dto.ProdutoDTO;
import br.com.fiap.produtomvc.models.Categoria;
import br.com.fiap.produtomvc.models.Loja;
import br.com.fiap.produtomvc.models.Produto;
import br.com.fiap.produtomvc.services.CategoriaService;
import br.com.fiap.produtomvc.services.LojaService;
import br.com.fiap.produtomvc.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private LojaService lojaService;

    @ModelAttribute("lojas")
    public List<LojaDTO> lojas(){
        return lojaService.findAll();
    }

    @ModelAttribute("categorias")
    public List<CategoriaDTO> categorias() {
        return categoriaService.findAll();
    }

    @GetMapping("/form")
    public String loadForm(Model model) {
        model.addAttribute("produtoDTO", new ProdutoDTO());
        return "produto/novo-produto";
    }

    @PostMapping()
    public String insert(@Valid ProdutoDTO produtoDTO,
                         BindingResult result,
                         RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "produto/novo-produto";
        }
        produtoDTO = service.insert(produtoDTO);
        attributes.addFlashAttribute("mensagem", "Produto salvo com sucesso");
        return "redirect:/produtos/form";
    }

    @GetMapping()
    public String findAll(Model model) {
        model.addAttribute("produtos", service.findAll());
        return "/produto/listar-produtos";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        ProdutoDTO produtoDTO = service.findById(id);
        model.addAttribute("produtoDTO", produtoDTO);
        return "/produto/editar-produto";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id,
                         @Valid ProdutoDTO produtoDTO,
                         BindingResult result) {
        if (result.hasErrors()) {
            produtoDTO.setId(id);
            return "/produto/editar-produto";
        }
        service.update(id, produtoDTO);
        return "redirect:/produtos";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        service.delete(id);
        return "redirect:/produtos";
    }
}










