/**
 * @author jawc
 */
package br.com.jawc.converters;

import br.com.jawc.domain.Product;
import br.com.jawc.services.interfaces.IProductService;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter(forClass = Product.class)
public class ProductConverter implements Converter<Product> {
    @Override
    public Product getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            Long id = Long.valueOf(value);
            IProductService productService = CDI.current().select(IProductService.class).get();
            return productService.findById(id);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Product product) {
        if (product == null || product.getId() == null) {
            return "";
        }return String.valueOf(product.getId());
    }
}
