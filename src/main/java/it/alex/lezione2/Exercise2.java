package it.alex.lezione2;

// Leggere un numero intero positivo,
// stampare dei `#` crescenti (1,2,3...) ad ogni riga fino ad arrivare
// ad un numero di `#` pari al numero inserito

// Es. con 5:
// #
// ##
// ###
// ####
// #####

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class HashPrinter {
    final int lineNumber;

    public HashPrinter(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public void printHashes() throws IllegalArgumentException {
        if (lineNumber <= 0) {
            throw new IllegalArgumentException("Errore, il numero inserito non può essere minore o uguale a zero!");
        }
        for (int i = 0; i < lineNumber; i++) {
            final int line = i + 1;
            System.out.println(line + ". " + "#".repeat(line));
        }
    }
}

public class Exercise2 {
    public static void main(String[] args) {
        System.out.print("Inserisci un numero intero positivo: ");

        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            final String line = reader.readLine();
            final int number = Integer.parseInt(line);
            final HashPrinter hashPrinter = new HashPrinter(number);
            hashPrinter.printHashes();
        } catch (NumberFormatException e) {
            System.err.println("Il carattere inserito non corrisponde alla specifica di numero intero!");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println("Ops, c'è stato un errore di I/O");
        }
    }
}