public class ItemVenda {
    private int numeroVenda;
    private int codigoProduto;
    private int qtdVendida;
    private double precoPago;

    public ItemVenda(int numeroVenda, int codigoProduto, int qtdVendida, double precoPago) {
        this.numeroVenda = numeroVenda;
        this.codigoProduto = codigoProduto;
        this.qtdVendida = qtdVendida;
        this.precoPago = precoPago;
    }

    public int getNumeroVenda() { return numeroVenda; }
    public void setNumeroVenda(int numeroVenda) { this.numeroVenda = numeroVenda; }

    public int getCodigoProduto() { return codigoProduto; }
    public void setCodigoProduto(int codigoProduto) { this.codigoProduto = codigoProduto; }

    public int getQtdVendida() { return qtdVendida; }
    public void setQtdVendida(int qtdVendida) { this.qtdVendida = qtdVendida; }

    public double getPrecoPago() { return precoPago; }
    public void setPrecoPago(double precoPago) { this.precoPago = precoPago; }

    public double getTotal() {
        return qtdVendida * precoPago;
    }

    public String toString() {
        return "Produto código: " + codigoProduto + " | Quantidade: " + qtdVendida +
               " | Preço: R$ " + precoPago + " | Total: R$ " + getTotal();
    }
}
