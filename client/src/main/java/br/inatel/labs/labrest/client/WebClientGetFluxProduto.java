package br.inatel.labs.labrest.client;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.reactive.function.client.WebClient;

import br.inatel.labs.labrest.client.dto.ProdutoDTO;
import reactor.core.publisher.Flux;

public class WebClientGetFluxProduto {

    public static void main(String[] args) {

        List<ProdutoDTO> listaProdutoDTOs = new ArrayList<ProdutoDTO>();

        Flux<ProdutoDTO> fluxProdutoDTO = WebClient.create("http://localhost:8080/")
                .get()
                .uri("produto")
                .retrieve()
                .bodyToFlux(ProdutoDTO.class);

        fluxProdutoDTO.subscribe(p -> listaProdutoDTOs.add(p));
        fluxProdutoDTO.blockLast(Duration.ofMillis(1000));

        System.out.println("Lista de produtos:");
        System.out.println(listaProdutoDTOs);

        System.out.println("Fim da execução");
    }
}
