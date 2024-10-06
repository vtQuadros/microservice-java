package br.edu.atitus.paradigma.produto_service.controlers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.atitus.paradigma.produto_service.entities.ProdutoEntity;
import br.edu.atitus.paradigma.produto_service.repositories.ProdutoRepository;

@RestController
@RequestMapping("produto-service")
public class ProdutoController {
	
	private final ProdutoRepository produtoRepository;
	
	public ProdutoController(ProdutoRepository produtoRepository) {
		super();
		this.produtoRepository = produtoRepository;
	}
	
	@Value("${server.port}")
	private int porta;
	
	
	@GetMapping("/{idProduto}/{moeda}")
	public ResponseEntity<ProdutoEntity> getProduto(
			@PathVariable Integer idProduto,
			@PathVariable String moeda) throws Exception{
		
		
		ProdutoEntity produto = produtoRepository.findById(idProduto).orElseThrow(() -> new Exception(" Produto não encontrado"));
		
		produto.setAmbiente("Produto-service run in port: " + porta);
		
		return ResponseEntity.ok(produto);
				
		
	}


	
	
}