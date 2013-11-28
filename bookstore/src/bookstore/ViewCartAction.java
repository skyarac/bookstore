package bookstore;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Query;
import org.hibernate.Session;

import bookstore.database.HibernateUtil;


/**
 * 
 * @author Administrator
 *
 */
public class ViewCartAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm srcForm,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        List cart = (List) session.getAttribute("cart");
        List bookInfo = getBookInfo(cart);
        session.setAttribute("books", bookInfo);

        return mapping.findForward("viewCart");
    }

    private List getBookInfo(List cart) {
        List bookInfo = new ArrayList();
        if (cart != null) {
            ListIterator iterator = cart.listIterator();
            Session dbSession = HibernateUtil.currentSession();
            Query query = dbSession
                    .createQuery(" from Bookinfo as b where b.id=:bookid");
            while (iterator.hasNext()) {
                query.setInteger("bookid", ((Integer) iterator.next()) .intValue());
                List result = query.list();
                if (result.size() > 0) {
                    bookInfo.add(result.get(0));
                }
            }
        }
        return bookInfo;
    }
}
