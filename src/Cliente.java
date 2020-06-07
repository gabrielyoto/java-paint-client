import java.net.*;
import java.io.*;

public class Cliente
{
	public static final String HOST_PADRAO  = "localhost";
	public static final int PORTA_PADRAO = 3000;
	private String host;
	private int porta;

	public Cliente()
	{
		this(HOST_PADRAO, PORTA_PADRAO);
	}

	public Cliente(String host)
	{
		this(host, PORTA_PADRAO);
	}

	public Cliente(String host, int porta)
	{
		Socket conexao = null;
		try
		{
			this.host = host;
			conexao = new Socket(host, porta);
			System.out.println("Conectado ao servidor " + host + ":" + porta);
		}
		catch (Exception erro)
		{
			System.err.println("Indique o servidor e a porta corretos!\n");
			return;
		}

		ObjectOutputStream transmissor = null;
		try
		{
				transmissor = new ObjectOutputStream(conexao.getOutputStream());
		}
		catch (Exception erro)
		{
				System.err.println("Indique o servidor e a porta corretos!\n");
				return;
		}

		ObjectInputStream receptor = null;
		try
		{
				receptor = new ObjectInputStream(conexao.getInputStream());
		}
		catch (Exception erro)
		{
				System.err.println("Indique o servidor e a porta corretos!\n");
				return;
		}

		Parceiro servidor = null;
		try
		{
				servidor = new Parceiro(conexao, receptor, transmissor);
		}
		catch (Exception erro)
		{
				System.err.println("Indique o servidor e a porta corretos!\n");
				return;
		}

		TratadoraDeComunicadoDeDesligamento tratadoraDeComunicadoDeDesligamento = null;
		try
		{
			tratadoraDeComunicadoDeDesligamento = new TratadoraDeComunicadoDeDesligamento(servidor);
		}
		catch (Exception ignored)
		{} // sei que servidor foi instanciado

		tratadoraDeComunicadoDeDesligamento.start();

		try
		{
			servidor.receba(new PedidoParaSair());
		}
		catch (Exception erro)
		{}
	}
}
