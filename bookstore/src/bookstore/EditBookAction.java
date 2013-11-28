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
public class EditBookAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm srcForm,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionMessages errors = new ActionMessages();
        Session dbSession = HibernateUtil.currentSession();
        // ��ѯ�����ͼ��id�����id����ݿ��в���������Ϊ�û�������������
        Bookinfo book;
        try {
            int bookid = Integer.parseInt(request.getParameter("bookid"));
            HibernateUtil.beginTransaction();
            Query query = dbSession
                    .createQuery(" from Bookinfo as b where b.id=:id");
            query.setInteger("id", bookid);
            List result = query.list();
            HibernateUtil.commitTransaction();
            if (result.size() != 0) {
                book = (Bookinfo) result.get(0);
            } else {
                book = new Bookinfo();
                book.setId(new Integer(0));
            }
        } catch (Exception e) {
            errors.add("wrongid", new ActionMessage("editbook.wrongid"));
            this.saveErrors(request, errors);
            return mapping.findForward("errorPage");
        }
        request.setAttribute("book", book);
        return mapping.findForward("editBook");
    }

}
