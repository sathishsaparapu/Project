import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ddtek.phoenix.sql.lib.HashMap;
import com.ddtek.phoenix.sql.lib.Iterator;

public class Uri {
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws URISyntaxException 
	 * @throws SQLException
	 * 
	 */
	public static String GetJson(String uri) throws IOException, URISyntaxException
	{
		
		int i=uri.indexOf("fields");
		String s=uri.substring(0, i-1);
		//System.out.println("URI after trim : "+s);
		System.out.println(uri.contains("fields"));
		 URL url = new URL(s);
		 String out = "";
		    HttpURLConnection request = (HttpURLConnection) url.openConnection();
		    request.setRequestProperty("Accept", "application/json");
		    request.setRequestMethod("GET");
		    request.setRequestProperty("Authorization", "Basic SEFOX0lOVEdfVVNFUjpXZWxjb21lMg==");
		   // System.out.println(request.getResponseCode());
		    BufferedReader in = new BufferedReader(  
			        new InputStreamReader(request.getInputStream()));
		    StringBuilder st=new StringBuilder();
		    while((out=in.readLine())!=null)
		    { 
		    	st.append(out);
	
		    }
		   
			return st.toString();
	}
	public void Uri_data(String query,int limit) throws SQLException, IOException, URISyntaxException {
			DriverData Dobj=new DriverData();
			Comparator Cobj=new Comparator();
			String uri="";
			Connection c = DriverManager.getConnection("jdbc:datadirect:ddhybrid://nc-lnx118:8080;databaseName=Demo;encryptionmethod=noEncryption","sathish","sathish");
			System.out.println("Connection Successful");
			java.sql.Statement st1,st=c.createStatement();
			if(limit>100)
			{
				limit=limit-100;
				query+=" "+100;
			}
			else
			{
				query+=" "+limit;
			}
			System.out.println(query);
			ResultSet res,res1=st.executeQuery(query);
			st1=c.createStatement();
			res=st1.executeQuery("select * from INFORMATION_SCHEMA.SYSTEM_REST_EVENTS");
			while (res.next())
			{
				uri=res.getString("URI");
				//System.out.println(uri);
			}
			String str=GetJson(uri);
			
			//System.out.println("===============================");
			
			
			JSONObject jsonobj=new JSONObject(str);
			JSONArray doc=jsonobj.getJSONArray("items");
			System.out.println(doc.toString());
			Vector<String> rs_keys= new Vector<String>();// form result set column names
			Vector<String> rs_keys1= new Vector<String>();
			DatabaseMetaData dmd = c.getMetaData();
			ResultSetMetaData rmdt=res1.getMetaData();
//			ResultSet rsmt = dmd.getColumns(null, "ORACLESALESCLOUD","ACTIVITIES", null);
			int count=rmdt.getColumnCount();
			int i1=1;
			  while(res1.next()) 
				  
			  {  
				
			while(i1!=count+1)
			  {
			rs_keys.add(rmdt.getColumnName(i1));
				
				i1++;
				
				}
			
		

		}
//			while(rsmt.next())
//			{
//				
//				rs_keys.add(rsmt.getString("COLUMN_NAME"));
//				count++;
//			
//				
//				}
			System.out.println(count);

			
			ArrayList<ArrayList<Object>> rs_ls=new ArrayList<ArrayList<Object>>();//to store RS values in list object array
			for (int i = 0; i < doc.length(); i++) {
				rs_ls.add(new ArrayList<Object>());
			}
			JSONObject rs_obj=new JSONObject();
			rs_obj=(JSONObject) doc.get(0);
			java.util.Iterator itr1= rs_obj.keys();
			String str1="";
			/*Storing keys from rest in a vector*/
			while(itr1.hasNext())
			{
				
				rs_keys1.add((String) itr1.next());
			}
			for (int i = 0; i < doc.length(); i++) {
				rs_obj=(JSONObject) doc.get(i);
				int x1=0;
				while(x1!=rs_keys.size())
				{
					rs_ls.get(i).add("");
					x1++;
				}
				
				for (int j = 0; j < rs_keys1.size(); j++) {
					int k=rs_keys.indexOf(rs_keys1.get(j).toUpperCase());
					String temp=rs_keys1.get(j);
					Object rs_vobj=rs_obj.get(temp);
					rs_ls.get(i).set(k,rs_vobj);
					
						}
			}
			/***********************************************************/
		
			//System.out.println(rs_keys.contains());
			Vector<String> no_cols =new Vector<String>();
			for (int i = 0; i < rs_keys.size(); i++) {
				//System.out.print(rs_keys.get(i)+" :\t ");
				for (int j = 0; j < doc.length(); j++) {
					if(rs_ls.get(j).get(i).equals("") && !no_cols.contains(rs_keys.get(i)))
						no_cols.add(rs_keys.get(i));
					//System.out.print("->"+rs_ls.get(j).get(i));				
				}
				//System.out.println();
			}
			//System.out.println(rs_ls.get(0).get(92));
			Dobj.GetDriverData(rs_ls,query);
			//Cobj.SetUriData(rs_ls);
			//System.out.println("URL not there in REST  "+ no_cols.toString());
		}

	}


