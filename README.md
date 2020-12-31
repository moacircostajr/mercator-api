# mercator-api-mysql

API desenvolvida com o propósito de servir a um sistema de gerenciamento de equipe de vendas.

Projeto desenvolvido em Java 11 com o framework Spring Boot 2.

### Introdução

Estas instruções lhe permitirão obter uma cópia do projeto e executá-lo na sua máquina local para desenvolvimento e testes. Veja as notas de compilação para saber como compilar o projeto.

## Pré-requisitos

A tecnologia de desenvolvimento dessa api pode ser instalada através do comando `sudo apt install openjdk-11-jdk maven` (no GNU/LINUX distribuição Debian/Ubuntu/derivados).

## Instalação

Após o download deste projeto, dentro de sua pasta principal deve ser executado o comando `mvn install`, para que seja feito o download e a instalação das dependências do projeto.

Após a instalação das dependências, basta executar os passos informados nas notas a seguir.

## Ambiente de desenvolvimento

O uso de uma IDE com suporte a linguagem java é essencial para se obter fluidez no desenvolvimento desse projeto. As IDEs mais conhecidas são o Eclipse, IntelliJ, Netbeans e Visual Studio Code.

O uso do framework Spring Boot permite que esse projeto seja executado a partir do arquivo /src/main/java/br/com/mercator/api/ServerApplication.java

## Compilação

Execute na pasta raiz do projeto o comando `mvn clean compile package` para compilar e empacotar o projeto.

O projeto compilado poderá ser encontrado no diretório `target/`, e poderá ser executado com o comando `java -jar nome_do_jar.jar`

## Ajuda

Para obter mais informações sobre as tecnologias utilizadas no desenvolvimento desta API, acesse [JAVA Docs](https://docs.oracle.com/en/java/javase/11/) e/ou [SpringBoot Docs](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/).

## Licença

Este projeto está licenciado sob os termos da [GNU General Public License v3.0](http://licencas.softwarelivre.org/gpl-3.0.pt-br.html).

## Autor

* **Moacir Costa** - *Desenvolvedor inicial*

## Agradecimentos

* A Jesus Cristo, que me deu fé, coragem, inteligência e determinação para chegar até aqui
* Ao meu irmão, Claudio Costa, que me ensinou a programar aplicações web nos seus momentos de folga
