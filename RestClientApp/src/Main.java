import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws SQLException, IOException, URISyntaxException {
		int limit=200;
		String query="select * from accounts limit";
		Uri obj= new Uri();
		obj.Uri_data(query,limit);
		// TODO Auto-generated method stub

	}

}
