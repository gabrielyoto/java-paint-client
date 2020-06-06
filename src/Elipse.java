import java.awt.*;
import java.util.StringTokenizer;

public class Elipse extends Figura
{
  protected Ponto inicio; //inicio = ponto do canto superior esquerdo
  protected Ponto fim; // fim = ponto do canto inferior direito
  protected int largura;
  protected int altura;

  public Elipse(Ponto inicio, Ponto fim) throws Exception
  {
    this (inicio, fim, Color.BLACK,  new Color(0, 0, 0, 0));
  }

  public Elipse(Ponto inicio, Ponto fim, Color corContorno) throws Exception
  {
    this (inicio, fim, corContorno, new Color( 0,0,0,0));
  }

  public Elipse (Ponto inicio , Ponto fim, Color corContorno, Color corPreenchimento) throws Exception
  {
    super(corContorno, corPreenchimento);

    this.fim = fim;
    this.inicio = inicio;
    this.altura = inicio.getDiff(fim)[1];
    this.largura = inicio.getDiff(fim)[0];
    this.corContorno = corContorno;
    this.corPreenchimento = corPreenchimento;
  }

  public Elipse (String s) throws Exception // "E:Inicio.x:Inicio.y:Fim.x:Fim.Y::R:G:B"
  {
    StringTokenizer quebrador = new StringTokenizer(s,":");

    String tipo = quebrador.nextToken();

    if (!tipo.equals("E") && !tipo.equals("e"))
    {
      throw new Exception("String de construção de figura inválida");
    }

    int	inicioX = Integer.parseInt(quebrador.nextToken());
    int inicioY = Integer.parseInt(quebrador.nextToken());
    int fimX = Integer.parseInt(quebrador.nextToken());
    int fimY = Integer.parseInt(quebrador.nextToken());

    Color corContorno = new Color (
      Integer.parseInt(quebrador.nextToken()),  // R
      Integer.parseInt(quebrador.nextToken()),  // G
      Integer.parseInt(quebrador.nextToken()), // B
      Integer.parseInt(quebrador.nextToken()) //A
    );
    Color corPreenchimento = new Color (
      Integer.parseInt(quebrador.nextToken()),  // R
      Integer.parseInt(quebrador.nextToken()),  // G
      Integer.parseInt(quebrador.nextToken()), // B
      Integer.parseInt(quebrador.nextToken()) //A
    );

    try
    {
      this.inicio = new Ponto (inicioX, inicioY);
      this.fim = new Ponto (fimX, fimY);
      this.altura = inicio.getDiff(fim)[1];
      this.largura = inicio.getDiff(fim)[0];
      this.corContorno = corContorno;
      this.corPreenchimento = corPreenchimento;
    }
    catch (Exception e)
    {
      System.out.println(e.getMessage());
    }
  }

  public void setInicio(int x, int y)
  {
    try
    {
      this.inicio = new Ponto (x,y,this.getCorContorno());
    }
    catch(Exception ex)
    {
      System.out.println(ex.getMessage());
    }
  }
  public void setFim(int x, int y)
  {
    try
    {
      this.fim = new Ponto (x,y,this.getCorContorno());
    }
    catch(Exception ex)
    {
      System.out.println(ex.getMessage());
    }
  }

  public Ponto getInicio()
  {
    return this.inicio;
  }

  public Ponto getFim()
  {
    return this.fim;
  }

  public void torneSeVisivel(Graphics g)
  {
    int inicioX = inicio.getMenorX(fim);
    int inicioY = inicio.getMenorY(fim);
    g.setColor(corPreenchimento);
    g.fillOval(inicioX, inicioY, largura, altura);
    g.setColor(corContorno);
    g.drawOval(inicioX, inicioY, largura, altura);
  }

  public String toString()
  {
    return "e:" +
            this.getInicio().getX() +
            ":" +
            this.getInicio().getY() +
            ":" +
            this.getFim().getX() +
            ":" +
            this.getFim().getY() +
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

