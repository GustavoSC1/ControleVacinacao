![GitHub Workflow Status](https://img.shields.io/github/workflow/status/GustavoSC1/ControleVacinacao/maven)
## Descrição

Vocês devem desenvolver um sistema para possibilitar que os profissionais de saúde possam cadastrar e consultar os dados de vacinação durante o processo de aplicação. O objetivo do sistema é permitir que o profissional de saúde possa cadastrar um cidadão, verificar quais vacinas já tomou, cadastrar os lotes das vacinas, cadastrar a vacina aplicada e a data das próximas doses, caso seja necessário. A figura baixo mostra o diagrama de caso de uso do sistema.

Obs:

1. O sistema também tem que ter a opção de cadastrar o profissional de saúde.
2. Lote de vacina = Toda vacina tem nome, laboratório, etc, mas quando ela é produzida gera o lote, um código de controle que permite saber data de fabricação, data de vencimento. Então quando chega doses de uma vacina é preciso cadastrar de que lote é e a quantidade.  
3. Quando for cadastrar a vacina de um cidadão é preciso que vincular com um lote cadastrado anteriormente.
4. A funcionalidade de "gerar relatório de vacinação" vai retornar algo bem simples como tipo de vacina, quantas doses aplicadas.

## Diagramas
![Diagrama 1](https://ik.imagekit.io/gustavosc/Teste_Software/diagrama_de_caso_de_uso_K-ZkM0Rqlsx.PNG?updatedAt=1636196834390)
![Diagrama 2](https://ik.imagekit.io/gustavosc/Teste_Software/diagrama_mOe5nZLsv.PNG?updatedAt=1636197663795)

## Script SQL
Para utilizar a aplicação, é preciso também criar o banco de dados MySQL.
Atualizar o  script à medida que o projeto for sendo desenvolvido.
```
create database vacinacao
default character set utf8
default collate utf8_general_ci;

use vacinacao;

create table funcionario (
  id int not null,
  nome varchar(30) not null,
  dataNascimento date,
  cpf varchar(11) not null,
  sexo enum('M', 'F'),
  endereco varchar(40) not null,
  conselhoRegional varchar(6) not null,
  senha varchar(20) not null,    
  primary key (id)
)default charset = utf8;
```
