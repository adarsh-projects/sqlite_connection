package sqlite1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class user_interface {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, NumberFormatException, IOException {
		sqlite_connection sc = new sqlite_connection();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while( true ) {
			// infinity loop
			System.out.println("1. Add Data");
			System.out.println("2. Display All Data");
			System.out.println("3. Display Data by Actor Name");
			System.out.println("press any number to exit");
			
			int n = Integer.parseInt(br.readLine());
			
			if(n == 1) {
				System.out.println("\nEnter data comma separated:\n");
				String s [] = br.readLine().split(",");
				sc.addData(s);
			
			}else if(n == 2){
				ResultSet rs = sc.displaMoviesDetails();
				if( rs == null ) {
					System.out.println("Your Database is Empty!");
				}else {
					while( rs.next() ) {
						System.out.println(" Movie Name: "+rs.getString("movies_name")+"\n Actor Name: "+rs.getString("actor_name")+"\n Actress Name "+rs.getString("actress_name")
						+"\n Director Name: "+rs.getString("director_name")+"\n Year Of Realease: "+rs.getString("year_of_release"));
						System.out.println("\n");
					}
				}
			
			}else if(n == 3){
				
				System.out.println("\nEnter Actor Name: \n");
				String actor_name = br.readLine();
				ResultSet rs = sc.displaMoviesDetails(actor_name);
				if( rs == null ) {
					System.out.println("Your Database does not have any movie of "+actor_name);
				}else {
					while( rs.next() ) {
						System.out.println(" Movie Name: "+rs.getString("movies_name")+"\n Actor Name: "+rs.getString("actor_name")+"\n Actress Name "+rs.getString("actress_name")
						+"\n Director Name: "+rs.getString("director_name")+"\n Year Of Realease: "+rs.getString("year_of_release"));
						System.out.println("\n");
					}
				}
				
			}/*else if(n == 4) {
				sc.deleteTable();
			}*/else{
				
				System.out.print("exit");
				break;
				
			}
		}
	}
}
