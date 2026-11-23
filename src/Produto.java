public class Produto {
    private int codigo;
    private String descricao;
    private double valorCompra;
    private double valorVenda;
    private int estoqueAtual;
    private int estoqueMin;

    public Produto(int codigo, String descricao, double valorCompra, double valorVenda, int estoqueAtual, int estoqueMin) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.valorCompra = valorCompra;
        this.valorVenda = valorVenda;
        this.estoqueAtual = estoqueAtual;
        this.estoqueMin = estoqueMin;
    }

    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getValorCompra() { return valorCompra; }
    public void setValorCompra(double valorCompra) { this.valorCompra = valorCompra; }

    public double getValorVenda() { return valorVenda; }
    public void setValorVenda(double valorVenda) { this.valorVenda = valorVenda; }

    public int getEstoqueAtual() { return estoqueAtual; }
    public void setEstoqueAtual(int estoqueAtual) { this.estoqueAtual = estoqueAtual; }

    public int getEstoqueMin() { return estoqueMin; }
    public void setEstoqueMin(int estoqueMin) { this.estoqueMin = estoqueMin; }

    public String toString() {
        return "Código: " + codigo + "\n" +
               "Descrição: " + descricao + "\n" +
               "Valor de compra: R$ " + valorCompra + "\n" +
               "Valor de venda: R$ " + valorVenda + "\n" +
               "Estoque atual: " + estoqueAtual + "\n" +
               "Estoque mínimo: " + estoqueMin;
    }
}
