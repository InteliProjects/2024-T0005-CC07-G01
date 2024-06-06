<table>
<tr>
<td>
<a href= "https://www.vivo.com.br/"> <img src="./imgs/vivo-logo.png" alt="vivo.com.br" border="0" width="20%"></a>
</td>
<td><a href= "https://www.inteli.edu.br/"><img src="./imgs/inteli-logo.png" alt="Inteli - Instituto de Tecnologia e Liderança" border="0" width="50%"></a>
</td>
</tr>
</table>

**Nimbus**: Desenvolvendo uma aplicação escalável para otimizar o tempo de acesso às bases de dados legadas.

# Introdução
Este é um dos repositórios do projeto de alunos do Inteli em parceria com a Vivo Telefônica no 1º semestre de 2024. Este projeto está sendo desenvolvido por alunos do Módulo 7 do curso de Ciência da Computação.

# Projeto: Nimbus
# Grupo: Nimbus

# Integrantes:

* [Arthur Oliveira](Arthur.Oliveira@sou.inteli.edu.br).
* [Guilherme Lima](Guilherme.Lima@sou.inteli.edu.br).
* [Guilherme Moura](Guilherme.Moura@sou.inteli.edu.br).
* [Tony Jonas](tony.sousa@sou.inteli.edu.br).
* [Vinicius Kumagai](Vinicius.Kumagai@sou.inteli.edu.br).

# Descrição

Em um mundo cada vez mais digital, a rapidez com que as informações são acessadas pode fazer toda a diferença, seja navegando na internet, realizando transações bancárias ou usando aplicativos no celular. Pensando nisso, nosso projeto tem como missão modernizar a maneira como a Vivo, que conta com uma grande quantidade de dados antigos - conhecidos como bancos de dados legados - acessa essas informações, tornando esse processo muito mais rápido e eficiente.

Utilizando a tecnologia de computação em nuvem (especificamente a AWS), uma abordagem inovadora que permite armazenar e acessar dados pela internet, conseguimos desenvolver uma solução, através de um barramento de dados, que reduz significativamente o tempo de espera ao buscar por informações nesses bancos de dados antigos. Imagine que antes, para obter uma resposta, o sistema levava cerca de 30 segundos; com nossa solução, esse tempo é reduzido para menos de 3 segundos. Isso não só melhora a experiência dos usuários, mas também possibilita que a Vivo opere de maneira mais ágil, confiável e segura. Nosso projeto é como uma ponte moderna, conectando o passado ao presente, garantindo que a informação esteja ao alcance de todos, instantaneamente.

# Configuração para desenvolvimento

A aplicação foi projetada para operar inteiramente na nuvem, utilizando a infraestrutura da Amazon Web Services (AWS), o que significa que não é possível executar todos os componentes de forma integrada em um ambiente local. Entretanto, desenvolvedores podem trabalhar em partes específicas da aplicação de forma isolada seguindo as orientações abaixo.

## Estrutura do Repositório
O repositório de código está organizado da seguinte maneira (a partir da raiz do projeto):

- `src/`: Pasta de código fonte dividida em back-end, front-end e infra.
  - `backend/`: Contém os microsserviços implementados em Java com Spring Boot.
    - `enderecos/`, `plano/`, `planos_usuarios/`, `usuario/`, `usuario_pf/`, `usuario_pj/`: Cada pasta representa um microsserviço distinto.
    - `application.properties`: Arquivo de configurações do Spring Boot.
  - `frontend/`: Código fonte do front-end da aplicação implementado com React.
    - `public/`: Arquivos públicos acessíveis pelo navegador.
    - `src/`: Código fonte React, incluindo componentes, estilos e testes.
    - `.env`: Arquivo para configuração de variáveis de ambiente do React.
  - `infra/`: Contém todos os scripts e definições de infraestrutura como código para provisionar recursos necessários na AWS.
    - `cloudFormation/`: Scripts do AWS CloudFormation para criar e gerenciar stacks na AWS.
    - `database/`: Configurações relacionadas a bancos de dados AWS RDS.
    - `docker/`: Arquivos Dockerfile para criação de contêineres.
    - `loadTest/`: Scripts para execução de testes de carga utilizando Grafana Cloud K6.
    - `minikube/`: Configurações para executar um ambiente Kubernetes localmente com Minikube.
    - `serverless/`: Definições para funções AWS Lambda.
  
## Pré-requisitos
Antes de iniciar o desenvolvimento, é necessário ter as seguintes ferramentas instaladas:
- **Docker**: Para construir e rodar contêineres locais dos microsserviços e outras dependências.
- **Node.js e npm**: Para trabalhar com o código do front-end.
- **Java Development Kit (JDK) e Maven**: Para compilar e gerenciar dependências dos microsserviços.
- **AWS CLI**: Para interagir com os serviços AWS, caso seja necessário criar ou modificar recursos na nuvem.
- Um IDE ou editor de texto, como **Visual Studio Code**, para desenvolvimento de código.

## Configuração local
Para desenvolver localmente, siga estes passos:

### Back-end (Microsserviços):

- Navegue até a pasta do microsserviço desejado dentro de src/backend/.
- Utilize o Maven para resolver as dependências e iniciar o serviço:
``` sh
mvn clean install
mvn spring-boot:run
```
- Cada microsserviço pode ser iniciado individualmente e expõe sua própria API REST para ser consumida.

### Front-end (React):

- Navegue até a pasta frontend/ no repositório.
- Instale todas as dependências necessárias executando o comando:

```sh
npm install
```
- Para iniciar o servidor de desenvolvimento do React, execute:

```sh
npm run dev
```
- Isso abrirá a aplicação React no navegador padrão em http://localhost:3000. Qualquer mudança no código será automaticamente recarregada na página.

### Docker
- Para construir imagens Docker dos microsserviços, você pode utilizar os `Dockerfile` localizados nas pastas correspondentes dentro de `src/backend/`.
- Navegue até a pasta do serviço específico e execute o comando para construir a imagem:

```sh
docker build -t nome_da_imagem .
```

- Para executar a imagem como um contêiner:
```sh
docker run -p port:port nome_da_imagem
```

### AWS CLI

- Configure suas credenciais da AWS utilizando o AWS CLI com o comando:

```sh
aws configure
```
Isso será necessário para realizar operações que envolvem a criação e gerenciamento de recursos AWS através dos scripts de infraestrutura.

### Kubernetes com Minikube:
- Se você precisa de um ambiente Kubernetes local, pode utilizar o Minikube seguindo as instruções específicas da pasta `infra/minikube/`.
- Certifique-se de ter o Minikube e o kubectl instalados e configurados em sua máquina antes de prosseguir.

### Testes de Carga com Grafana Cloud K6:
- Para executar testes de carga em seus microsserviços, utilize os scripts fornecidos na pasta `infra/loadTest/`.
- Garanta que você tenha a ferramenta K6 instalada localmente ou acesse a ferramenta através do Grafana Cloud, conforme sua preferência.

# Tags
- SPRINT 1: **Fundamentos e Planejamento**
  - Entendimento de Negócio
  - Entendimento do Usuário
  - MVP com deploy da aplicação com arquitetura básica
  - Requisitos Funcionais e Não Funcionais
- SPRINT 2: **Estruturação e Desenvolvimento Inicial**
  - Arquitetura corporativa
  - Front-end
  - Back-end
  - Artigo (v1)
- SPRINT 3: **Modelagem Avançada e Primeiras Entregas**
  - Modelagem e Implementação
  - Relatório técnico
  - Artigo (v2)
- SPRINT 4: **Testes e Ajustes**
  - Testes do sistema
  - Definição da aplicação
  - Artigo (v3)
- SPRINT 5: **Aprimoramento Final e Documentação**
  - Refinamentos da aplicação
  - Artigo completo (v4)

# Licença
<img src="./imgs/cc-license-1.png" alt="Imagem da licença do projeto-1" border="0" width="50%">
<img src="./imgs/cc-license-2.png" alt="Imagem da licença do projeto-2" border="0" width="50%">

A Licença do projeto é a [Attribution 4.0 International](https://creativecommons.org/licenses/by/4.0/?ref=chooser-v1)