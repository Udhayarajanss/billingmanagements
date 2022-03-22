import java.util.*;
import java.sql.*;
public class Storeitem {
	int storeid;
	int itemid;
	String itemname;
	int itemprice;
	int itemstock;
	static String query;
	static Scanner sc=new Scanner(System.in);
	Storeitem(int storeid,int itemid,String itemname,int itemprice,int itemstock)
	{
		this.storeid=storeid;
		this.itemid=itemid;
		this.itemname=itemname;
		this.itemprice=itemprice;
		this.itemstock=itemstock;
	}
	void startAddStoreItem(Connection con,int sid)throws Exception
	{
		if(Store.storeExists(con, sid))
		{
			query="insert into billings.storeitems(storeid,itemid,itemname,itemprice,stock) values (?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setInt(1, storeid);
			ps.setInt(2,itemid);
			ps.setString(3, itemname);
			ps.setInt(4,itemprice);
			ps.setInt(5,itemstock);
			if(ps.executeUpdate()>0)
				System.out.println("items added sucessfully! in store ");
		}
		else
		{
			System.out.println("store doesnot exists!");
		}
	}
	static void updateStoreitemStock(Connection con,int sid)throws  Exception
	{
		int iid,n;
		//System.out.println("enter store id: ");
		//sid=sc.nextInt();
		System.out.println("enter item id: ");
		iid=sc.nextInt();
		System.out.println("how many stocks do you want to add: ");
		n=sc.nextInt();
		query=String.format("update billings.storeitems set stock=stock+%d where (storeid=%d and itemid=%d)",n,sid,iid);
		Statement st=con.createStatement();
		if(st.executeUpdate(query)>0)
			System.out.println("stock added successfully in store! ");
		query=String.format("update billings.items set itemstock=itemstock-%d where itemid=%d",n,iid);
		st.executeUpdate(query);
	}
}
