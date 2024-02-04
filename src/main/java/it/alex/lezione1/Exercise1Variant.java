package it.alex.lezione1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Scrivere un'implementazione che permetta la lettura di un numero intero
// e che stampi a video se multiplo di 3, 5, entrambi o nessuno dei due.

public class Exercise1Variant {
    public static void main(String[] args) {
        System.out.println("Inserisci un numero");
        final InputStreamReader input = new InputStreamReader(System.in);
        final BufferedReader bufferedReader = new BufferedReader(input);

        try {
            final String text = bufferedReader.readLine();
            int intero = Integer.parseInt(text);
            if (intero % 3 == 0) {
                System.out.println("Il numero è multiplo di 3");
            }
            if (intero % 5 == 0) {
                System.out.println("Il numero è multiplo di 5");
            }
            if (intero % 3 != 0 && intero % 5 != 0) {
                System.out.println("Il numero non è né multiplo di 3 né di 5");
            }
        } catch (NumberFormatException n) {
            System.out.println("Non è un numero intero!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
