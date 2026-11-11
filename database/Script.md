# Script de Criação do Banco de Dados

Para o projeto ConectaONG, utilizamos o MySQL como banco de dados. A ideia foi organizar as informações dos doadores, das ONGs e das doações realizadas, deixando todos esses dados armazenados para que o sistema consiga consultar e atualizar quando necessário.

O banco foi criado pelo MySQL Workbench e recebeu o nome de `mydb`.

### Tabelas criadas

O banco possui quatro tabelas:

- **doador:** guarda as informações dos restaurantes e estabelecimentos que realizam as doações.
- **entidade_beneficente:** armazena os dados das ONGs que recebem os alimentos.
- **doacao:** registra cada doação, informando quem doou, qual ONG recebeu, a data e o status.
- **item_doacao:** guarda os alimentos de cada doação, com a quantidade, categoria, unidade de medida e validade.

As tabelas foram relacionadas utilizando chaves primárias e estrangeiras. Dessa forma, conseguimos identificar qual doador realizou determinada doação, qual ONG recebeu e quais alimentos fazem parte dela.

### Script SQL

O arquivo `conectaong_schema.sql`, que está nesta mesma pasta, contém o código SQL utilizado para criar o banco e suas tabelas.

Ele foi exportado diretamente pelo MySQL Workbench, contendo somente a estrutura do banco, sem os dados que utilizamos durante os testes.

Para utilizar o script em outro computador, basta abrir o arquivo no MySQL Workbench e executá-lo em um banco de teste. É importante não executar em um banco com dados que precisam ser mantidos, pois o script contém comandos que removem tabelas existentes.

### Integração com o Java

Depois da criação do banco, fizemos a conexão com o projeto Java utilizando Spring Boot e JPA.

Durante os testes, conseguimos cadastrar doadores, ONGs, doações e itens alimentícios. Também realizamos consultas, alterações e exclusões para verificar se as informações estavam sendo salvas corretamente.

Os registros foram conferidos diretamente no MySQL Workbench, confirmando que a aplicação estava se comunicando com o banco de dados.
