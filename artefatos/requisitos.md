# Requisitos Funcionais e Não Funcionais

Os requisitos funcionais referem-se às funcionalidades específicas que um sistema deve realizar para satisfazer as necessidades dos usuários, descrevendo as ações que o sistema deve executar em resposta a entradas específicas.

Os requisitos não funcionais definem as características do sistema que não se relacionam diretamente com as funcionalidades, mas sim com os atributos de desempenho, segurança, usabilidade e outras qualidades que afetam a experiência do usuário e a eficácia do sistema como um todo.

No entanto, para definir melhor os requisitos funcionais e não funcionais do nosso projeto, precisamos entender os objetivos da nossa solução final. Dessa forma, a solução final do projeto será divididas em 2 principais entregas:

- Um barramento de dados (um sistema de integração que permite a comunicação entre diferentes sistemas, facilitando a troca de dados e a orquestração de processos entre sistemas de TI), que será um funil com 3 etapas: consulta em cache, consulta no banco de dados com informações essenciais em nuvem, e consulta inteligente nos bancos de dados legados.
  - A primeira etapa utilizará Amazon ElastiCache para armazenar e recuperar rapidamente os dados esperados pelas requisições do aplicativo da Vivo. Caso o dado necessário não esteja em cache, a requisição passará para a segunda etapa.
  - Na segunda etapa, será feita uma consulta no Amazon RDS (Relational Database Service), que conterá dados essenciais para o aplicativo da Vivo e será atualizado a cada mudança no banco de dados original. Caso o dado necessário pela requisição não se encontre no nosso banco de dados em nuvem, a requisição passará para a terceira etapa.
  - Na terceira e última etapa do barramento de dados, caso os dados requisitados não tenham sido obtidos em nenhuma etapa anterior, será feita uma consulta inteligente nos bancos de dados legados da Vivo. Essa consulta inteligente é basicamente uma condensação de várias requisições com o mesmo objetivo, onde serão agrupadas em consultas únicas, a fim de não sobrecarregar os bancos de dados legados. Quando a resposta dos bancos legados for obtida, os dados serão decompostos separadamente por cada requisição feita no barramento de dados.
- Monitoramento contínuo do barramento de dados:
  - Através do Amazon CloudWatch e AWS Grafana, conseguiremos ter acesso, em tempo real, à situação, em termos de infraestrutura, do barramento de dados. Entre as informações essenciais que serão obtidas, temos: capacidade total da nossa aplicação e a taxa de utilização dela. Nesse sentido, será feita uma automação para quando a taxa de utilização estiver próxima do limite médio estabelecido inicialmente, os responsáveis pelo sistema serão alertados por meio de envio de emails.

Somado a isso, será desenvolvida uma aplicação que simulará os dados únicos de cada cliente, para conseguirmos mensurar nosso barramento de dados através de uma aplicação visual. Por fim, para testar o limite da nossa aplicação, serão realizados diversos testes de carga, a fim de entender o limite médio que nosso barramento de dados suportará, sem que precise escalar exponencialmente o uso de recursos.

Nesse contexto, os requisitos funcionais e não funcionais serão divididos com base em cada aplicação (barramento de dados, aplicação de monitoramento e simulador)

## Barramento de dados

### Requisitos Funcionais
**RF1**: O sistema deve ser capaz de acessar e integrar-se com múltiplas bases de dados legadas da Vivo para recuperar informações relevantes dos usuários.

**RF2**: O sistema deve realizar consultas em cache antes de acessar o banco de dados principal para otimizar a velocidade de resposta.

**RF3**: Em caso de falha de cache, o sistema deve consultar um banco de dados secundário com informações essenciais.

**RF4**: Se os dados não forem encontrados no cache ou no banco de dados secundário, o sistema deve realizar uma consulta inteligente nos bancos de dados legados.

### Requisitos Não Funcionais
**RNF1**: Disponibilidade - Disponibilidade garantida de 99,9% para o barramento de dados.

- Descrição do Teste: Configurar o Amazon CloudWatch para emitir alertas em caso de inatividade do serviço. Realizar testes de ping automatizados em intervalos regulares de 5 minutos. Registrar e analisar todos os períodos de inatividade detectados para garantir que a disponibilidade mensal não caia abaixo de 99,9%.

**RNF2**: Desempenho - Tempo de resposta para consultas de dados não deve exceder 2 segundos.
- Descrição do Teste: Utilizar a ferramenta AWS X-Ray para monitorar e analisar as latências de todas as transações no barramento de dados. Realizar testes de estresse periódicos com o AWS Load Testing Service para garantir que o tempo de resposta médio não exceda 2 segundos sob carga normal.

## Aplicação de monitoramento
### Requisitos Funcionais
**RF5**: A aplicação deve apresentar em tempo real a capacidade total e a taxa de utilização do sistema.

**RF6**: Deve alertar automaticamente os responsáveis quando a utilização do sistema se aproximar de um limite pré-estabelecido.

### Requisitos Não Funcionais
**RNF3:** Capacidade de Concorrência - A aplicação deve suportar a visualização simultânea por até 30 usuários.
- Descrição do Teste: Executar testes de carga usando o AWS Load Testing Service para simular pelo menos 30 usuários acessando o sistema simultaneamente. Monitorar a utilização de recursos, a latência e o tempo de carregamento das páginas para assegurar que a performance não seja comprometida.

**RNF4:** Compatibilidade de Navegador - A interface deve ser compatível com as versões mais recentes dos principais navegadores.
- Descrição do Teste: Testar a aplicação de monitoramento usando ferramentas de automação de testes de UI como Selenium em diferentes versões dos navegadores Chrome, Firefox, Edge e Safari. Avaliar a exibição de gráficos e controles de UI para garantir a funcionalidade total em todas as plataformas.

## Simulador
### Requisitos Funcionais
**RF7**: A aplicação deve simular os dados únicos de cada cliente para testar o barramento de dados.

**RF8**: Deve ser capaz de realizar testes de carga para avaliar o desempenho do sistema.

**RF9**: Os usuários devem se autenticar com seus próprios logins e senhas.

### Requisitos Não Funcionais
**RNF5:** Compatibilidade de SO - O simulador deve ser capaz de operar em diferentes sistemas operacionais, incluindo Windows e Linux.
- Descrição do Teste: Realizar testes de integração contínua em ambientes Windows e Linux usando Jenkins ou outra ferramenta de CI/CD para garantir que cada build seja compatível com ambos os sistemas operacionais. Usar virtualização ou contêineres para simular os ambientes de SO.
  
**RNF6:** Usabilidade - A usabilidade do simulador deve ser validada para garantir uma experiência do usuário acessível e satisfatória.
- Descrição do Teste: Conduzir testes de usabilidade com um grupo diversificado de usuários finais, coletando dados qualitativos e quantitativos sobre a facilidade de uso. Utilizar cenários de teste que cubram todas as funcionalidades do simulador.