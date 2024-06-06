#  Modernização da Eficiência Operacional: Reduzindo o Tempo de Resposta em Consultas a Bancos de Dados Legados Através da Nuvem

Março, 2024

Arthur Tsukamoto, Guilherme Lima, Guilherme Moura, Tony Jonas e Vinícius Kumagai.

## 1. Introdução 

Cloud computing é um serviço baseado em assinatura onde você consegue obter espaço de armazenamento em rede e recursos computacionais[[9]](#9). Esse tipo de computação está em constante crescimento nos dias atuais, permitindo que pessoas e empresas consigam estabelecer as suas operações sem precisar investir massivamente em infraestrutura de servidores. O sonho de longa data de computação como uma utilidade tem o potencial para transformar uma grande parte da industria de TI, fazendo softwares ainda mais atrativos como serviços e moldando o forma como que o hardware de TI é desenvolvido e comprado[[10]](#10).

Os benefícios desse tipo de tecnologia se espalham por diversos aspectos, entre os mais relevantes para o tópico do presente artigo estão a otimização de custos e a escalabilidade. O serviço de cloud computing nos maiores provedores, como a Amazon Web Services, Azure Cloud e a Oracle Cloud Infrastructure, segue o modelo de pay-as-you-go, ou seja, o beneficiário é cobrado apenas pelo tempo de uso ou pelo consumo de recursos de um serviço. Isso implica que não existe desperdício de nenhum valor investido nos serviços. Computação em nuvem pode ser uma alternativa significativamente mais barata do que comprar e manter a infraestrutura de sistema interno[[11]](#11). Além disso, a virtualização dos sistemas na cloud permite que recursos sejam alocados de forma virtual, isso colabora para que os sistemas se tornem escaláveis com uma maior facilidade e menor custo. O principal problema com tais aplicações web é a falta de habilidade em planejar antecipadamente ou ainda de prever o número de usuários que estarão acessando as aplicações. A solução é escalar a aplicação web em uma maneira dinâmica e deixar o número de servidores web e componentes de aplicações web crescerem ou diminuírem sob demanda[[12]](#12). 

O desafio da eficiência operacional em ambientes tecnológicos contemporâneos é um tema central para empresas que buscam aprimorar a experiência do usuário e otimizar suas operações. Um aspecto crítico frequentemente enfrentado é o tempo de resposta nas consultas aos bancos de dados legados, uma questão que afeta diretamente a agilidade das aplicações e, consequentemente, a satisfação do cliente[[1]](#1)[[2]](#2).

O principal problema enfrentado por organizações que lidam com um vasto legado de dados é a latência nas consultas a esses repositórios. A demanda por respostas rápidas é mais premente do que nunca, especialmente em um ambiente de negócios que se move a um ritmo acelerado. O desafio da eficiência passa, portanto, pela necessidade de acesso imediato a informações cruciais, o que implica em soluções que consigam escalar de forma responsiva às exigências do momento. Uma possível solução é implementar e escalar escalar o barramento de dados legado através da Cloud para que as aplicações possam consumir as informações em menor tempo de forma escalável, elástica e com tolerância a falhas.

Nesse contexto, o problema, apresentado pela Vivo, reside nas operações que exigem consultas a sistemas de bancos de dados legados, onde o tempo de resposta prolongado se torna um gargalo operacional. Com tempos médios de resposta chegando a 30 segundos por consulta, a eficiência das transações e a experiência do usuário são comprometidas. Esta situação destaca a urgência de uma solução que não só acelere as consultas, mas também ofereça escalabilidade e confiabilidade em tempo real. Diante desse cenário, a busca por soluções eficientes torna-se crucial para atender às demandas de um mundo cada vez mais interconectado e dinâmico.

Após entender esse conceito, é possível tirar a conclusão de que o uso da computação em nuvem pode ser utilizado como uma solução viável para a problemática em questão. Este projeto propõe uma abordagem inovadora para a modernização do barramento de dados legado, utilizando serviços da Amazon Web Services (AWS). O objetivo principal é reduzir significativamente o tempo de resposta nas consultas, almejando alcançar patamares inferiores a 3 segundos. A escolha da AWS como provedor de serviços em nuvem é respaldada por sua robustez, flexibilidade e vasta gama de ferramentas especializadas[[3]](#3).

A proposta de solução envolve a implementação de uma arquitetura escalável e distribuída, incorporando serviços-chave da AWS para otimização e melhor desempenho. Um componente crucial desta abordagem é a estratégia de cache no back-end, que visa reduzir o tempo das requisições ao evitar o máximo possível o acesso direto ao banco de dados legado.

Este trabalho não apenas aborda um desafio operacional específico, mas também contribui para o avanço do conhecimento na área de sistemas distribuídos e eficiência operacional em ambientes de nuvem. A implementação bem-sucedida desta proposta não só resolverá os problemas imediatos da empresa em questão, mas também oferecerá insights valiosos para organizações que enfrentam desafios semelhantes em seus ambientes distribuídos. A eficácia dessa abordagem, combinada com a lógica de cache no back-end, pode representar um marco na busca por soluções ágeis e escaláveis em ambientes tecnológicos dinâmicos e em constante crescimento.

## Trabalhos relacionados

Para compreender melhor a importância da redução do tempo de resposta em consultas em bancos de dados legados através de um barramento de nuvem em cloud, é relevante examinar estudos anteriores que abordam temas semelhantes, como escalabilidade em nuvem, computação distribuída e otimização de recursos.

Wang L.C et al. (2022) abordaram a aplicação da computação em nuvem para aprimorar o planejamento de recursos empresariais através de sistemas de APS (Advanced Planning Scheduling). O estudo destacou que os custos de infraestrutura muitas vezes impedem as empresas de médio porte de adotarem esses sistemas, prejudicando suas operações. No entanto, o uso de frameworks baseados em nuvem, como o C-APS (Cloud Advanced Planning Scheduling), foi identificado como uma solução viável para reduzir significativamente esses custos, resultando em uma maior eficiência operacional com menores custos totais de propriedade[[5]](#5).
 
Thain D et al. (2005) apresentaram o projeto Condor, atualmente conhecido como HTCondor, um framework de computação distribuída desenvolvido pela Universidade de Wisconsin–Madison. O HTCondor utiliza uma abordagem de sistema em grid para distribuir tarefas entre diversas máquinas, permitindo a resolução de problemas computacionalmente intensivos. Em uma demonstração, o Condor foi o primeiro sistema capaz de resolver um problema de otimização combinatória conhecido como o problema de atribuição quadrática (QAP) com 30 instâncias, superando limitações anteriores de tempo de processamento. Esses resultados evidenciam como a computação distribuída pode viabilizar a resolução eficiente de problemas que anteriormente eram considerados inviáveis[[6]](#6).

Saputra R. (2023), da Universidade de Budi Luhur, na Indonésia, apresentou uma discussão relevante sobre a otimização dos serviços de transação em empresas de telecomunicação por meio do uso de cache, utilizando o banco de dados em memória Memcached. O estudo destacou o papel crucial do cache na melhoria da velocidade de acesso às consultas mais frequentes dos clientes. Ao armazenar essas consultas em cache, o acesso ao banco de dados legado foi agilizado, resultando em uma redução significativa no tempo de resposta das consultas. Consequentemente, a demanda sobre o banco de dados legado diminuiu em até 5%, o que não apenas melhorou sua disponibilidade, mas também contribuiu para uma experiência mais eficiente para os usuários finais[[7]](#7).

Blinowski G et al. (2022), fizeram um benchmark comparativo entre arquiteturas monolíticas e de microserviços oferecendo insights valiosos sobre o desempenho dessas abordagens em diferentes cenários de implantação. Os resultados dos experimentos indicaram que os sistemas monolíticos demonstraram melhor desempenho quando executados em uma única máquina. Por outro lado, as arquiteturas de microserviços mostraram-se mais eficientes em ambientes distribuídos, especialmente em sistemas com múltiplas máquinas. Essa constatação ressalta a importância de considerar não apenas o desempenho absoluto de cada arquitetura, mas também as características específicas do ambiente de implantação ao tomar decisões de design e implementação de sistemas[[8]](#8).  

Esses estudos destacam a importância da computação em nuvem na redução dos custos de infraestrutura, permitindo que empresas de médio porte adotem sistemas avançados de planejamento e programação. Além disso, enfatizam como a computação distribuída pode melhorar o tempo de resposta das consultas, mesmo em sistemas legados, ao distribuir tarefas entre várias máquinas e fazer uso estratégico de cache.

Os estudos mencionados utilizaram tecnologias específicas para abordar problemas pontuais: desde frameworks baseados em nuvem para planejamento de recursos, passando por sistemas em grid para computação distribuída, até o uso de cache para otimização de acesso a dados. Cada um desses estudos enfrentou limitações, seja na adaptação de recursos empresariais para empresas de menor porte, seja na otimização de processos computacionais de alta intensidade, ou ainda na eficiência de transações em telecomunicações. O projeto do grupo pretende superar essas limitações ao adotar uma estratégia de barramento de dados legado na nuvem, integrando serviços da AWS para aprimorar a eficiência operacional e reduzir os tempos de resposta, com o objetivo de alcançar a marca inédita de menos de 3 segundos para consultas a bancos de dados legados.

Ao considerar essas descobertas em conjunto, fica claro que um barramento de dados baseado em nuvem, implementado com técnicas como cache e distribuição de tarefas, pode não apenas otimizar a utilização dos recursos, mas também resultar em uma experiência de usuário mais eficiente e satisfatória. Reduzindo o tempo de resposta das consultas e melhorando a acessibilidade aos dados, essa abordagem tem o potencial de transformar positivamente a maneira como as empresas operam e entregam valor aos seus clientes.

## Materiais e Métodos

A solução para otimizar o tempo de resposta em consultas a bancos de dados legados integrou uma série de serviços da Amazon Web Services (AWS) em um fluxo de trabalho coeso. Esta seção detalha cada etapa do processo, realçando o papel de cada serviço AWS selecionado e como eles contribuem para a eficácia da solução.

**Planejamento e Design da Arquitetura**
A fase inicial envolveu um planejamento detalhado da arquitetura do sistema. A seleção do Amazon Route 53 foi estratégica para o gerenciamento do tráfego de DNS, otimizando a rota de conexão entre os usuários finais e a aplicação, o que reduz a latência e melhora a acessibilidade. Paralelamente, o Amazon CloudFront foi integrado como uma rede de entrega de conteúdo (CDN), com o objetivo de acelerar a distribuição de conteúdo estático e dinâmico. Esta escolha assegura que os usuários experimentem tempos de carregamento rápidos, independentemente de sua localização geográfica, melhorando a experiência do usuário de forma significativa.

**Implementação de Cache no Back-End**
Para abordar a latência nas consultas aos bancos de dados legados, adotou-se uma estratégia de cache no back-end utilizando o Amazon ElastiCache. Este serviço permite armazenar em cache os dados frequentemente requisitados, o que minimiza a dependência de consultas repetitivas ao banco de dados e, consequentemente, melhora o desempenho da aplicação. A implementação dessa camada de cache foi crucial para a redução do tempo de resposta das consultas, garantindo uma recuperação de dados mais rápida e eficiente.

**Desenvolvimento do Barramento de Dados**
A construção de um barramento de dados na nuvem foi essencial para facilitar a comunicação e a integração entre diferentes sistemas e componentes da aplicação. A introdução do Elastic Load Balancer (ELB) nessa etapa permitiu uma distribuição eficaz do tráfego de entrada para várias instâncias do aplicativo, assegurando uma carga balanceada e mantendo a aplicação estável e escalável sob diferentes condições de uso.

**Monitoramento e Otimização**
O monitoramento contínuo do sistema foi realizado através do Amazon CloudWatch e do AWS Grafana, fornecendo insights detalhados sobre o desempenho da aplicação em tempo real. Esta análise contínua possibilitou a identificação rápida de quaisquer gargalos operacionais, permitindo intervenções imediatas para otimizar a performance. A capacidade de visualizar métricas detalhadas e tendências de uso foi instrumental para o ajuste fino da solução.

**Avaliação e Ajustes**
Com base nos dados coletados durante a fase de monitoramento, procedeu-se a uma avaliação criteriosa da arquitetura e dos serviços implementados. O Amazon RDS desempenhou um papel fundamental nesta fase, atuando como o banco de dados em nuvem principal. Sua alta disponibilidade, segurança e capacidade de gestão de dados foram decisivas para o sucesso do projeto. Ajustes foram realizados para melhor alinhar a solução com os objetivos de redução do tempo de resposta e escalabilidade.

Esta metodologia permitiu o desenvolvimento de uma solução eficaz para o problema de latência nas consultas a bancos de dados legados, assegurando que a aplicação possa escalar dinamicamente de acordo com a demanda, e mantendo os tempos de resposta dentro dos objetivos estabelecidos, ou seja, não apenas atendeu aos objetivos de redução do tempo de resposta mas também assegurou a escalabilidade e a resiliência do sistema.

## Resultados
Este projeto, focado na modernização da eficiência operacional através do uso de tecnologias de nuvem, alcançou resultados notáveis, demonstrando a eficácia das soluções implementadas para superar os desafios apresentados inicialmente. A seguir, descrevemos os resultados alcançados, fundamentados nos requisitos não funcionais definidos e nas baterias de testes executadas, proporcionando uma visão clara do impacto dessas implementações.

Este projeto, focado na modernização da eficiência operacional através do uso de tecnologias de nuvem, alcançou avanços significativos, superando os desafios iniciais. A seguir, detalhamos os resultados obtidos, evidenciando o impacto positivo das implementações.

### Disponibilidade
Testes intensivos de disponibilidade foram conduzidos em um ambiente controlado, simulando acessos contínuos ao sistema por um período estendido. A metodologia adotada incluiu a utilização de scripts automatizados para gerar tráfego de rede e solicitações ao sistema, assegurando uma avaliação rigorosa da capacidade do sistema de permanecer operacional sob demanda contínua. Os dados coletados revelaram uma taxa de disponibilidade superior a 99,9%, demonstrando a robustez e a confiabilidade do barramento de dados desenvolvido, mesmo sob condições adversas de estresse de rede.

### Desempenho
Priorizando a performance, os testes de desempenho focaram em quantificar os tempos de resposta para consultas ao banco de dados sob diferentes cargas de trabalho. Utilizando a ferramenta K6, simulações com até 100 usuários virtuais foram executadas, emulando cenários de uso real e medindo a resposta do sistema. Os resultados destacaram uma melhoria expressiva na velocidade de resposta, com tempos médios de resposta mantendo-se abaixo de 2 segundos, um marco importante que ressalta a eficiência do sistema proposto em comparação com as soluções anteriores.

### Capacidade e Concorrência
O sistema demonstrou excelente capacidade e concorrência, suportando visualizações simultâneas por até 30 usuários. Essa capacidade foi validada através de testes de carga, que simularam o acesso simultâneo por múltiplos usuários, avaliando a estabilidade e a escalabilidade da solução. Os testes confirmaram que a arquitetura escalável e as técnicas de balanceamento de carga adotadas permitiram que o sistema atendesse efetivamente a picos de demanda sem comprometer a experiência do usuário.

### Compatibilidade
Para garantir a compatibilidade ampla, a solução testada em uma variedade de navegadores web e sistemas operacionais. Nos navegadores Mozilla Firefox, Google Chrome, Opera e Brave, foram realizados testes que incluíram a avaliação de funcionalidades chave e a interface do usuário, assegurando que a aplicação funcionasse de maneira uniforme e sem falhas em diferentes plataformas. Similarmente, a compatibilidade com sistemas operacionais como Windows e Linux foi confirmada através de uma série de testes funcionais, que demonstraram a operacionalidade completa da solução nestes ambientes, enfatizando a flexibilidade e a acessibilidade do sistema.

### Usabilidade
Avaliações de usabilidade envolveram participantes de diferentes perfis, buscando uma ampla gama de feedback sobre a interface e a experiência do usuário. Estes testes, que variaram desde navegação intuitiva até a execução de tarefas específicas, revelaram insights valiosos para aprimoramentos. Com base no feedback recebido, foram implementadas melhorias focadas em tornar a interface mais intuitiva e fácil de usar, elevando significativamente a satisfação dos usuários finais e facilitando a interação com a aplicação.

## Conclusão
O projeto apresentado abordou um desafio premente na esfera da eficiência operacional em TI: a otimização do tempo de resposta para consultas a bancos de dados legados através da inovadora implementação de um barramento de dados na nuvem. A conclusão deste esforço não apenas atestou a viabilidade técnica de tal abordagem, como também demonstrou impactos significativos em diversos aspectos críticos para operações empresariais modernas, incluindo melhorias expressivas em disponibilidade, desempenho, capacidade de concorrência, compatibilidade de sistemas e aprimoramento da usabilidade.

Com os estudos feitos, foi revelado um panorama onde diversas iniciativas buscaram abordar problemas semelhantes, cada uma contribuindo com soluções parciais ou com foco em aspectos específicos da complexidade que envolve a integração de sistemas legados em infraestruturas modernas de TI. Por exemplo, Wang L.C. et al. (2022) e Saputra R. (2023) exploraram a aplicação de tecnologias em nuvem para otimizar a gestão de recursos empresariais e a velocidade de transações em telecomunicações, respectivamente, através de estratégias que, embora eficazes em seus contextos, não abordavam diretamente a problemática de latência em consultas a bancos de dados legados com um olhar holístico.

Em contraste, o projeto atual, ao utilizar uma gama de serviços especializados da Amazon Web Services (AWS), conseguiu não apenas reduzir o tempo de resposta para menos de 3 segundos em consultas a dados legados, mas também assegurou uma arquitetura capaz de escalar dinamicamente em resposta a demandas variáveis, mantendo a aplicação estável e acessível mesmo sob elevadas cargas de uso. Esta conquista representa um avanço substancial em relação aos estudos anteriores, destacando-se pela capacidade de oferecer uma solução integral e robusta para o problema.

Além disso, a abordagem adotada neste projeto ressalta a importância de um planejamento arquitetônico cuidadoso e da escolha criteriosa de tecnologias, características que foram fundamentais para alcançar os resultados observados. Tal êxito não apenas valida a estratégia proposta, como também busca estabelecer um novo benchmark para futuras iniciativas visando a modernização de infraestruturas de TI que se deparam com desafios similares.

### Trabalhos Futuros
Embora os resultados deste projeto sejam promissores, eles também abrem caminho para várias áreas de investigação futura. Primeiramente, aprofundar no estudo de otimizações adicionais do barramento de dados e na implementação de estratégias de cache mais sofisticadas pode levar a uma redução ainda maior no tempo de resposta das consultas. Além disso, a exploração de novas tecnologias de cloud e a expansão para outras plataformas de nuvem podem oferecer alternativas para melhorar a eficiência e a escalabilidade.

Outra linha de pesquisa relevante envolve a análise detalhada do impacto da modernização na experiência do usuário final. Estudos focados em medir a satisfação dos usuários e em identificar áreas específicas para melhorias na interface podem fornecer insights valiosos para o desenvolvimento de soluções ainda mais amigáveis e acessíveis.

Por fim, considerar a segurança de dados e a conformidade com regulamentações globais de proteção de dados em projetos de modernização é crucial. Pesquisas futuras poderiam se concentrar em desenvolver e integrar soluções que não apenas otimizem o desempenho e a eficiência, mas também garantam a segurança e a privacidade dos dados dos usuários.

## Referências

<a id="1">[1]</a> 
Basavegowda Ramu, Vivek. (2023). "Optimizing Database Performance: Strategies for Efficient Query Execution and Resource Utilization". International Journal of Computer Trends and Technology. 71. 15-21. 10.14445/22312803/IJCTT-V71I7P103. 

<a id="2">[2]</a> 
Mrs, Dr & Suri, Pushpa & Sharma, Meenakshi. (2011). "A Comparative Study Between the Performance of Relational & Object Oriented Database in Data Warehousing". International Journal of Database Management Systems ( IJDMS ). 3. 10.5121/ijdms.2011.3208. 

<a id="3">[3]</a> 
Bulbul Gupta, Pooja Mittal, Tabish Mufti (2021). "A Review on Amazon Web Services(AWS), Microsoft Azure & Google Cloud Platform (GCP) Services". ICIDSSD. EAI. 
DOI: 10.4108/eai.27-2-2020.2303255

<a id="4">[4]</a> 
S. Tantiphuwanart, N. Tuaycharoen, D. Wanvarie, N. Pratanwanich and A. Suchato, "Performance Improvement on a Learning Assessment Web Application Using AWS DynamoDB as a Cache Database," 2023 20th International Joint Conference on Computer Science and Software Engineering (JCSSE), Phitsanulok, Thailand, 2023, pp. 303-308, doi: 10.1109/JCSSE58229.2023.10201973. 

<a id="5">[5]</a> 
Li-Chih Wang, Chun-Chih Chen, Jen-Li Liu, Pei-Chun Chu, Framework and deployment of a cloud-based advanced planning and scheduling system, Robotics and Computer-Integrated Manufacturing, Volume 70, 2021, 102088, ISSN 0736-5845, doi:10.1016/JRCIM.2020.102088.

<a id="6">[6]</a> 
Thain, Douglas, Todd Tannenbaum, and Miron Livny. "Distributed computing in practice: the Condor experience." Concurrency and computation: practice and experience 17.2‐4 (2005): 323-356.

<a id="7">[7]</a>
Saputra, Riki Ramdani. “OPTIMIZATION OF TELECOMMUNICATION TRANSACTION DATABASE WITH NOSQL MEMCACHED.” JATISI (Jurnal Teknik Informatika dan Sistem Informasi) (2023): 1027.

<a id="8">[8]</a>
Blinowski, Grzegorz, Anna Ojdowska, and Adam Przybyłek. "Monolithic vs. microservice architecture: A performance and scalability evaluation." IEEE Access 10 (2022): 20357-20374.

<a id="9">[9]</a>
Huth, Alexa, and James Cebula. "The basics of cloud computing." United States Computer (2011): 1-4.

<a id="10">[10]</a>
Armbrust, Michael, et al. "A view of cloud computing." Communications of the ACM 53.4 (2010): 50-58.

<a id="11">[11]</a>
Khajeh-Hosseini, Ali, David Greenwood, and Ian Sommerville. "Cloud migration: A case study of migrating an enterprise it system to iaas." 2010 IEEE 3rd International Conference on cloud computing. IEEE, 2010.

<a id="12">[12]</a>
Falatah, Maram Mohammed, and Omar Abdullah Batarfi. "Cloud scalability considerations." International Journal of Computer Science and Engineering Survey 5.4 (2014): 37.

<!---
PARA SER UTILIZADO NA METOLOGIA


A proposta de solução envolve a implementação de uma arquitetura escalável e distribuída, incorporando serviços-chave da AWS para otimização e melhor desempenho. Destacam-se o Amazon Route 53 e Amazon CloudFront para entrega eficiente de conteúdo baseada na localização do usuário, o Elastic Load Balancer para distribuição equitativa da carga, e a utilização de duas Availability Zones para garantir alta disponibilidade e tolerância a falhas.

Um componente crucial desta abordagem é a estratégia de cache no back-end, que visa reduzir o tempo das requisições ao evitar o máximo possível o acesso direto ao banco de dados legado. Para isso, será implementado um banco de dados adicional além daquele que será gerenciado pelo Amazon RDS, funcionando como uma memória cache, otimizando consultas recorrentes e melhorando significativamente o desempenho do sistema[[4]](#4).

Além disso, a solução final do projeto será dividida em duas principais entregas, sendo a primeira um barramento de dados que facilita a comunicação entre diferentes sistemas. Este barramento consistirá em três etapas: consulta em cache utilizando o Amazon ElastiCache, consulta no Amazon RDS para dados essenciais em nuvem e, em última instância, consulta inteligente nos bancos de dados legados da Vivo.

Para monitorar continuamente o barramento de dados, utilizaremos o Amazon CloudWatch e AWS Grafana, proporcionando a visualização de dados de log em tempo real do sistema. A capacidade total da aplicação e a taxa de utilização serão essenciais, e alertas serão automatizados por meio do envio de emails quando a taxa de utilização se aproximar do limite estabelecido.

Ademais, serão empregados o Amazon ElastiCache para armazenamento eficiente, Amazon RDS como banco de dados essencial em nuvem, Amazon Grafana para análise de dados em tempo real, K6 para testes de carga e garantia de escalabilidade, e Amazon CloudFront para a distribuição de conteúdo estático, o front-end e hospedando o back-end, respectivamente.
---!>
