public class Editor {
  private static String[] savedArgs;

  public static String[] getArgs() { return savedArgs; }

  public static void main(String[] args)
  {
    new Janela();
    savedArgs = args;

    if (args.length > 2)
    {
      System.err.println("Uso esperado: java Editor [HOST [PORTA]]\n");
    }


  }
}
