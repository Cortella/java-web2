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
	
	public void salvar(Usuario usuario) {
		if(usuario.getId() != 0) {
			alterar(usuario);
		}else {
			cadastrar(usuario);
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
	
	public Usuario buscarPorId(int id) {
		String sql = "SELECT * FROM USUARIO WHERE ID =?";
		Usuario usuario = null;
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1,id);
			
			ResultSet resultado = preparador.executeQuery();
			
			if(resultado.next()) {
				
				usuario = new Usuario();
				usuario.setId(resultado.getInt("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	public List<Usuario> buscarPorNome(String nome) {
		String sql = "SELECT * FROM USUARIO WHERE nome like ?";
		List<Usuario> lista= new ArrayList<Usuario>();
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1,"%"+nome+"%");
			
			ResultSet resultado = preparador.executeQuery();
			
			while(resultado.next()) {
				
				Usuario usuario = new Usuario();
				usuario.setId(resultado.getInt("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));
				lista.add(usuario);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/**
	  Buscar por login e senha de Usuario
	  @param usuario Objeto com login e senha a ser consultado no banco
	  @return null quando nao encontra no banco ou um ponteiro para um objeto usuario completo quando encontra
	 */
	public Usuario autenticar(Usuario user) {
		String sql = "SELECT * FROM USUARIO WHERE login = ? and senha = ?";
		Usuario usuarioRetorno = null;
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1,user.getLogin());
			preparador.setString(2,user.getSenha());
			
			ResultSet resultado = preparador.executeQuery();
			
			if(resultado.next()) {
				
				usuarioRetorno = new Usuario();
				usuarioRetorno.setId(resultado.getInt("id"));
				usuarioRetorno.setNome(resultado.getString("nome"));
				usuarioRetorno.setLogin(resultado.getString("login"));
				usuarioRetorno.setSenha(resultado.getString("senha"));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarioRetorno;
	}
	
	public boolean existe(Usuario user) {
		String sql = "SELECT * FROM USUARIO WHERE login = ? and senha = ?";
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1,user.getLogin());
			preparador.setString(2,user.getSenha());
			
			ResultSet resultado = preparador.executeQuery();
			
			return resultado.next();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}



