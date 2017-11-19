package br.com.lelis.livros.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.lelis.livros.dao.LivroDao;
import br.com.lelis.livros.modelo.Livro;

@Controller
public class LivrosController {

	private final LivroDao dao;
	
	@Autowired
	public LivrosController(LivroDao dao) {
		this.dao = dao;
	}
	
	@RequestMapping("novoLivro")
	public String form() {
		return "livros/formulario";
	}
	
	@RequestMapping("adicionaLivro")
	public String adiciona(Livro livro) {
		dao.adiciona(livro);
		return "redirect:listaLivros";
	}
	
	@RequestMapping("listaLivros")
	public String lista(Model model) {
		model.addAttribute("livros", dao.lista());
		return "livros/lista";
	}
	
	@RequestMapping("mostraFormulario")
	public String mostra(Model model,Livro livro) {
		model.addAttribute("livro",dao.buscaPorId(livro.getId()));
		return "livros/formulario-altera";
	}
	
	@RequestMapping("alteraLivro")
	public String altera(Livro livro) {
		dao.altera(livro);
		return "redirect:listaLivros";
	}
	
	@RequestMapping("removeLivro")
	public String remove(Livro livro) {
		dao.remove(livro.getId());
		return "redirect:listaLivros";
	}
	
	@RequestMapping("finalizaLivro")
	public String finaliza(Livro livro) {
		dao.finaliza(livro.getId());
		return "redirect:listaLivros";
	}
	
}
