import java.awt.*;
import java.util.*;
 
public class Linha extends Figura
{
    protected Ponto p1, p2;
    
    public Linha (int x1, int y1, int x2, int y2)
    {
        this (x1, y1, x2, y2, Color.BLACK);
    }
	
    public Linha (int x1, int y1, int x2, int y2, Color cor)
    {
        super(cor);
		
		try
		{
			this.p1 = new Ponto (x1,y1,cor);
			this.p2 = new Ponto (x2,y2,cor);
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
    }

    public Linha (String s) throws Exception {
        StringTokenizer quebrador = new StringTokenizer(s,":");

        String tipo = quebrador.nextToken();
        if (!tipo.equals("l") && !tipo.equals("L")) {
            throw new Exception("String inválida!");
        }

        int   x1  = Integer.parseInt(quebrador.nextToken());
        int   y1  = Integer.parseInt(quebrador.nextToken());

        int   x2  = Integer.parseInt(quebrador.nextToken());
        int   y2  = Integer.parseInt(quebrador.nextToken());

        Color cor = new Color (
            Integer.parseInt(quebrador.nextToken()), // R
           Integer.parseInt(quebrador.nextToken()), // G
           Integer.parseInt(quebrador.nextToken()), // B
            Integer.parseInt(quebrador.nextToken()) //A
        );
		
		try
		{
			this.p1 = new Ponto (x1,y1,cor);
			this.p2 = new Ponto (x2,y2,cor);
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
        this.corContorno = cor;
    }

    public void setP1 (int x, int y)
    {
		try
		{
			this.p1 = new Ponto (x,y,this.getCorContorno());
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
    }

    public void setP2 (int x, int y)
    {
		try
		{
			this.p2 = new Ponto (x,y,this.getCorContorno());
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
    }

    public Ponto getP1 ()
    {
        return this.p1;
    }

    public Ponto getP2 ()
    {
        return this.p2;
    }

    public void torneSeVisivel (Graphics g)
    {
        g.setColor(this.corContorno);
        g.drawLine(this.p1.getX(), this.p1.getY(), this.p2.getX(), this.p2.getY());
    }

    public void esconder (Graphics g)
    {
    }

    public String toString()
    {
        return "l:" +
        this.p1.getX() +
        ":" +
        this.p1.getY() +
        ":" +
        this.p2.getX() +
        ":" +
        this.p2.getY() +
        ":" +
        this.getCorContorno().getRed() +
        ":" +
        this.getCorContorno().getGreen() +
        ":" +
        this.getCorContorno().getBlue() +
        ":" +
        this.getCorContorno().getAlpha();
    }
}
