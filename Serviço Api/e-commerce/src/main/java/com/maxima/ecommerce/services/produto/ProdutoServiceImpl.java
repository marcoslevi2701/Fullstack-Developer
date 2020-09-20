package com.maxima.ecommerce.services.produto;

import com.maxima.ecommerce.domain.Produto;
import com.maxima.ecommerce.dto.ItensPedidoDTO;
import com.maxima.ecommerce.repository.ProdutoRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
       this.produtoRepository = produtoRepository;
    }

    @Override
    public List<Produto> findAll() {
       return this.produtoRepository.findAll();
    }

    @Override
    public Optional<Produto> findByProdutoID_IdAndProdutoID_Codigo(String id, String codigo) {
       Validate.notNull(id,"Necessário informar o id");
       Validate.notNull(codigo,"Necessário informar o codigo do produto");
       return this.produtoRepository.findByProdutoID_IdAndProdutoID_Codigo(id,codigo);
    }

    @Override
    public Produto save(Produto produto) {
       validarInformacoesProduto(produto);
       return this.produtoRepository.save(produto);
    }

    @Override
    public List<ItensPedidoDTO> validarProdutosExistentes(List<ItensPedidoDTO> itensPedidoDTOS) {
        List<ItensPedidoDTO> itensInexistens = itensPedidoDTOS.stream()
                .filter(item ->
                  !findByProdutoID_IdAndProdutoID_Codigo(item.getId_produto(),item.getCodigo_produto()).isPresent()
                ).collect(Collectors.toList());

        return itensInexistens;
    }

    @Override
    public Long validarItensRepetidosPedido(List<ItensPedidoDTO> itensPedidoDTOS) {
        Long quantidade = itensPedidoDTOS.stream()
                                         .map(item -> item.getCodigo_produto())
                                         .distinct()
                                         .count();
       return quantidade;
    }


    private void validarInformacoesProduto(Produto produto){
        Validate.notNull(produto,"Necessário informar o produto");
        Validate.notNull(produto.obterId(),"Necessário informar o id");
        Validate.notNull(produto.obterCodigo(),"Necessário informar o codigo do produto");
        Validate.isTrue(StringUtils.isNotBlank(produto.getNome()),"Necessário informar o nome do produto");
        Validate.notNull(produto.getPrecoUnitario(),"Necessário informar o preço do produto");
    }


}
