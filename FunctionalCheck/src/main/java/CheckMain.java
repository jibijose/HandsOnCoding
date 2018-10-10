import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

public class CheckMain {

  public static void main(String[] args) {
    List<Integer> myNumList = Arrays.asList(1,66,39,41,465,54,6);
    List<Integer> myNumList50 = findNumbers(myNumList, CheckMain::isMoreThanFifty);
    System.out.println(myNumList50);
  }

  public static List<Integer> findNumbers(List<Integer> l, BiPredicate<Integer, Integer> p) {
    List<Integer> newList = new ArrayList<>();
    for (Integer i : l) {
      if (p.test(i, i + 10)) {
        newList.add(i);
      }
    }
    return newList;
  }

  public static boolean isMoreThanFifty(int n1, int n2) {
    return (n1 + n2) > 50;
  }
}
