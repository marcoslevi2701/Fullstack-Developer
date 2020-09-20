package com.maxima.ecommerce.resources;

import com.maxima.ecommerce.domain.Produto;
import com.maxima.ecommerce.dto.ProdutoDTO;
import com.maxima.ecommerce.services.produto.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/produtos")

public class ProdutoResource {

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoResource(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> findAll(){
        List<Produto> produtos = this.produtoService.findAll();
        if(CollectionUtils.isEmpty(produtos)){
            return new ResponseEntity("Não foram encontrados produtos", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(ProdutoDTO.fromListDTO(produtos), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/{codigo}")
    public ResponseEntity<ProdutoDTO> findByIdAndCode(@PathVariable(name = "id") String id,
                                                      @PathVariable(name = "codigo") String codigo){
        Optional<Produto> produto = this.produtoService.findByProdutoID_IdAndProdutoID_Codigo(id,codigo);
        if(!produto.isPresent()){
            return new ResponseEntity("Não foi encontrado produto com os dados informado.",HttpStatus.NOT_FOUND);
        }
        ProdutoDTO produtoDTO = ProdutoDTO.fromDTO(produto.get());
        return new ResponseEntity(produtoDTO,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> save(@RequestBody Produto produto){
        produto = this.produtoService.save(produto);
        ProdutoDTO produtoDTO = ProdutoDTO.fromDTO(produto);
        return new ResponseEntity(produtoDTO,HttpStatus.CREATED);
    }
}
