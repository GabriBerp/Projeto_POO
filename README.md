# Projeto_POO
repositório para guardar os códigos feitos para o meu projeto de Programação Orientada a Objeto no 3º período de Ciência da computação na UNICAP

### Ideia inicial do projeto
a ideia inicial desse projeto era ser um "Sistema de Automação de Maquinas em uma fabrica ficticia"</br>
a ideia evoluiu e acabou se transformando em um "Sistema para Automatizar Maquinas em X fabricas ficticias"</br>

### Explicando como funciona
no começo da execução, a pessoa podera escolher uma quantia de X fabricas, contanto que o numero seja um inteiro maior que 0, ele será aceito.</br>
cada fabrica pode ser de um tipo especifico:</br>
- Padrão</br>
- Tecidos</br>
- Alimentos</br>
- Veiculos</br>

(obs: atualmente o tipo da fabrica não muda nada relacionado ao seu funcionamento, mas é algo planejado)</br>

Depois de escolher quantas fabricas quer controlar, e o tipo de cada uma, é quando o codigo realmente começa.</br>
um menu aparece para o usuario, esse é o "Menu de fabricas" responsavel por controlar as fabricas.</br>
nele, você pode realizar 4 ações:<\br>
- 1. Iniciar (inicia a automação da fabrica selecionada)</br>
- 2. Trocar Fabrica (permite o usuario trocar a fabrica que ele deseja controlar)</br>
- 3. Listar Fabricas (lista todas as maquinas mostrando seus IDs, tipos e maquinas)</br>
- 4. Sair (encerra o programa)</br>

quando o usuario inicia uma fabrica, a mesma (caso não tenha sido iniciada antes) vai pedir ao usuario o numero de maquinas daquela fabrica que ele deseja automatizar</br>
o numero novamente se limita a inteiros maiores que zero.</br>
cada maquina pode ser de 3 tipos diferentes:</br>
- Produção</br>
- Empacotamento</br>
- Inspeção</br>

após todas as maquinas serem criadas, o usuario pode ligar/desligar as maquinas, listar elas, ou então, automatizar/parar automação das maquinas, seja de forma especifica ou no geral</br>

### Ideias planejadas para o projeto
- Fazer uso de Threads em java para tornar as automações das maquinas como algo em segundo plano, e permitir que elas realmente produzam / empacotem / inspecionem alguma coisa, e que o usuario possa ver esses dados.
