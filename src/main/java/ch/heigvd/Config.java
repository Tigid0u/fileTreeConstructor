package ch.heigvd;

public class Config {
  public String name;

  Config(String str) {
    this.name = str;
  }

  public static Config getConfigFromFile(String a) {
    System.out.println("Config !");
    return new Config(a);
  }
}
