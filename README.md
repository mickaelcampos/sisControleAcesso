# sisControleAcesso

*Trabalho Acadêmico da disciplina de Desenvolvimento de Sistemas Orientados a Objetos da Universidade Federal de Santa Catarina*

### **Problema:** ###

  Uma empresa possui diversas salas em seu prédio. Essas salas somente deveriam ser acessadas por funcionários devidamente cadastrados e que tenham permissão para acessá-las. Para
evitar que funcionários não autorizados acessem essas salas, iremos implementar um sistema
orientado a objetos em Java que permitirá o controle de acesso às salas.

### **Escopo do desenvolvimento:** ###

  O sistema é responsável por controlar o acesso às salas do prédio de uma empresa.
O sistema permite o cadastro de funcionários, identificando-os e definindo autorização de acesso
que indicam quais salas podem ser acessadas. Os diretores da empresa estão autorizados a entrar em
qualquer sala, pois este cargo garante nível total de acesso.
Ao tentar acessar determinada sala o sistema verifica se o funcionário existe no cadastro de
funcionários autorizados. Sempre que um usuário entra/sai de uma sala, o sistema faz um registro
do acesso, contendo data, hora, identificação do usuário e local acessado, para garantir total controle
em eventuais investigações por exemplo. Usuários que tentarem acessar salas sem possuir nível de
acesso para tal, são registrado e, em caso de três tentativas consecutivas, tem seu acesso bloqueado.
O sistema emite relatórios informativos referente aos acessos realizados com sucesso e os
acessos negados.

#### Critérios de Avaliação:

Qualidade da solução do problema apresentado atendendo ao escopo do desenvolvimento (3,0 pontos), observando os seguintes cenários:

* Ao menos um cadastro, contemplando: inclusão, exclusão, alteração e listagem (1,0 pontos).
* Registro (inclusão, exclusão, alteração e listagem) de ao menos um tipo de emissão de documento, movimentação, log de uso, jogada do jogador, etc. (1,0 pontos).
* Geração de ao menos um relatório/pesquisa de registros armazenados (1,0 pontos).
* Qualidade da notação UML no diagrama de classes e sua consistência com o código (0,5 pontos).
* Interface Gráfica Java Swing funcional. A interface gráfica deve ter sido completamente desenvolvida de forma manual, sem apoio de ferramenta para desenhar a tela. (1,5 pontos)
* Camada de persistência (em arquivo) implementada corretamente, gravando todos os cadastros e registros em arquivos e permitindo a sua recuperação (1,5 pontos).
* Utilização correta do MVC e da separação em camadas (2,0 pontos)
* Implementação de ao menos um exemplo de Polimorfismo (0,5 pontos)
* Tratamento correto de todas as exceções, utilizando ao menos duas classes de exceção estendidas (1,0 ponto).
