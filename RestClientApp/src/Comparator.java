import java.sql.Array;
import java.util.ArrayList;
import java.util.Vector;

public class Comparator {
	ArrayList<ArrayList<Object>> dr_ls;
	ArrayList<ArrayList<Object>> ur_ls;
	Vector<String> keys;
public Comparator()
{
	dr_ls=new ArrayList<ArrayList<Object>>();//to
	ur_ls=new ArrayList<ArrayList<Object>>();//to
	keys=new Vector<String>();
}
public void SetUriData(ArrayList<ArrayList<Object>> Arr2)
{
	ur_ls=Arr2;
}
public void SetDriverData(ArrayList<ArrayList<Object>> Arr1)
{
	dr_ls=Arr1;
}
public void SetKeys(Vector<String> rs_keys) {
	keys=rs_keys;
	
}
public void Compare()
{
	System.out.println("uri size "+ur_ls.size()+" res size"+dr_ls.size()+"keys size "+keys.size());
	int i=0,k;
	int count=0;
	Vector<String> mis_mat_col=new Vector<String>();
	for (int j = 0; j < dr_ls.size(); j++) {
		i=0;
		k=ur_ls.get(j).size();
		while(i!=k)
		{
			//if(ur_ls.get(j).get(i).equals(dr_ls.get(j).get(i).equals(true)))
			if(ur_ls.get(j).get(i)!=null && dr_ls.get(j).get(i)!=null)
			{
				if(ur_ls.get(j).get(i).toString().equals(dr_ls.get(j).get(i).toString()))
				{
					
				}
				//System.out.println(ur_ls.get(j).get(i).toString()+"->"+dr_ls.get(j).get(i).toString());	
				else
				{
					if(!mis_mat_col.contains(keys.get(i)))
						mis_mat_col.add(keys.get(i));
					System.out.println(keys.get(i));
					System.out.println(ur_ls.get(j).get(i).toString()+"->"+dr_ls.get(j).get(i).toString());
					count++;
				}
				}
			else
				if(ur_ls.get(j).get(i).equals(dr_ls.get(j).get(i)))
				{
					
				}
				else
				{
					System.out.println(keys.get(i));
					System.out.println(ur_ls.get(j).get(i)+"->"+dr_ls.get(j).get(i));
					count++;
				}
					
			i++;
		}
	}
	
	System.out.println("miss match count "+count);
	System.out.println(mis_mat_col.toString());
}

}