package reposity14082020;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImovelRepository {
	private ConnectionManager connectionManager;

	public ImovelRepository(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
		this.createTable();
	}

	private void createTable() {
		PreparedStatement st = null;
		try {
			st = connectionManager
					.preparedStatement("create table if not exists aluno(" + "id integer not null primary key,"
							+ "nome varchar(250) not null," + "idade varchar(250) not null" + ")");

			st.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public Aluno findBy(Long id) throws SQLException {
		Aluno found = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = connectionManager.preparedStatement("select id, nome , idade from aluno where id = ?");
			st.setLong(1, id);
			rs = st.executeQuery();

			if (rs.next()) {
				found = new Aluno(rs.getLong("id"), rs.getString("nome"), rs.getInt("idade"));

			}

		} catch (Exception e) {
			st.close();
			rs.close();
		}
		return found;
	}

	public Aluno save(Aluno aluno) {
		boolean exists = false;
		PreparedStatement stSave = null;
		try {
			if (exists) {
				stSave = connectionManager.preparedStatement("update aluno set nome = ?, idade = ? where id = ?");
				stSave.setString(1, aluno.getNome());
				stSave.setInt(2, aluno.getIdade());
				stSave.setLong(3, aluno.getId());
			} else {
				Long id = selectNewId();
				System.out.println("Novo id: " + id);
				stSave = connectionManager.preparedStatement("insert into aluno (id, nome,idade) values (?,?,?)");
				stSave.setLong(1, id);
				stSave.setString(2, aluno.getNome());
				stSave.setInt(3, aluno.getIdade());
			}
			stSave.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				stSave.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return aluno;

	}

	private Long selectNewId() throws SQLException{
		PreparedStatement psSelect = connectionManager.preparedStatement("select coalesce(max(id),0)+1 as newId from imovel");
		ResultSet rsSelect = psSelect.executeQuery();
		try {
			if (rsSelect.next()) {
				return rsSelect.getLong("newId");
			}
		} finally {
			rsSelect.close();
			psSelect.close();
		}
		return null;
	
	}
}
