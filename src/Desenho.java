import java.util.ArrayList;
import java.util.Objects;

public class Desenho extends Comunicado
{
  private static final long serialVersionUID = 6529685098267757691L;
  private String nome;
  private final String dataCriacao;
  private final String dataUltimaAtualizacao;
  private final ArrayList<String> conteudo = new ArrayList<>();

  public Desenho(String nome, String dataCriacao, String dataUltimaAtualizacao)
  {
    this.nome = nome;
    this.dataCriacao = dataCriacao;
    this.dataUltimaAtualizacao = dataUltimaAtualizacao;
  }

  public int addFigura(String figura)
  {
    conteudo.add(figura);
    return conteudo.size();
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

  public String getDataCriacao() {
    return dataCriacao;
  }

  public String getDataUltimaAtualizacao() {
    return dataUltimaAtualizacao;
  }
}
