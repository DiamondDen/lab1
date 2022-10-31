package net.diamondden.sweetness;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class Sweetness {
  private int weight;
  private String name;
  private Instant beforeDate;
  private int kKalIn100;
}
