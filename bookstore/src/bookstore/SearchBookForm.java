package bookstore;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Administrator
 *
 */
public class SearchBookForm extends ActionForm {

 
 
	private static final long serialVersionUID = 1L;
	private String name;
    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        this.name = null;
    }

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        return super.validate(mapping, request);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
