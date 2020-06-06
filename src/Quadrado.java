import java.awt.*;
import java.util.StringTokenizer;

public class Quadrado extends Figura
{
    protected Ponto inicio; //inicio do quadrado, extremidade superior
    protected Ponto fim; //ponto base do quadrado, extremidade inferior
    protected int lado;

    public Quadrado(Ponto inicio, Ponto fim) throws Exception
    {
        this(inicio, fim, Color.BLACK, new Color(0, 0, 0, 0));
    }

    public Quadrado(Ponto inicio, Ponto fim, Color corContorno) throws Exception
    {
        this(inicio, fim, Color.BLACK, new Color(0, 0, 0, 0));
    }

    public Quadrado(Ponto inicio, Ponto fim, Color corContorno, Color corPreenchimento) throws Exception
    {
        super(corContorno, corPreenchimento);

        this.fim = fim;
        this.inicio = inicio;
        this.corContorno = corContorno;
        this.corPreenchimento = corPreenchimento;
        this.lado = inicio.getDiff(fim)[1];
    }

    public Quadrado(String s) throws Exception //"Q:inicio.X:inicio.Y:fim.X:fim.Y:R:G:B"
    {
        StringTokenizer quebrador = new StringTokenizer(s, ":");

        String tipo = quebrador.nextToken();

        if (!tipo.equals("Q") && !tipo.equals("q"))
        {
            throw new Exception("String de construção de figura inválida");
        }

        int iniciox = Integer.parseInt(quebrador.nextToken());
        int inicioy = Integer.parseInt(quebrador.nextToken());
        int fimx = Integer.parseInt(quebrador.nextToken());
        int fimy = Integer.parseInt(quebrador.nextToken());

        Color corContorno = new Color(
                Integer.parseInt(quebrador.nextToken()),  // R
                Integer.parseInt(quebrador.nextToken()),  // G
                Integer.parseInt(quebrador.nextToken()), // B
                Integer.parseInt(quebrador.nextToken()) //A
        );
        Color corPreenchimento = new Color(
                Integer.parseInt(quebrador.nextToken()),  // R
                Integer.parseInt(quebrador.nextToken()),  // G
                Integer.parseInt(quebrador.nextToken()), // B
                Integer.parseInt(quebrador.nextToken()) //A
        );

        try
        {
            this.inicio = new Ponto(iniciox, inicioy);
            this.fim = new Ponto(fimx, fimy);
            this.corContorno = corContorno;
            this.corPreenchimento = corPreenchimento;
            this.lado = inicio.getDiff(fim)[1];
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
        int inicioX = this.inicio.getMenorX(this.fim);
        int inicioY = this.inicio.getMenorY(this.fim);
        g.setColor(corPreenchimento);
        g.fillRect(inicioX, inicioY, lado, lado);
        g.setColor(corContorno);
        g.drawRect(inicioX, inicioY, lado, lado);

    }

    public String toString()
    {
        return "q:" +
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
