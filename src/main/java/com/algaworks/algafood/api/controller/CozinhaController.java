package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.CozinhaInputDisassembler;
import com.algaworks.algafood.api.assembler.CozinhaModelAssembler;
import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.api.model.input.CozinhaInput;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping(value ="/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CadastroCozinhaService cozinhaCadastro;
	
	@Autowired
	private CozinhaModelAssembler cozinhaModelAssembler;
	
	@Autowired
	private CozinhaInputDisassembler cozinhaInputDisassembler; 
	
	@GetMapping
	public List<CozinhaModel> listar(){	 
		List<Cozinha> todasCozinhas = cozinhaRepository.findAll();
		return cozinhaModelAssembler.toCollectModel(todasCozinhas);
		
	}
	
	@GetMapping(value = "/{cozinhaId}") 
	public CozinhaModel buscar(@PathVariable Long cozinhaId) {
		Cozinha cozinha = cozinhaCadastro.buscarOuFalha(cozinhaId);
		 return cozinhaModelAssembler.toModel(cozinha);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CozinhaModel adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {
		
		Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);
		
		cozinha = cozinhaCadastro.salvar(cozinha);
   
		return cozinhaModelAssembler.toModel(cozinha);
	}
	
	@PutMapping(value = "/{cozinhaId}")
	public CozinhaModel atualizar(@PathVariable Long cozinhaId,@RequestBody @Valid CozinhaInput cozinhaInput){
		cozinhaInputDisassembler.toDomainObject(cozinhaInput);
		
		Cozinha cozinhaAtual = cozinhaCadastro.buscarOuFalha(cozinhaId);
		
		cozinhaInputDisassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);
		
		cozinhaAtual = cozinhaCadastro.salvar(cozinhaAtual);
		
			return cozinhaModelAssembler.toModel(cozinhaAtual);			
	}
	
	@DeleteMapping(value = "/{cozinhaId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cozinhaId){
			 cozinhaCadastro.excluir(cozinhaId);	
	}
}
