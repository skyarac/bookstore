package bookstore;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;
import org.hibernate.Query;
import org.hibernate.Session;

import bookstore.database.Bookinfo;
import bookstore.database.HibernateUtil;

public class SaveBookAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm srcForm,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionMessages errors = new ActionMessages();
        Bookinfo book = new Bookinfo();
        EditBookForm form = (EditBookForm) srcForm;
        Session dbSession = HibernateUtil.currentSession();
        if (form.getId().intValue() != 0) {
            Query query = dbSession
                    .createQuery("from Bookinfo as b where b.id=:id");
            query.setInteger("id", form.getId().intValue());
            HibernateUtil.beginTransaction();
            List result = query.list();
            HibernateUtil.commitTransaction();
            if (result.size() != 0) {
                book = (Bookinfo) result.get(0);
            }
        }
        book.setBookName(form.getBookName());
        book.setIsbn("111-222-333");
        book.setWriter(form.getWriter());
        book.setIntro(form.getIntro());
        book.setPublisher(form.getPublisher());
        book.setPrice(form.getPrice());
        book.setRemaining(form.getRemaining());
        book.setDate(new Date(System.currentTimeMillis()));
        FormFile file = form.getPicture();
        if (!file.getFileName().equals("")) {
            String[] fileNameSpt = file.getFileName().split("\\.");
            String ext = fileNameSpt[fileNameSpt.length - 1].toLowerCase();
            if (!ext.equals("gif") && !ext.equals("jpg")) {
                errors.add("wrongPic", new ActionMessage("savebook.wrongFile"));
                this.saveErrors(request, errors);
                return mapping.findForward("errorPage");
            }
            InputStream is = file.getInputStream();
            if (is == null) {
                errors
                        .add("wrongPic", new ActionMessage(
                                "savebook.invalidPic"));
                this.saveErrors(request, errors);
                return mapping.findForward("errorPage");
            }
            String fileName = Long.toString(System.currentTimeMillis()) + "."
                    + ext;
            String serverFile = servlet.getServletContext().getRealPath("/") + "/upload/"
                    + fileName;
            OutputStream os = new FileOutputStream(serverFile);
            byte[] buf = new byte[1024];
            while (is.available() != 0) {
                is.read(buf);
                os.write(buf);
            }
            book.setPicture(fileName);
        }

        Session session = HibernateUtil.currentSession();
        HibernateUtil.beginTransaction();
        session.saveOrUpdate(book);
        HibernateUtil.commitTransaction();

        return mapping.findForward("searchBook");
    }
}
