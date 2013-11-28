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

import bookstore.database.HibernateUtil;
import bookstore.database.Order;
import bookstore.database.Userinfo;


/**
 * 
 * @author Administrator
 *
 */
public class OrderDetailAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm srcForm,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionMessages errors = new ActionMessages();
        if(request.getSession().getAttribute("user") == null) {
            return mapping.findForward("frontPage");
        }
        Userinfo user = (Userinfo) request.getSession().getAttribute("user");
        if (request.getParameter("id") == null
                || request.getParameter("id").equals("")) {
            errors.add("noid", new ActionMessage("orderdetail.noid"));
            this.saveErrors(request, errors);
            return mapping.findForward("errorPage");
        }
        int id = Integer.parseInt(request.getParameter("id"));
        Order order = loadOrderInfo(id, user.getId().intValue(), errors);
        if(order == null) {
            this.saveErrors(request, errors);
            return mapping.findForward("errorPage");
        }
        request.setAttribute("order", order);
        return mapping.findForward("orderdetail");
    }

    private Order loadOrderInfo(int id, int userid, ActionMessages errors) {
        Session dbSession = HibernateUtil.currentSession();
        try {
            HibernateUtil.beginTransaction();
            Query query = dbSession.createQuery(" from Order as o where o.id=:id and userid=:userid")
                .setInteger("id", id)
                .setInteger("userid", userid)
                .setMaxResults(1);
            List result = query.list();
            HibernateUtil.commitTransaction();
            if(result.size() != 0) {
                return (Order) result.get(0);
            }
            errors.add("noOrder", new ActionMessage("orderdetail.noOrder"));
        } catch(Exception e) {
            errors.add("dbError", new ActionMessage("globle.dbError"));
        }
        return null;
    }

}
