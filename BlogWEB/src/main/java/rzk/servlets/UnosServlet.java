package rzk.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rzk.beans.UnosBlogaBeanRemote;

/**
 * Servlet implementation class UnosServlet
 */
@WebServlet("/UnosServlet")
public class UnosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UnosServlet() {
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
		// uzimamo bean iz sesije
		UnosBlogaBeanRemote ubr = (UnosBlogaBeanRemote) request.getSession().getAttribute("bean");
		String tekst = request.getParameter("tekst");
		//System.out.println(request.getParameter("kategorija"));
		int kategorijaId = Integer.parseInt(request.getParameter("kategorija"));
		ubr.postuj(tekst, kategorijaId);
	}

}
