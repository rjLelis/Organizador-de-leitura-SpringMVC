package br.com.lelis.livros.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OlaMundoController {

	@RequestMapping("/olaMundo")
	public String ola() {
		return "livros/ola-mundo";
	}
}
