# sisControleAcesso

*Trabalho Acadêmico da disciplina de Desenvolvimento de Sistemas Orientados a Objetos da Universidade Federal de Santa Catarina*

**Problema:**

  Uma empresa possui diversas salas em seu prédio. Essas salas somente deveriam ser acessadas por funcionários devidamente cadastrados e que tenham permissão para acessá-las. Para
evitar que funcionários não autorizados acessem essas salas, iremos implementar um sistema
orientado a objetos em Java que permitirá o controle de acesso às salas.

**Escopo do desenvolvimento:**

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
