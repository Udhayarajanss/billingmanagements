import java.util.*;
import java.sql.*;
public class Item 
{
	private String itemname;
	private int price;
	private int itemstock;
	static Scanner sc=new Scanner(System.in);
	static String query;
	Item(String itemname,int price,int itemstock)
	{
		this.itemname=itemname;
		this.price=price;
		this.itemstock=itemstock;
	}
	void addItemtodatabase(Connection con) throws Exception
	{
		query="insert into billings.items (itemname,itemprice,itemstock) values (?,?,?)";
		PreparedStatement st=con.prepareStatement(query);
		st.setString(1,itemname);
		st.setInt(2,price);
		st.setInt(3,itemstock);
		if(st.executeUpdate()>0)
			System.out.println("item added sucessfully!");
		st.close();
	}
	static void showAllItems(Connection con) throws Exception
	{
		System.out.println("ALL ITEM DETAILS ARE");
		query="select * from billings.items";
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		while(rs.next())
		{
			System.out.println(rs.getInt("itemid")+" , "+rs.getString("itemname")+" , "+rs.getInt("itemprice")+" , "+rs.getInt("itemstock"));
		}
		st.close();
	}
	static boolean itemExist(Connection con,int id) throws Exception
	{
		boolean flag=false;
		Statement st=con.createStatement();
	    query=String.format("select * from billings.items where itemid=%d",id);
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
	static void showItem(Connection con,int id) throws Exception
	{
		Statement st=con.createStatement();
		query=String.format("select * from billings.items where  itemid = %d",id);
		ResultSet rs=st.executeQuery(query);
		rs.next();
		System.out.println("item id: "+rs.getInt("itemid"));
		System.out.println("item name: "+rs.getString("itemname"));
		System.out.println("item price : "+rs.getInt("itemprice"));
		System.out.println("item stock : "+rs.getInt("itemstock"));
	}
	static void deleteItem(Connection con,int id) throws Exception
	{
		query=String.format("delete from billings.items where itemid=%d",id);
		Statement st=con.createStatement();
		if(st.executeUpdate(query)>0)
			System.out.println("item deleted sucessfully");
	}
	static void updateName(Connection con,int id)throws Exception
	{
		System.out.println("enter new name of item: ");
		String name;
		name=sc.next();
		query=String.format("update billings.items set itemname='%s' where itemid=%d ",name,id);
		Statement st=con.createStatement();
    	if(st.executeUpdate(query)>0)
    		System.out.println("itemname name changed successfully");
	}
	static void updatePrice(Connection con,int id)throws Exception
	{
		System.out.println("enter new price: ");
		int p=sc.nextInt();
		query=String.format("update billings.items set itemprice=%d where itemid=%d", p,id);
		Statement st=con.createStatement();
    	if(st.executeUpdate(query)>0)
    		System.out.println(" Itemprice changed successfully");
	}
	static void updateStock(Connection con,int id)throws Exception
	{
		System.out.println("enter new stock number: ");
		int n=sc.nextInt();
		query=String.format("update billings.items set itemstock=%d where itemid=%d",n,id);
		Statement st=con.createStatement();
    	if(st.executeUpdate(query)>0)
    		System.out.println("Itemstock changed successfully");
	}
}
