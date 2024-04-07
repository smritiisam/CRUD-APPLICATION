package org.example;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Operations {

    public static void fetchData(ResultSet rs) throws SQLException {
        System.out.println("********************** STUDENT INFORMATION ********************** \n");

        while(rs.next()){
            System.out.println("Learner id: "+ rs.getInt("id"));
            System.out.println("name: "+ rs.getString("name"));
            System.out.println("phone no.: "+ rs.getString("phone"));
            System.out.println("hobbies: "+ rs.getString("hobbies"));
            System.out.println("-----------------------------------------------------------------");

        }
    }
}
