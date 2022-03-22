import java.util.*;
import java.sql.*;
public class Customer {
	private String name;
	private int phone;
	private String address;
	static Scanner sc=new Scanner(System.in);
	Customer(String name,int phone,String address)
	{
		this.name=name;
		this.phone=phone;
		this.address=address;
	}
	void addCustomertodatabase(Connection con) throws Exception
	{
		String query="insert into billings.customers (name,address,phone) values (?,?,?)";
		PreparedStatement st=con.prepareStatement(query);
		st.setString(1,name);
		st.setString(2,address);
		st.setInt(3,phone);
		st.executeUpdate();
		System.out.println("customer created succesfully!");
		st.close();
	}
	static void showAllcustomers(Connection con) throws Exception
	{
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from customers");
		while(rs.next())
		{
			System.out.println(rs.getInt("cid")+" , "+rs.getString("name")+" , "+rs.getString("address")+" , "+rs.getInt("phone"));
		}
		st.close();
	}
	static boolean customerExists(Connection con,int id)throws Exception
	{
		boolean flag=false;
		Statement st=con.createStatement();
		String query;
	    query=String.format("select * from billings.customers where cid=%d",id);
	    ResultSet rs=st.executeQuery(query);
	    while(rs.next())
	    {
	    	flag=true;
	    	break;
	    }
	    if(flag)
	    	return true;
	    else 
	    	return false;
	}
	static void showCustomer(Connection con,int id) throws Exception
	{
		Statement st=con.createStatement();
		String query;
		query=String.format("select * from billings.customers where cid=%d",id);
		ResultSet rs=st.executeQuery(query);
		rs.next();
		System.out.println("customer id: "+rs.getInt("cid"));
		System.out.println("customer name: "+rs.getString("name"));
		System.out.println("customer address: "+rs.getString("address"));
		System.out.println("customer phone: "+rs.getInt("phone"));
	}
    static void deleteCustomer(Connection con,int id)throws Exception
    {
    	String query=String.format("delete from billings.customers where cid=%d",id);
    	Statement st=con.createStatement();
    	st.executeUpdate(query);
    	System.out.println("customer deleted succesfully");
    }
    static void updateName(Connection con,int id)throws Exception
    {
    	System.out.println("enter new name: ");
    	String n=sc.next();
    	String query=String.format("update billings.customers set customers.name='%s' where customers.cid=%d",n,id);
    	Statement st=con.createStatement();
    	if(st.executeUpdate(query)>0)
    		System.out.println("name changed successfully");
    	
    }
    static void updateAddress(Connection con,int id)throws Exception
    {
    	System.out.println("enter new address: ");
    	String adr=sc.next();
    	String query=String.format("update billings.customers set address='%s' where cid=%d",adr,id);
    	Statement st=con.createStatement();
    	if(st.executeUpdate(query)>0)
    		System.out.println("address changed successfully");
    }
    static void updatePhone(Connection con,int id)throws Exception
    {
    	System.out.println("enter new phone number: ");
    	int ph=sc.nextInt();
    	String query=String.format("update billings.customers set phone=%d where cid=%d",ph,id);
    	Statement st=con.createStatement();
    	if(st.executeUpdate(query)>0)
    		System.out.println("address changed successfully");
    }
}
