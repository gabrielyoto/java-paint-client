import java.util.ArrayList;

public class ListaDesenhos extends Comunicado {
  ArrayList<Desenho> desenhos;
  public ListaDesenhos(ArrayList<Desenho> desenhos)
  {
    this.desenhos = desenhos;
  }

  public ArrayList<Desenho> getDesenhos() {
    return desenhos;
  }
}
