/**
 * @author jawc
 */
package br.com.jawc.converters;

import br.com.jawc.domain.Client;
import br.com.jawc.services.interfaces.IClientService;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter(forClass = Client.class)
public class ClientConverter implements Converter<Client> {
    @Override
    public Client getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if(value ==null || value.isEmpty()){
            return null;
        }
        try {
            Long id = Long.valueOf(value);
            IClientService clientService = CDI.current().select(IClientService.class).get();
            return clientService.findById(id);
        } catch (NumberFormatException e) {
            return null;
        }

    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Client client) {
        if(client == null || client.getId() == null){
            return "";
        }return String.valueOf(client.getId());
    }
}
