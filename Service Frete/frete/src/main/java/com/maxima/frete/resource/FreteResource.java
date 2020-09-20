package com.maxima.frete.resource;

import com.maxima.frete.servico.FreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@CrossOrigin("http://localhost:4200")
@RequestMapping("/frete")
@RestController
public class FreteResource {

    private final FreteService freteService;

    @Autowired
    public FreteResource(FreteService freteService) {
        this.freteService = freteService;
    }

    @GetMapping
    public ResponseEntity<BigDecimal> getValueFare(@RequestParam(required = true) Integer quantidade){
       return new ResponseEntity(this.freteService.calcularFrete(quantidade), HttpStatus.OK);
    }

}
