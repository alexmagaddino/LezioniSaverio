package it.alex.lezione2;

// Concetto di equals per comparare le classi

 class Equals {
    final int voto;

    public Equals(int voto) {
        this.voto = voto;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Equals) {
            return this.voto == ((Equals) o).voto;
        }
        return false;
    }
}

public class EqualsTest {
    public static void main(String[] args) {
        final int intValue = 7;
        final Equals p1 = new Equals(intValue);
        final Equals p2 = new Equals(7);

        System.out.println("Sono uguali: " + p1.equals(p2));
        System.out.println("Sono uguali: " + (p1 == p2));
        System.out.println("Sono uguali: " + (intValue == 7));
    }
}