package reposity14082020;

import java.sql.SQLException;

public class AppRepository {
	public static void main(String[] args) throws SQLException {		
		ConnectionManager connManager = new ConnectionManager();
		
		try {
			ImovelRepository repo = new ImovelRepository(connManager);
			Aluno al = new Aluno("Hector", 26);
			al = repo.save(al);
			connManager.commit();
			
			Long id = al.getId();
			System.out.println(id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
