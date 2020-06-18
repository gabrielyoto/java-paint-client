import java.net.*;
import java.io.*;
import java.util.Vector;

public class Cliente
{
	public static final String HOST_PADRAO  = "localhost";
	public static final int PORTA_PADRAO = 3000;

	protected Parceiro servidor = null;

	public Cliente() throws ConnectException
	{
		this(HOST_PADRAO, PORTA_PADRAO);
	}

	public Cliente(String host) throws ConnectException
	{
		this(host, PORTA_PADRAO);
	}

	public Cliente(String host, int porta) throws ConnectException
	{
		Socket conexao;
		try
		{
			conexao = new Socket(host, porta);
			System.out.println("Conectado ao servidor " + host + ":" + porta);
		}
		catch (Exception erro)
		{
			System.err.println("Indique o servidor e a porta corretos!\n");
			throw new ConnectException();
		}

		ObjectOutputStream transmissor;
		try
		{
				transmissor = new ObjectOutputStream(conexao.getOutputStream());
		}
		catch (Exception erro)
		{
				System.err.println("Indique o servidor e a porta corretos!\n");
				throw new ConnectException();
		}

		ObjectInputStream receptor;
		try
		{
				receptor = new ObjectInputStream(conexao.getInputStream());
		}
		catch (Exception erro)
		{
				System.err.println("Indique o servidor e a porta corretos!\n");
				throw new ConnectException();
		}

		Parceiro servidor;
		try
		{
				servidor = new Parceiro(conexao, receptor, transmissor);
				this.servidor = servidor;
		}
		catch (Exception erro)
		{
				System.err.println("Indique o servidor e a porta corretos!\n");
				throw new ConnectException();
		}

		TratadoraDeComunicadoDeDesligamento tratadoraDeComunicadoDeDesligamento;
		try
		{
			tratadoraDeComunicadoDeDesligamento = new TratadoraDeComunicadoDeDesligamento(servidor);
		}
		catch (Exception ignored)
		{
			throw new ConnectException();
		}

		tratadoraDeComunicadoDeDesligamento.start();
	}

	public void salvar(Vector<Figura> figuras)
	{
		try
		{
			servidor.receba(new PedidoDeSalvar(figuras));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void desconectarSe()
	{
		try
		{
			servidor.receba(new PedidoParaSair());
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
