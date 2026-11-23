import java.util.*;
import java.io.*;

public class GerenciarProduto {
    private List<Produto> listaProdutos = new ArrayList<>();

    public GerenciarProduto() {
        carregarProdutos();
    }

    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public void cadastrarProduto(String descricao, double valorCompra, double valorVenda, int estoqueAtual, int estoqueMin) {
        int novoCodigo = gerarCodigoAutomatico();
        Produto produto = new Produto(novoCodigo, descricao, valorCompra, valorVenda, estoqueAtual, estoqueMin);
        listaProdutos.add(produto);
        salvarProdutos();
        System.out.println("Produto cadastrado com sucesso! Código: " + novoCodigo);
    }

    public Produto consultarProduto(int codigo) {
        for (Produto p : listaProdutos) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }

    public void listarProdutos() {
        if (listaProdutos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        for (Produto p : listaProdutos) {
            System.out.println("\n" + p);
        }
    }

    public void alterarProduto(int codigo, String descricao, double novaCompra, double novaVenda, int novoEstoqueAtual, int novoEstoqueMin) {
        Produto produto = consultarProduto(codigo);
        if (produto != null) {
            produto.setDescricao(descricao);
            produto.setValorCompra(novaCompra);
            produto.setValorVenda(novaVenda);
            produto.setEstoqueAtual(novoEstoqueAtual);
            produto.setEstoqueMin(novoEstoqueMin);
            salvarProdutos();
            System.out.println("Produto alterado com sucesso.");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    public void excluirProduto(int codigo) {
        Produto produto = consultarProduto(codigo);
        if (produto != null) {
            listaProdutos.remove(produto);
            salvarProdutos();
            System.out.println("Produto excluído com sucesso.");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    public int gerarCodigoAutomatico() {
        int maiorCodigo = 0;
        for (Produto p : listaProdutos) {
            if (p.getCodigo() > maiorCodigo) {
                maiorCodigo = p.getCodigo();
            }
        }
        return maiorCodigo + 1;
    }

    public void salvarProdutos() {
        try (PrintWriter gravador = new PrintWriter(new FileWriter("produtos.txt"))) {
            for (Produto p : listaProdutos) {
                gravador.println(p.getCodigo() + ";" + p.getDescricao() + ";" + p.getValorCompra() + ";" +
                        p.getValorVenda() + ";" + p.getEstoqueAtual() + ";" + p.getEstoqueMin());
            }
        } catch (Exception erro) {
            System.out.println("Não foi possível salvar os produtos: " + erro.getMessage());
        }
    }

    private void carregarProdutos() {
        File arquivo = new File("produtos.txt");
        if (!arquivo.exists()) return;

        try (Scanner leitor = new Scanner(arquivo)) {
            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();
                if (linha.trim().isEmpty()) continue;
                String[] partes = linha.split(";");
                int codigo = Integer.parseInt(partes[0]);
                String descricao = partes[1];
                double valorCompra = Double.parseDouble(partes[2]);
                double valorVenda = Double.parseDouble(partes[3]);
                int estoqueAtual = Integer.parseInt(partes[4]);
                int estoqueMin = Integer.parseInt(partes[5]);
                listaProdutos.add(new Produto(codigo, descricao, valorCompra, valorVenda, estoqueAtual, estoqueMin));
            }
        } catch (Exception erro) {
            System.out.println("Não foi possível carregar os produtos: " + erro.getMessage());
        }
    }
}
