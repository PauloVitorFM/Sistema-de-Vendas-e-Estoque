import java.util.*;
import java.io.*;
import java.time.LocalDate;

public class GerenciadorVenda {
    private List<Venda> listaVendas = new ArrayList<>();

    public GerenciadorVenda() {
        carregarVendas();
    }

    public List<Venda> getListaVendas() {
        return listaVendas;
    }

    public int gerarNumeroVenda() {
        int maior = 0;
        for (Venda v : listaVendas) {
            if (v.getNumeroVenda() > maior) {
                maior = v.getNumeroVenda();
            }
        }
        return maior + 1;
    }

    public void adicionarVenda(Venda venda) {
        listaVendas.add(venda);
        salvarVendas();
    }

    public void listarVendas() {
        if (listaVendas.isEmpty()) {
            System.out.println("Nenhuma venda cadastrada.");
            return;
        }
        for (Venda v : listaVendas) {
            System.out.println("\n" + v);
            for (ItemVenda item : v.getItens()) {
                System.out.println("  " + item);
            }
        }
    }

    public void listarVendasPorPeriodo(LocalDate inicio, LocalDate fim) {
        boolean achou = false;
        for (Venda v : listaVendas) {
            if ((v.getDataVenda().isEqual(inicio) || v.getDataVenda().isAfter(inicio)) &&
                (v.getDataVenda().isEqual(fim) || v.getDataVenda().isBefore(fim))) {
                System.out.println(v);
                achou = true;
            }
        }
        if (!achou) {
            System.out.println("Nenhuma venda encontrada nesse período.");
        }
    }

    public void salvarVendas() {
        try (PrintWriter gravador = new PrintWriter(new FileWriter("vendas.txt"))) {
            for (Venda v : listaVendas) {
                StringBuilder itensTexto = new StringBuilder();
                for (ItemVenda item : v.getItens()) {
                    if (itensTexto.length() > 0) itensTexto.append("|");
                    itensTexto.append(item.getCodigoProduto()).append(",")
                             .append(item.getQtdVendida()).append(",")
                             .append(item.getPrecoPago());
                }
                gravador.println(v.getNumeroVenda() + ";" + v.getDataVenda() + ";" + v.getTipoVenda() + ";" +
                        v.getCodigoCliente() + ";" + v.getDataVencimento() + ";" + itensTexto);
            }
        } catch (Exception erro) {
            System.out.println("Não foi possível salvar as vendas: " + erro.getMessage());
        }
    }

    private void carregarVendas() {
        File arquivo = new File("vendas.txt");
        if (!arquivo.exists()) return;

        try (Scanner leitor = new Scanner(arquivo)) {
            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();
                if (linha.trim().isEmpty()) continue;
                String[] partes = linha.split(";", -1);
                int numero = Integer.parseInt(partes[0]);
                LocalDate dataVenda = LocalDate.parse(partes[1]);
                String tipo = partes[2];
                int codigoCliente = Integer.parseInt(partes[3]);
                LocalDate dataVencimento = LocalDate.parse(partes[4]);

                Venda venda = new Venda(numero, dataVenda, tipo, codigoCliente, dataVencimento);

                if (partes.length > 5 && !partes[5].isEmpty()) {
                    String[] itens = partes[5].split("\\|");
                    for (String textoItem : itens) {
                        String[] dadosItem = textoItem.split(",");
                        int codigoProduto = Integer.parseInt(dadosItem[0]);
                        int qtd = Integer.parseInt(dadosItem[1]);
                        double preco = Double.parseDouble(dadosItem[2]);
                        venda.adicionarItem(new ItemVenda(numero, codigoProduto, qtd, preco));
                    }
                }
                listaVendas.add(venda);
            }
        } catch (Exception erro) {
            System.out.println("Não foi possível carregar as vendas: " + erro.getMessage());
        }
    }
}
