package Controller;

import DataAccessLayer.CharityDAO;
import Model.ItemDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CharitableOrgItemsServlet", urlPatterns = {"/CharitableOrgItemsServlet"})
public class CharitableOrgItemsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("inside doget charitable org items servlet");

        HttpSession session = request.getSession();
        String purchaseSuccess = (String) session.getAttribute("purchaseSuccess");
        if (purchaseSuccess != null) {
            request.setAttribute("purchaseSuccess", purchaseSuccess);
            session.removeAttribute("purchaseSuccess");
        }

        CharityDAO charityDAO = new CharityDAO();
        List<ItemDTO> itemsForCharity = charityDAO.getAllAvailableItemsForCharity();

        System.out.println("Number of items fetched: " + (itemsForCharity != null ? itemsForCharity.size() : "null"));

        request.setAttribute("itemsForCharity", itemsForCharity);
        request.getRequestDispatcher("Views/charityItems.jsp").forward(request, response);
    }
}
