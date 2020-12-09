/*
 * Copyright 2009-2014 PrimeTek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.primefaces.showcase.view.data.treetable;

import javax.faces.view.ViewScoped;
import org.primefaces.model.TreeNode;
import org.primefaces.showcase.service.DocumentService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.showcase.domain.Document;

@Named("ttMultiViewStateView")
@ViewScoped
public class MultiViewStateView implements Serializable {
    
    private TreeNode root;
    
    @Inject
    private DocumentService service;
    
    @PostConstruct
    public void init() {
        root = service.createDocuments();
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setService(DocumentService service) {
        this.service = service;
    }

    public void clearMultiViewState() {
        FacesContext context = FacesContext.getCurrentInstance();
        String viewId = context.getViewRoot().getViewId();
        PrimeFaces.current().multiViewState().clearAll(viewId, true, (clientId) -> {
            showMessage(clientId);
        });
    }

    private void showMessage(String clientId) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, clientId + " multiview state has been cleared out", null));
    }
    
    public void someAction(Document document) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Action on " + document.getName(), null));
    }
}

