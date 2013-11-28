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
public class PayForm extends ActionForm {
 
	private static final long serialVersionUID = 1L;

	private String address;

    private String postcode;

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        this.address = null;
        this.postcode = null;
    }

    public ActionErrors validate(ActionMapping mapping,
            HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (this.address == null || this.address.equals("")
                || this.postcode == null || this.postcode.equals("")) {
            errors.add("lackInfo", new ActionMessage("viewcart.error"));
        }
        return errors;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

}
