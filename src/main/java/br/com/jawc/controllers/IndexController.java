/**
 * @author jawc
 */
package br.com.jawc.controllers;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@ViewScoped
public class IndexController implements Serializable {

    private static final long serialVersionUID = 1L;

    public String redirectClient() {
        return "/client/list.xhtml?faces-redirect=true";
    }

    public String redirectProduct() {
        return "/product/list.xhtml?faces-redirect=true";
    }
}
