package net.diamondden.sweetness.provider;

import net.diamondden.sweetness.Sweetness;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class FileSweetsProvider implements SweetsProvider {

  private final File file;

  public FileSweetsProvider(String path) throws FileNotFoundException {
    this.file = new File(path);
    if (!this.file.exists())
      throw new FileNotFoundException(path);
  }

  public Sweetness parseSweetness(String raw) {
    String[] args = raw.split(";");
    if (args.length != 4) {
      System.err.println("Incorrect number of parameters in '" + raw + "'");
      return null;
    }

    int weight;
    try {
      weight = Integer.parseInt(args[0]);
    } catch (NumberFormatException e) {
      System.err.println("Error on parsing weight " + args[0] + " in '" + raw + "'");
      return null;
    }
    String name = args[1];
    Instant instant;
    try {
      instant = LocalDate.parse(args[2]).atStartOfDay(ZoneId.of("Europe/Moscow")).toInstant();
    } catch (Exception e) {
      System.err.println("Error on parsing date " + args[2] + " in '" + raw + "'");
      return null;
    }
    int kkal;
    try {
      kkal = Integer.parseInt(args[3]);
    } catch (NumberFormatException e) {
      System.err.println("Error on parsing kkal " + args[3] + " in '" + raw + "'");
      return null;
    }

    return new Sweetness(weight, name, instant, kkal);
  }

  @Override
  public Map<String, Sweetness> loadSweetness() {
    if (!this.file.exists()) return Collections.emptyMap();

    Map<String, Sweetness> sweetnessMap = new HashMap<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(this.file))) {
      String line;
      while ((line = reader.readLine()) != null) {
        Sweetness sweetness = this.parseSweetness(line);
        if (sweetness == null) continue;
        sweetnessMap.put(sweetness.getName(), sweetness);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return sweetnessMap;
  }
}
