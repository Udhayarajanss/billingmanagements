import java.util.*;
import java.sql.*;
public class Add {
	static Scanner sc=new Scanner(System.in);
	static void addCustomer(Connection con) throws Exception
	{
		String name,address;
		int phone;
		System.out.println("enter customer name: ");
		name=sc.next();
		System.out.println("enter your phone no: ");
		phone=sc.nextInt();
		System.out.println("enter your address:  ");
		address=sc.next();
		Customer c=new Customer(name,phone,address);
		c.addCustomertodatabase(con);
	}
	static void addItem(Connection con)throws Exception
	{
		String itemname;
		int itemprice,itemstock;
		System.out.println("enter item name: ");
		itemname=sc.next();
		System.out.println("enter item price: ");
		itemprice=sc.nextInt();
		System.out.println("enter item stock: ");
		itemstock=sc.nextInt();
		Item i=new Item(itemname,itemprice,itemstock);
		i.addItemtodatabase(con);
	}
	static void addBill(Connection con) throws Exception
	{
		System.out.println("enter your customer id: ");
		int id=sc.nextInt();
		System.out.println("enter store id: ");
		int sid=sc.nextInt();
		if(Customer.customerExists(con,id))
		{
			//String date;
			//System.out.println("enter date: ");
			//date=sc.next();
			Bill b=new Bill(id,sid);
			b.startAddBillItems(con);
		}
		else
		{
			System.out.println("customer doesnot exists");
		}
	}
	static void addStore(Connection con) throws Exception
	{
		System.out.println("enter store name: ");
		String name=sc.next();
		System.out.println("enter store address: ");
		String address=sc.next();
		Store st=new Store(name,address);
		st.addStoreToDB(con);
	}
}
