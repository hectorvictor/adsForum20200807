package reposity14082020;

public class Aluno {

	private long id;
	private String nome;
	private int idade;

	public Aluno(long id, String nome, int idade) {
		this(nome, idade);
		this.id = id;

	}

	public Aluno(String nome, int idade) {
		this.nome = nome;
		this.idade = idade;
	}

	public long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public int getIdade() {
		return idade;
	}

	@Override
	public String toString() {
		return "Aluno [id=" + id + ", nome=" + nome + ", idade=" + idade + "]";
	}
	
	

}
