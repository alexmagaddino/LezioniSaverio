package it.alex.lezione4;

// Partendo dall'esercizio 3, creare una classe astratta Articolo, con prezzo e id(solo l'id final),
// marca, modello ed enum(o stringa) tipologia.
// far estendere Computer ad articolo settando a Computer la tipologia, variare il magazzino in modo
// da poter gestire più tipi di articolo ad esempio: TV, cellulare.

// Aggiungere gestione magazzino che inserito un id mi stampi le sue informazioni e possa
// applicare uno sconto (sempre da stampare)

// Aggiungere funzione che dato un id e un nuovo importo cambi il prezzo di un articolo

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

enum WarehouseItemType {
    COMPUTER, TV, SMARTPHONE
}

abstract class WarehouseItem {
    private final String id;
    private final String brand;
    private final String model;
    private float price;
    private final WarehouseItemType type;

    WarehouseItem(String id, String brand, String model, float price, WarehouseItemType type) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public WarehouseItemType getType() {
        return type;
    }

    @Override
    public String toString() {
        return type + " - " + brand + " - " + model + " - " + "€" +
                String.format("%.2f", price).replace(".", ",");
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof WarehouseItem actual)) {
            return false;
        }
        return this.id.equals(actual.id) &&
                this.type.equals(actual.type) &&
                this.brand.equals(actual.brand) &&
                this.model.equals(actual.model);
    }

    @Override
    public int hashCode() {
        return this.brand.hashCode() + this.model.hashCode();
    }

    public void print() {
        System.out.println(this);
    }

    public float getDiscountedPrice(float discount) throws IllegalArgumentException {
        if (discount > 100 || discount < 0) {
            throw new IllegalArgumentException("Lo sconto non può essere " +
                    "negativo o superiore a 100");
        }
        return price * (100 - discount) / 100;
    }

    public boolean isOfBrand(String brand) {
        return this.getBrand().equalsIgnoreCase(brand);
    }

    public boolean isOfBrandAndModel(String brand, String model) {
        return this.getBrand().equalsIgnoreCase(brand) && this.getModel().equalsIgnoreCase(model);
    }
}

class Computer extends WarehouseItem {
    public Computer(String id, String brand, String model, float price) {
        super(id, brand, model, price, WarehouseItemType.COMPUTER);
    }
}

class Warehouse {
    private final ArrayList<WarehouseItem> items;

    public Warehouse() {
        items = new ArrayList<>();
        items.add(new Computer("A1", "Apple", "MacBook Pro", 1729.99f));
        items.add(new Computer("A2", "Apple", "MacBook Air", 999.99f));
        items.add(new Computer("H1", "HP", "Pavillion Pro", 899));
        items.add(new Computer("H2", "HP", "650", 459f));
        items.add(new Computer("D1", "Dell", "XPS", 1249));
    }

    public int howManyComputersInWarehouse() {
        return items.size();
    }

    public boolean hasBrand(String brand) {
        for (WarehouseItem i : items) {
            if (i.isOfBrand(brand)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasBrandAndModel(String brand, String model) {
        for (WarehouseItem i : items) {
            if (i.isOfBrandAndModel(brand, model)) {
                return true;
            }
        }
        return false;
    }

    public boolean addNewItem(WarehouseItem item) {
        return items.add(item);
    }
}

public class Exercise4 {

    public static void main(String[] args) {
        final Warehouse warehouse = new Warehouse();

//        Aggiungere gestione magazzino che inserito un id mi stampi le sue informazioni e possa
// applicare uno sconto (sempre da stampare)

// Aggiungere funzione che dato un id e un nuovo importo cambi il prezzo di un articolo
        while (true) {
            System.out.print("""
                    Seleziona l'operazione da eseguire
                    1: Per sapere quanti Articoli ci sono nel magazzino
                    2: Per sapere se una determinata marca è presente
                    3: Per sapere se una determinata marca e modello è presente
                    4: Aggiungere un nuovo articolo
                    5: Calcola sconto su un articolo
                    6: Cambia prezzo ad un articolo
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
                case "5":
                    calculateDiscount(warehouse, reader);
                    break;
                case "6":
                    applyNewPrice(warehouse, reader);
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
                " articoli");
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

    // TODO da rivedere
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

        final WarehouseItem newItem = new Computer(
                "C" + w.howManyComputersInWarehouse(),
                brand,
                model,
                price
        );
        if (w.addNewItem(newItem)) {
            System.out.println("Inserito con successo");
        } else {
            System.out.println("Non è stato possibile inserire il pc");
        }
    }

    private static void calculateDiscount(Warehouse w, BufferedReader r) {
        // TODO
    }

    private static void applyNewPrice(Warehouse w, BufferedReader r) {
        // TODO
    }
}