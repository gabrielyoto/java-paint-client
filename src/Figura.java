import java.awt.*;
import java.util.Scanner;

public abstract class Figura
{
  protected Color corContorno, corPreenchimento;

  protected Figura()
  {
    this (Color.BLACK, new Color(0, 0, 0, 0));
  }

  protected Figura(Color corContorno)
  {
    this.corContorno = corContorno;
    this.corPreenchimento = new Color(0, 0, 0, 0);
  }

  protected Figura(Color corContorno, Color corPreenchimento)
  {
    this.corContorno = corContorno;
    this.corPreenchimento = corPreenchimento;
  }

  public void setCorContorno(Color cor)
  {
    this.corContorno = cor;
  }

  public void setCorPreenchimento(Color cor)
  {
    this.corPreenchimento = cor;
  }

  public Color getCorContorno()
  {
    return this.corContorno;
  }

  public Color getCorPreenchimento()
  {
    return this.corPreenchimento;
  }

  //public abstract boolean equals(Object obj);
  //public abstract int hashCode();
  //public abstract Object clone();
  public abstract String toString();
  public abstract void torneSeVisivel(Graphics g);
}
