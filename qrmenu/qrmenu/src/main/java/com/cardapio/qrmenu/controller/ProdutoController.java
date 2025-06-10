package com.cardapio.qrmenu.controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.cardapio.qrmenu.model.Produto;
import com.cardapio.qrmenu.repository.CategoriaRepository;
import com.cardapio.qrmenu.repository.ProdutoRepository;
import com.cardapio.qrmenu.service.FileStorageService;


@Controller
@RequestMapping("/admin/produtos")
public class ProdutoController {

  @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("produtos", produtoRepository.findAll());
        return "admin/produtos/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("produto", new Produto());
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "admin/produtos/form";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Produto produto = produtoRepository.findById(id).orElseThrow();
        model.addAttribute("produto", produto);
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "admin/produtos/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Produto produto,
                         @RequestParam("imagem") MultipartFile imagem) {

        if (!imagem.isEmpty()) {
            String nomeArquivo = fileStorageService.salvar(imagem);
            produto.setUrlImagem(nomeArquivo);
        }

        produtoRepository.save(produto);
        return "redirect:/admin/produtos";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        produtoRepository.deleteById(id);
        return "redirect:/admin/produtos";
    }
}



