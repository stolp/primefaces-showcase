package org.primefaces.showcase.rest;

import org.primefaces.model.rest.AutoCompleteSuggestion;
import org.primefaces.model.rest.AutoCompleteSuggestionResponse;
import org.primefaces.showcase.domain.Theme;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Named("themeRestService")
@Path("/theme")
public class ThemeService {

    @Inject
    private org.primefaces.showcase.service.ThemeService service;

    @GET
    @Path("/autocomplete")
    @Produces({ MediaType.APPLICATION_JSON })
    public AutoCompleteSuggestionResponse autocomplete(@QueryParam("query") String query) {
        String queryLowerCase = query.toLowerCase();
        List<Theme> allThemes = service.getThemes();
        return new AutoCompleteSuggestionResponse(allThemes.stream()
                .filter(t -> t.getName().toLowerCase().contains(queryLowerCase))
                .map(t -> new AutoCompleteSuggestion(Integer.toString(t.getId()), t.getDisplayName()))
                .collect(Collectors.toList()));
    }
}
