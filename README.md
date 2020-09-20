#  e-commerce-app

Minimo [Spring Boot](http://projects.spring.io/spring-boot/)  e-commerce-app.

## Requisitos

Para construir e executar o aplicativo, você precisa:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)
- [Node.js] (https://nodejs.org/en/)
- [Angular-CLI] (https://cli.angular.io/)
- [https://www.postgresql.org/]

## Executando o aplicativo localmente

Para executar o sistema deve seguir os passos na seguinte ordem:

1 - Criação do banco de dados.
  1.1 - Abrir o banco de dados postgres e criar o seguinte banco de dados: dbecommerce.

2 - EXECUTAR SERVICO API
  2.1 - Abrir a pasta EXECUTAVEIS
  2.2 - Abrir um cmd na pasta e executar o seguinte comando: java -jar e-commerce-0.0.1-SNAPSHOT.jar.
  
  Com este comando ele irá executar o serviço de API , além de criar a estrutura das tabelas e inserir os insumos de algumas tabelas.
  
3 - EXECUTAR SERVICO API(FRETE)
  3.1 - Abrir a pasta EXECUTAVEIS
  3.2 - Abrir um cmd na pasta e executar o seguinte comando: frete-0.0.1-SNAPSHOT.jar.

  Com este comando irá executar o servico de calculo do frete.

4 - EXECUTAR SERVICO WEB ANGULAR
 4.2 - DESCOMPACTAR O ARQUIVO : Serviço Web (Angular).7z
 4.1 - Abrir a pasta : Serviço Web (Angular)\e-commerce-angular
 4.2 - Abrir um cmd na pasta acima e rodar o seguinte comando : npm install para baixar as dependencias.
 4.3 - Após a execução , rodar o comando na mesma pasta : ng serve
 4.4 - Abrir uma pagina web com : localhost:4200
 
 5- Após feito esses passos , já pode executar o sistema normalmente. 
	
