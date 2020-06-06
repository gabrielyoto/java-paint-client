import java.awt.*;
import java.util.*;

public class Circulo extends Figura
{
  protected Ponto centro;
  protected int raio;

  public Circulo (int x, int y, int raio) throws Exception
  {
    this (x, y, raio, Color.BLACK, new Color(0, 0, 0, 0));
  }

  public Circulo (int x, int y, int raio, Color corContono) throws Exception
  {
    this(x, y, raio, corContono, new Color(0, 0, 0, 0));
  }

  public Circulo (int x, int y, int raio, Color corContorno, Color corPreenchimento) throws Exception
  {
    super(corContorno, corPreenchimento);

    if (raio <= 0)
    {
      throw new Exception("Raio deve ser maior que zero");
    }

    this.centro = new Ponto (x,y);
    this.raio = raio;
  }

  public Circulo (String s) throws Exception  // "C:x:y:raio:R:G:B:A:R:G:B:a"
  {
    StringTokenizer quebrador = new StringTokenizer(s, ":");

    String tipo = quebrador.nextToken();

    if (!tipo.equals("C") && !tipo.equals("c"))
    {
      throw new Exception("String de construção de figura inválida");
    }

    int   x  = Integer.parseInt(quebrador.nextToken());
    int   y  = Integer.parseInt(quebrador.nextToken());

    int   raio  = Integer.parseInt(quebrador.nextToken());

    if (raio <= 0)
    {
      throw new Exception("A medida do raio deve ser positiva");
    }

    Color corContorno = new Color (
      Integer.parseInt(quebrador.nextToken()), // R
      Integer.parseInt(quebrador.nextToken()), // G
      Integer.parseInt(quebrador.nextToken()), // B
      Integer.parseInt(quebrador.nextToken()) //A
    );

    Color corPreenchimento = new Color (
      Integer.parseInt(quebrador.nextToken()), // R
      Integer.parseInt(quebrador.nextToken()), // G
      Integer.parseInt(quebrador.nextToken()), // B
      Integer.parseInt(quebrador.nextToken()) //A
    );

    this.centro  = new Ponto (x,y);
    this.raio = raio;
    this.corContorno = corContorno;
    this.corPreenchimento = corPreenchimento;
  }

  public void setCentro (int x, int y)
  {
    try
    {
      this.centro = new Ponto (x,y,this.getCorContorno());
    }
    catch(Exception ex)
    {
      System.out.println(ex.getMessage());
    }
  }

  public Ponto getCentro()
  {
    return this.centro;
  }

  public void setRaio(int raio) throws Exception
  {

    if (raio <= 0)
    {
      throw new Exception("A medida do raio deve ser maior que zero");
    }

    this.raio = raio;
  }

  public int getRaio()
  {
    return this.raio;
  }

  public void torneSeVisivel (Graphics g)
  {
    int x = this.centro.getX() - this.raio;
    int y = this.centro.getY() - this.raio;
    g.setColor(this.corPreenchimento);
    g.fillOval(x, y, (2 * this.raio), (2 * this.raio));
    g.setColor(this.corContorno);
    g.drawOval(x, y, (2 * this.raio), (2 * this.raio));
  }

  public String toString()
  {
    return "c:" +
            this.centro.getX() +
            ":" +
            this.centro.getY() +
            ":" +
            this.raio +
            ":" +
            this.getCorContorno().getRed() +
            ":" +
            this.getCorContorno().getGreen() +
            ":" +
            this.getCorContorno().getBlue() +
            ":" +
            this.getCorContorno().getAlpha() +
            ":" +
            this.getCorPreenchimento().getRed() +
            ":" +
            this.getCorPreenchimento().getGreen() +
            ":" +
            this.getCorPreenchimento().getBlue() +
            ":" +
            this.getCorPreenchimento().getAlpha();
  }
}
