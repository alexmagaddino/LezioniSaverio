package it.alex.lezione3;

// Definire una classe computer con le proprietà:
// float prezzo - string marca - string modello.
// Le proprietà devono essere final.
// Creare una funzione che stampi la marca del computer - modello - prezzo (€).
// Creare una funzione sconto che accetta una percentuale com input e mi restituisce l'importo scontato.
// Lo sconto deve essere di tipo float.
// Il valore inserito di sconto inserito non può essere nè negativo nè maggiore di 100.
// Definire una classe *magazzino*, che contiene un array di computer, dovrà contenere una
// funzione statica main
// in cui posso interrogarlo chiedendo quanti computer ci sono, se una determinata marca è presente,
// se marca e modello è presente, e la possibilità di inserire un computer nuovo.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Computer {
    private final String brand;
    private final String model;
    private final float price;

    public Computer(String brand, String model, float price) {
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    @Override
    public String toString() {
        return brand + " - " + model + " - " + "€" +
                String.format("%.2f", price).replace(".", ",");
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Computer actual)) {
            return false;
        }
        return this.brand.equals(actual.brand) && this.model.equals(actual.model);
    }

    @Override
    public int hashCode() {
        return this.brand.hashCode() + this.model.hashCode();
    }

//    public void print() {
//        System.out.println(this);
//    }
//
//    public float getDiscountedPrice(float discount) throws IllegalArgumentException {
//        if (discount > 100 || discount < 0) {
//            throw new IllegalArgumentException("Lo sconto non può essere " +
//                    "negativo o superiore a 100");
//        }
//        return price * (100 - discount) / 100;
//    }

    public boolean isOfBrand(String brand) {
        return this.brand.equalsIgnoreCase(brand);
    }

    public boolean isOfBrandAndModel(String brand, String model) {
        return this.brand.equalsIgnoreCase(brand) && this.model.equalsIgnoreCase(model);
    }
}

class Warehouse {
    private final ArrayList<Computer> computers;

    public Warehouse() {
        computers = new ArrayList<>();
        computers.add(new Computer("Apple", "MacBook Pro", 1729.99f));
        computers.add(new Computer("Apple", "MacBook Air", 999.99f));
        computers.add(new Computer("HP", "Pavillion Pro", 899));
        computers.add(new Computer("HP", "650", 459f));
        computers.add(new Computer("Dell", "XPS", 1249));
    }

    public int howManyComputersInWarehouse() {
        return computers.size();
    }

    public boolean hasBrand(String brand) {
        for (Computer c : computers) {
            if (c.isOfBrand(brand)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasBrandAndModel(String brand, String model) {
        for (Computer c : computers) {
            if (c.isOfBrandAndModel(brand, model)) {
                return true;
            }
        }
        return false;
    }

    public boolean addComputer(Computer computer) {
        return computers.add(computer);
    }
}

public class Exercise3 {

    public static void main(String[] args) {
        final Warehouse warehouse = new Warehouse();

        while (true) {
            System.out.print("""
                    Seleziona l'operazione da eseguire
                    1: per sapere quanti Computer ci sono nel magazzino
                    2: per sapere se una determinata marca è presente
                    3: per sapere se una determinata marca e modello è presente
                    4: aggiungere un nuovo computer
                    Qualsiasi altro carattere per uscire
                    """);

            final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            final String input;
            try {
                input = reader.readLine();
            } catch (IOException e) {
                System.err.println(e.getMessage());
                continue;
            }

            switch (input) {
                case "1":
                    askSizeToWarehouse(warehouse);
                    break;
                case "2":
                    askBrandToWarehouse(warehouse, reader);
                    break;
                case "3":
                    askModelToWarehouse(warehouse, reader);
                    break;
                case "4":
                    addToWarehouse(warehouse, reader);
                    break;
                default:
                    try {
                        System.out.print("Ci vediamo cowboy!");
                        reader.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } finally {
                        System.exit(0);
                    }
            }
        }
    }

    private static void askSizeToWarehouse(Warehouse w) {
        System.out.println("Nel magazzino ci sono: " + w.howManyComputersInWarehouse() +
                " computer");
    }

    private static void askBrandToWarehouse(Warehouse w, BufferedReader r) {
        System.out.print("Scrivi il brand che vuoi controllare: ");
        final String brand;
        try {
            brand = r.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (w.hasBrand(brand)) {
            System.out.println("La marca è presente!");
        } else {
            System.out.println("La marca non è presente!");
        }
    }

    private static void askModelToWarehouse(Warehouse w, BufferedReader r) {
        System.out.print("Scrivi il brand che vuoi controllare: ");
        final String brand;
        try {
            brand = r.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.print("Scrivi il modello che vuoi controllare: ");
        final String model;
        try {
            model = r.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (w.hasBrandAndModel(brand, model)) {
            System.out.println("Il modello è presente!");
        } else {
            System.out.println("Il modello non è presente!");
        }
    }

    private static void addToWarehouse(Warehouse w, BufferedReader r) {
        System.out.print("Inserisci il brand che vuoi aggiungere: ");
        final String brand;
        try {
            brand = r.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.print("Inserisci il modello che vuoi aggiungere: ");
        final String model;
        try {
            model = r.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String priceString;
        float price;
        while (true) {
            System.out.print("Inserisci il prezzo del computer: ");
            try {
                priceString = r.readLine();
                price = Float.parseFloat(priceString);
                break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NumberFormatException e) {
                System.out.println("Il valore inserito non è un float!");
            }
        }

        if (w.addComputer(new Computer(brand, model, price))) {
            System.out.println("Inserito con successo");
        } else {
            System.out.println("Non è stato possibile inserire il pc");
        }
    }
}
