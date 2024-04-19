package org.example;

import org.example.beans.Country;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        Country countryConstructor = (Country) context.getBean("countryConstructor");
        Country countrySetter = (Country) context.getBean("countrySetter");

        System.out.println("Pays (injection par constructeur): " + countryConstructor.getName() + ", Population: " + countryConstructor.getPopulation());
        System.out.println("Pays (injection par accesseur): " + countrySetter.getName() + ", Population: " + countrySetter.getPopulation());
        
        // Récupérer les instances des beans
        Country countryWithParams = (Country) context.getBean("countryWithParams");
        Country countryWithoutParams = (Country) context.getBean("countryWithoutParams");

        // Afficher les informations
        System.out.println("Pays avec paramètres : CountryName : " + countryWithParams.getName() + ", Population : " + countryWithParams.getPopulation());
        System.out.println("Pays sans paramètres : CountryName : " + countryWithoutParams.getName() + ", Population : " + countryWithoutParams.getPopulation());
    }
}
