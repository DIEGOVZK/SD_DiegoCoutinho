package br.inatel.labs.labjpa;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import br.inatel.labs.labjpa.entity.Endereco;
import br.inatel.labs.labjpa.service.EnderecoService;

@SpringBootTest
public class EnderecoTest {
    
    @Autowired
    private EnderecoService service;

    @Test
    public void testDoUUIDGeradoPeloJPAListener() {

        Endereco endereco = new Endereco();

        endereco.setRua("Rua das Arvores");
        endereco.setNumero("525");
        endereco.setComplemento("Casa");
        endereco.setBairro("Ponta da cruz");
        endereco.setCidade("Ilha bela");
        endereco.setUf("SP");

        Endereco e = service.salvar(endereco);
        System.out.println(e);
    
    }
    
}
