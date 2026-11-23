import java.util.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GerenciarProduto gerProduto = new GerenciarProduto();
        GerenciadorCliente gerCliente = new GerenciadorCliente();
        GerenciadorVenda gerVenda = new GerenciadorVenda();

        int opcao = 0;
        do {
            try {
                System.out.println("\n===== MENU PRINCIPAL =====");
                System.out.println("1 - Produtos");
                System.out.println("2 - Clientes");
                System.out.println("3 - Realizar venda");
                System.out.println("4 - Consultas");
                System.out.println("5 - Sair");
                System.out.print("Opção: ");
                opcao = sc.nextInt();
                sc.nextLine();

                if (opcao == 1) {
                    menuProdutos(sc, gerProduto);
                } else if (opcao == 2) {
                    menuClientes(sc, gerCliente);
                } else if (opcao == 3) {
                    realizarVenda(sc, gerProduto, gerCliente, gerVenda);
                } else if (opcao == 4) {
                    menuConsultas(sc, gerProduto, gerVenda);
                } else if (opcao == 5) {
                    System.out.println("Encerrando...");
                } else {
                    System.out.println("Opção inválida.");
                }
            } catch (InputMismatchException erro) {
                System.out.println("Entrada inválida. Digite apenas números quando solicitado.");
                sc.nextLine();
            }
        } while (opcao != 5);

        sc.close();
    }

    public static void menuProdutos(Scanner sc, GerenciarProduto gerProduto) {
        int opcao = 0;
        do {
            System.out.println("\n===== MENU PRODUTOS =====");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Listar produtos");
            System.out.println("3 - Consultar produto");
            System.out.println("4 - Alterar produto");
            System.out.println("5 - Excluir produto");
            System.out.println("6 - Voltar");
            System.out.print("Opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            if (opcao == 1) {
                System.out.print("Descrição: ");
                String descricao = sc.nextLine();
                System.out.print("Valor de compra: ");
                double valorCompra = sc.nextDouble();
                System.out.print("Valor de venda: ");
                double valorVenda = sc.nextDouble();
                System.out.print("Estoque atual: ");
                int estoqueAtual = sc.nextInt();
                System.out.print("Estoque mínimo: ");
                int estoqueMin = sc.nextInt();
                sc.nextLine();
                gerProduto.cadastrarProduto(descricao, valorCompra, valorVenda, estoqueAtual, estoqueMin);

            } else if (opcao == 2) {
                gerProduto.listarProdutos();

            } else if (opcao == 3) {
                System.out.print("Código do produto: ");
                int codigo = sc.nextInt();
                sc.nextLine();
                Produto p = gerProduto.consultarProduto(codigo);
                if (p == null) {
                    System.out.println("Produto não encontrado.");
                } else {
                    System.out.println("\n" + p);
                }

            } else if (opcao == 4) {
                System.out.print("Código do produto: ");
                int codigo = sc.nextInt();
                sc.nextLine();
                System.out.print("Nova descrição: ");
                String descricao = sc.nextLine();
                System.out.print("Novo valor de compra: ");
                double valorCompra = sc.nextDouble();
                System.out.print("Novo valor de venda: ");
                double valorVenda = sc.nextDouble();
                System.out.print("Novo estoque atual: ");
                int estoqueAtual = sc.nextInt();
                System.out.print("Novo estoque mínimo: ");
                int estoqueMin = sc.nextInt();
                sc.nextLine();
                gerProduto.alterarProduto(codigo, descricao, valorCompra, valorVenda, estoqueAtual, estoqueMin);

            } else if (opcao == 5) {
                System.out.print("Código do produto: ");
                int codigo = sc.nextInt();
                sc.nextLine();
                gerProduto.excluirProduto(codigo);

            } else if (opcao != 6) {
                System.out.println("Opção inválida.");
            }
        } while (opcao != 6);
    }

    public static void menuClientes(Scanner sc, GerenciadorCliente gerCliente) {
        int opcao = 0;
        do {
            System.out.println("\n===== MENU CLIENTES =====");
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Listar clientes");
            System.out.println("3 - Consultar cliente");
            System.out.println("4 - Alterar cliente");
            System.out.println("5 - Excluir cliente");
            System.out.println("6 - Voltar");
            System.out.print("Opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            if (opcao == 1) {
                System.out.print("Nome: ");
                String nome = sc.nextLine();
                System.out.print("Endereço: ");
                String endereco = sc.nextLine();
                System.out.print("Telefone: ");
                String telefone = sc.nextLine();
                gerCliente.cadastrarCliente(nome, endereco, telefone);

            } else if (opcao == 2) {
                gerCliente.listarClientes();

            } else if (opcao == 3) {
                System.out.print("Código do cliente: ");
                int codigo = sc.nextInt();
                sc.nextLine();
                Cliente c = gerCliente.consultarCliente(codigo);
                if (c == null) {
                    System.out.println("Cliente não encontrado.");
                } else {
                    System.out.println("\n" + c);
                }

            } else if (opcao == 4) {
                System.out.print("Código do cliente: ");
                int codigo = sc.nextInt();
                sc.nextLine();
                System.out.print("Novo nome: ");
                String nome = sc.nextLine();
                System.out.print("Novo endereço: ");
                String endereco = sc.nextLine();
                System.out.print("Novo telefone: ");
                String telefone = sc.nextLine();
                gerCliente.alterarCliente(codigo, nome, endereco, telefone);

            } else if (opcao == 5) {
                System.out.print("Código do cliente: ");
                int codigo = sc.nextInt();
                sc.nextLine();
                gerCliente.excluirCliente(codigo);

            } else if (opcao != 6) {
                System.out.println("Opção inválida.");
            }
        } while (opcao != 6);
    }

    public static void realizarVenda(Scanner sc, GerenciarProduto gerProduto, GerenciadorCliente gerCliente, GerenciadorVenda gerVenda) {
        int numeroVenda = gerVenda.gerarNumeroVenda();
        LocalDate dataVenda = LocalDate.now();

        System.out.print("Tipo de venda (vista/prazo): ");
        String tipoVenda = sc.nextLine();

        int codigoCliente = -1;
        LocalDate dataVencimento = dataVenda;

        if (tipoVenda.equalsIgnoreCase("prazo")) {
            System.out.print("Código do cliente: ");
            codigoCliente = sc.nextInt();
            sc.nextLine();
            Cliente cliente = gerCliente.consultarCliente(codigoCliente);
            if (cliente == null) {
                System.out.println("Cliente não encontrado. Venda cancelada.");
                return;
            }

            System.out.print("Data de vencimento (AAAA-MM-DD): ");
            dataVencimento = LocalDate.parse(sc.nextLine());

            if (dataVencimento.isBefore(dataVenda)) {
                System.out.println("A data de vencimento não pode ser anterior à data da venda.");
                return;
            }
        }

        Venda venda = new Venda(numeroVenda, dataVenda, tipoVenda, codigoCliente, dataVencimento);
        String continuar;

        do {
            System.out.print("Código do produto: ");
            int codigoProduto = sc.nextInt();
            sc.nextLine();
            Produto produto = gerProduto.consultarProduto(codigoProduto);

            if (produto == null) {
                System.out.println("Produto não encontrado.");
            } else {
                System.out.println("Produto: " + produto.getDescricao());
                System.out.print("Quantidade: ");
                int qtd = sc.nextInt();
                sc.nextLine();

                if (qtd <= 0) {
                    System.out.println("Quantidade inválida.");
                } else if (qtd > produto.getEstoqueAtual()) {
                    System.out.println("Estoque insuficiente.");
                } else {
                    ItemVenda item = new ItemVenda(numeroVenda, codigoProduto, qtd, produto.getValorVenda());
                    venda.adicionarItem(item);
                    produto.setEstoqueAtual(produto.getEstoqueAtual() - qtd);
                    System.out.println("Item adicionado à venda.");
                }
            }

            System.out.print("Deseja adicionar outro produto? (s/n): ");
            continuar = sc.nextLine();
        } while (continuar.equalsIgnoreCase("s"));

        if (venda.getItens().isEmpty()) {
            System.out.println("Venda cancelada, pois nenhum item foi adicionado.");
            return;
        }

        gerVenda.adicionarVenda(venda);
        gerProduto.salvarProdutos();
        System.out.println("Venda realizada com sucesso! Número: " + numeroVenda);
        System.out.println("Total: R$ " + venda.getTotalVenda());
    }

    public static void menuConsultas(Scanner sc, GerenciarProduto gerProduto, GerenciadorVenda gerVenda) {
        int opcao = 0;
        do {
            System.out.println("\n===== CONSULTAS =====");
            System.out.println("1 - Produtos abaixo do estoque mínimo");
            System.out.println("2 - Vendas por período");
            System.out.println("3 - Listar todas as vendas");
            System.out.println("4 - Voltar");
            System.out.print("Opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            if (opcao == 1) {
                boolean achou = false;
                for (Produto p : gerProduto.getListaProdutos()) {
                    if (p.getEstoqueAtual() < p.getEstoqueMin()) {
                        System.out.println(p.getCodigo() + " - " + p.getDescricao() +
                                " | Estoque atual: " + p.getEstoqueAtual() +
                                " | Estoque mínimo: " + p.getEstoqueMin());
                        achou = true;
                    }
                }
                if (!achou) {
                    System.out.println("Nenhum produto abaixo do estoque mínimo.");
                }

            } else if (opcao == 2) {
                System.out.print("Data inicial (AAAA-MM-DD): ");
                LocalDate inicio = LocalDate.parse(sc.nextLine());
                System.out.print("Data final (AAAA-MM-DD): ");
                LocalDate fim = LocalDate.parse(sc.nextLine());
                gerVenda.listarVendasPorPeriodo(inicio, fim);

            } else if (opcao == 3) {
                gerVenda.listarVendas();

            } else if (opcao != 4) {
                System.out.println("Opção inválida.");
            }
        } while (opcao != 4);
    }
}
