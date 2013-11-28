package bookstore;

import java.util.List;

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
public class BookDetailAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm srcForm,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionMessages errors = new ActionMessages();
        //如果id为空的话，报错。
        if (request.getParameter("id") == null
                || request.getParameter("id").equals("")) {
            errors.add("noid", new ActionMessage("bookdetail.noid"));
            this.saveErrors(request, errors);
            return mapping.findForward("errorPage");
        }
        int id = Integer.parseInt(request.getParameter("id"));
        Bookinfo book = loadBookInfo(id, errors);
        if(book == null) {
            this.saveErrors(request, errors);
            return mapping.findForward("errorPage");
        }
        request.setAttribute("book", book);
        return mapping.findForward("bookdetail");
    }

    private Bookinfo loadBookInfo(int id, ActionMessages errors) {
        Session dbSession = HibernateUtil.currentSession();
        try {
            HibernateUtil.beginTransaction();
            Query query = dbSession.createQuery(" from Bookinfo as b where b.id=:id")
                .setInteger("id", id)
                .setMaxResults(1);
            List result = query.list();
            HibernateUtil.commitTransaction();
            if(result.size() != 0) {
                return (Bookinfo) result.get(0);
            }
            errors.add("noBook", new ActionMessage("bookdetail.noBook"));
        } catch(Exception e) {
            errors.add("dbError", new ActionMessage("globle.dbError"));
        }
        return null;
    }

}
