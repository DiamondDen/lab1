package net.diamondden.sweetness;

import lombok.val;
import net.diamondden.sweetness.provider.FileSweetsProvider;
import net.diamondden.sweetness.provider.SortedProvider;
import net.diamondden.sweetness.provider.SweetsProvider;

import java.util.*;

public class Main {
  public static void main(String[] args) {
    new Main();
  }

  public Main() {
    this.parseFIle("./test1.txt");
    this.parseFIle("./test2.txt");
    this.parseFIle("./test3.txt");
  }

  private void parseFIle(String filePath) {
    try {
      System.out.println(filePath + ":");
      this.handleSweets(new SortedProvider(new FileSweetsProvider(filePath)));
    } catch (Exception e) {
      System.err.println("Error on parse " + filePath + ":");
      e.printStackTrace();
    }
  }

  private void printMoreThen5000(Map<String, Sweetness> map) {
    System.out.println("Больше 5000ккал");
    for (Map.Entry<String, Sweetness> stringSweetnessEntry : map.entrySet()) {
      Sweetness sweetness = stringSweetnessEntry.getValue();
      if (sweetness.getKKalIn100() * 10 > 5000) {
        System.out.println(" " + stringSweetnessEntry.getKey());
      }
    }
  }

  private void printChocolate(Map<String, Sweetness> map) {
    val chocolate = map.get("шоколад");
    if (chocolate == null) {
      System.out.println("Шоколадок не найдено(");
    } else {
      System.out.println("Всего шоколадок: " + chocolate.getWeight());
    }
  }

  private void printAllSweets(Map<String, Sweetness> map) {
    System.out.println("Сладостей:");
    for (String s : map.keySet()) {
      System.out.println(" " + s);
    }
  }

  public void handleSweets(SweetsProvider provider) {
    val map = provider.loadSweetness();
    this.printAllSweets(map);
    this.printChocolate(map);
    this.printMoreThen5000(map);
  }
}