import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

public class DriverData {
	public void GetDriverData(ArrayList<ArrayList<Object>> Arr2,String query) throws SQLException
	{

		Connection c = DriverManager
				.getConnection(
						"jdbc:datadirect:oraclesalescloud://ebqjdev-test.crm.us1.oraclecloud.com;"
								+ "refreshschema=false;recordrestevents=true;WSFetchsize=100;DATABASENAME=TESTING ;ConfigOptions=()",
						"HAN_INTG_USER", "Welcome2");
		System.out.println("Connection Successful");
		Statement st1=c.createStatement(),st = c.createStatement();
		ArrayList<ArrayList<Object>> rs_ls=new ArrayList<ArrayList<Object>>();//to
		Comparator Cobj=new Comparator();
		Vector<String> rs_keys= new Vector<String>();
//		String query="SELECT * FROM ACCOUNTS LIMIT 4";
		  ResultSet res1,res =
		  st.executeQuery(query); 
		  ResultSetMetaData rmdt=res.getMetaData();
		  int count=rmdt.getColumnCount();
		 // System.out.println(rmdt.getColumnLabel(188));
		  System.out.println("column count==="+count);
		  int i=1,k=0,x=0;
		  //String str="[{";
		  
		  while(res.next()) 
			  
		  { 
			  rs_ls.add(new ArrayList<>());
			  i=1;
		while(i!=count+1)
		  {
			if(x<=0)
			rs_keys.add(rmdt.getColumnName(i));
			
			//System.out.print(rmdt.getColumnName(i)+"\t");
			rs_ls.get(k).add(res.getString(i));
			//System.out.println(res.getString(i));
			i++;
			
			}
		x++;
		k++;
	

	}
//		  System.out.println(rs_ls.get(0).get(91));
//		 for (int j = 0; j < rs_ls.size(); j++) {
//			for (int j2 = 0; j2 < rs_ls.get(j).size(); j2++) {
//				System.out.print("->"+rs_ls.get(j).get(j2));
//				
//			}
//			System.out.println();
//		}
		Cobj.SetDriverData(rs_ls);
		Cobj.SetUriData(Arr2);
		Cobj.SetKeys(rs_keys);
		 Cobj.Compare();
		 // System.out.println(rmdt.getColumnName(188));
	}

}
