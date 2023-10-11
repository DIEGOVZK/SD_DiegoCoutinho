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