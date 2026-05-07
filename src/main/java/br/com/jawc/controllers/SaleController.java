/**
 * @author jawc
 */
package br.com.jawc.controllers;

import br.com.jawc.domain.Client;
import br.com.jawc.domain.Product;
import br.com.jawc.domain.Sale;
import br.com.jawc.services.interfaces.IClientService;
import br.com.jawc.services.interfaces.IProductService;
import br.com.jawc.services.interfaces.ISaleService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class SaleController implements Serializable {
    private static final long serialVersionUID = 1L;

    private Sale sale;
    private List<Product> products;
    private List<Client> clients;
    private Product selectedProduct;
    private Integer quantity;

    private Boolean isUpdate;

    @Inject
    private IClientService clientService;

    @Inject
    private IProductService productService;

    @Inject
    private ISaleService saleService;

    @PostConstruct
    public void init() {
        this.sale = new Sale();
        this.products = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.isUpdate = false;
        consultProducts();
        consultClients();
    }

    public void addItem(){
        try {
            if (selectedProduct != null && quantity != null && quantity > 0) {
                sale.addProduct(selectedProduct, quantity);
                enviarMensagem("Produto adicionado com sucesso!", FacesMessage.SEVERITY_INFO);
                this.selectedProduct = null;
                this.quantity = 0;
            } else {
                enviarMensagem("Erro! Cheque o produto selecionado e a quantidade.", FacesMessage.SEVERITY_ERROR);
            }
        } catch (Exception e) {
            enviarMensagem("Erro ao adicionar item na venda!", FacesMessage.SEVERITY_ERROR);
            e.printStackTrace();
        }
    }

    public void add() {
        try{
            if(this.sale.getClient() != null && !this.sale.getItems().isEmpty()){
                saleService.save(sale);
                enviarMensagem("Venda salva com sucesso!", FacesMessage.SEVERITY_INFO);
                this.sale = new Sale();
                consultProducts();
                consultClients();
            }
        }catch (Exception e){
            enviarMensagem("Erro ao cadastrar venda!", FacesMessage.SEVERITY_ERROR);
        }
    }

    public void finish() {
        try {
            if (sale.getClient() == null || sale.getItems().isEmpty()) {
                enviarMensagem("Adicione um cliente e itens antes de finalizar!", FacesMessage.SEVERITY_WARN);
                return;
            }

            sale.setStatus(Sale.Status.COMPLETED);
            if (sale.getId() == null) {
                saleService.save(sale);
            } else {
                saleService.update(sale);
            }
            enviarMensagem("Venda Finalizada com Sucesso!", FacesMessage.SEVERITY_INFO);

            // Reseta para uma nova venda
            this.sale = new Sale();
            this.selectedProduct = null;
            this.quantity = 0;
            consultClients();
            consultProducts();
        } catch (Exception e) {
            enviarMensagem("Erro ao finalizar venda!", FacesMessage.SEVERITY_ERROR);
            e.printStackTrace();
        }
    }

    public void cancel() {
        try {
            sale.setStatus(Sale.Status.CANCELLED);
            if (sale.getId() == null) {
                enviarMensagem("Venda Cancelada!", FacesMessage.SEVERITY_INFO);
            } else {
                saleService.update(sale);
                enviarMensagem("Venda Cancelada no sistema!", FacesMessage.SEVERITY_INFO);
            }

            this.sale = new Sale();
            this.selectedProduct = null;
            this.quantity = 0;
            consultClients();
            consultProducts();
        } catch (Exception e) {
            enviarMensagem("Erro ao cancelar venda!", FacesMessage.SEVERITY_ERROR);
            e.printStackTrace();
        }
    }

    private void consultClients() {
        try{
            this.clients = clientService.findAll();
        } catch (Exception e) {
            enviarMensagem("Erro ao listar clientes", FacesMessage.SEVERITY_ERROR);
        }
    }

    private void consultProducts() {
        try{
            this.products = productService.findAll();
        } catch (Exception e) {
            enviarMensagem("Erro ao listar produtos", FacesMessage.SEVERITY_ERROR);
        }
    }

    public void enviarMensagem(String message, FacesMessage.Severity severity) {
        FacesContext.getCurrentInstance().addMessage("growl",
                new FacesMessage(severity, message, null));
    }


    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getUpdate() {
        return isUpdate;
    }

    public void setUpdate(Boolean update) {
        isUpdate = update;
    }

    public IProductService getProductService() {
        return productService;
    }

    public void setProductService(IProductService productService) {
        this.productService = productService;
    }

    public IClientService getClientService() {
        return clientService;
    }

    public void setClientService(IClientService clientService) {
        this.clientService = clientService;
    }

    public ISaleService getSaleService() {
        return saleService;
    }

    public void setSaleService(ISaleService saleService) {
        this.saleService = saleService;
    }
}