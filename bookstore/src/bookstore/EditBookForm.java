package bookstore;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

/**
 * 
 * @author liuyu
 *2007-7-21
 */
public class EditBookForm extends ActionForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String bookName;

    private String writer;

    private String publisher;

    private String intro;

    private double price;

    private short remaining;

    private FormFile picture;

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        this.bookName = null;
        this.writer = null;
        this.publisher = null;
        this.intro = null;
        this.price = 0;
        this.remaining = 0;
        super.reset(mapping, request);
    }

    public ActionErrors validate(ActionMapping mapping,
            HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (this.bookName == null || this.bookName.equals("")
                || this.writer == null || this.writer.equals("")
                || this.publisher == null || this.publisher.equals("")
                || this.intro == null || this.intro.equals("")) {
            errors.add("lackInfo", new ActionMessage("editbook.lackInfo"));
        }
        return errors;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public short getRemaining() {
        return remaining;
    }

    public void setRemaining(short remaining) {
        this.remaining = remaining;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public FormFile getPicture() {
        return picture;
    }

    public void setPicture(FormFile picture) {
        this.picture = picture;
    }

}
