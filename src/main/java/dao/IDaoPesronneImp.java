package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metier.entites.Personne;
import metier.entites.Produit;

public class IDaoPesronneImp implements IPersonneDao<Personne>{
	private Connection con = SingletonConnection.getConn();
	@Override
	public void save(Personne obj) {
		try{
		PreparedStatement ps = con.prepareStatement("INSERT INTO personne (nom,prenom,email,password) VALUES (?,?,?,?)");
		ps.setString(1, obj.getNom());
		ps.setString(2, obj.getPrenom());
		ps.setString(3, obj.getEmail());
		ps.setString(4, obj.getPassword());
		ps.executeUpdate();
		
		ps.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	@Override
	public boolean findByEmailAndPassword(String email,String password) {
		Personne perso = new Personne();
		// TODO Auto-generated method stub
		List<String> ids = new ArrayList<String>();
		if(con != null) {			
			try {
				PreparedStatement ps = con.prepareStatement("select * from personne where password = ? and email = ?");
				ps.setString(1,password);
				ps.setString(2, email);
				ResultSet rs = (ResultSet) ps.executeQuery();
				if(rs.next()) {
					return true;
				}
				else { return false; }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	

}








