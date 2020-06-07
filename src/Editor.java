public class Editor {
  public static void main(String[] args)
  {
    new Janela();

    if (args.length > 2)
    {
      System.err.println("Uso esperado: java Cliente [HOST [PORTA]]\n");
    }
  }
}
