package bookstore;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class LoginForm extends ActionForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// ��Ӧindex.jsp�е��û���
    private String userName;

    // ��Ӧindex.jsp�е��û�����
    private String password;

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        this.userName = null;
        this.password = null;
    }

    public ActionErrors validate(ActionMapping mapping,
            HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (userName == null || userName.equals("") || password == null
                || password.equals("")) {
            errors.add("inputErrors", new ActionMessage("index.invalidInput"));
        }
        return errors;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
