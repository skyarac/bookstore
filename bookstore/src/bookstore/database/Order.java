package bookstore.database;

import java.util.Date;


/**
 * Order generated by MyEclipse - Hibernate Tools
 */

public class Order  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
     private Bookinfo bookinfo;
     private Userinfo userinfo;
     private short number;
     private String address;
     private String postcode;
     private Date orderdate;
     private short status;


    // Constructors

    /** default constructor */
    public Order() {
    }

    
    /** full constructor */
    public Order(Bookinfo bookinfo, Userinfo userinfo, short number, String address, String postcode, Date orderdate, short status) {
        this.bookinfo = bookinfo;
        this.userinfo = userinfo;
        this.number = number;
        this.address = address;
        this.postcode = postcode;
        this.orderdate = orderdate;
        this.status = status;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Bookinfo getBookinfo() {
        return this.bookinfo;
    }
    
    public void setBookinfo(Bookinfo bookinfo) {
        this.bookinfo = bookinfo;
    }

    public Userinfo getUserinfo() {
        return this.userinfo;
    }
    
    public void setUserinfo(Userinfo userinfo) {
        this.userinfo = userinfo;
    }

    public short getNumber() {
        return this.number;
    }
    
    public void setNumber(short number) {
        this.number = number;
    }

    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return this.postcode;
    }
    
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Date getOrderdate() {
        return this.orderdate;
    }
    
    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }
   
}