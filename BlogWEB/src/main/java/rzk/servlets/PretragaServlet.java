package rzk.servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Blog;
import rzk.beans.PretragaBlogaBeanRemote;

/**
 * Servlet implementation class PretragaServlet
 */
@WebServlet("/PretragaServlet")
public class PretragaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	PretragaBlogaBeanRemote pbr;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PretragaServlet() {
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
		String tekst = request.getParameter("pretraga");
		List<Blog> blogovi = pbr.pretraziBlogove(tekst);
		System.out.println(blogovi.size());
		request.getSession().setAttribute("blogovi", blogovi);		
		request.setAttribute("blogovi", blogovi);

		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

}
