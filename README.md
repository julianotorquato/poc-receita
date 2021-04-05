# POC Receita

## Tabela de Índice

1.  [Descrição](#id-descricao)
2.  [Pré-requisitos](#id-requisitos)
3.  [Tecnologias](#id-tecnologias)
4.  [Storage](#id-storage)
5.  [Instalação e execução](#id-instalacao)
6.  [Autor](#id-autor)

<div id='id-descricao'/>

## Descrição

**POC Receita** é uma aplicação SprintBoot standalone na linguagem Java e com SpringBoot como seu principal framework.

---

<div id='id-requisitos'/>

## ✋🏻 Pré-requisitos

- Java (1.8)

---

<div id='id-tecnologias'/>

### 🚀 Tecnologias

- [SpringBoot](https://spring.io/) - facilita a criação de aplicações Spring com poucas configurações e não faz uso de XML, usa anotações.
- [Maven](https://maven.apache.org/) - ferramenta de building para: testar e executar.
- [MapsStruct](https://mapstruct.org/) - realiza a conversão dos DTO para os domains e vise versa.
- [Lombok](https://projectlombok.org/) - diminui a verbosidade do código.

---

<div id='id-storage'/>

## Storage

Na raiz do projeto tem uma pasta cujo nome é **storage**. Nela contém os arquivos que a aplicação manipula durante a execução. 

* storage
    * in: Diretório onde possui os CSV's de entradas para processar.
    * out: Diretório onde possui os CSV's de saídas / processados.
    * log: Diretório de log das ocorrências. Caso alguma linha do csv não consegue ser processada.

---


<div id='id-instalacao'/>

## 🔥 Instalação e execução

Faça o clone desse repositório e entre na pasta

```
cd poc-receita
```

Instalar as dependências

```
mvn install -DskipTests
```

Executar aplicação

```
mvn spring-boot:run
```

Executar aplicação informando CSV. Substitua o valor **storage/in/account.csv** pelo seu arquivo.

```
mvn spring-boot:run -Dspring-boot.run.arguments=storage/in/account.csv
```

Executar os testes

```
mvn test
```

---

<div id='id-autor'/>

## Desenvolvido por

**[Juliano Torquato](https://github.com/julianotorquato)**

---



