package forumjdbc0808;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AppJdbc {

	public static void main(String[] args) throws SQLException {
		criarTabela();
		// inserirRegistro();
		deletarRegistro();
		atualizarRegistro();
		listaProdutos();

	}

	private static void listaProdutos() throws SQLException {
		Connection conn = null;
		Statement listar = null;
		ResultSet produtosListados = null;
		String sql = "select top 30 id , nome from produto  order by id asc";

		try {
			conn = new AppJdbc().Conectar();
			listar = conn.createStatement();
			produtosListados = listar.executeQuery(sql);

			while (produtosListados.next()) {
				System.out.println(
						"Id->>" + produtosListados.getInt("id") + "   Nome->>" + produtosListados.getString("Nome"));
			}
		} finally {
			listar.close();
			produtosListados.close();
		}

	}

	private static void atualizarRegistro() throws SQLException {
		Connection conn = null;
		PreparedStatement updateRegistro = null;
		String sql = "update produto set nome = ? where id = select min(id) from produto";

		try {
			conn = new AppJdbc().Conectar();
			updateRegistro = conn.prepareStatement(sql);
			updateRegistro.setNString(1, "Arroz");
			updateRegistro.execute();

		} finally {
			updateRegistro.close();
		}

	}

	private static void deletarRegistro() throws SQLException {
		Connection conn = null;
		PreparedStatement excluirRegistro = null;
		String sql = "delete from produto where id = ?";
		try {
			conn = new AppJdbc().Conectar();
			excluirRegistro = conn.prepareStatement(sql);

			for (int i = 1; i <= 10; i++) {
				excluirRegistro.setInt(1, i);
				excluirRegistro.execute();
			}

			int quant = excluirRegistro.executeUpdate();
			System.out.println(quant);

		} finally {
			excluirRegistro.close();
		}

	}

	private static void inserirRegistro() throws SQLException {
		Connection conn = null;
		PreparedStatement inserirProduto = null;
		String sql = "insert into produto (id, nome) values (?,?)";
		try {
			conn = new AppJdbc().Conectar();
			inserirProduto = conn.prepareStatement(sql);

			for (int cont = 1; cont <= 2000; cont++) {
				inserirProduto.setInt(1, cont);
				inserirProduto.setString(2, "Produto " + cont);
				inserirProduto.execute();
			}

		} finally {
			inserirProduto.close();
		}

	}

	private static void criarTabela() throws SQLException {
		Connection conn = null;
		Statement tabela = null;
		String sql = "create table if not exists produto ("
		        + "id integer not null primary key,"
				+ "nome varchar(60) not null "
		        + ")";

		try {
			conn = new AppJdbc().Conectar();
			tabela = conn.createStatement();
			tabela.execute(sql);

		} finally {
			tabela.close();
		}

	}

	public Connection Conectar() throws SQLException {
		return DriverManager.getConnection("jdbc:h2:~/testejdbc", "sa", "");
	}

}
