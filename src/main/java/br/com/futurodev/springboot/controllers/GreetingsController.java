package br.com.futurodev.springboot.controllers;

import br.com.futurodev.springboot.model.ProdutoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.futurodev.springboot.repository.ProdutoRepository;

import java.util.List;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
    @Autowired
    private ProdutoRepository produtoRepository;

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String mensagem(@PathVariable String name) {
        return "Olá " + name + " estamos começando nosso trabalho com Spring Boot!";
    }

    @RequestMapping(value = "/media/{numero1}&{numero2}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public double media(@PathVariable String numero1, @PathVariable String numero2) {
        double n1 = Double.parseDouble(numero1);
        double n2 = Double.parseDouble(numero2);
        double media = (n1 + n2)/2;
        return media;
    }

    @RequestMapping(value = "/produto/{descricao}" , method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String salvar(@PathVariable String descricao){

        ProdutoModel produto = new ProdutoModel();
        produto.setDescricao(descricao);
        produtoRepository.save(produto);


        return "Produto "+descricao+" registrado com sucesso!";

    }

    @GetMapping(value = "/produtos")
    @ResponseBody
    public ResponseEntity<List<ProdutoModel>> listarProdutos(){

        List<ProdutoModel> produtos = produtoRepository.findAll();

        return new ResponseEntity<List<ProdutoModel>>(produtos, HttpStatus.OK);

    }
//
//    @GetMapping(value = "/produtos/findId")
//    @ResponseBody
//    public ResponseEntity<String> findPelaId (@RequestParam Long idProd){
//       produtoRepository.findById(idProd);
//
//
//        return new ResponseEntity<String>("Produto deletado com sucesso.",HttpStatus.OK);
//
//
//    }

    @PostMapping(value = "/produto/salvar")
    @ResponseBody
    public ResponseEntity<ProdutoModel> salvar(@RequestBody ProdutoModel produto){

        ProdutoModel prod = produtoRepository.save(produto);
        return new ResponseEntity<ProdutoModel>(prod, HttpStatus.CREATED);

    }

    @DeleteMapping(value = "/produto/delete")
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long idProduto){

        produtoRepository.deleteById(idProduto);

        return new ResponseEntity<String>("Produto deletado com sucesso.",HttpStatus.OK);

    }


}

