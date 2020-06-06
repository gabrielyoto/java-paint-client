import java.awt.*;
import java.util.*;

public class Ponto extends Figura
{
  protected int x,  y;

  public Ponto (int x, int y) throws Exception
  {
    this (x, y, Color.BLACK);
  }

  public Ponto (int x, int y, Color cor) throws Exception
  {
    super (cor);

    if (x < 0 || y < 0)
    {
      throw new Exception("A posição dos pontos devem ser positivas");
    }

    this.x = x;
    this.y = y;
  }

  public Ponto (String s) throws Exception
  {
    StringTokenizer quebrador = new StringTokenizer(s,":");

    if (!quebrador.nextToken().equals("p"))
    {
      throw new Exception("String de construção de figura inválida");
    }

    this.x = Integer.parseInt(quebrador.nextToken());
    this.y = Integer.parseInt(quebrador.nextToken());

    this.corContorno = new Color (
      Integer.parseInt(quebrador.nextToken()),  // R
      Integer.parseInt(quebrador.nextToken()),  // G
      Integer.parseInt(quebrador.nextToken()), // B
      Integer.parseInt(quebrador.nextToken()) //A
    );
  }

  public void setX (int x) throws Exception
  {
    if (x < 0)
    {
      throw new Exception("A posição do ponto deve ser positiva");
    }

    this.x = x;
  }

  public void setY (int y) throws Exception
  {
    if (y < 0)
    {
      throw new Exception("A posição do ponto deve ser positiva");
    }

    this.y = y;
  }

  public int getX ()
  {
    return this.x;
  }

  public int getY ()
  {
    return this.y;
  }

  public void torneSeVisivel (Graphics g)
  {
    g.setColor (this.corContorno);
    g.drawLine (this.x,this.y,this.x,this.y);
  }

  public String toString()
  {
    return "p:" +
            this.x +
            ":" +
            this.y +
            ":" +
            this.getCorContorno().getRed() +
            ":" +
            this.getCorContorno().getGreen() +
            ":" +
            this.getCorContorno().getBlue() +
            ":" +
            this.getCorContorno().getAlpha();
  }

  public int[] getDiff(Ponto p2)
  {
    int xDiff = Math.abs(this.getX() - p2.getX());
    int yDiff = Math.abs(this.getY() - p2.getY());
    return new int[] {xDiff, yDiff};
  }

  public int getMenorX(Ponto p2)
  {
    return Math.min(this.getX(), p2.getX());
  }

  public  int getMenorY(Ponto p2)
  {
    return Math.min(this.getY(), p2.getY());
  }
}
