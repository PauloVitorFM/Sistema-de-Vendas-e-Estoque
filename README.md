Projeto-Software_de_Vendas

Sistema de vendas em Java, em modo texto (console), que controla produtos, clientes e vendas. Os dados são gravados em arquivos de texto, para que as informações permaneçam mesmo depois que o programa é encerrado.

O projeto foi desenvolvido com base no desafio 21 do capítulo 13 do livro Fundamentos da Programação de Computadores (3ª edição), de Ana Fernanda Gomes Ascencio e Edilene Aparecida Veneruchi de Campos.

Funcionalidades
Produtos: cadastrar, listar, consultar, alterar e excluir. O código é gerado automaticamente.
Clientes: cadastrar, listar, consultar, alterar e excluir. O código é gerado automaticamente.
Realizar venda: venda à vista ou a prazo. Na venda a prazo, o cliente precisa estar cadastrado e a data de vencimento não pode ser anterior à data da venda. A quantidade vendida não pode ser maior que o estoque, e o estoque é atualizado ao confirmar a venda.
Consultas:
produtos com estoque abaixo do mínimo;
vendas em um período informado;
listagem de todas as vendas.
Estrutura do projeto
src/
├── Main.java               # Menu principal e interação com o usuário
├── Produto.java            # Modelo de produto
├── Cliente.java            # Modelo de cliente
├── Venda.java              # Modelo de venda
├── ItemVenda.java          # Modelo de item de uma venda
├── GerenciarProduto.java   # Regras e persistência de produtos
├── GerenciadorCliente.java # Regras e persistência de clientes
└── GerenciadorVenda.java   # Regras e persistência de vendas
Pré-requisitos
Java Development Kit (JDK) 8 ou superior. Para verificar, execute java -version e javac -version no terminal.
Como compilar e executar

A partir da pasta raiz do projeto:

bash
# Compilar (-encoding UTF-8 é necessário por causa dos acentos no código)
javac -encoding UTF-8 -d bin src/*.java

# Executar
java -cp bin Main

Os arquivos de dados (produtos.txt, clientes.txt e vendas.txt) são criados na pasta de onde o programa é executado. Se a pasta raiz for usada, eles ficarão lá.

Como usar

Ao iniciar, o menu principal oferece as opções:

Produtos: abre o submenu de manutenção de produtos.
Clientes: abre o submenu de manutenção de clientes.
Realizar venda: informa o tipo de venda (vista ou prazo), o cliente (se for a prazo), a data de vencimento (se for a prazo, no formato AAAA-MM-DD) e os produtos com suas quantidades. Ao final, pergunta se deseja adicionar outro produto.
Consultas: abre o submenu de consultas.
Sair: encerra o programa.

As datas devem ser digitadas no formato AAAA-MM-DD, por exemplo 2026-11-30.

Pontos de melhoria
* Aprimorar o controle de exclusão de produtos, garantindo a integridade do histórico de vendas.
* Aprimorar a organização do registro de itens vendidos, com armazenamento próprio.
* Ampliar as consultas, com visões de faturamento, valores recebidos e lucro por período.
* Aprimorar o processo de registro das modalidades de venda.
* Refinar o cadastro de produtos, para manter as descrições dentro de um padrão de tamanho.
* Aprimorar a robustez no armazenamento e na leitura dos dados.
Autor:
Paulo Vitor Freitas Maia
