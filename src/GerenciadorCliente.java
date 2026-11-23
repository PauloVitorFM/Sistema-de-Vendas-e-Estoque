import java.util.*;
import java.io.*;

public class GerenciadorCliente {
    private List<Cliente> listaClientes = new ArrayList<>();

    public GerenciadorCliente() {
        carregarCliente();
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void cadastrarCliente(String nome, String endereco, String telefone) {
        int novoCodigo = gerarCodigoAutomatico();
        Cliente cliente = new Cliente(novoCodigo, nome, endereco, telefone);
        listaClientes.add(cliente);
        salvarCliente();
        System.out.println("Cliente cadastrado com sucesso! Código: " + novoCodigo);
    }

    public Cliente consultarCliente(int codigo) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getCodigo() == codigo) {
                return cliente;
            }
        }
        return null;
    }

    public void listarClientes() {
        if (listaClientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        for (Cliente c : listaClientes) {
            System.out.println("\n" + c);
        }
    }

    public void alterarCliente(int codigo, String novoNome, String novoEndereco, String novoTelefone) {
        Cliente cliente = consultarCliente(codigo);
        if (cliente != null) {
            cliente.setNome(novoNome);
            cliente.setEndereco(novoEndereco);
            cliente.setTelefone(novoTelefone);
            salvarCliente();
            System.out.println("Cliente alterado com sucesso.");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public void excluirCliente(int codigo) {
        Cliente cliente = consultarCliente(codigo);
        if (cliente != null) {
            listaClientes.remove(cliente);
            salvarCliente();
            System.out.println("Cliente excluído com sucesso.");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private int gerarCodigoAutomatico() {
        int maiorCodigo = 0;
        for (Cliente cliente : listaClientes) {
            if (cliente.getCodigo() > maiorCodigo) {
                maiorCodigo = cliente.getCodigo();
            }
        }
        return maiorCodigo + 1;
    }

    private void salvarCliente() {
        try (PrintWriter gravador = new PrintWriter(new FileWriter("clientes.txt"))) {
            for (Cliente c : listaClientes) {
                gravador.println(c.getCodigo() + ";" + c.getNome() + ";" + c.getEndereco() + ";" + c.getTelefone());
            }
        } catch (Exception erro) {
            System.out.println("Não foi possível salvar os clientes: " + erro.getMessage());
        }
    }

    public void carregarCliente() {
        File arquivo = new File("clientes.txt");
        if (!arquivo.exists()) return;

        try (Scanner leitor = new Scanner(arquivo)) {
            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();
                if (linha.trim().isEmpty()) continue;
                String[] partes = linha.split(";");
                int codigo = Integer.parseInt(partes[0]);
                String nome = partes[1];
                String endereco = partes[2];
                String telefone = partes[3];
                listaClientes.add(new Cliente(codigo, nome, endereco, telefone));
            }
        } catch (Exception erro) {
            System.out.println("Não foi possível carregar os clientes: " + erro.getMessage());
        }
    }
}
