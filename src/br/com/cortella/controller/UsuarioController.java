package br.com.cortella.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cortella.entidades.Usuario;
import br.com.cortella.jdbc.UsuarioDAO;

/**
 * Servlet implementation class UsuarioController
 */
@WebServlet("/usucontroller.do")
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		System.out.println("Chamando metodo GET");
		
		//Captura parametro da tela
		String acao = request.getParameter("acao");
		UsuarioDAO usuDao = new UsuarioDAO();
		
		if(acao!=null && acao.equals("exc")) {
			//Captura parametro da tela
			
			String id = request.getParameter("id");
			
			Usuario usuario = new Usuario();
			usuario.setId(Integer.parseInt(id));
			usuDao.excluir(usuario);
			
			//Redirecionando pelo cliente (Browser
			response.sendRedirect("usucontroller.do?acao=lis");
		}
		
		if(acao!=null && acao.equals("alt")) {
			//Captura parametro da tela
			
			String id = request.getParameter("id");
			
			//busca objeto no banco
			Usuario usuario = usuDao.buscarPorId(Integer.parseInt(id));
			
			//seta atributos no request com objeto usuario
			request.setAttribute("usuario", usuario);
			
			//Encaminha para a tela
			RequestDispatcher saida = request.getRequestDispatcher("frmusuario.jsp");
			saida.forward(request, response);
		}
		
		if(acao!=null && acao.equals("cad")) {
			
			//Instancia novo objeto e set valores vazios
			Usuario usuario = new Usuario();
			
			usuario.setId(0);
			usuario.setNome("");
			usuario.setLogin("");
			usuario.setSenha("");
			//seta atributos no request com objeto usuario
			request.setAttribute("usuario", usuario);
			
			//Encaminha para a tela
			RequestDispatcher saida = request.getRequestDispatcher("frmusuario.jsp");
			saida.forward(request, response);
		}
		
		if(acao!=null && acao.equals("lis")) {
			//Obter a lista
			List<Usuario> lista = usuDao.buscarTodos();
			
			//Engavetar a lista no request
			request.setAttribute("lista", lista);
			
			//Encaminhamento ao JSP
			RequestDispatcher saida = request.getRequestDispatcher("listausuarios.jsp");
			saida.forward(request, response);
		}
		
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		System.out.println("Chamando metodo POST");
		
		//RECEBE DADOS DA TELA
		String id = request.getParameter("txtid");
		String nome = request.getParameter("txtnome");
		String login = request.getParameter("txtlogin");
		String senha = request.getParameter("txtsenha");
		
		//CRIA OBJ USUARIO E SETA VALORES
		Usuario usu = new Usuario();
		
		if(id!= "") {
			usu.setId(Integer.parseInt(id));
		}
		
		usu.setNome(nome);
		usu.setLogin(login);
		usu.setSenha(senha);		
		
		//PEDE PARA USUARIODAO CADASTRAR NO BANDO DE DADOS
		UsuarioDAO usuarioDAO = new UsuarioDAO();

		usuarioDAO.cadastrar(usu);
		
		//SAIDA BROWSER
		PrintWriter saida = response.getWriter();
		saida.print("Salvo com sucesso!");
	}

}
