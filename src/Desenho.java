import java.net.Inet4Address;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Desenho extends Comunicado
{
  private static final long serialVersionUID = 6529685098267757691L;
  private String nome;
  private final String dataCriacao;
  private String dataUltimaAtualizacao;
  private String ip = "";
  private final ArrayList<String> conteudo = new ArrayList<>();

  public Desenho(String nome, String dataCriacao, String dataUltimaAtualizacao)
  {
    this.nome = nome;
    this.dataCriacao = dataCriacao;
    this.dataUltimaAtualizacao = dataUltimaAtualizacao;
    try {
      this.ip = Inet4Address.getLocalHost().getHostAddress();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public Desenho(String nome, Date dataCriacao, Date dataUltimaAtualizacao)
  {
    SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
    this.nome = nome;
    this.dataCriacao = dtf.format(dataCriacao);
    this.dataUltimaAtualizacao = dtf.format(dataUltimaAtualizacao);
  }

  public int addFigura(String figura)
  {
    conteudo.add(figura);
    return conteudo.size();
  }

  public String remove(int i)
  {
    return conteudo.remove(i);
  }

  public int getQuantidade()
  {
    return conteudo.size();
  }

  public String getFigura(int i)
  {
    return conteudo.get(i);
  }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Desenho)) return false;
    Desenho desenho = (Desenho) o;
    return nome.equals(desenho.nome) &&
        dataCriacao.equals(desenho.dataCriacao) &&
        dataUltimaAtualizacao.equals(desenho.dataUltimaAtualizacao) &&
        conteudo.equals(desenho.conteudo);
  }

  public int hashCode() {
    return Objects.hash(nome, dataCriacao, dataUltimaAtualizacao, conteudo);
  }

  public String toString()
  {
    StringBuilder stringBuilder = new StringBuilder();
    for (String figura : this.conteudo)
    {
      stringBuilder.append(figura).append("\n");
    }
    return stringBuilder.toString();
  }

  public ArrayList<String> getConteudo() {
    return conteudo;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome)
  {
    this.nome = nome;
  }

  public void setIp()
  {
    try {
      this.ip = Inet4Address.getLocalHost().getHostAddress();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public void setAtualizacao(String dataUltimaAtualizacao)
  {
    this.dataUltimaAtualizacao = dataUltimaAtualizacao;
  }

  public String getDataCriacao() {
    return dataCriacao;
  }

  public String getDataUltimaAtualizacao() {
    return dataUltimaAtualizacao;
  }

  public String getIp() {
    return ip;
  }
}
