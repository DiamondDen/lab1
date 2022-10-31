package net.diamondden.sweetness.provider;

import net.diamondden.sweetness.Sweetness;

import java.util.Map;

public interface SweetsProvider {
  Map<String, Sweetness> loadSweetness();
}
