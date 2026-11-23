import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Venda {
    private int numeroVenda;
    private LocalDate dataVenda;
    private String tipoVenda;
    private int codigoCliente;
    private LocalDate dataVencimento;
    private List<ItemVenda> itens = new ArrayList<>();

    public Venda(int numeroVenda, LocalDate dataVenda, String tipoVenda, int codigoCliente, LocalDate dataVencimento) {
        this.numeroVenda = numeroVenda;
        this.dataVenda = dataVenda;
        this.tipoVenda = tipoVenda;
        this.codigoCliente = codigoCliente;
        this.dataVencimento = dataVencimento;
    }

    public int getNumeroVenda() { return numeroVenda; }
    public LocalDate getDataVenda() { return dataVenda; }
    public String getTipoVenda() { return tipoVenda; }
    public int getCodigoCliente() { return codigoCliente; }
    public LocalDate getDataVencimento() { return dataVencimento; }
    public List<ItemVenda> getItens() { return itens; }

    public void adicionarItem(ItemVenda item) {
        itens.add(item);
    }

    public double getTotalVenda() {
        double total = 0;
        for (ItemVenda item : itens) {
            total += item.getTotal();
        }
        return total;
    }

    public String toString() {
        return "Venda nº " + numeroVenda + " | Data: " + dataVenda +
               " | Tipo: " + tipoVenda + " | Cliente: " + codigoCliente +
               " | Vencimento: " + dataVencimento + " | Total: R$ " + getTotalVenda();
    }
}
