import javax.swing.*;

public class TratadoraDeComunicadoDeDesligamento extends Thread
{
  private final Parceiro servidor;
  protected final Janela janela;
  protected final JLabel statusBar;

  public TratadoraDeComunicadoDeDesligamento(Parceiro servidor, Janela janela, JLabel statusBar) throws Exception
  {
    if (servidor == null)
      throw new Exception("Porta inválida");

    this.servidor = servidor;
    this.janela = janela;
    this.statusBar = statusBar;
  }

  public void run()
  {
    for(;;)
    {
      try
      {
        if (this.servidor.espie() instanceof ComunicadoDeDesligamento)
        {
          System.out.println ("\nO servidor vai ser desligado agora;");
          System.err.println ("volte mais tarde!\n");
          JOptionPane.showMessageDialog(janela, "Servidor desligado! Conecte-se novamente mais tarde!");
          statusBar.setText("Conexão: desconectado");
          return;
        }
      }
      catch (Exception ignored)
      {}
    }
  }
}
