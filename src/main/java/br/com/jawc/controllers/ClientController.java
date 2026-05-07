/**
 * @author jawc
 */
package br.com.jawc.controllers;

import br.com.jawc.domain.Client;
import br.com.jawc.services.ClientService;
import br.com.jawc.services.interfaces.IClientService;
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
public class ClientController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private IClientService clientService;

    private Client client;
    private List<Client> clients;
    private Boolean isUpdate;

    private String cpfMask;
    private String telMask;

    
    @PostConstruct
    public void init() {
        this.isUpdate = false;
        this.client = new Client();
        consult();
    }

    public void add(){
        try{
            cleanMask();
            clientService.save(client);
            this.client  = new Client();
            this.cpfMask = null;
            this.telMask = null;
            consult();
            enviarMensagem("Cliente cadastrado com sucesso!", FacesMessage.SEVERITY_INFO);
        }catch(Exception e){
            enviarMensagem("Erro ao cadastrar Cliente!", FacesMessage.SEVERITY_ERROR);
        }
    }

    public void delete(Client customer){
        try{
            clientService.delete(customer);
            clients.remove(customer);
            this.client  = new Client();
            this.cpfMask = null;
            this.telMask = null;
            consult();
            enviarMensagem("Cliente deletado com sucesso!", FacesMessage.SEVERITY_INFO);
        }catch(Exception e){
            enviarMensagem("Erro ao deletar Cliente!", FacesMessage.SEVERITY_ERROR);
        }
    }

    public void edit(Client customer){
            isUpdate = true;
            this.client = customer;
            this.cpfMask = customer.getCpf();
            this.telMask = customer.getTel();
    }

    public void update(){
        try{
            cleanMask();
            clientService.update(client);
            isUpdate = false;
            this.client  = new Client();
            this.cpfMask = null;
            this.telMask = null;
            consult();
            enviarMensagem("Cliente atualizado com sucesso!", FacesMessage.SEVERITY_INFO);
        }catch(Exception e){
            enviarMensagem("Erro ao atualizar Cliente!", FacesMessage.SEVERITY_ERROR);
        }
    }

    public void cancel() {
        this.client = new Client();
        this.cpfMask = null;
        this.telMask = null;
        this.isUpdate = false;
    }

    private void consult() {
        try {
            this.clients = clientService.findAll();
        } catch (Exception e) {
            enviarMensagem("Erro ao listar clientes", FacesMessage.SEVERITY_ERROR);        }
        }

    private void enviarMensagem(String message, FacesMessage.Severity severity) {
        FacesContext.getCurrentInstance().addMessage("growl",
                new FacesMessage(severity, message, null));
    }

    private void cleanMask() {
        if (cpfMask != null) {
            this.client.setCpf(cpfMask.replaceAll("[^0-9]", ""));
        }
        if(telMask != null) {
            this.client.setTel(telMask.replaceAll("[^0-9]", ""));
        }
    }

    public IClientService getClientService() {
        return clientService;
    }

    public void setClientService(IClientService clientService) {
        this.clientService = clientService;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Boolean getUpdate() {
        return isUpdate;
    }

    public void setUpdate(Boolean update) {
        isUpdate = update;
    }

    public String getCpfMask() {
        return cpfMask;
    }

    public void setCpfMask(String cpfMask) {
        this.cpfMask = cpfMask;
    }

    public String getTelMask() {
        return telMask;
    }

    public void setTelMask(String telMask) {
        this.telMask = telMask;
    }
}
