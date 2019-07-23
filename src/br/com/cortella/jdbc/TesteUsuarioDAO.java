package br.com.cortella.jdbc;

import java.util.List;

import br.com.cortella.entidades.Usuario;

public class TesteUsuarioDAO {

	public static void main(String[] args) {
		//testCadastrar();
		//testAlterar();
		//testExcluir();
		//testBuscarTodos();
		//testAutenticar();
		testBucarPorId();

	}

	private static void testCadastrar() {
		Usuario usu = new Usuario();
		usu.setNome("Bruno");
		usu.setLogin("cortella");
		usu.setSenha("abcd1234");
		
		UsuarioDAO usaDao = new UsuarioDAO();
		usaDao.cadastrar(usu);
	}
	
	private static void testAlterar() {
		Usuario usu = new Usuario();
		usu.setId(1);
		usu.setNome("JoaoDas");
		usu.setLogin("js");
		usu.setSenha("js1234");
		
		UsuarioDAO usaDao = new UsuarioDAO();
		usaDao.alterar(usu);
	}
	
	private static void testExcluir() {
		Usuario usu = new Usuario();
		usu.setId(1);
		UsuarioDAO usaDao = new UsuarioDAO();
		usaDao.excluir(usu);
	}
	
	private static void testBuscarTodos() {
		UsuarioDAO usaDao = new UsuarioDAO();
		List<Usuario> listaResultado = usaDao.buscarTodos();
		for(Usuario u: listaResultado) {
			System.out.println(u.getId() + " " + u.getNome() + " " + u.getSenha() + " " + u.getLogin());
		}	
	}
	public static void testAutenticar() {
		Usuario usuario = new Usuario();
		usuario.setLogin("jao");
		usuario.setSenha("jao");
		
		UsuarioDAO usaDao = new UsuarioDAO();
		usaDao.autenticar(usuario);
		System.out.println(usaDao.autenticar(usuario));
	}
	
	public static void testBucarPorId() {
		UsuarioDAO usuarioDao = new UsuarioDAO();
		//System.out.println(usuarioDao.buscarPorId(3));
		Usuario usu = usuarioDao.buscarPorId(3);
		if(usu != null) {
			System.out.println("Nome : " + usu.getNome());
		}else {
			System.out.println("Usuario nao encontrado!");
		}
		
	}
}
