/**
 * @author jawc
 */
package br.com.jawc.domain;

import br.com.jawc.annotation.TipoChave;
import br.com.jawc.dao.Persistence;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Sells implements Persistence {

    public enum Status {
        INICIADA, CONCLUIDA, CANCELADA;
    }
    @TipoChave("getCodigo")
    private String codigo;

    private Client client;

    private Set<ProductQuantity> products;

    private BigDecimal valorTotal;

    private Instant dataVenda;

    private Status status;

    public Sells() {
        products = new HashSet<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<ProductQuantity> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductQuantity> products) {
        this.products = products;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Instant getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Instant dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void adicionarProduto(Product product, Integer quantity) {
        validarStatus();
        Optional<ProductQuantity> op =
                products.stream().filter(filter -> filter.getProduct().getCodigo().equals(product.getCodigo())).findAny();
        if (op.isPresent()) {
            ProductQuantity produtpQtd = op.get();
            produtpQtd.adicionar(quantity);
        } else {
            // Criar fabrica para criar ProdutoQuantidade
            ProductQuantity prod = new ProductQuantity();
            prod.setProduct(product);
            prod.adicionar(quantity);
            products.add(prod);
        }
        recalcularValorTotalVenda();
    }

    private void validarStatus() {
        if (this.status == Status.CONCLUIDA) {
            throw new UnsupportedOperationException("IMPOSSÍVEL ALTERAR VENDA FINALIZADA");
        }
    }

    public void removerProduto(Product product, Integer quantity) {
        validarStatus();
        Optional<ProductQuantity> op =
                products.stream().filter(filter -> filter.getProduct().getCodigo().equals(product.getCodigo())).findAny();

        if (op.isPresent()) {
            ProductQuantity produtpQtd = op.get();
            if (produtpQtd.getQuantity()>quantity) {
                produtpQtd.remover(quantity);
                recalcularValorTotalVenda();
            } else {
                products.remove(op.get());
                recalcularValorTotalVenda();
            }

        }
    }

    public void removerTodosProdutos() {
        validarStatus();
        products.clear();
        valorTotal = BigDecimal.ZERO;
    }

    public Integer getQuantidadeTotalProdutos() {
        // Soma a quantidade getQuantidade() de todos os objetos ProdutoQuantidade
        int result = products.stream()
                .reduce(0, (partialCountResult, prod) -> partialCountResult + prod.getQuantity(), Integer::sum);
        return result;
    }

    private void recalcularValorTotalVenda() {
        validarStatus();
        BigDecimal valorTotal = BigDecimal.ZERO;
        for (ProductQuantity prod : this.products) {
            valorTotal = valorTotal.add(prod.getValorTotal());
        }
        this.valorTotal = valorTotal;
    }
}
