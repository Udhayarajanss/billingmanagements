import java.util.*;
import java.sql.*;
public class Billingmanagement {
	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/billings","root","udhaya");
		int choice,choice2;
		if(con!=null)
			System.out.println("connected");
		else
			System.out.println("not connected");
		while(true)
		{
			System.out.println("1.Add 2.Delete 3.Update 4.view 5.exit");
			choice=sc.nextInt();
			if(choice==1)
			{
				System.out.println("1.Addcustomer 2.AddItem 3.Addbill 4.AddStore 5.AddItemsToStore ");
				choice2=sc.nextInt();
				if(choice2==1)
					Add.addCustomer(con);
				else if(choice2==2)
					Add.addItem(con);
				else if(choice2==3)
					Add.addBill(con);
				else if(choice2==4)
					Add.addStore(con);
				else if(choice2==5)
				{
					System.out.println("enter store id: ");
					int n=sc.nextInt();
					if(Store.storeExists(con, n))
						Store.addItemsToStore(con,n);
					else
						System.out.println("store doesnot exists!");
				}
				else
					System.out.println("invalid choice");
			}
			else if(choice==2)
			{
				int id;
				System.out.println("1.Deletecustomer 2.DeleteItem 3.Deletebill 4.DeleteStore");
				choice2=sc.nextInt();
				if(choice2==1)
				{
					System.out.println("enter customer id to delete: ");
					id=sc.nextInt();
					if(Customer.customerExists(con, id))
						Customer.deleteCustomer(con,id);
					else 
						System.out.println("customer doesnot exists!");
				}
				else if(choice2==2)
				{
					System.out.println("enter item id to delete: ");
					id=sc.nextInt();
					if(Item.itemExist(con, id))
						Item.deleteItem(con, id);
					else
						System.out.println("item doesnot exists!");
				}
				else if(choice2==3)
				{
					System.out.println("enter bill id to delete: ");
					id=sc.nextInt();
					if(Bill.billExists(id, con))
						Bill.deleteBill(id, con);
					else
						System.out.println("bill doesnot exists!");
				}
				else if(choice2==4)
				{
					System.out.println("enter store id to delete :");
					id=sc.nextInt();
					if(Store.storeExists(con, id))
						Store.deleteStore(con, id);
					else
						System.out.println("store doesnot exist ");
				}
				
			}
			else if(choice==3)
			{
				System.out.println("1.updatecustomer 2.updateitem 3.updatestore ");
				choice2=sc.nextInt();
				if(choice2==1)
					Update.updateCustomer(con);
				else if(choice2==2)
					Update.updateItem(con);
				else if(choice2==3)
					Update.updateStore(con);
			}
			else if(choice==4)
			{
				System.out.println("1.viewAllcustomer 2.viewAllitem 3.viewAllbill 4.viewSingleCusotmer  ");
				System.out.println("5.viewSingleItem 6.viewSingleBill 7.showStore  8.viewstorereport");
				choice2=sc.nextInt();
				if(choice2==1)
					Customer.showAllcustomers(con);
				else if(choice2==2)
					Item.showAllItems(con);
				else if(choice2==3)
					Bill.showAllBills(con);
				else if(choice2==4)
				{
					System.out.println("enter customer id: ");
					int id=sc.nextInt();
					if(Customer.customerExists(con,id))
						Customer.showCustomer(con,id);
					else
						System.out.println("customer not found");
				}
				else if(choice2==5)
				{
					System.out.println("enter Item id: ");
					int id=sc.nextInt();
					if(Item.itemExist(con,id))
						Item.showItem(con,id);
					else
						System.out.println("item does not exits!");
				}
				else if(choice2==6)
				{
					System.out.println("enter your bill id: ");
					int bid=sc.nextInt();
					if(Bill.billExists(bid, con))
						Bill.showBill(bid,con);
					else 
						System.out.println("bill doesnot exists!");
				}
				else if(choice2==7)
				{
					System.out.println("Enter store id: ");
					int n=sc.nextInt();
					if(Store.storeExists(con, n))
						Store.showStoreitems(con, n);
					else
						System.out.println("store doesnot exist!");
				}
				else if(choice2==8)
				{
					System.out.println("enter store id");
					int id=sc.nextInt();
					if(Store.storeExists(con, id))
						Store.getCurrentDayReport(con, id);
				}
			}
			else
			{
				con.close();
				sc.close();
				break;
			}
		}
	}
}
