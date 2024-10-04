import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class main {

    public static void main(String[] args) {

        Trader raoul = new Trader("Raul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000), new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710), new Transaction(mario, 2012, 700), new Transaction(alan, 2012, 950));

        // 1 Encuentre todas las transacciones del año 2011 y ordénelas por valor (menor a menor).
        List<Transaction> tr2011 = transactions.stream()
                .filter(transaction -> transaction.getAnio() == 2011 )
                //Sort from the greater to the less
                .sorted(comparing(Transaction::getValor).reversed()) //Así sería al revés
                .collect(toList());


        for (Transaction transaction : tr2011) {

            System.out.println(transaction.toString() + "\n");

        }

    }




}
