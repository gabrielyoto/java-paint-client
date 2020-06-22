import say.swing.JFontChooser;
import javax.swing.JFileChooser;
import java.io.File;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.net.ConnectException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.awt.Font;

public class Janela extends JFrame {
  protected JButton btnPonto = new JButton("Ponto"),
    btnLinha = new JButton("Linha"),
    btnCirculo = new JButton("Circulo"),
    btnElipse = new JButton("Elipse"),
    btnQuadrado = new JButton("Quadrado"),
    btnRetangulo = new JButton("Retangulo"),
    btnPoligono = new JButton("Polígono"),
    btnTexto = new JButton("Texto"),
    btnCorContorno = new JButton("Contorno"),
    btnCorPreenchimento = new JButton("Preenchimento"),
    btnAbrir = new JButton("Abrir"),
    btnSalvar = new JButton("Salvar"),
    btnApagar = new JButton("Apagar"),
    btnSair = new JButton("Sair"),
    btnConectar = new JButton("Conectar"),
    btnDesconectar = new JButton("Desconectar"),
    btnSalvarRemoto = new JButton("Salvar no servidor"),
    btnSelecionar = new JButton("Selecionar do servidor");

  protected MeuJPanel pnlDesenho = new MeuJPanel();

  protected JLabel statusBar1 = new JLabel("Mensagem:");
  protected JLabel statusBarCon = new JLabel("Conexão: desconectado");
  protected JLabel statusBar2 = new JLabel("Coordenada:");

  protected boolean esperaPonto, esperaInicioReta, esperaFimReta, esperaCentro, esperaRaio,
          esperaInicioElipse, esperaFimElipse, esperaInicioQuadrado, esperaFimQuadrado, esperaInicioRetangulo,
          esperaFimRetangulo, esperaTexto, esperaInicioPoligono, esperaFimPoligono;

  protected Color corContorno = Color.BLACK, corPreenchimento = new Color(0, 0, 0, 0);

  protected Ponto p1;
  protected Ponto inicioElipse;
  protected Ponto fimElipse;
  protected Ponto inicioQuadrado;
  protected Ponto fimQuadrado;
  protected Ponto inicioRetangulo;
  protected Ponto fimRetangulo;

  protected int linhasTemporarias = 0, linhasTotal = 0;
  protected String texto = "";
  protected Vector<Integer> linhasX = new Vector<>(), linhasY = new Vector<>();
  protected Font fonte;

  protected Vector<Figura> figuras = new Vector<>();
  protected Vector<Figura> linhasProv = new Vector<>();

  protected Cliente cliente = null;
  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  protected final String hoje = dtf.format(LocalDateTime.now());
  protected Desenho desenho = new Desenho("", hoje, hoje);

  public Janela() {
    super("Editor Gráfico");
    try {
      Image btnPontoImg = ImageIO.read(this.getClass().getResource("resources/ponto.jpg"));
      btnPonto.setIcon(new ImageIcon(btnPontoImg));
    } catch (IOException e) {
      JOptionPane.showMessageDialog(
        null,
        "Arquivo ponto.jpg não foi encontrado",
        "Arquivo de imagem ausente",
        JOptionPane.WARNING_MESSAGE
      );
    }

    try {
      Image btnLinhaImg = ImageIO.read(getClass().getResource("resources/linha.jpg"));
      btnLinha.setIcon(new ImageIcon(btnLinhaImg));
    } catch (IOException e) {
      JOptionPane.showMessageDialog(
        null,
        "Arquivo linha.jpg n�o foi encontrado",
        "Arquivo de imagem ausente",
        JOptionPane.WARNING_MESSAGE
      );
    }

    try {
      Image btnCirculoImg = ImageIO.read(getClass().getResource("resources/circulo.jpg"));
      btnCirculo.setIcon(new ImageIcon(btnCirculoImg));
    } catch (IOException e) {
      JOptionPane.showMessageDialog(
        null,
        "Arquivo circulo.jpg n�o foi encontrado",
        "Arquivo de imagem ausente",
        JOptionPane.WARNING_MESSAGE
      );
    }

    try {
      Image btnElipseImg = ImageIO.read(getClass().getResource("resources/elipse.jpg"));
      btnElipse.setIcon(new ImageIcon(btnElipseImg));
    } catch (IOException e) {
      JOptionPane.showMessageDialog(
        null,
        "Arquivo elipse.jpg n�o foi encontrado",
        "Arquivo de imagem ausente",
        JOptionPane.WARNING_MESSAGE
      );
    }

    try {
      Image btnQuadradoImg = ImageIO.read(getClass().getResource("resources/quadrado.jpg"));
      btnQuadrado.setIcon(new ImageIcon(btnQuadradoImg));
    } catch (IOException e) {
      JOptionPane.showMessageDialog(
        null,
        "Arquivo quadrado.jpg n�o foi encontrado",
        "Arquivo de imagem ausente",
        JOptionPane.WARNING_MESSAGE
      );
    }

    try {
      Image btnRetanguloImg = ImageIO.read(getClass().getResource("resources/retangulo.jpg"));
      btnRetangulo.setIcon(new ImageIcon(btnRetanguloImg));
    } catch (IOException e) {
      JOptionPane.showMessageDialog(
        null,
        "Arquivo retangulo.jpg n�o foi encontrado",
        "Arquivo de imagem ausente",
        JOptionPane.WARNING_MESSAGE
      );
    }

    try {
      Image btnPoligonoImg = ImageIO.read(getClass().getResource("resources/poligono.jpg"));
      btnPoligono.setIcon(new ImageIcon(btnPoligonoImg));
    } catch (IOException e) {
      JOptionPane.showMessageDialog(
        null,
        "Arquivo poligono.jpg n�o foi encontrado",
        "Arquivo de imagem ausente",
        JOptionPane.WARNING_MESSAGE
      );
    }

    try {
      Image btnTextoImg = ImageIO.read(getClass().getResource("resources/texto.jpg"));
      btnTexto.setIcon(new ImageIcon(btnTextoImg));
    } catch (IOException e) {
      JOptionPane.showMessageDialog(
        null,
        "Arquivo texto.jpg n�o foi encontrado",
        "Arquivo de imagem ausente",
        JOptionPane.WARNING_MESSAGE
      );
    }

    try {
      Image btnCorImg = ImageIO.read(getClass().getResource("resources/cores.jpg"));
      btnCorContorno.setIcon(new ImageIcon(btnCorImg));
      btnCorPreenchimento.setIcon(new ImageIcon(btnCorImg));
    } catch (IOException e) {
      JOptionPane.showMessageDialog(
        null,
        "Arquivo cores.jpg não foi encontrado",
        "Arquivo de imagem ausente",
        JOptionPane.WARNING_MESSAGE
      );
    }

    try {
      Image btnAbrirImg = ImageIO.read(getClass().getResource("resources/abrir.jpg"));
      btnAbrir.setIcon(new ImageIcon(btnAbrirImg));
    } catch (IOException e) {
      JOptionPane.showMessageDialog(
        null,
        "Arquivo abrir.jpg n�o foi encontrado",
        "Arquivo de imagem ausente",
        JOptionPane.WARNING_MESSAGE
      );
    }

    try {
      Image btnSalvarImg = ImageIO.read(getClass().getResource("resources/salvar.jpg"));
      btnSalvar.setIcon(new ImageIcon(btnSalvarImg));
    } catch (IOException e) {
      JOptionPane.showMessageDialog(
        null,
        "Arquivo salvar.jpg n�o foi encontrado",
        "Arquivo de imagem ausente",
        JOptionPane.WARNING_MESSAGE
      );
    }

    try {
      Image btnApagarImg = ImageIO.read(getClass().getResource("resources/apagar.jpg"));
      btnApagar.setIcon(new ImageIcon(btnApagarImg));
    } catch (IOException e) {
      JOptionPane.showMessageDialog(
        null,
        "Arquivo apagar.jpg n�o foi encontrado",
        "Arquivo de imagem ausente",
        JOptionPane.WARNING_MESSAGE
      );
    }

    try {
      Image btnSairImg = ImageIO.read(getClass().getResource("resources/sair.jpg"));
      btnSair.setIcon(new ImageIcon(btnSairImg));
    } catch (IOException e) {
      JOptionPane.showMessageDialog(
          null,
          "Arquivo sair.jpg n�o foi encontrado",
          "Arquivo de imagem ausente",
          JOptionPane.WARNING_MESSAGE
      );
    }

    try {
      Image btnConectarImg = ImageIO.read(getClass().getResource("resources/conectar.jpg"));
      btnConectar.setIcon(new ImageIcon(btnConectarImg));
    } catch (IOException e) {
      JOptionPane.showMessageDialog(
          null,
          "Arquivo conectar.jpg não foi encontrado",
          "Arquivo de imagem ausente",
          JOptionPane.WARNING_MESSAGE
      );
    }

    try {
      Image img = ImageIO.read(getClass().getResource("resources/desconectar.jpg"));
      btnDesconectar.setIcon(new ImageIcon(img));
    } catch (IOException e) {
      JOptionPane.showMessageDialog(
          null,
          "Arquivo desconectar.jpg não foi encontrado",
          "Arquivo de imagem ausente",
          JOptionPane.WARNING_MESSAGE
      );
    }

    try {
      Image img = ImageIO.read(getClass().getResource("resources/salvarRemoto.jpg"));
      btnSalvarRemoto.setIcon(new ImageIcon(img));
    } catch (IOException e) {
      JOptionPane.showMessageDialog(
          null,
          "Arquivo salvarRemoto.jpg não foi encontrado",
          "Arquivo de imagem ausente",
          JOptionPane.WARNING_MESSAGE
      );
    }

    try {
      Image img = ImageIO.read(getClass().getResource("resources/carregar.jpg"));
      btnSelecionar.setIcon(new ImageIcon(img));
    } catch (IOException e) {
      JOptionPane.showMessageDialog(
          null,
          "Arquivo carregar.jpg não foi encontrado",
          "Arquivo de imagem ausente",
          JOptionPane.WARNING_MESSAGE
      );
    }

    btnPonto.addActionListener(new DesenhoDePonto());
    btnLinha.addActionListener(new DesenhoDeReta());
    btnCirculo.addActionListener(new DesenhoDeCirculo());
    btnCorContorno.addActionListener(new EscolheCorContorno());
    btnCorPreenchimento.addActionListener(new EscolheCorPreenchimento());
    btnElipse.addActionListener(new DesenhoDeElipse());
    btnQuadrado.addActionListener(new DesenhoDeQuadrado());
    btnRetangulo.addActionListener(new DesenhoDeRetangulo());
    btnPoligono.addActionListener(new DesenhoDePoligono());
    btnTexto.addActionListener(new EscolheFonte());
    btnAbrir.addActionListener(new AbrirArquivo());
    btnSair.addActionListener(new EncerrarPrograma());
    btnSalvar.addActionListener(new SalvarArquivo());
    btnApagar.addActionListener(new Apagar());
    btnConectar.addActionListener(new Conectar());
    btnSalvarRemoto.addActionListener(new SalvarRemoto());
    btnDesconectar.addActionListener(new Desconectar());
    //btnSelecionar.addActionListener(new SelecionarRemoto());

    JPanel pnlBotoes = new JPanel();
    JPanel pnlBotoes2 = new JPanel();
    JPanel botoes = new JPanel();
    FlowLayout flwBotoes1 = new FlowLayout();
    FlowLayout flwBotoes2 = new FlowLayout();
    GridLayout grdBotoes = new GridLayout(2, 1);
    pnlBotoes.setLayout(flwBotoes1);
    pnlBotoes2.setLayout(flwBotoes2);
    JPanel pnlBotoesCliente = new JPanel();
    JPanel botoesCliente = new JPanel();
    FlowLayout flwBotoesCliente = new FlowLayout();
    GridLayout grdBotoesCliente = new GridLayout(2, 1);
    pnlBotoesCliente.setLayout(flwBotoesCliente);

    pnlBotoes.add(btnAbrir);
    pnlBotoes.add(btnSalvar);
    pnlBotoes.add(btnPonto);
    pnlBotoes.add(btnLinha);
    pnlBotoes.add(btnCirculo);
    pnlBotoes.add(btnElipse);
    pnlBotoes.add(btnQuadrado);
    pnlBotoes.add(btnRetangulo);
    pnlBotoes2.add(btnPoligono);
    pnlBotoes2.add(btnTexto);
    pnlBotoes2.add(btnCorContorno);
    pnlBotoes2.add(btnCorPreenchimento);
    pnlBotoes2.add(btnApagar);
    pnlBotoes2.add(btnSair);
    pnlBotoesCliente.add(btnConectar);
    pnlBotoesCliente.add(btnSalvarRemoto);
    pnlBotoesCliente.add(btnSelecionar);
    pnlBotoesCliente.add(btnDesconectar);
    botoes.setLayout(grdBotoes);
    botoes.add(pnlBotoes);
    botoes.add(pnlBotoes2);

    JPanel pnlStatus = new JPanel();
    JPanel pnlStatus2 = new JPanel();
    botoesCliente.setLayout(grdBotoesCliente);
    GridLayout grdStatus = new GridLayout(1, 2);
    pnlStatus2.setLayout(grdStatus);
    pnlStatus.setLayout(grdStatus);
    botoesCliente.add(pnlBotoesCliente);
    botoesCliente.add(pnlStatus);
    pnlStatus.add(statusBar1);
    pnlStatus2.add(statusBarCon);
    pnlStatus2.add(statusBar2);
    pnlStatus.add(pnlStatus2);

    Container cntForm = this.getContentPane();
    cntForm.setLayout(new BorderLayout());
    cntForm.add(botoes, BorderLayout.NORTH);
    cntForm.add(pnlDesenho, BorderLayout.CENTER);
    cntForm.add(botoesCliente, BorderLayout.SOUTH);

    this.addWindowListener(new FechamentoDeJanela());

    this.setSize(900, 600);
    this.setVisible(true);
  }

  protected class MeuJPanel extends JPanel implements MouseListener, MouseMotionListener {
    public MeuJPanel() {
      super();

      this.addMouseListener(this);
      this.addMouseMotionListener(this);
    }

    public void paint(Graphics g) {
      for (Figura figura : figuras) figura.torneSeVisivel(g);
    }

    public void mousePressed(MouseEvent e) {
      if (esperaPonto) {
        try {
          figuras.add(new Ponto(e.getX(), e.getY(), corContorno));
          desenho.addFigura(figuras.get(figuras.size() - 1).toString());
        } catch (Exception ex) {
          System.out.println(ex.getMessage());
        }
        figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
        esperaPonto = true;
      } else if (esperaInicioReta) {
        try {
          p1 = new Ponto(e.getX(), e.getY(), corContorno);
        } catch (Exception ex) {
          System.out.println(ex.getMessage());
        }
        esperaInicioReta = false;
        esperaFimReta = true;
        statusBar1.setText("Mensagem: clique o ponto final da reta");
      } else if (esperaFimReta) {
        esperaFimReta = false;
        esperaInicioReta = true;
        desenho.addFigura(figuras.get(figuras.size() - 1).toString());
        linhasTemporarias = 0;
        statusBar1.setText("Mensagem:");
      } else if (esperaCentro) {
        try {
          p1 = new Ponto(e.getX(), e.getY(), corContorno);
        } catch (Exception ex) {
          System.out.println(ex.getMessage());
        }
        esperaCentro = false;
        esperaRaio = true;
        statusBar1.setText("Mensagem: clique a extremidade do circulo");
      } else if (esperaRaio) {
        esperaRaio = false;
        linhasTemporarias = 0;
        desenho.addFigura(figuras.get(figuras.size() - 1).toString());
        statusBar1.setText("Mensagem: ");
        esperaCentro = true;
      } else if (esperaInicioElipse) {
        try {
          inicioElipse = new Ponto(e.getX(), e.getY(), corContorno);
        } catch (Exception ex) {
          System.out.println(ex.getMessage());
        }
        esperaInicioElipse = false;
        esperaFimElipse = true;
        statusBar1.setText("Mensagem: Clique no ponto inferior direito da elipse");
      } else if (esperaFimElipse) {
        statusBar1.setText("Mensagem: ");
        linhasTemporarias = 0;
        esperaFimElipse = false;
        esperaInicioElipse = true;
        desenho.addFigura(figuras.get(figuras.size() - 1).toString());
      } else if (esperaInicioQuadrado) {
        try {
          inicioQuadrado = new Ponto(e.getX(), e.getY(), corContorno);
        } catch (Exception ex) {
          System.out.println(ex.getMessage());
        }
        esperaInicioQuadrado = false;
        esperaFimQuadrado = true;
        statusBar1.setText("Mensagem: Clique no ponto inferior direito do Quadrado ");
      } else if (esperaFimQuadrado) {
        linhasTemporarias = 0;
        statusBar1.setText("Mensagem: ");
        esperaFimQuadrado = false;
        esperaInicioQuadrado = true;
        desenho.addFigura(figuras.get(figuras.size() - 1).toString());
      } else if (esperaInicioRetangulo) {
        try {
          inicioRetangulo = new Ponto(e.getX(), e.getY(), corContorno);
        } catch (Exception ex) {
          System.out.println(ex.getMessage());
        }
        esperaInicioRetangulo = false;
        esperaFimRetangulo = true;
        statusBar1.setText("Mensagem: Clique no ponto inferior direito do retângulo");
      } else if (esperaFimRetangulo) {
        linhasTemporarias = 0;
        esperaFimRetangulo = false;
        statusBar1.setText("Mensagem: ");
        desenho.addFigura(figuras.get(figuras.size() - 1).toString());
        esperaInicioRetangulo = true;
      } else if (esperaTexto) {
        try {
          texto = JOptionPane.showInputDialog(Janela.this, "Texto a ser digitado");
          Texto text = new Texto(
            texto, e.getX(), e.getY(), fonte.getFontName(), fonte.getStyle(), fonte.getSize(), corContorno
          );
          text.torneSeVisivel(pnlDesenho.getGraphics());
          figuras.add(text);
          desenho.addFigura(figuras.get(figuras.size() - 1).toString());
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      } else if (esperaInicioPoligono) {
        linhasTotal = 0;
        linhasTotal++;
        linhasX.add(e.getX());
        linhasY.add(e.getY());
        esperaFimPoligono = true;
        esperaInicioPoligono = false;
        statusBar1.setText("Mensagem: clique nos vértices para criar um polígono e para terminar clique com o botão direito");
      } else if (e.getButton() != MouseEvent.BUTTON3 && esperaFimPoligono) {
        linhasTotal++;
        linhasX.add(e.getX());
        linhasY.add(e.getY());
        int[] arrayX = new int[linhasX.size()];
        int[] arrayY = new int[linhasY.size()];
        for (int i = 0; i < linhasX.size(); i++) arrayX[i] = linhasX.get(i);
        for (int i = 0; i < linhasY.size(); i++) arrayY[i] = linhasY.get(i);
        pnlDesenho.getGraphics().setColor(corContorno);
        pnlDesenho.getGraphics().drawPolyline(arrayX, arrayY, linhasTotal);
        for (int i = 0; i + 1 < linhasX.size(); i++) {
          linhasProv.add(new Linha(linhasX.get(i), linhasY.get(i), linhasX.get(i + 1), linhasY.get(i + 1), corContorno));
        }
      } else if (e.getButton() == MouseEvent.BUTTON3 && esperaFimPoligono) {
        linhasProv.clear();
        esperaFimPoligono = false;
        linhasTemporarias = 0;
        esperaInicioPoligono = true;
        int[] arrayX = new int[linhasX.size()];
        int[] arrayY = new int[linhasY.size()];
        for (int i = 0; i < linhasX.size(); i++) arrayX[i] = linhasX.get(i);
        for (int i = 0; i < linhasY.size(); i++) arrayY[i] = linhasY.get(i);
        figuras.add(new Poligono(arrayX, arrayY, linhasTotal, corContorno, corPreenchimento));
        pnlDesenho.getGraphics().clearRect(0, 0, pnlDesenho.getWidth(), pnlDesenho.getHeight());
        figuras.remove(figuras.size() - 2);
        for (Figura figura : figuras) {
          figura.torneSeVisivel(pnlDesenho.getGraphics());
        }
        linhasX.clear();
        linhasY.clear();
        desenho.addFigura(figuras.get(figuras.size() - 1).toString());
        statusBar1.setText("Mensagem: ");
      }
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
      statusBar2.setText("Coordenada: " + e.getX() + "," + e.getY());
      if (esperaFimReta) {
        linhasTemporarias++;
        figuras.add(new Linha(p1.getX(), p1.getY(), e.getX(), e.getY(), corContorno));
        if (linhasTemporarias > 1) {
          pnlDesenho.getGraphics().clearRect(0, 0, pnlDesenho.getWidth(), pnlDesenho.getHeight());
          figuras.remove(figuras.size() - 2);
          linhasTemporarias--;
          for (Figura figura : figuras) {
            figura.torneSeVisivel(pnlDesenho.getGraphics());
          }
        }
      } else if (esperaRaio) {
        int altura = e.getY() - p1.getY();
        int largura = e.getX() - p1.getX();
        int raio = (int) Math.round(Math.sqrt((altura * altura) + (largura * largura)));
        linhasTemporarias++;
        try {
          figuras.add(new Circulo(p1.getX(), p1.getY(), raio, corContorno, corPreenchimento));
        } catch (Exception ex) {
          System.out.println(ex.getMessage());
        }
        if (linhasTemporarias > 1) {
          pnlDesenho.getGraphics().clearRect(0, 0, pnlDesenho.getWidth(), pnlDesenho.getHeight());
          figuras.remove(figuras.size() - 2);
          for (Figura figura : figuras) {
            figura.torneSeVisivel(pnlDesenho.getGraphics());
          }
        }
      } else if (esperaFimElipse) {
        linhasTemporarias++;
        try {
          fimElipse = new Ponto(e.getX(), e.getY(), corContorno);
          figuras.add(new Elipse(inicioElipse, fimElipse, corContorno, corPreenchimento));
        } catch (Exception ex) {
          System.out.println(ex.getMessage());
        }
        if (linhasTemporarias > 1) {
          pnlDesenho.getGraphics().clearRect(0, 0, pnlDesenho.getWidth(), pnlDesenho.getHeight());
          figuras.remove(figuras.size() - 2);
          for (Figura figura : figuras) {
            figura.torneSeVisivel(pnlDesenho.getGraphics());
          }
        }
      } else if (esperaFimQuadrado) {
        linhasTemporarias++;
        try {
          fimQuadrado = new Ponto(e.getX(), e.getY(), corContorno);
          figuras.add(new Quadrado(inicioQuadrado, fimQuadrado, corContorno, corPreenchimento));
        } catch (Exception ex) {
          System.out.println(ex.getMessage());
        }
        if (linhasTemporarias > 1) {
          pnlDesenho.getGraphics().clearRect(0, 0, pnlDesenho.getWidth(), pnlDesenho.getHeight());
          figuras.remove(figuras.size() - 2);
          for (Figura figura : figuras) {
            figura.torneSeVisivel(pnlDesenho.getGraphics());
          }
        }
      } else if (esperaFimRetangulo) {
        linhasTemporarias++;
        try {
          fimRetangulo = new Ponto(e.getX(), e.getY(), corContorno);
          figuras.add(new Retangulo(inicioRetangulo, fimRetangulo, corContorno, corPreenchimento));
        } catch (Exception ex) {
          System.out.println(ex.getMessage());
        }
        if (linhasTemporarias > 1) {
          pnlDesenho.getGraphics().clearRect(0, 0, pnlDesenho.getWidth(), pnlDesenho.getHeight());
          figuras.remove(figuras.size() - 2);
          for (Figura figura : figuras) {
            figura.torneSeVisivel(pnlDesenho.getGraphics());
          }
        }
      } else if (esperaFimPoligono) {
        Linha novaLinha = new Linha(
          linhasX.get(linhasX.size() - 1), linhasY.get(linhasY.size() - 1), e.getX(), e.getY(), corContorno
        );
        linhasTemporarias++;
        figuras.add(novaLinha);
        if (linhasTemporarias > 1) {
          pnlDesenho.getGraphics().clearRect(0, 0, pnlDesenho.getWidth(), pnlDesenho.getHeight());
          for (Figura linha : linhasProv) {
            linha.torneSeVisivel(pnlDesenho.getGraphics());
          }
          figuras.remove(figuras.size() - 2);
          linhasTemporarias--;
          for (Figura figura : figuras) {
            figura.torneSeVisivel(pnlDesenho.getGraphics());
          }
        }
      }
    }
  }

  protected class DesenhoDePonto implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      esperaPonto = true;
      esperaInicioReta = false;
      esperaFimReta = false;
      esperaCentro = false;
      esperaRaio = false;
      esperaInicioElipse = true;
      esperaFimElipse = false;
      esperaInicioQuadrado = false;
      esperaFimQuadrado = false;
      esperaInicioRetangulo = false;
      esperaFimRetangulo = false;
      esperaInicioPoligono = false;
      esperaFimPoligono = false;
      esperaTexto = false;

      statusBar1.setText("Mensagem: clique o local do ponto desejado");
    }
  }

  protected class DesenhoDeReta implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      esperaPonto = false;
      esperaInicioReta = true;
      esperaFimReta = false;
      esperaCentro = false;
      esperaRaio = false;
      esperaInicioElipse = true;
      esperaFimElipse = false;
      esperaInicioQuadrado = false;
      esperaFimQuadrado = false;
      esperaInicioRetangulo = false;
      esperaFimRetangulo = false;
      esperaInicioPoligono = false;
      esperaFimPoligono = false;
      esperaTexto = false;

      statusBar1.setText("Mensagem: clique o ponto inicial da reta");
    }
  }

  protected class DesenhoDeCirculo implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      esperaPonto = false;
      esperaInicioReta = false;
      esperaFimReta = false;
      esperaCentro = true;
      esperaRaio = false;
      esperaInicioElipse = false;
      esperaFimElipse = false;
      esperaInicioQuadrado = false;
      esperaFimQuadrado = false;
      esperaInicioRetangulo = false;
      esperaFimRetangulo = false;
      esperaInicioPoligono = false;
      esperaFimPoligono = false;
      esperaTexto = false;

      statusBar1.setText("Mensagem: clique o ponto central do círculo");
    }
  }

  protected class DesenhoDeElipse implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      esperaPonto = false;
      esperaInicioReta = false;
      esperaFimReta = false;
      esperaCentro = false;
      esperaRaio = false;
      esperaInicioElipse = true;
      esperaFimElipse = false;
      esperaInicioQuadrado = false;
      esperaFimQuadrado = false;
      esperaInicioRetangulo = false;
      esperaFimRetangulo = false;
      esperaInicioPoligono = false;
      esperaFimPoligono = false;
      esperaTexto = false;

      statusBar1.setText("Mensagem: clique no canto superior esquerdo da elipse");
    }
  }

  protected class DesenhoDeQuadrado implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      esperaPonto = false;
      esperaInicioReta = false;
      esperaFimReta = false;
      esperaCentro = false;
      esperaRaio = false;
      esperaInicioElipse = false;
      esperaFimElipse = false;
      esperaInicioQuadrado = true;
      esperaFimQuadrado = false;
      esperaInicioRetangulo = false;
      esperaFimRetangulo = false;
      esperaInicioPoligono = false;
      esperaFimPoligono = false;
      esperaTexto = false;

      statusBar1.setText("Mensagem: clique no canto superior esquerdo do quadrado");
    }
  }

  protected class DesenhoDeRetangulo implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      esperaPonto = false;
      esperaInicioReta = false;
      esperaFimReta = false;
      esperaCentro = false;
      esperaRaio = false;
      esperaInicioElipse = false;
      esperaFimElipse = false;
      esperaInicioQuadrado = false;
      esperaFimQuadrado = false;
      esperaInicioRetangulo = true;
      esperaFimRetangulo = false;
      esperaInicioPoligono = false;
      esperaFimPoligono = false;
      esperaTexto = false;

      statusBar1.setText("Mensagem: clique no canto superior esquerdo do retangulo");
    }
  }

  protected class DesenhoDePoligono implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      esperaPonto = false;
      esperaInicioReta = false;
      esperaFimReta = false;
      esperaCentro = false;
      esperaRaio = false;
      esperaInicioElipse = false;
      esperaFimElipse = false;
      esperaInicioQuadrado = false;
      esperaFimQuadrado = false;
      esperaInicioRetangulo = false;
      esperaFimRetangulo = false;
      esperaInicioPoligono = true;
      esperaFimPoligono = false;
      esperaTexto = false;

      statusBar1.setText("Mensagem: clique no ponto inicial do polígono");
    }
  }

  protected class EscolheCorContorno implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      Color novaCorContorno = JColorChooser.showDialog(
              null,
              "Selecione a cor para o contorno",
              Color.BLACK
      );

      if (novaCorContorno != null) {
        corContorno = novaCorContorno;
      }
    }
  }

  protected class EscolheFonte implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      JFontChooser fontChooser = new JFontChooser();
      int result = fontChooser.showDialog(Janela.this);
      if (result == JFontChooser.OK_OPTION) {
        fonte = fontChooser.getSelectedFont();
        esperaPonto = false;
        esperaInicioReta = false;
        esperaFimReta = false;
        esperaCentro = false;
        esperaRaio = false;
        esperaInicioElipse = false;
        esperaFimElipse = false;
        esperaInicioQuadrado = false;
        esperaFimQuadrado = false;
        esperaInicioRetangulo = false;
        esperaFimRetangulo = false;
        esperaInicioPoligono = false;
        esperaFimPoligono = false;
        esperaTexto = true;
      }
    }
  }

  protected class EscolheCorPreenchimento implements ActionListener {
    public void actionPerformed(ActionEvent e)
    {
      Color novaCorPreenchimento = JColorChooser.showDialog(
              null,
              "Selecione a cor para o contorno",
              Color.BLACK
      );

      if (novaCorPreenchimento != null) {
        corPreenchimento = novaCorPreenchimento;
      }
    }
  }

  protected class AbrirArquivo implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      JFileChooser fileChooser = new JFileChooser();
      FileNameExtensionFilter filtro = new FileNameExtensionFilter("Arquivos Java-Paint", "javapaint");
      fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
      fileChooser.setFileFilter(filtro);
      int result = fileChooser.showOpenDialog(Janela.this);
      if (result == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        try {
          Scanner leitor = new Scanner(selectedFile);
          figuras.clear();
          pnlDesenho.getGraphics().clearRect(0, 0, pnlDesenho.getWidth(), pnlDesenho.getHeight());
          while (leitor.hasNextLine()) {
            String figura = leitor.nextLine();
            StringTokenizer quebrador = new StringTokenizer(figura,":");
            switch (quebrador.nextToken()) {
              case "p":
                figuras.add(new Ponto(figura));
                figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
                break;
              case "c":
                figuras.add(new Circulo(figura));
                figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
                break;
              case "l":
                figuras.add(new Linha(figura));
                figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
                break;
              case "e":
                figuras.add(new Elipse(figura));
                figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
                break;
              case "q":
                figuras.add(new Quadrado(figura));
                figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
                break;
              case "r":
                figuras.add(new Retangulo(figura));
                figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
                break;
              case "t":
                figuras.add(new Texto(figura));
                figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
                break;
              case "po":
                figuras.add(new Poligono(figura));
                figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
                break;
            }
          }
          leitor.close();
        } catch (FileNotFoundException ex) {
          JOptionPane.showMessageDialog(Janela.this, "Arquivo não encontrado!");
          ex.printStackTrace();
        } catch (Exception exception) {
          exception.printStackTrace();
        }
      }
    }
  }

  protected class SalvarArquivo implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setDialogTitle("Salvar como...");

      int userSelection = fileChooser.showSaveDialog(Janela.this);
      if (userSelection == JFileChooser.APPROVE_OPTION)
      {
        File arquivo = fileChooser.getSelectedFile();
        File arquivoExt;
        String nome = arquivo.getName();
        if (nome.split("\\.").length <= 1 ||
          (nome.split("\\.").length > 1 && !nome.split("\\.")[1].equals("javapaint"))) {
          arquivoExt = new File(arquivo.getAbsolutePath() + ".javapaint");
        } else {
          arquivoExt = arquivo;
        }
        try {
          if (arquivoExt.createNewFile()) {
            System.out.println("Arquivo criado: " + arquivoExt.getName());
          } else {
            System.out.println("Arquivo já existe.");
            if (JOptionPane.showConfirmDialog(Janela.this, "Arquivo já existente\n" +
                "Deseja sobrescrever?", "Atenção", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION)
              return;
          }
          try {
            FileWriter escritor = new FileWriter(arquivoExt.getAbsoluteFile());
            for (Figura figura : figuras) {
              escritor.write(figura.toString() + "\n");
            }
            escritor.close();
          } catch (IOException ex) {
            JOptionPane.showMessageDialog(Janela.this, "Erro na escrita do arquivo!");
            ex.printStackTrace();
          }
        } catch (IOException ex) {
          JOptionPane.showMessageDialog(Janela.this, "Erro na criação do arquivo!");
          ex.printStackTrace();
        }
      }
    }
  }

  protected  class Apagar implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      if (figuras.size() <= 0) {
        JOptionPane.showMessageDialog(Janela.this, "Não existem figuras para serem apagadas!");
        return;
      }
      figuras.remove(figuras.size() - 1);
      pnlDesenho.getGraphics().clearRect(0, 0, pnlDesenho.getWidth(), pnlDesenho.getHeight());
      for (Figura figura : figuras) {
        figura.torneSeVisivel(pnlDesenho.getGraphics());
      }
    }
  }

  protected class EncerrarPrograma implements ActionListener {
    public void actionPerformed(ActionEvent e)
    {
      if (JOptionPane.showConfirmDialog(Janela.this, "Você realmente deseja fechar o programa", "Paint",
          JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
        System.exit(0);
    }
  }

  protected static class FechamentoDeJanela extends WindowAdapter {
    public void windowClosing(WindowEvent e)
    {
      System.exit(0);
    }
  }

  protected class Conectar implements ActionListener {
    public void actionPerformed(ActionEvent e)
    {
      try
      {
        String[] args = Editor.getArgs();
        String host = "localhost";
        int porta = 3000;
        if (args[0] != null)
          host = args[0];
        if (args[1] != null)
          porta = Integer.parseInt(args[1]);
        cliente = new Cliente(host, porta);
        statusBarCon.setText("Conexão: conectado");
      }
      catch (ConnectException ex)
      {
        JOptionPane.showMessageDialog(Janela.this, "Não foi possível conectar ao " +
            "servidor!\nTente novamente mais tarde");
      }
    }
  }

  protected class Desconectar implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      if (cliente == null)
      {
        JOptionPane.showMessageDialog(Janela.this, "Nenhuma conexão encontrada!");
        return;
      }
      cliente.desconectarSe();
      statusBarCon.setText("Conexão: desconectado");
      cliente = null;
    }
  }

  protected class SalvarRemoto implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      if (cliente == null)
      {
        JOptionPane.showMessageDialog(Janela.this, "Nenhuma conexão encontrada!");
        return;
      }
      String nome = desenho.getNome();
      if (nome.equals(""))
        nome = JOptionPane.showInputDialog(Janela.this, "Digite o nome do desenho");
      desenho.setNome(nome);
      cliente.salvar(desenho);
    }
  }
}
