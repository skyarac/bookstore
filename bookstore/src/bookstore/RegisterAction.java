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
import bookstore.database.Userinfo;

/**
 * 
 * @author Administrator
 *
 */
public class RegisterAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm srcForm,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        RegisterForm form = (RegisterForm) srcForm;
        ActionMessages errors = new ActionMessages();
        try {
            if (!checkUserName(form.getUsername())) {
                errors.add("usedUsername", new ActionMessage(
                        "register.usedUsername"));
                this.saveErrors(request, errors);
                return mapping.getInputForward();
            }
            Userinfo user = new Userinfo();
            user.setUsername(form.getUsername());
            user.setPwd(form.getPassword());
            user.setEmail(form.getEmail());
            user.setAddress( form.getAddress());
            user.setPostcode(form.getPostcode());
            Session session = HibernateUtil.currentSession();
            HibernateUtil.beginTransaction();
            session.saveOrUpdate(user);
            HibernateUtil.commitTransaction();
            request.getSession().setAttribute("user", user);
            System.out.println(user.getId());
        } catch (Exception e) {
            errors.add("dbError", new ActionMessage("globle.dbError"));
            this.saveErrors(request, errors);
            return mapping.getInputForward();
        }
        return mapping.findForward("frontPage");
    }

    private boolean checkUserName(String username) {
        Session session = HibernateUtil.currentSession();
        HibernateUtil.beginTransaction();
        Query query = session.createQuery(
                "from Userinfo as u where u.username=:username").setString(
                "username", username).setMaxResults(1);
        List result = query.list();
        HibernateUtil.commitTransaction();
        return result.size() == 0;
    }
}
