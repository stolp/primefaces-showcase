package org.primefaces.showcase.rest;

import org.primefaces.model.rest.AutoCompleteSuggestion;
import org.primefaces.model.rest.AutoCompleteSuggestionResponse;
import org.primefaces.showcase.domain.Country;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Named("countryRestService")
@Path("/country")
public class CountryService {

    @Inject
    private org.primefaces.showcase.service.CountryService service;

    @GET
    @Path("/autocomplete")
    @Produces({ MediaType.APPLICATION_JSON })
    public AutoCompleteSuggestionResponse autocomplete(@QueryParam("query") String query) {
        String queryLowerCase = query.toLowerCase();
        List<Country> allCountrys = service.getCountries();
        return new AutoCompleteSuggestionResponse(allCountrys.stream()
                .filter(t -> t.getName().toLowerCase().contains(queryLowerCase))
                .map(t -> new AutoCompleteSuggestion(Integer.toString(t.getId()), t.getName()))
                .collect(Collectors.toList()));
    }
}
