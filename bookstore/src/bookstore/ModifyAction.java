package bookstore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.hibernate.Session;

import bookstore.database.HibernateUtil;
import bookstore.database.Userinfo;



/**
 * 
 * @author Administrator
 *
 */
public class ModifyAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm srcForm,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        Userinfo user = (Userinfo) session.getAttribute("user");
        RegisterForm form = (RegisterForm) srcForm;
        if (form.getPassword() != null && !form.getPassword().equals("")
                && form.getPassword().equals(form.getConfirm())) {
            user.setPwd(form.getPassword());
        }
        if (form.getEmail() != null && !form.getEmail().equals("")) {
            user.setEmail(form.getEmail());
        }
        if (form.getAddress() != null && !form.getAddress().equals("")) {
        	//new String( form.getAddress().getBytes("ISO-8859-1"), "GB2312")
            user.setAddress(form.getAddress());
        }
        if (form.getPostcode() != null && !form.getPostcode().equals("")) {
            user.setPostcode(form.getPostcode());
        }

        Session dbSession = HibernateUtil.currentSession();
        ActionMessages errors = new ActionMessages();
        try {
            HibernateUtil.beginTransaction();
            dbSession.update(user);
            HibernateUtil.commitTransaction();
        } catch (Exception e) {
            errors.add("dbError", new ActionMessage("globle.dbError"));
            this.saveErrors(request, errors);
            return mapping.getInputForward();
        }
        return mapping.findForward("frontPage");
    }

}
