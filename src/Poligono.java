import java.awt.*;
import java.util.StringTokenizer;

public class Poligono extends Figura {
  protected int[] pontosX;
  protected int[] pontosY;
  protected int nPontos;
  protected Color corContorno;
  protected Color corPreenchimento;

  public Poligono (String s) throws Exception {

    StringTokenizer quebrador = new StringTokenizer(s,":");

    String tipo = quebrador.nextToken();

    if (!tipo.equals("Po") && !tipo.equals("po"))
    {
      throw new Exception("String de construção de figura inválida");
    }

    int	nPontos = Integer.parseInt(quebrador.nextToken());
    int[] pontosX = new int[nPontos];
    int[] pontosY = new int[nPontos];

    for (int i = 0; i < nPontos; i++) {
      pontosX[i] = Integer.parseInt(quebrador.nextToken());
      pontosY[i] = Integer.parseInt(quebrador.nextToken());
    }

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
      this.pontosX = pontosX;
      this.pontosY = pontosY;
      this.nPontos = nPontos;
      this.corContorno = corContorno;
      this.corPreenchimento = corPreenchimento;
    }
    catch (Exception e)
    {
      System.out.println(e.getMessage());
    }
  }

  public Poligono (int[] pontosX, int[] pontosY, int nPontos) {
    this(pontosX, pontosY, nPontos, Color.BLACK, new Color(0, 0, 0, 0));
  }

  public Poligono (int[] pontosX, int[] pontosY, int nPontos, Color corContorno) {
    this(pontosX, pontosY, nPontos, corContorno, new Color(0, 0, 0, 0));
  }

  public Poligono (int[] pontosX, int[] pontosY, int nPontos, Color corContorno, Color corPreenchimento) {
    super(corContorno, corPreenchimento);

    this.pontosX = pontosX;
    this.pontosY = pontosY;
    this.nPontos = nPontos;
    this.corContorno = corContorno;
    this.corPreenchimento = corPreenchimento;
  }

  public void torneSeVisivel(Graphics g) {
    g.setColor(corContorno);
    g.drawPolygon(pontosX, pontosY, nPontos);
    g.setColor(corPreenchimento);
    g.fillPolygon(pontosX, pontosY, nPontos);
  }

  public String toString() {
    StringBuilder string = new StringBuilder("po:" + this.nPontos + ":");
    for (int i = 0; i < nPontos; i++) {
      string.append(this.pontosX[i]).append(":").append(this.pontosY[i]).append(":");
    }
    string.append(this.getCorContorno().getRed()).append(":").append(this.getCorContorno().getGreen()).append(":").
      append(this.getCorContorno().getBlue()).append(":").append(this.getCorContorno().getAlpha()).append(":").
      append(this.getCorPreenchimento().getRed()).append(":").append(this.getCorPreenchimento().getGreen()).
      append(":").append(this.getCorPreenchimento().getBlue()).append(":").append(this.getCorPreenchimento().getAlpha());
    return string.toString();
  }
}
