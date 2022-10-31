package net.diamondden.sweetness.provider;

import lombok.RequiredArgsConstructor;
import lombok.val;
import net.diamondden.sweetness.Sweetness;

import java.util.*;

@RequiredArgsConstructor
public class SortedProvider implements SweetsProvider {

  private final SweetsProvider provider;

  @Override
  public Map<String, Sweetness> loadSweetness() {
    val map = provider.loadSweetness();
    Map<String, Sweetness> result = new LinkedHashMap<>();
    List<Map.Entry<String, Sweetness>> list = new ArrayList<>(map.entrySet());
    list.sort(Map.Entry.comparingByKey());
    for (Map.Entry<String, Sweetness> stringSweetnessEntry : list) {
      result.put(stringSweetnessEntry.getKey(), stringSweetnessEntry.getValue());
    }
    return result;
  }
}
