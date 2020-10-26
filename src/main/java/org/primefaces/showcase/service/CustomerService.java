package org.primefaces.showcase.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.primefaces.showcase.domain.Country;
import org.primefaces.showcase.domain.Customer;
import org.primefaces.showcase.domain.CustomerStatus;
import org.primefaces.showcase.domain.Representative;

@Named
@ApplicationScoped
public class CustomerService {

    private Country[] countries;

    private Representative[] representatives;

    private String[] names;

    List<Customer> customers;

    @PostConstruct
    public void init() {
        customers = new ArrayList<>();

        countries = new Country[]{
                new Country(0, "Argentina", "ar"),
                new Country(1, "Australia", "au"),
                new Country(2, "Brazil", "br"),
                new Country(3, "Canada", "ca"),
                new Country(4, "Germany", "de"),
                new Country(5, "France", "fr"),
                new Country(6, "India", "in"),
                new Country(7, "Italy", "it"),
                new Country(8, "Japan", "jp"),
                new Country(9, "Russia", "ru"),
                new Country(10, "Spain", "es"),
                new Country(11, "United Kingdom", "gb")};

        representatives = new Representative[]{new Representative("Amy Elsner", "amyelsner.png"),new Representative("Anna Fali", "annafali.png"),
                new Representative("Asiya Javayant", "asiyajavayant.png"),new Representative("Bernardo Dominic", "bernardodominic.png"),
                new Representative("Elwin Sharvill", "elwinsharvill.png"),new Representative("Ioni Bowcher", "ionibowcher.png"),
                new Representative("Ivan Magalhaes", "ivanmagalhaes.png"),new Representative("Onyama Limba", "onyamalimba.png"),
                new Representative("Stephen Shaw", "stephenshaw.png"),new Representative("Xuxue Feng", "xuxuefeng.png")};

        names = new String[]{"James","David","Jeanfrancois","Ivar","Tony","Adams","Claire","Costa","Juan","Maria","Jennifer","Stacey","Leja","Morrow",
                "Arvin","Darci","Izzy","Lionel","Clifford","Emily","Kadeem","Mujtaba","Aika","Mayumi","Misaki","Silvio","Nicolas","Antonio",
                "Deepesh","Aditya","Aruna","Jones","Julie","Smith","Johnson","Francesco","Salvatore","Kaitlin","Faith","Maisha","Jefferson",
                "Leon","Rodrigues","Alejandro","Munro","Cody","Chavez","Sinclair","Isabel","Octavia","Murillo","Greenwood","Wickens","Ashley"};
    }


    public List<Customer> getCustomers(int number) {
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            customers.add(new Customer(i + 1000, getCustomerName(), getCountry(), getDate(), CustomerStatus.random(), getActivity(), getRepresentative()));
        }
        return customers;
    }

    private String getCustomerName() {
        return this.getName();
    }

    private String getName() {
        return names[(int) (Math.random() * names.length)];
    }

    private Country getCountry() {
        return countries[(int) (Math.random() * countries.length)];
    }

    private LocalDate getDate() {
        LocalDate now = LocalDate.now();
        long randomDay = ThreadLocalRandom.current().nextLong(now.minusDays(30).toEpochDay(), now.toEpochDay());
        return LocalDate.ofEpochDay(randomDay);
    }

    private int getActivity() {
        return (int) (Math.random() * 100);
    }

    private Representative getRepresentative() {
        return representatives[(int) (Math.random() * representatives.length)];
    }
}