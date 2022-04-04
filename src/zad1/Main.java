/**
 *
 *  @author Śnieżko Eugeniusz S23951
 *
 */

package zad1;


/*<-- niezbędne importy */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    // Lista destynacji: port_wylotu port_przylotu cena_EUR 
    List<String> dest = Arrays.asList(
      "bleble bleble 2000",
      "WAW HAV 1200",
      "xxx yyy 789",
      "WAW DPS 2000",
      "WAW HKT 1000"
    );
    double ratePLNvsEUR = 4.30;
    List<String> result = dest.stream()
            .filter(x -> x.startsWith("WAW"))
            .map(x-> {
              String [] temp = x.split(" ");
              return "to " + temp[1] + " price in PLN: " + (int) (Integer.parseInt(temp[2]) * ratePLNvsEUR);})
            .collect(Collectors.toList());
    /*<-- tu należy dopisać fragment
     * przy czym nie wolno używać żadnych własnych klas, jak np. ListCreator
     * ani też żadnych własnych interfejsów
     * Podpowiedź: należy użyć strumieni
     */

    for (String r : result) System.out.println(r);
  }
}
/*
to HAV - price in PLN:	5160
to DPS - price in PLN:	8600
to HKT - price in PLN:	4300
dest.stream()
            .filter(x -> x.startsWith("WAW"))
            .map(x-> {
              String [] temp = x.split(" ");
              return "to " + temp[1] + " price in PLN: " + (int) (Integer.parseInt(temp[2]) * ratePLNvsEUR);})
            .collect(Collectors.toList());
 */