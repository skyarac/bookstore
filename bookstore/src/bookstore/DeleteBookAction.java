package bookstore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.hibernate.Query;
import org.hibernate.Session;

import bookstore.database.Bookinfo;
import bookstore.database.HibernateUtil;

/**
 * 
 * @author liuyu
 *2007-7-21
 */
public class DeleteBookAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm srcForm,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionMessages errors = new ActionMessages();
        int bookid;
        try {
            bookid = Integer.parseInt(request.getParameter("bookid"));
            Session session = HibernateUtil.currentSession();
            Bookinfo book;
            HibernateUtil.beginTransaction();
            Query query = HibernateUtil.currentSession().createQuery(
                    "from Bookinfo where id=:id");
            query.setInteger("id", bookid);
            book = (Bookinfo) query.list().get(0);
            session.delete(book);
            HibernateUtil.commitTransaction();
        } catch (Exception e) {
            errors.add("noid", new ActionMessage("shoppingcart.noid"));
            this.saveErrors(request, errors);
            return mapping.findForward("errorPage");
        }
        return mapping.getInputForward();
    }

}
