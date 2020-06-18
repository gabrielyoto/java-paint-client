import java.util.Vector;

public class PedidoDeSalvar extends Comunicado
{
  private final Vector<Figura> figuras;

  public PedidoDeSalvar(Vector<Figura> figuras)
  {
    this.figuras = figuras;
  }

  public Vector<Figura> getFiguras()
  {
    return this.figuras;
  }

  public String toString ()
  {
    StringBuilder string = new StringBuilder();
    for (Figura figura : figuras)
    {
      string.append(figura.toString());
    }
    return string.toString();
  }
}
