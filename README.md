### Repositório de C216 SD - Sistemas distribuídos
#### Diego Anestor Coutinho
---

Sistemas computacionais distribuídos são aqueles que consistem de vários componentes que se comunicam e coordenam por meio de uma rede. Esses sistemas permitem o compartilhamento de recursos e escalabilidade.

--- 

# Sistema Nota-Compra-Item:
## Aula 1:

Este repositório contém o código-fonte de um sistema de gestão de compras desenvolvido em Java utilizando o framework Spring Boot, além de Hibernate e JPA para persistência de dados. O sistema permite o registro de fornecedores, produtos, notas de compra e itens de nota de compra.

### Mapeamento de Banco de Dados

O mapeamento das entidades para o banco de dados é realizado por meio de anotações JPA, como `@Entity`, `@Id`, `@GeneratedValue`, `@ManyToOne` e `@ManyToMany`. O Spring Boot e o Hibernate cuidam da criação das tabelas e relacionamentos no banco de dados automaticamente.

![Project UML](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/documentation/sysModel.drawio.png)

### [Fornecedor (Fornecedor.java)](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/middleware/src/main/java/br/inatel/labs/labjpa/entity/Fornecedor.java)

A entidade `Fornecedor` representa as informações sobre um fornecedor de produtos. Cada fornecedor possui um ID único e uma razão social. Os fornecedores podem estar associados a vários produtos.

#### Atributos:

- `id` (Long): Identificador único do fornecedor.
- `razaoSocial` (String): Nome ou razão social do fornecedor.
- `listaProduto` (List<Produto>): Lista de produtos fornecidos por este fornecedor.

### [Nota de Compra (NotaCompra.java)](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/middleware/src/main/java/br/inatel/labs/labjpa/entity/NotaCompra.java)

A entidade `NotaCompra` representa uma nota de compra emitida por um fornecedor. Cada nota de compra possui um ID único, data de emissão e está associada a um fornecedor. Além disso, a nota de compra pode conter vários itens de compra.

#### Atributos:

- `id` (Long): Identificador único da nota de compra.
- `dataEmissao` (LocalDate): Data de emissão da nota de compra.
- `fornecedor` (Fornecedor): Fornecedor associado à nota de compra.
- `listaNotaCompraItem` (List<NotaCompraItem>): Lista de itens de compra incluídos na nota.

#### Métodos:

- `getCalculoTotalNota()`: Método que calcula o valor total da nota de compra somando os valores dos itens de compra.

### [Item de Nota de Compra (NotaCompraItem.java)](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/middleware/src/main/java/br/inatel/labs/labjpa/entity/NotaCompraItem.java)

A entidade `NotaCompraItem` representa um item dentro de uma nota de compra. Cada item possui um ID único, está associado a um produto, possui uma quantidade e um valor de compra. O item de compra também é vinculado a uma nota de compra.

#### Atributos:

- `id` (Long): Identificador único do item de compra.
- `produto` (Produto): Produto associado ao item de compra.
- `quantidade` (Integer): Quantidade do produto comprada.
- `valorCompraProduto` (BigDecimal): Valor unitário do produto na compra.

#### Métodos:

- `getCalculoTotal()`: Método que calcula o valor total do item de compra multiplicando a quantidade pelo valor unitário.

### [Produto (Produto.java)](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/middleware/src/main/java/br/inatel/labs/labjpa/entity/Produto.java)

A entidade `Produto` representa informações sobre um produto disponível para compra. Cada produto possui um ID único, uma descrição e pode estar associado a vários fornecedores.

#### Atributos:

- `id` (Long): Identificador único do produto.
- `descricao` (String): Descrição do produto.
- `listaFornecedor` (List<Fornecedor>): Lista de fornecedores que oferecem este produto.

#### Anotações de Validação

O código-fonte também faz uso de anotações de validação fornecidas pelo framework Jakarta Validation. Estas anotações são utilizadas para garantir que os dados inseridos nas entidades estejam de acordo com as regras de validação.

As principais anotações de validação usadas no código são:

- `@NotNull`: Garante que um campo não seja nulo.
- `@Size(min = 2, max = 200)`: Define o tamanho mínimo e máximo de uma String.
- `@Past`: Garante que uma data seja no passado.
- `@Positive`: Garante que um número seja positivo.

## Conclusões ao final da aula 1:
![Project UML](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/documentation/imgDatabaseH2Aula1.png)

---  

# Services do sistema Nota-Compra-Item:
## Aula 2:

Os serviços a seguir foram criados para possibilitar a manipulação dessas entidades.

### [FornecedorService.java](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/middleware/src/main/java/br/inatel/labs/labjpa/service/FornecedorService.java)

O serviço `FornecedorService` é responsável por lidar com operações relacionadas aos fornecedores. Ele oferece métodos para salvar, buscar, listar e remover fornecedores no banco de dados.

#### Métodos:

- `salvar(Fornecedor f)`: Este método permite salvar ou atualizar um fornecedor no banco de dados. Se o fornecedor já existe, ele será atualizado; caso contrário, será criado um novo registro.
- `buscarPeloId(Long id)`: Este método busca um fornecedor no banco de dados com base no seu ID e retorna o fornecedor correspondente.
- `listar()`: Este método recupera todos os fornecedores cadastrados no banco de dados e os retorna como uma lista.
- `remover(Fornecedor f)`: Este método remove um fornecedor do banco de dados.

### [NotaCompraService.java](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/middleware/src/main/java/br/inatel/labs/labjpa/service/NotaCompraService.java)

O serviço `NotaCompraService` lida com operações relacionadas às notas de compra e itens de notas de compra. Ele oferece métodos para salvar, buscar, listar e remover notas de compra e seus itens.

#### Métodos

**Nota de Compra:**

- `salvar(NotaCompra nc)`: Salva ou atualiza uma nota de compra no banco de dados.
- `buscarNotaCompraPeloId(Long id)`: Busca uma nota de compra no banco de dados com base no seu ID.
- `listarNotaCompra()`: Recupera todas as notas de compra cadastradas no banco de dados e as retorna como uma lista.
- `remover(NotaCompra nc)`: Remove uma nota de compra do banco de dados.

**Item de Nota de Compra:**

- `salvarNotaCompraItem(NotaCompraItem nci)`: Salva ou atualiza um item de nota de compra no banco de dados.
- `buscarNotaCompraItemPeloId(Long id)`: Busca um item de nota de compra no banco de dados com base no seu ID.
- `listarNotaCompraItem()`: Recupera todos os itens de nota de compra cadastrados no banco de dados e os retorna como uma lista.
- `remover(NotaCompraItem nci)`: Remove um item de nota de compra do banco de dados.

### [ProdutoService.java](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/middleware/src/main/java/br/inatel/labs/labjpa/service/ProdutoService.java)

O serviço `ProdutoService` é responsável por operações relacionadas aos produtos. Ele oferece métodos para salvar, buscar, listar e remover produtos no banco de dados.

#### Métodos:

- `salvar(Produto p)`: Salva ou atualiza um produto no banco de dados.
- `buscarPeloId(Long id)`: Busca um produto no banco de dados com base no seu ID.
- `listar()`: Recupera todos os produtos cadastrados no banco de dados e os retorna como uma lista.
- `remove(Produto p)`: Remove um produto do banco de dados.

Estes serviços são parte essencial do sistema de gerenciamento de compras e possibilitam a interação com as entidades do sistema, permitindo a criação, consulta, listagem e exclusão de registros no banco de dados. Os serviços estão anotados com `@Service` para que possam ser injetados em outras partes do aplicativo e anotados com `@Transactional` para garantir a consistência e atomicidade das operações no banco de dados.

### Testes do serviço do produto
![testLoadProduct](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/documentation/testLoadProduct.png)

### Testes do serviço do Fornecedor
![testLoadFornecedor](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/documentation/testLoadFornecedor.png)

### Testes do serviço do NotaCompra
![testLoadNotaCompra](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/documentation/testLoadNotaCompra.png)

### Testes do serviço do testLoadNotaCompraItem
![testLoadNotaCompraItem](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/documentation/testLoadNotaCompraItem.png)

### Testes do salvamento dos itens no mundo relacional
![testItensSalvos](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/documentation/testItensSalvos.png)

---  

# Eager loading & Lazy loading, w/ PrePersist:
## Aula 3:

O código está contido no arquivo `LoadingDemo.java`, e ele ilustra a diferença entre carregamento "eager" e "lazy" de entidades relacionadas. Além disso, no arquivo `NotaCompraService.java`, foi adicionado um método para possibilitar o carregamento "lazy" planejado.

### [demoEagerLoading()](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/middleware/src/test/java/br/inatel/labs/labjpa/EnderecoTest.java)

Neste método, é demonstrado o carregamento "eager" de entidades. O código faz o seguinte:

- Carrega um item de nota de compra pelo seu ID.
- Acessa a data de emissão da nota de compra relacionada ao item.
- Imprime a data de emissão.
- Exibe a mensagem "Aconteceu o carregamento EAGER".

#### `lazyLoading()`

Neste método, é demonstrado o carregamento "lazy" de entidades. O código faz o seguinte:

- Carrega uma nota de compra pelo seu ID.
- Obtém a lista de itens de nota de compra relacionados à nota (notaCompraItem) sem forçar o carregamento dos itens.
- Calcula o tamanho da lista.
- Imprime o tamanho da lista.
- Exibe a mensagem "O carregamento foi LAZY".

#### `lazyLoadingPlanejado()`

Neste método, é demonstrado o carregamento "lazy" planejado de entidades. O código faz o seguinte:

- Carrega uma nota de compra pelo seu ID, utilizando um método personalizado `buscarNotaCompraPeloIdComListaItem`.
- Obtém a lista de itens de nota de compra relacionados à nota sem forçar o carregamento dos itens.
- Calcula o tamanho da lista.
- Imprime o tamanho da lista.
- Exibe a mensagem "O carregamento ocorreu normalmente".

### [Updated: NotaCompraService.java](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/middleware/src/main/java/br/inatel/labs/labjpa/service/NotaCompraService.java)

Para possibilitar o carregamento "lazy" planejado, foi adicionado o método `buscarNotaCompraPeloIdComListaItem` ao serviço `NotaCompraService`. Esse método permite carregar a lista de itens de nota de compra quando necessário, em vez de carregá-la automaticamente.

#### `buscarNotaCompraPeloIdComListaItem(Long id)`

Este método busca uma nota de compra no banco de dados com base no seu ID, mas também carrega a lista de itens de nota de compra associados à nota sem forçar o carregamento. Essa abordagem permite um carregamento mais eficiente das entidades relacionadas, evitando que todas as informações sejam carregadas automaticamente quando a nota de compra é recuperada.

### Teste de carrgeamento Eager
![testLoadProduct](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/documentation/testEagerLoading.png)

### Teste de carrgeamento Lazy
![testLoadProduct](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/documentation/testLazyLoadingExeption.png)

### Teste de carrgeamento Lazy (planejado)
![testLoadProduct](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/documentation/testLazyLoadingWorking.png)

## Uso do PrePersist

### UML atualizado para inclusão da classe Endereco
![testLoadProduct](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/documentation/sysModelWEndereco.drawio.png)

O código demonstra a criação de uma entidade `Endereco`, um serviço relacionado e uma integração de 1:1 com a entidade `Fornecedor`. Além disso, um teste `EnderecoTest` é apresentado para verificar o funcionamento do `PrePersist` com a geração de UUID.

### [Endereco.java](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/middleware/src/main/java/br/inatel/labs/labjpa/entity/Endereco.java)

O arquivo `Endereco.java` representa a entidade `Endereco`, que é mapeada no banco de dados. Esta entidade contém informações sobre um endereço, incluindo um UUID gerado automaticamente no momento da criação.

#### Atributos:

- `codigo` (String): O código do endereço gerado automaticamente. É uma chave primária.
- `rua` (String): Nome da rua.
- `numero` (String): Número da residência.
- `complemento` (String): Complemento do endereço (opcional).
- `bairro` (String): Nome do bairro (opcional).
- `cidade` (String): Nome da cidade.
- `uf` (String): Sigla do estado (com validação de tamanho máximo de 2).

### [EnderecoService.java](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/middleware/src/main/java/br/inatel/labs/labjpa/service/EnderecoService.java)

O arquivo `EnderecoService.java` contém o serviço responsável pelas operações relacionadas à entidade `Endereco`.

#### Métodos:

- `salvar(Endereco e)`: Este método permite salvar ou atualizar um endereço no banco de dados. Ele utiliza o `EntityManager` para realizar a operação de merge e retorna o endereço.

### [Updated: Fornecedor.java](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/middleware/src/main/java/br/inatel/labs/labjpa/entity/Fornecedor.java)

No arquivo `Fornecedor.java`, foi adicionada uma relação de 1:1 com a entidade `Endereco`. Esta associação permite que um fornecedor tenha um único endereço relacionado.

### Teste do PrePersist para geração do UUID automático
![testLoadProduct](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/documentation/testPrePersist.png)

--- 

# Uso de DTO e Repositories managed by JPA:
## Aula 4:

O código inclui definições de repositórios que interagem com entidades do banco de dados, bem como um DTO para representar o resultado de consultas personalizadas.

### [TotalCompradoPorFornecedor.java (DTO)](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/middleware/src/main/java/br/inatel/labs/labjpa/dto/TotalCompradoPorFornecedor.java)

Este arquivo representa um **DTO (Data Transfer Object)** chamado `TotalCompradoPorFornecedor`, que é usado para representar os resultados de consultas personalizadas. Este DTO possui dois atributos: `fornecedorRazaoSocial` e `totalComprado`.

- `fornecedorRazaoSocial` (String): Representa a razão social de um fornecedor.
- `totalComprado` (BigDecimal): Representa o valor total comprado por esse fornecedor.

Este DTO é útil para encapsular informações específicas de consultas e transmiti-las para outras partes do sistema de forma organizada.

### [FornecedorRepository.java](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/middleware/src/main/java/br/inatel/labs/labjpa/repository/FornecedorRepository.java)

Este arquivo representa o repositório da entidade `Fornecedor`. O repositório é uma interface que estende a interface `JpaRepository`. Ele define métodos para realizar operações de CRUD (Create, Read, Update, Delete) relacionadas à entidade `Fornecedor`.

### [NotaCompraItemRepository.java](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/middleware/src/main/java/br/inatel/labs/labjpa/repository/NotaCompraItemRepository.java)

Este arquivo representa o repositório da entidade `NotaCompraItem`. Da mesma forma que o `FornecedorRepository`, este repositório estende a interface `JpaRepository` e oferece métodos para manipular a entidade `NotaCompraItem` no banco de dados.

### [NotaCompraRepository.java](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/middleware/src/main/java/br/inatel/labs/labjpa/repository/NotaCompraRepository.java)

Este arquivo representa o repositório da entidade `NotaCompra`. Ele também estende a interface `JpaRepository` e fornece métodos para gerenciar a entidade `NotaCompra` no banco de dados.

### [ProdutoRepository.java](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/middleware/src/main/java/br/inatel/labs/labjpa/repository/ProdutoRepository.java)

Este arquivo representa o repositório da entidade `Produto`. Da mesma forma que os outros repositórios, este estende a interface `JpaRepository` e oferece métodos para realizar operações relacionadas aos produtos no banco de dados.

### [RelatorioRepository.java](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/middleware/src/main/java/br/inatel/labs/labjpa/repository/RelatorioRepository.java)

Este arquivo representa um repositório especializado que estende a interface `JpaRepository`. Ele contém uma consulta personalizada definida pela anotação `@Query`.

#### Método:

- `pesquisarTotalCompradoPorFornecedor()`: Este método realiza uma consulta personalizada para calcular o valor total comprado por fornecedor. Ele utiliza a classe `TotalCompradoPorFornecedor` para encapsular os resultados da consulta, incluindo a razão social do fornecedor e o valor total comprado.

#### Anotações:

- `@Query`: Define uma consulta personalizada usando a linguagem JPQL (Java Persistence Query Language). A consulta calcula o valor total comprado por fornecedor através da soma da quantidade multiplicada pelo valor de compra do item da nota de compra. Os resultados são encapsulados no DTO `TotalCompradoPorFornecedor`.

## Atualização dos Serviços para Utilizar Repositórios JPA

Neste repositório, foram feitas atualizações nos serviços para que eles utilizem os repositórios gerenciados pelo JPA. Isso torna o acesso ao banco de dados mais eficiente e seguro. As principais mudanças foram feitas nos serviços `FornecedorService`, `NotaCompraService`, e `ProdutoService`, além da adição do serviço `RelatorioService`.

### [FornecedorService.java](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/middleware/src/main/java/br/inatel/labs/labjpa/service/FornecedorService.java)

O arquivo `FornecedorService.java` foi atualizado para utilizar o repositório `FornecedorRepository` gerenciado pelo JPA. Agora, as operações relacionadas aos fornecedores, como salvar, buscar, listar e remover, são realizadas através do repositório.

### [NotaCompraService.java](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/middleware/src/main/java/br/inatel/labs/labjpa/service/NotaCompraService.java)

O arquivo `NotaCompraService.java` também foi atualizado para utilizar os repositórios `NotaCompraRepository` e `NotaCompraItemRepository` gerenciados pelo JPA. As operações de CRUD relacionadas a notas de compra e itens de nota de compra são realizadas através desses repositórios.

### [ProdutoService.java](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/middleware/src/main/java/br/inatel/labs/labjpa/service/ProdutoService.java)

No arquivo `ProdutoService.java`, as operações relacionadas aos produtos foram atualizadas para utilizar o repositório `ProdutoRepository`. Isso torna as operações de salvar, buscar, listar e remover produtos mais eficientes e seguras.

### [RelatorioService.java](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/middleware/src/main/java/br/inatel/labs/labjpa/service/RelatorioService.java)

Foi adicionado um novo serviço chamado `RelatorioService`, que utiliza o repositório `RelatorioRepository` gerenciado pelo JPA. Este serviço é responsável por realizar consultas personalizadas para gerar relatórios, como o total comprado por fornecedor. As consultas são definidas no repositório através da anotação `@Query` e os resultados são encapsulados em DTOs, tornando a geração de relatórios mais simples e organizada.

Essas atualizações no projeto tornam o acesso ao banco de dados mais eficiente e organizado, facilitando as operações de CRUD e a geração de relatórios personalizados.

### Testes utilizando DTO
![testLoadProduct](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/documentation/testDTO.png)

### Testes após a inclusão dos repositórios gerenciados pelo JPA
![testLoadProduct](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/documentation/testDTORepository.png)

--- 

# Laboratório de REST:
## Aula 5:
### Implementação básica para testes:
![basicRESTUML](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/documentation/helloControllerUML.drawio.png)

### [HelloController.java](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/rest/src/main/java/br/inatel/labs/labrest/server/controller/HelloController.java)

Este arquivo representa um controlador REST chamado `HelloController`. Este controlador está mapeado para o endpoint `/hello`.

#### Método:

- `processarGetHello()`: Este método está mapeado para uma requisição GET no endpoint `/hello`. Ele cria uma nova instância de `MyMessage`, define seu campo `info` para "Olá mundo REST" e retorna essa instância. Este método é usado para demonstrar uma simples interação RESTful.

#### Anotações:

- `@RestController`: Esta anotação é usada para marcar a classe como um controlador REST no Spring, significando que ela está pronta para processar requisições HTTP que chegam.

- `@RequestMapping("hello")`: Esta anotação é usada para mapear este controlador para o endpoint `/hello`.

- `@GetMapping`: Esta anotação é usada para mapear o método `processarGetHello()` para uma requisição GET no endpoint `/hello`.

### [MyMessage.java](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/rest/src/main/java/br/inatel/labs/labrest/server/controller/MyMessage.java)

Esta classe representa uma mensagem simples com um campo `info` do tipo `String`.

#### Campo:

- `info`: Este é um campo privado do tipo `String`. Ele armazena a informação que a instância de `MyMessage` carrega.

#### Métodos:

- `getInfo()`: Este método retorna o valor atual do campo `info`.

- `setInfo(String info)`: Este método recebe um parâmetro do tipo `String` e define o valor do campo `info` para o valor passado como parâmetro.

### Testes acessando o endpoint /hello
![testLoadEndpoint](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/documentation/firstExampleRest.png)

### Implementação do sistema de produtos:
![RESTUMLProdutoUm](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/documentation/controllerServiceModel.drawio.png)

### [ProdutoService.java](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/rest/src/main/java/br/inatel/labs/labrest/server/service/ProdutoService.java)

Esta classe é um serviço que gerencia uma lista de produtos. Ela é marcada com a anotação `@Service`, indicando que é um componente de serviço no Spring.

#### Campo:

- `produtos`: Este é um campo privado que armazena uma lista de produtos. Ele é inicializado como um `ArrayList` de `Produto`.

#### Métodos:

- `setup()`: Este método é marcado com a anotação `@PostConstruct`, indicando que deve ser executado após a injeção de dependência pelo Spring. Ele adiciona três produtos à lista de produtos.

- `findAll()`: Este método retorna a lista completa de produtos.

- `findById(Long id)`: Este método recebe um `id` como parâmetro e retorna um `Optional` que pode conter o produto com o `id` correspondente. Se nenhum produto com o `id` fornecido for encontrado, o `Optional` estará vazio.

- `create(Produto produto)`: Este método recebe um objeto `Produto` como parâmetro, atribui um `id` aleatório ao produto, adiciona o produto à lista de produtos e retorna o produto.

- `update(Produto produto)`: Este método recebe um objeto `Produto` como parâmetro, remove qualquer produto existente com o mesmo `id` da lista de produtos e adiciona o novo produto à lista.

- `delete(Long id)`: Este método recebe um `id` como parâmetro e remove o produto com o `id` correspondente da lista de produtos.

#### Anotações:

- `@Service`: Esta anotação é usada para marcar a classe como um componente de serviço no Spring.

- `@PostConstruct`: Esta anotação é usada para marcar o método `setup()` para ser executado após a injeção de dependência pelo Spring.

### [ProdutoController.java](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/rest/src/main/java/br/inatel/labs/labrest/server/controller/ProdutoController.java)

Esta classe é um controlador REST que gerencia as operações HTTP relacionadas aos produtos. Ela é mapeada para o endpoint `/produto`.

#### Campo:

- `service`: Este é um campo privado do tipo `ProdutoService`. Ele é injetado automaticamente pelo Spring (anotação `@Autowired`).

#### Métodos:

- `getProdutos()`: Este método está mapeado para uma requisição GET no endpoint `/produto`. Ele retorna uma lista de todos os produtos.

- `getProdutoById(Long produtoId)`: Este método está mapeado para uma requisição GET no endpoint `/produto/{id}`. Ele recebe um `id` de produto como parâmetro e retorna o produto correspondente. Se nenhum produto com o `id` fornecido for encontrado, uma exceção `ResponseStatusException` com o status HTTP `NOT_FOUND` é lançada.

- `postProduto(Produto produto)`: Este método está mapeado para uma requisição POST no endpoint `/produto`. Ele recebe um objeto `Produto` como corpo da requisição, cria um novo produto e retorna o produto criado. Este método retorna o status HTTP `CREATED`.

- `putProduto(Produto produto)`: Este método está mapeado para uma requisição PUT no endpoint `/produto`. Ele recebe um objeto `Produto` como corpo da requisição e atualiza o produto correspondente. Este método retorna o status HTTP `NO_CONTENT`.

- `deleteProduto(Long produtoId)`: Este método está mapeado para uma requisição DELETE no endpoint `/produto/{id}`. Ele recebe um `id` de produto como parâmetro e deleta o produto correspondente. Este método retorna o status HTTP `NO_CONTENT`.

#### Anotações:

- `@RestController`: Esta anotação é usada para marcar a classe como um controlador REST no Spring.

- `@RequestMapping("produto")`: Esta anotação é usada para mapear este controlador para o endpoint `/produto`.

- `@Autowired`: Esta anotação é usada para injetar automaticamente o `ProdutoService` no controlador.

- `@GetMapping`, `@PostMapping`, `@PutMapping`, `@RequestMapping`: Estas anotações são usadas para mapear os métodos para as respectivas requisições HTTP nos endpoints correspondentes.

- `@ResponseStatus`: Esta anotação é usada para definir o status HTTP retornado pelos métodos `postProduto(Produto produto)`, `putProduto(Produto produto)` e `deleteProduto(Long produtoId)`.

### [Produto.java](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/rest/src/main/java/br/inatel/labs/labrest/server/model/Produto.java)

Esta classe representa um produto com um `id`, uma `descricao` e um `preco`.

#### Campos:

- `id`: Este é um campo privado do tipo `Long`. Ele armazena o identificador único do produto.

- `descricao`: Este é um campo privado do tipo `String`. Ele armazena a descrição do produto.

- `preco`: Este é um campo privado do tipo `BigDecimal`. Ele armazena o preço do produto.

#### Construtores:

- `Produto(Long id, String descricao, BigDecimal preco)`: Este construtor aceita três parâmetros e inicializa os campos `id`, `descricao` e `preco` com os valores fornecidos.

- `Produto()`: Este é o construtor padrão sem parâmetros.

#### Métodos:

- `getId()`, `getDescricao()`, `getPreco()`: Estes métodos são getters que retornam o valor dos campos `id`, `descricao` e `preco`, respectivamente.

- `setId(Long id)`, `setDescricao(String descricao)`, `setPreco(BigDecimal preco)`: Estes métodos são setters que definem o valor dos campos `id`, `descricao` e `preco`, respectivamente.

### Testes:
A imagem abaixo representa o resultado de uma requisição GET para o endpoint `/produtos`. O resultado é uma lista de produtos em formato JSON.
![GetBrowserBasic](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/documentation/addedMockedObjectsTestBrowser.png)
![GetBrowserBasic](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/documentation/addedMockedObjects.png)

Após isso, realizou-se o teste no caminho com o id 1, e o resultado foi o seguinte:
![GetBrowserBasicIdOne](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/documentation/buscaProdutoByIdOne.png)

Ao buscar por um ID out of range, o resultado é um erro. Então realizou-se a imlementação da resposta cujo retorno é 404, com `throw new ResponseStatusException(HttpStatus.NOT_FOUND, errMsg)`, e o resultado foi o seguinte:
![GetOutOFBounds](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/documentation/BuscaPeloProdutoIdErroredByIdOutOfBounds.png)

Após editar o application.properties para remover o Stack Trace, o resultado foi o seguinte:
![GetOutOFBoundsNoStack](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/documentation/aposRemoverStackTrace.png)

---  

## Laboratório de REST parte 2 (continuação):  
## Aula 6:  

### [ProdutoDTO.java](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/client/src/main/java/br/inatel/labs/labrest/client/dto/ProdutoDTO.java)

Esta classe representa um objeto de transferência de dados (DTO) de produto com um `id`, uma `descricao` e um `preco`.

#### Campos:

- `id`: Este é um campo privado do tipo `Long`. Ele armazena o identificador único do produto.

- `descricao`: Este é um campo privado do tipo `String`. Ele armazena a descrição do produto.

- `preco`: Este é um campo privado do tipo `BigDecimal`. Ele armazena o preço do produto.

#### Métodos:

- `getId()`, `getDescricao()`, `getPreco()`: Estes métodos são getters que retornam o valor dos campos `id`, `descricao` e `preco`, respectivamente.

- `setId(Long id)`, `setDescricao(String descricao)`, `setPreco(BigDecimal preco)`: Estes métodos são setters que definem o valor dos campos `id`, `descricao` e `preco`, respectivamente.

- `hashCode()`: Este método sobrescreve o método `hashCode` da classe `Object`. Ele retorna um valor de hash para o objeto, que é baseado no campo `id`.

- `equals(Object object)`: Este método sobrescreve o método `equals` da classe `Object`. Ele verifica se o objeto dado é igual a este objeto com base no campo `id`.

### [WebClientGetFluxProduto.java](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/client/src/main/java/br/inatel/labs/labrest/client/WebClientGetFluxProduto.java)

Esta classe é responsável por fazer uma requisição GET para o endpoint `/produto` e receber uma lista de produtos.

#### Método principal:

- `main(String[] args)`: Este é o método principal que é executado quando a classe é iniciada.

  - Cria uma lista vazia de `ProdutoDTO` chamada `listaProdutoDTOs`.

  - Cria um `Flux<ProdutoDTO>` chamado `fluxProdutoDTO` que faz uma requisição GET para o endpoint `/produto` e converte a resposta em um fluxo de `ProdutoDTO`.

  - Subscreve-se ao `fluxProdutoDTO` e adiciona cada `ProdutoDTO` recebido à `listaProdutoDTOs`.

  - Bloqueia a execução até que o `fluxProdutoDTO` seja concluído.

  - Imprime a lista de `ProdutoDTO` recebida.


### [WebClientGetMonoProdutoPeloId.java](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/client/src/main/java/br/inatel/labs/labrest/client/WebClientGetMonoProdutoPeloId.java)

Esta classe é responsável por fazer uma requisição GET para o endpoint `/produto/{id}` e receber um produto específico pelo seu ID.

#### Método principal:

- `main(String[] args)`: Este é o método principal que é executado quando a classe é iniciada.

    - Cria um `Mono<ProdutoDTO>` chamado `monoProdutoDTO` que faz uma requisição GET para o endpoint `/produto/{id}` com o ID do produto desejado e converte a resposta em um `ProdutoDTO`.

    - Bloqueia a execução até que o `Mono<ProdutoDTO>` seja concluído e armazena o resultado em `produtoDTO`.

    - Imprime o `produtoDTO` no console.

    - Em caso de exceção `WebClientResponseException`, imprime o código de status e a mensagem da exceção.

    - Imprime "Fim da execução" no console.

### [WebClientPostProduto.java](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/client/src/main/java/br/inatel/labs/labrest/client/WebClientPostProduto.java)

Esta classe é responsável por criar um novo produto e fazer uma requisição POST para o endpoint `/produto`.

#### Método principal:

- `main(String[] args)`: Este é o método principal que é executado quando a classe é iniciada.

    - Cria um novo objeto `ProdutoDTO` chamado `novoProduto` e define seus campos `descricao` e `preco`.

    - Cria um `ProdutoDTO` chamado `produtoCriado` que faz uma requisição POST para o endpoint `/produto` com `novoProduto` como corpo da requisição e converte a resposta em um `ProdutoDTO`.

    - Imprime o `produtoCriado` no console.

    - Imprime "Fim da execução" no console.

### Testes:
A imagem abaixo representa o resultado de uma requisição GET para o endpoint `/produtos` através do método dentro da aplicação de cliente. O resultado é uma lista de produtos em formato JSON.
![GetFlux](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/documentation/testGetClientREST.png)

Após isso, realizou-se o teste no caminho com o id 1, e o resultado foi o seguinte:
![GetFluxByIDOne](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/documentation/testGetClientRESTSingle.png)

Ao buscar por um ID out of range, o resultado é um erro. Então realizou-se a imlementação de um handler para exeções, e o resultado foi o seguinte:
![GetFluxByIDOneWErros](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/documentation/testGetClientRESTSingleWithError.png)

Depois, realizou-se o teste para a criação de um novo produto, e o resultado foi o seguinte:
![PostFlux](https://github.com/DIEGOVZK/SD_DiegoCoutinho/blob/main/documentation/testCreateClientREST.png)