/**
 *
 *  @author Śnieżko Eugeniusz S23951
 *
 */

package zad2;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/*<--
 *  niezbędne importy
 */
public class Main {
  public static void main(String[] args) {
    /*<--
     *  definicja operacji w postaci lambda-wyrażeń:
     *  - flines - zwraca listę wierszy z pliku tekstowego
     *  - join - łączy napisy z listy (zwraca napis połączonych ze sobą elementów listy napisów)
     *  - collectInts - zwraca listę liczb całkowitych zawartych w napisie
     *  - sum - zwraca sumę elmentów listy liczb całkowitych
     */

      Function <String,List<String>> flines = e -> {
      try {
        return Files.readAllLines(Paths.get(e));
      } catch (IOException ex) {
        ex.printStackTrace();
        return new ArrayList<>();
      }
    };
    // spytac co najlepsze do joinowania stringow
      Function <List<String>, String> join = entryList -> {
        StringBuilder sb = new StringBuilder();
        for (String s : entryList) sb.append(s);

        return sb.toString();
      };

      Function <String, List<Integer>> collectInts = string ->
              Arrays.stream(string .replaceAll("\\D", " ")
                              .replaceAll(" +", " ")
                              .split(" "))
                      .filter(n-> n.length()>0)
                      .map(Integer::parseInt).collect(Collectors.toList());

    // spytac co zwracac z opszonala
      Function <List<Integer>, Integer> sum = n -> {
      Optional<Integer> result = n.stream().reduce(Integer::sum);
      return result.orElseThrow(IllegalArgumentException::new);
    };

    String fname = System.getProperty("user.home") + "/LamComFile.txt";
    InputConverter<String> fileConv = new InputConverter<>(fname);
    List<String> lines = fileConv.convertBy(flines);
    String text = fileConv.convertBy(flines, join);
    List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
    System.out.println("tu");
    System.out.println(ints);
    Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

    System.out.println(lines); //1
    System.out.println(text);  //2
    System.out.println(ints);  //3
    System.out.println(sumints); //4

    List<String> arglist = Arrays.asList(args);
    InputConverter<List<String>> slistConv = new InputConverter<>(arglist);
    sumints = slistConv.convertBy(join, collectInts, sum);
    System.out.println(sumints); //5

  }
}
/*
Dla następujących danych z pliku:
Cars:
- Fiat: 15, Ford: 20
- Opel: 8, Mitsubishi: 10

oraz  nastepujących argumentów wywołania metody main:
Warszawa 100 Kielce 200 Szczecin 300
program powinien wyprowadzić na konsolę:
[Cars:, - Fiat: 15, Ford: 20, - Opel: 8, Mitsubishi: 10]
Cars:- Fiat: 15, Ford: 20- Opel: 8, Mitsubishi: 10
[15, 20, 8, 10]
53
600
 */

//      Function<String, List<Integer>> collectInts = input ->{
//        String[] temp = input.replaceAll("\\D", " ")
//                .trim().replaceAll(" +","").split(" ");
//        return Arrays.stream(temp).map(Integer::parseInt).collect(Collectors.toList());
//      };
//    Function <String, List<String>> flines = (path) -> {
//      try {
//        List <String> result = new ArrayList<>();
//
//        BufferedReader br = new BufferedReader(new FileReader(path));
//        String temp = br.readLine();
//        while(temp != null){
//          result.add(temp);
//        }
//          br.close();
//        return result;
//
//      } catch (IOException e) {
//        e.printStackTrace();
//        return new ArrayList<>();
//      }
//    };
