/**
 * @author jawc
 */
package br.com.jawc.controllers;

import br.com.jawc.domain.Client;
import br.com.jawc.domain.Product;
import br.com.jawc.domain.Sale;
import br.com.jawc.services.ClientService;
import br.com.jawc.services.ProductService;
import br.com.jawc.services.SaleService;
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

    private void addItem(){
        try {
            if (selectedProduct != null && quantity > 0) {
                sale.addProduct(selectedProduct, quantity);
                enviarMensagem("Sucesso!", FacesMessage.SEVERITY_INFO);
                this.selectedProduct = null;
                this.quantity = 0;
            } else {
                enviarMensagem("Erro ao cadastrar venda! Cheque o produto selecionado e sua quantidade", FacesMessage.SEVERITY_ERROR);
            }
        } catch (Exception e) {
            enviarMensagem("Erro ao cadastrar venda!", FacesMessage.SEVERITY_ERROR);
        }
    }

    private void add() {
        try{
            if(this.sale.getClient() != null && !this.sale.getItems().isEmpty()){
                saleService.save(sale);
                enviarMensagem("Sucesso!", FacesMessage.SEVERITY_INFO);
                this.sale = new Sale();
                consultProducts();
                consultClients();
            }
        }catch (Exception e){
            enviarMensagem("Erro ao cadastrar venda!", FacesMessage.SEVERITY_ERROR);
        }
    }

    private void finish(){
        sale.setStatus(Sale.Status.COMPLETED);
        saleService.update(sale);
        enviarMensagem("Venda Completa!", FacesMessage.SEVERITY_INFO);
        this.sale = new Sale();
        consultClients();
        consultProducts();
    }

    private void cancel(){
        sale.setStatus(Sale.Status.CANCELLED);
        saleService.update(sale);
        enviarMensagem("Venda Cancelada!", FacesMessage.SEVERITY_INFO);
        this.sale = new Sale();
        consultClients();
        consultProducts();
    }

    private void consultClients() {
        try{
            this.clients = clientService.findAll();
        } catch (Exception e) {
            enviarMensagem("Erro ao listar clients", FacesMessage.SEVERITY_ERROR);
        }
    }

    private void consultProducts() {
        try{
            this.products = productService.findAll();
        } catch (Exception e) {
            enviarMensagem("Erro ao listar produtos", FacesMessage.SEVERITY_ERROR);
        }
    }

    private void enviarMensagem(String message, FacesMessage.Severity severity) {
        FacesContext.getCurrentInstance().addMessage("growl",
                new FacesMessage(severity, message, null));
    }

}
