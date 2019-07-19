package br.com.cortella.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.cortella.entidades.Usuario;

public class UsuarioDAO {
	private Connection con = Conexao.getConnection();
	
	public void cadastrar(Usuario u) {
		//Monta SQL
		String sql = "INSERT INTO USUARIO (nome,login,senha) values(?,?,?)";
		
		//CONSTROE PREPAREDSTATEMENT
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, u.getNome());
			preparador.setString(2 , u.getLogin());
			preparador.setString(3 , u.getSenha());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("CADASTRO REALIZADO COM SUCESSO");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void alterar(Usuario u) {
		//Monta SQL
		String sql = "UPDATE USUARIO SET nome=?,login=?,senha=? WHERE id=?";
		
		//CONSTROE PREPAREDSTATEMENT
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, u.getNome());
			preparador.setString(2 , u.getLogin());
			preparador.setString(3 , u.getSenha());
			preparador.setInt(4 , u.getId());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("ALTERACAO REALIZADA COM SUCESSO");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void excluir(Usuario u) {
		//Monta SQL
		String sql = "DELETE FROM USUARIO WHERE id=?";
		
		//CONSTROE PREPAREDSTATEMENT
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1 , u.getId());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("EXCLUIDO COM SUCESSO");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Usuario> buscarTodos() {
		//Monta SQL
		String sql = "SELECT * FROM USUARIO";
		List<Usuario> lista = new ArrayList<Usuario>();
		//CONSTROE PREPAREDSTATEMENT
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			
			ResultSet resultado = preparador.executeQuery();
			
			while(resultado.next()) {
				Usuario usu = new Usuario();
				usu.setId(resultado.getInt("id"));
				usu.setNome(resultado.getString("nome"));
				usu.setLogin(resultado.getString("login"));
				usu.setSenha(resultado.getString("senha"));
				
				lista.add(usu);
			}
			preparador.close();
			
			System.out.println("EXCLUIDO COM SUCESSO");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista;
	}
	
}
