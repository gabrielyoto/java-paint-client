import java.awt.*;
import java.util.StringTokenizer;

public class Texto extends Figura {
  protected String texto;
  protected int x;
  protected int y;
  protected Font fonte;
  protected String fontName;
  protected int fontStyle;
  protected int fontSize;

  public Texto (String s) throws Exception {
    StringTokenizer quebrador = new StringTokenizer(s,":");

    String tipo = quebrador.nextToken();

    if (!tipo.equals("t") && !tipo.equals("T"))
    {
      throw new Exception("String de construção de figura inválida");
    }

    String texto = quebrador.nextToken();
    int x = Integer.parseInt(quebrador.nextToken());
    int y = Integer.parseInt(quebrador.nextToken());
    String fontName = quebrador.nextToken();
    int fontStyle = Integer.parseInt(quebrador.nextToken());
    int fontSize = Integer.parseInt(quebrador.nextToken());

    Color corContorno = new Color (
      Integer.parseInt(quebrador.nextToken()),  // R
      Integer.parseInt(quebrador.nextToken()),  // G
      Integer.parseInt(quebrador.nextToken()), // B
      Integer.parseInt(quebrador.nextToken()) //A
    );

    try
    {
      this.texto = texto;
      this.x = x;
      this.y = y;
      this.corContorno = corContorno;
      this.fontName = fontName;
      this.fontStyle = fontStyle;
      this.fontSize = fontSize;
    }
    catch (Exception e)
    {
      System.out.println(e.getMessage());
    }
  }

  public Texto (String texto, int x, int y) {
    this(texto, x, y, "TimesRoman", Font.PLAIN, 12, Color.BLACK);
  }

  public Texto (String texto, int x, int y, String fontName, int fontStyle, int fontSize) {
    this(texto, x, y, fontName, fontStyle, fontSize, Color.BLACK);
  }

  public Texto (String texto, int x, int y, String fontName, int fontStyle, int fontSize, Color corContorno) {
    super(corContorno, Color.BLACK);
    this.texto = texto;
    this.x = x;
    this.y = y;
    this.fontName = fontName;
    this.fontStyle = fontStyle;
    this.fontSize = fontSize;
  }

  public void torneSeVisivel(Graphics g) {
    g.setColor(corContorno);
    g.setFont(new Font(fontName, fontStyle, fontSize));
    g.drawString(texto, x, y);
  }

  public String toString() {
    return "t:" +
    this.texto +
        ":" +
        x +
        ":" +
        y +
        ":" +
        fontName +
        ":" +
        fontStyle +
        ":" +
        fontSize +
        ":" +
        this.corContorno.getRed() +
        ":" +
        this.corContorno.getGreen() +
        ":" +
        this.corContorno.getBlue() +
        ":" +
        this.corContorno.getAlpha();
  }
}
