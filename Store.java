import java.util.*;
import java.sql.*;
public class Store {
	String sname;
	String address;
	static String query;
	static Scanner sc=new Scanner(System.in);
	Store(String sname,String address)
	{
		this.sname=sname;
		this.address=address;
	}
	void addStoreToDB(Connection con) throws Exception
	{
		query="insert into stores(storename,storeaddress) values (?,?)";
		PreparedStatement st=con.prepareStatement(query);
		st.setString(1, sname);
		st.setString(2, address);
		if(st.executeUpdate()>0)
			System.out.println("store added succesfully ");
	}
	static void addItemsToStore(Connection con, int sid) throws Exception
	{
		Statement st=con.createStatement();
		ResultSet rs;
		int stock;
		//query=String.format("select storename from , null)
		System.out.println("how many kinds items do you want to add in stores");
		int n=sc.nextInt();
		for(int i=0;i<n;i++)
		{
			System.out.println("enter item id: ");
			int id=sc.nextInt();
			query=String.format("select items.itemname,items.itemprice from billings.items where items.itemid=%d",id);
			rs=st.executeQuery(query);
			rs.next();
			String in=rs.getString("itemname");
			int price=rs.getInt("itemprice");
			if(Item.itemExist(con, id))
			{
				System.out.println("how many stock of "+in+" do you want");
				stock=sc.nextInt();
				query=String.format("update billings.items set itemstock=itemstock-%d where itemid=%d",stock,id);
				st.executeUpdate(query);
				Storeitem si=new Storeitem(sid,id,in,price,stock);
				si.startAddStoreItem(con,sid);
			
			}
					
		}
				
	}
	
	static boolean storeExists(Connection con,int id)throws Exception
	{
		boolean flag=false;
		Statement st=con.createStatement();
		String query;
	    query=String.format("select storeid from billings.stores where storeid=%d",id);
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
	static void updateName(Connection con,int sid) throws Exception
	{
		System.out.println("enter new store name: ");
		String name=sc.next();
		query=String.format("update billings.stores set storename='%s' where storeid=%d",name,sid);
		Statement st=con.createStatement();
		if(st.executeUpdate(query)>0)
			System.out.println("store name changed sucessfully");
	}
	static void updateAddress(Connection con,int sid)throws Exception
	{
		System.out.println("enter updated address: ");
		String name=sc.next();
		query=String.format("update billings.stores set storeaddress='%s' where storeid=%d",name,sid);
		Statement st=con.createStatement();
		if(st.executeUpdate(query)>0)
			System.out.println("store deleted  changed sucessfully");
	}
	static void deleteStore(Connection con,int sid) throws Exception
	{
		query=String.format("delete from billings.stores where storeid=%d", sid);
		Statement st=con.createStatement();
		if(st.executeUpdate(query)>0)
			System.out.println("store name changed sucessfully");
	}
	static void showStoreitems(Connection con,int sid)throws Exception
	{
		ResultSet rs;
		System.out.println("store "+sid+"  items");
		Statement st=con.createStatement();
		query=String.format("select  stores.storeid,storeitems.itemname,storeitems.itemprice, storeitems.stock from stores inner join storeitems on stores.storeid=storeitems.storeid and stores.storeid=%d",sid);
		rs=st.executeQuery(query);
		System.out.println("storeid:   itemname:    itemprice:  stock ");
		while(rs.next())
		{
			System.out.println(rs.getInt("stores.storeid")+"             ,"+rs.getString("storeitems.itemname")+"   ,"+rs.getInt("storeitems.itemprice")+"   ,"+rs.getInt("storeitems.stock"));
		}
	}
	static void getCurrentDayReport(Connection con,int sid)throws Exception
	{
		int bid;
		query="select billid from billings.bills where storeid=? and date=CURRENT_DATE";
		PreparedStatement ps=con.prepareStatement(query);
		ps.setInt(1,sid);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			bid=rs.getInt("billid");
			Bill.showBill(bid,con);
		}
		query="select sum(amount) from billings.bills where storeid=? and date=CURRENT_DATE";
		ps=con.prepareStatement(query);
		ps.setInt(1, sid);
		rs=ps.executeQuery();
		rs.next();
		int n=rs.getInt("sum(amount)");
		System.out.println("total revenue of store "+sid+" today="+n);
		//query="select itemname,itemid,quantity,count(itemid),sum(value) from billings.billitems group by itemid having storeid=1"
	}
}
