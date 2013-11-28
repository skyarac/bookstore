package bookstore;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.hibernate.Query;
import org.hibernate.Session;

import bookstore.database.HibernateUtil;

public class ShoppingCartAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm srcForm,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        ActionMessages errors = new ActionMessages();
        if (request.getParameter("bookid") == null) {
            errors.add("noBookid", new ActionMessage("shoppingcart.noid"));
            this.saveErrors(request, errors);
            return mapping.findForward("errorPage");
        }
        String strBookid = (String) request.getParameter("bookid");
        int bookid;
        try {
            bookid = Integer.parseInt(strBookid);
        } catch (NumberFormatException nfe) {
            errors.add("noBookid", new ActionMessage("shoppingcart.noid"));
            this.saveErrors(request, errors);
            return mapping.findForward("errorPage");
        }
        if (CheckBookid(request, errors, bookid)) {
            List cart;
            if (session.getAttribute("cart") == null) {
                cart = new ArrayList();
                session.setAttribute("cart", cart);
            } else {
                cart = (ArrayList) session.getAttribute("cart");
            }
            cart.add(new Integer(bookid));
        } else {
            this.saveErrors(request, errors);
            return mapping.findForward("errorPage");
        }
        return mapping.findForward("bookReady");
    }

    private boolean CheckBookid(HttpServletRequest request,
            ActionMessages errors, int id) {
        try {
            Session session = HibernateUtil.currentSession();
            HibernateUtil.beginTransaction();
            Query query = session
                    .createQuery("select count(*) from Bookinfo as b where b.id=:id");
            query.setInteger("id", id);
            int result = ((Integer) query.uniqueResult()).intValue();
            HibernateUtil.commitTransaction();
            if (result == 0) {
                errors.add("noBookid", new ActionMessage("shoppingcart.noid"));
                return false;
            }
        } catch (Exception e) {
            errors.add("dbError", new ActionMessage("globle.dbError"));
            return false;
        }
        return true;
    }

}
