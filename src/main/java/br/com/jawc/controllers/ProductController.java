/**
 * @author jawc
 */
package br.com.jawc.controllers;

import br.com.jawc.domain.Product;
import br.com.jawc.services.interfaces.IProductService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ProductController implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private IProductService productService;

    private Product prod;
    private List<Product> products;
    private Boolean isUpdate;
    @PostConstruct
    public void init() {
        this.isUpdate = false;
        this.prod = new Product();
        consult();
    }

    public void add(){
        try{
            productService.save(prod);
            this.prod = new Product();
            consult();
            enviarMensagem("Produto cadastrado com sucesso!", FacesMessage.SEVERITY_INFO);
        }catch (Exception e){
            enviarMensagem("Erro ao cadastrar produto!", FacesMessage.SEVERITY_ERROR);
        }
    }

    public void edit(Product prod){
        isUpdate = true;
        this.prod = prod;
    }

    public void update(){
        try{
            productService.update(prod);
            isUpdate = false;
            this.prod = new Product();
            consult();
            enviarMensagem("Produto alterado com sucesso!", FacesMessage.SEVERITY_INFO);
        }catch (Exception e){
            enviarMensagem("Erro ao alterar produto!", FacesMessage.SEVERITY_ERROR);
        }
    }

    public void delete(Product prod){
        try{
            productService.delete(prod);
            products.remove(prod);
            this.prod = new Product();
            consult();
            enviarMensagem("Produto deletado com sucesso!", FacesMessage.SEVERITY_INFO);
        }catch (Exception e){
            enviarMensagem("Erro ao deletar produto!", FacesMessage.SEVERITY_ERROR);
        }
    }

    private void consult() {
        try{
            this.products = productService.findAll();
        } catch (Exception e) {
            enviarMensagem("Erro ao listar produtos", FacesMessage.SEVERITY_ERROR);
        }
    }

    private void cancel(){
       this.prod = new Product();
       this.isUpdate = false;
    }

    private void enviarMensagem(String message, FacesMessage.Severity severity) {
        FacesContext.getCurrentInstance().addMessage("growl",
                new FacesMessage(severity, message, null));
    }

    public IProductService getProductService() {
        return productService;
    }

    public void setProductService(IProductService productService) {
        this.productService = productService;
    }

    public Product getProd() {
        return prod;
    }

    public void setProd(Product prod) {
        this.prod = prod;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean update) {
        isUpdate = update;
    }
}
