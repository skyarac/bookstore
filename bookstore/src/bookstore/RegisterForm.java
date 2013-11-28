package bookstore;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;


/**
 * 
 * @author Administrator
 *
 */
public class RegisterForm extends ActionForm {
 
	private static final long serialVersionUID = 1L;

	public String username;

    public String password;
    
    public String confirm;

    public String email;

    public String address;

    public String postcode;

    public RegisterForm() {

    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        this.username = null;
        this.password = null;
        this.email = null;
        this.address = null;
        this.postcode = null;
    }

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if(this.username == null || this.username.equals("")
                || this.password == null || this.password.equals("")
                || this.email == null || this.email.equals("")) {
            errors.add("InvalidInput", new ActionMessage("register.invalidInput"));
        }
        if(!this.password.equals(this.confirm)) {
            errors.add("InvalidPwd", new ActionMessage("register.invalidPwd"));
        }
        return errors;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

}
