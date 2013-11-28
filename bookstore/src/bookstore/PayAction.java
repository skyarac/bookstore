package bookstore;

import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;

import bookstore.database.Bookinfo;
import bookstore.database.HibernateUtil;
import bookstore.database.Order;
import bookstore.database.Userinfo;

public class PayAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm srcForm,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        Session dbSession = HibernateUtil.currentSession();
        List books = (List) session.getAttribute("books");
        PayForm form = (PayForm) srcForm;
        Hashtable result = combineBook(books);
        Enumeration enu = result.keys();
        Bookinfo book = new Bookinfo();
        HibernateUtil.beginTransaction();
        while (enu.hasMoreElements()) {
            Order order = new Order();
            order.setUserinfo((Userinfo) session.getAttribute("user"));
            //new String(form.getAddress().getBytes("ISO-8859-1"), "GB2312")
            order.setAddress(form.getAddress());
            order.setPostcode(form.getPostcode());
            order.setStatus((short) 1);
            order.setOrderdate(new Date(System.currentTimeMillis()));
            Integer bookid = (Integer) enu.nextElement();
            Integer number = (Integer) result.get(bookid);
            book.setId(bookid);
            order.setBookinfo(book);
            order.setNumber(number.shortValue());
            dbSession.saveOrUpdate(order);
        }
        HibernateUtil.commitTransaction();
        session.removeAttribute("books");
        session.removeAttribute("cart");
        return mapping.findForward("frontPage");
    }

    private Hashtable combineBook(List books) {
        ListIterator iter = books.listIterator();
        Hashtable result = new Hashtable();
        while (iter.hasNext()) {
            Bookinfo book = (Bookinfo) iter.next();
            if (result.get(book.getId()) == null) {
                result.put(book.getId(), new Integer(1));
            } else {
                Integer now = (Integer) result.get(book.getId());
                result.remove(book.getId());
                result.put(book.getId(), new Integer(now.intValue() + 1));
            }
        }
        return result;
    }
}
