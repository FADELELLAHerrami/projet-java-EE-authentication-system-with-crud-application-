package web;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.IProduitDao;
import dao.ProduitDaoImp;
import metier.entites.Produit;

@SuppressWarnings("serial")
@WebServlet(name="mo-servlet",urlPatterns = {"*.p"})
public class ServletController extends HttpServlet {
	private IProduitDao metier;
	

	@Override
	public void init() throws ServletException {
		metier = new ProduitDaoImp();
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//session
		HttpSession session = req.getSession(false);
	    if (session == null || session.getAttribute("authenticated") == null) {
	        req.getRequestDispatcher("login.jsp").forward(req, res);
	        return;
	    }
		
		//Get path
		String path=req.getServletPath();
		//login
		if (path.equals("/login.p")) {
            req.getRequestDispatcher("login.jsp").forward(req, res);
            return;
        }
		//Delete Product
		if(path.equals("/supprimer.p")) {
			Long id = Long.parseLong(req.getParameter("id"));
			metier.Delete(id);
			res.sendRedirect("recherche.p?motCle=");
		}else {
			switch (path) {
				//Principal page
				case "/index.p": {
					req.getRequestDispatcher("Produit.jsp").forward(req, res);
					break;
				}
				//Research 
				case "/recherche.p": {
					String motCle=req.getParameter("motCle");
					ProduitModel model = new ProduitModel();
					model.setMotCle(motCle);
					List<Produit> produits = metier.SearchProd("%"+motCle+"%");
					model.setProduits(produits);
					req.setAttribute("model", model);
					req.getRequestDispatcher("Produit.jsp").forward(req, res);
					break;
				}
				//Get Add product page
				case "/saisire.p": {
					req.setAttribute("produit", new Produit());
					req.getRequestDispatcher("AjouterProd.jsp").forward(req, res);
					break;
				}
				//Get Product
				case "/editer.p": {
					Long id = Long.parseLong(req.getParameter("id"));
					Produit p= metier.getProd(id);
					
					req.setAttribute("produit", p);
					req.getRequestDispatcher("EditProd.jsp").forward(req, res);
					break;
				}
				//Error 404
				default:
					req.getRequestDispatcher("NotFound.jsp").forward(req, res);
				}
		}
		
		
	} 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get path
		String path = req.getServletPath();
		
		switch (path) {
		case "/login.p": {
			String Email = "admin@gmail.com";
			String Password = "admin";
			
			String email = req.getParameter("email");
            String password = req.getParameter("password");
            System.out.println(email);
            System.out.println(password);

            if (email.equals(Email) && password.equals(Password)) {
            	HttpSession session = req.getSession();
                session.setAttribute("authenticated", true);
            	req.getRequestDispatcher("Produit.jsp").forward(req, res);
            } else {
                req.setAttribute("error", "Invalid username or password");
                req.getRequestDispatcher("login.jsp").forward(req, res);
            }
            break;
		}
	
		//Add Product
		case "/add.p": {
			String designation = req.getParameter("designation");
			double prix =Double.parseDouble(req.getParameter("prix"));
			int quantite=Integer.parseInt(req.getParameter("quantite"));
			
			Produit p = metier.AddProd(new Produit(designation, prix, quantite));
			req.setAttribute("produit", p);
			req.getRequestDispatcher("Confirmation.jsp").forward(req, res);
			break;
		}
		//Update Product
		case "/update.p": {
			Long id =Long.parseLong(req.getParameter("id"));
			String designation = req.getParameter("designation");
			double prix =Double.parseDouble(req.getParameter("prix"));
			int quantite=Integer.parseInt(req.getParameter("quantite"));
			
			Produit p = new Produit(designation, prix, quantite);
			p.setId(id);
			metier.Update(p);
			req.setAttribute("produit", p);
			req.getRequestDispatcher("Confirmation.jsp").forward(req, res);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + path);
		}
		
	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}
}
