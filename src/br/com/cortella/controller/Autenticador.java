package br.com.cortella.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cortella.entidades.Usuario;
import br.com.cortella.jdbc.UsuarioDAO;

/**
 * Servlet implementation class Autenticador
 */
@WebServlet(name = "AutenticadorController", urlPatterns = { "/autcontroller.do" })
public class Autenticador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Autenticador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Captura dados da tela
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		//Criamos objeto usuario
		Usuario usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setLogin(senha);
		
		//Passa para o dao conferir no banco
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuRetorno = usuarioDAO.autenticar(usuario);
		if(usuRetorno != null) {
			//Encaminhando ao index.jsp
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else {
			response.sendRedirect("login.html");
		}
		
	}

}
