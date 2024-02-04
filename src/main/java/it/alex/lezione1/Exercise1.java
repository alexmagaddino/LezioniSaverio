package it.alex.lezione1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Scrivere un'implementazione che permetta la lettura di un numero intero e stampi a video se pari o meno

public class Exercise1 {
    public static void main(String[] args) {
        System.out.println("Inserisci un numero");
        final InputStreamReader input = new InputStreamReader(System.in);
        final BufferedReader bufferedReader = new BufferedReader(input);

        try {
            final String text = bufferedReader.readLine();
            int intero = Integer.parseInt(text);
            if (intero % 2 == 0) {
                System.out.println("è pari");
            } else {
                System.out.println("è dispari");
            }
        } catch (NumberFormatException n) {
            System.out.println("Non è un numero intero!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}