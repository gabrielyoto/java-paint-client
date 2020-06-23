import java.util.ArrayList;

public class ListaDesenhos extends Comunicado {
  ArrayList<String> desenhos;
  public ListaDesenhos(ArrayList<String> desenhos)
  {
    this.desenhos = desenhos;
  }

  public ArrayList<String> getDesenhos() {
    return desenhos;
  }
}
