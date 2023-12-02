package br.inatel.labs.labrest.client;

import br.inatel.labs.labrest.client.dto.ProdutoDTO;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import reactor.core.publisher.Mono;

public class WebClientGetMonoProdutoPeloId {

    public static void main(String[] args) {

        try {
            Mono<ProdutoDTO> monoProdutoDTO = WebClient.create("http://localhost:8080/")
                    .get()
                    .uri("produto/{id}", 4)
                    .retrieve()
                    .bodyToMono(ProdutoDTO.class);

            ProdutoDTO produtoDTO = monoProdutoDTO.block();

            System.out.println("Produto:");
            System.out.println(produtoDTO);

        } catch (WebClientResponseException e) {
            System.out.println("Status code: " + e.getStatusCode());
            System.out.println("Mensagem: " + e.getMessage());
        }

        System.out.println("Fim da execução");
    }
}
