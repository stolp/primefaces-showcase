/**
 * Copyright 2009-2020 PrimeTek.
 * <p>
 * Licensed under PrimeFaces Commercial License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * Licensed under PrimeFaces Commercial License, Version 1.0 (the "License");
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.primefaces.showcase.component.menu;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.primefaces.component.api.AjaxSource;
import org.primefaces.component.api.UIOutcomeTarget;
import org.primefaces.component.menu.AbstractMenu;
import org.primefaces.component.menu.BaseMenuRenderer;
import org.primefaces.component.menuitem.UIMenuItem;
import org.primefaces.component.submenu.UISubmenu;
import org.primefaces.expression.SearchExpressionFacade;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.Submenu;
import org.primefaces.util.AjaxRequestBuilder;
import org.primefaces.util.ComponentTraversalUtils;
import org.primefaces.util.WidgetBuilder;

public class ShowcaseMenuRenderer extends BaseMenuRenderer {

    @Override
    protected void encodeMarkup(FacesContext context, AbstractMenu abstractMenu) throws IOException {
        ShowcaseMenu menu = (ShowcaseMenu) abstractMenu;
        ResponseWriter writer = context.getResponseWriter();
        String style = menu.getStyle();
        String styleClass = menu.getStyleClass();
        styleClass = (styleClass == null) ? "layout-menu" : "layout-menu " + styleClass;

        writer.startElement("div", menu);
        writer.writeAttribute("class", styleClass, "styleClass");
        if (style != null) {
            writer.writeAttribute("style", style, "style");
        }
        writer.writeAttribute("role", "menubar", null);

        if (menu.getElementsCount() > 0) {
            encodeElements(context, menu, menu.getElements(), false);
        }

        writer.endElement("div");
    }

    protected void encodeElements(FacesContext context, AbstractMenu menu, List<MenuElement> elements, boolean isNestedSubmenu) throws IOException {
        for (MenuElement element : elements) {
            encodeElement(context, menu, element, isNestedSubmenu);
        }
    }

    protected void encodeElement(FacesContext context, AbstractMenu menu, MenuElement element, boolean isNestedSubmenu) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        if (element.isRendered()) {
            if (element instanceof MenuItem) {
                MenuItem menuItem = (MenuItem) element;
                encodeMenuItem(context, menu, menuItem, isNestedSubmenu);

            } else if (element instanceof Submenu) {
                if (isNestedSubmenu) {
                    Submenu submenu = (Submenu) element;
                    String style = submenu.getStyle();

                    writer.startElement("a", null);
                    writer.writeAttribute("role", "menuitem", null);
                    writer.writeAttribute("class", "p-link", null);
                    writer.writeAttribute("data-meta", submenu.getLabel(), null);
                    writer.writeText(submenu.getLabel(), null);
                    writer.endElement("a");

                    writer.startElement("div", null);
                    writer.writeAttribute("class", "p-toggleable-content", null);
                    if (style != null) {
                        writer.writeAttribute("style", style, null);
                    }
                    writer.startElement("ul", null);
                    writer.writeAttribute("role", "menu", null);

                    for (Object e : submenu.getElements()) {
                        encodeNestedMenuItem(context, menu,(MenuItem) e, isNestedSubmenu);
                    }

                    writer.endElement("ul");
                    writer.endElement("div");

                } else {
                    Submenu submenu = (Submenu) element;

                    encodeSubmenu(context, menu, submenu, isNestedSubmenu);
                }
            }
        }
    }

    protected void encodeNestedMenuItem(FacesContext context, AbstractMenu menu, MenuItem menuItem, boolean isNestedSubmenu) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        Object label = menuItem.getValue();
        String title = menuItem.getTitle() != null ? menuItem.getTitle()  + " " + label : label.toString();
        String targetURL = getTargetURL(context, (UIOutcomeTarget) menuItem);

        writer.startElement("li", null);
        writer.writeAttribute("role", "menuitem", null);
        writer.startElement("a", null);
        writer.writeAttribute("role", "menuitem", null);
        writer.writeAttribute("href", targetURL, null);
        writer.writeAttribute("data-meta", title, null);
        writer.writeText(label, null);
        writer.endElement("a");
        writer.endElement("li");
    }

    protected void encodeSubmenu(FacesContext context, AbstractMenu menu, Submenu submenu, boolean isNestedSubmenu) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String label = submenu.getLabel();
        int childrenElementsCount = submenu.getElementsCount();
        boolean expanded = submenu.isExpanded();

        if (isNestedSubmenu) {
            writer.startElement("a", null);
            writer.writeAttribute("href", "#", null);
            writer.writeAttribute("role", "menuitem", null);
            writer.writeAttribute("data-meta", label, null);
            writer.writeText(label, null);
            writer.endElement("a");
        } else {
            writer.startElement("div", null);
            writer.writeAttribute("class", "menu-category", null);

            if (label != null) {
                writer.writeText(label, null);
            }

            writer.endElement("div");
        }

        //submenus and menuitems
        if (childrenElementsCount > 0) {

            writer.startElement("div", null);
            writer.writeAttribute("class", "menu-items", null);
            if (expanded) {
                writer.writeAttribute("style", "display:block", null);
            }
            encodeElements(context, menu, submenu.getElements(), true);
            writer.endElement("div");
        }
    }

    protected void encodeMenuItem(FacesContext context, AbstractMenu menu, MenuItem menuitem, boolean isNestedSubmenu) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        boolean disabled = menuitem.isDisabled();
        String style = menuitem.getStyle();
        String styleClass = menuitem.getStyleClass();

        writer.startElement("a", null);
        writer.writeAttribute("role", "menuitem", null);
        if (style != null) {
            writer.writeAttribute("style", style, null);
        }
        if (styleClass != null) {
            writer.writeAttribute("class", styleClass, null);
        }

        if (disabled) {
            writer.writeAttribute("href", "#", null);
            writer.writeAttribute("onclick", "return false;", null);
        } else {
            String onclick = menuitem.getOnclick();

            //GET
            if (menuitem.getUrl() != null || menuitem.getOutcome() != null) {
                String targetURL = getTargetURL(context, (UIOutcomeTarget) menuitem);
                writer.writeAttribute("href", targetURL, null);

                if (menuitem.getTarget() != null) {
                    writer.writeAttribute("target", menuitem.getTarget(), null);
                }
            } //POST
            else {
                writer.writeAttribute("href", "#", null);

                UIComponent form = ComponentTraversalUtils.closestForm(context, menu);
                if (form == null) {
                    throw new FacesException("MenuItem must be inside a form element");
                }

                String command;
                if (menuitem.isDynamic()) {
                    String menuClientId = menu.getClientId(context);
                    Map<String, List<String>> params = menuitem.getParams();
                    if (params == null) {
                        params = new LinkedHashMap<String, List<String>>();
                    }
                    List<String> idParams = new ArrayList<String>();
                    idParams.add(menuitem.getId());
                    params.put(menuClientId + "_menuid", idParams);

                    command = menuitem.isAjax() ? createAjaxRequest(context, menu, (AjaxSource) menuitem, form, params) : buildNonAjaxRequest(context, menu, form, menuClientId, params, true);
                } else {
                    command = menuitem.isAjax() ? createAjaxRequest(context, (AjaxSource) menuitem, form) : buildNonAjaxRequest(context, ((UIComponent) menuitem), form, ((UIComponent) menuitem).getClientId(context), true);
                }

                onclick = (onclick == null) ? command : onclick + ";" + command;
            }

            if (onclick != null) {
                writer.writeAttribute("onclick", onclick, null);
            }
        }

        encodeMenuItemContent(context, menu, menuitem);

        writer.endElement("a");
    }

    @Override
    protected void encodeMenuItemContent(FacesContext context, AbstractMenu menu, MenuItem menuitem) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        Object value = menuitem.getValue();
        String title = menuitem.getTitle() != null ? menuitem.getTitle()  + " " + value : value.toString();

        if (value != null) {
            writer.writeAttribute("data-meta", title, null);
            writer.writeText(value, "value");
        }
    }

    @Override
    protected void encodeScript(FacesContext context, AbstractMenu abstractMenu) throws IOException {
        ShowcaseMenu menu = (ShowcaseMenu) abstractMenu;
        String clientId = menu.getClientId(context);
        WidgetBuilder wb = getWidgetBuilder(context);
        wb.init("Showcase", menu.resolveWidgetVar(), clientId)
                .attr("stateful", menu.isStateful());
        wb.finish();
    }

    protected String createAjaxRequest(FacesContext context, AjaxSource source, UIComponent form) {
        UIComponent component = (UIComponent) source;
        String clientId = component.getClientId(context);

        AjaxRequestBuilder builder = getAjaxRequestBuilder();

        builder.init()
                .source(clientId)
                .form(SearchExpressionFacade.resolveClientId(context, component, source.getForm()))
                .process(component, source.getProcess())
                .update(component, source.getUpdate())
                .async(source.isAsync())
                .global(source.isGlobal())
                .delay(source.getDelay())
                .timeout(source.getTimeout())
                .partialSubmit(source.isPartialSubmit(), source.isPartialSubmitSet(), source.getPartialSubmitFilter())
                .resetValues(source.isResetValues(), source.isResetValuesSet())
                .ignoreAutoUpdate(source.isIgnoreAutoUpdate())
                .onstart(source.getOnstart())
                .onerror(source.getOnerror())
                .onsuccess(source.getOnsuccess())
                .oncomplete(source.getOncomplete())
                .params(component);

        if (form != null) {
            builder.form(form.getClientId(context));
        }

        builder.preventDefault();

        return builder.build();
    }

    protected String createAjaxRequest(FacesContext context, AbstractMenu menu, AjaxSource source, UIComponent form,
                                       Map<String, List<String>> params) {

        String clientId = menu.getClientId(context);

        AjaxRequestBuilder builder = getAjaxRequestBuilder();

        builder.init()
                .source(clientId)
                .process(menu, source.getProcess())
                .update(menu, source.getUpdate())
                .async(source.isAsync())
                .global(source.isGlobal())
                .delay(source.getDelay())
                .timeout(source.getTimeout())
                .partialSubmit(source.isPartialSubmit(), source.isPartialSubmitSet(), source.getPartialSubmitFilter())
                .resetValues(source.isResetValues(), source.isResetValuesSet())
                .ignoreAutoUpdate(source.isIgnoreAutoUpdate())
                .onstart(source.getOnstart())
                .onerror(source.getOnerror())
                .onsuccess(source.getOnsuccess())
                .oncomplete(source.getOncomplete())
                .params(params);

        if (form != null) {
            builder.form(form.getClientId(context));
        }

        builder.preventDefault();

        return builder.build();
    }

    protected AjaxRequestBuilder getAjaxRequestBuilder() {
        Class rootContext;
        Object requestContextInstance;
        AjaxRequestBuilder builder;

        try {
            rootContext = Class.forName("org.primefaces.context.PrimeRequestContext");
        } catch (ClassNotFoundException ex) {
            try {
                rootContext = Class.forName("org.primefaces.context.RequestContext");
            } catch (ClassNotFoundException ex1) {
                throw new RuntimeException(ex1);
            }
        }

        try {
            Method method = rootContext.getMethod("getCurrentInstance");
            requestContextInstance = method.invoke(null);

            method = requestContextInstance.getClass().getMethod("getAjaxRequestBuilder");
            builder = (AjaxRequestBuilder) method.invoke(requestContextInstance);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return builder;
    }
}
