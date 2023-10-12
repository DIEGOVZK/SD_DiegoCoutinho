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