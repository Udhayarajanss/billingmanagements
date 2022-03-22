import java.util.*;
import java.sql.*;
public class Update {
	static Scanner sc=new Scanner(System.in);
	static void updateCustomer(Connection con)throws Exception
	{
		System.out.println("enter customer id: ");
		int id=sc.nextInt();
		if(Customer.customerExists(con, id))
		{
			System.out.println("1.updatename 2.updateaddress 3.updatephone");
			int choice;
			choice=sc.nextInt();
			if(choice==1)
				Customer.updateName(con,id);
			else if(choice==2)
				Customer.updateAddress(con,id);
			else if(choice==3)
				Customer.updatePhone(con,id);
		}
		else
		{
			System.out.println("customer does not exists! ");
		}
	}
	static void updateItem(Connection con)throws Exception
	{
		System.out.println("enter item id: ");
		int iid=sc.nextInt();
		if(Item.itemExist(con, iid))
		{
			System.out.println("1.updatename 2.updateprice 3.updatestock");
			int choice;
			choice=sc.nextInt();
			if(choice==1)
				Item.updateName(con,iid);
			else if(choice==2)
				Item.updatePrice(con, iid);
			else if(choice==3)
				Item.updateStock(con,iid);	
		}
		else
		{
			System.out.println("item does not exits!");
		}
	}
	static void updateStore(Connection con)throws Exception
	{
		int sid,ch;
		System.out.println("enter store id: ");
		sid=sc.nextInt();
		if(Store.storeExists(con, sid))
		{
			System.out.println("1.update store name 2.update store Address 3.updatestock");
			ch=sc.nextInt();
			if(ch==1)
				Store.updateName(con, sid);
			else if(ch==2)
				Store.updateAddress(con, sid);
			else if(ch==3)
				Storeitem.updateStoreitemStock(con,sid);
		}
	}
}
