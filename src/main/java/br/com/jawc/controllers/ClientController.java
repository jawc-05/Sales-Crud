/**
 * @author jawc
 */
package br.com.jawc.controllers;

import br.com.jawc.domain.Client;
import br.com.jawc.services.ClientService;
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

    @Inject
    private ClientService clientService;

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
}
