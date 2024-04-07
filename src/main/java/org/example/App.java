package org.example;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.Scanner;



public class App 
{
    public static void main( String[] args )
    {

        String url = "jdbc:mysql://localhost:3306/attempt5";
        String username = "root";
        String pwd = "smritidb03";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver") ;
        }
        catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            Connection conn = DriverManager.getConnection(url, username, pwd);
            System.out.println("connection done");

            Statement st = conn.createStatement();

                Scanner sc = new Scanner(System.in);
                System.out.println("Entre the value\n1:to fetch\n2:to Insert\n3:to delete data:-");
                int ch = sc.nextInt();

                switch (ch) {

                    //fetching data from db:
                    case 1:
                        ResultSet fetch =  st.executeQuery("Select * from learners");
                        Operations.fetchData(fetch);
                        break;


                    //Inserting data into db:
                    case 2:
                        ResultSet MaxIdFetch =  st.executeQuery("Select Max(id) as max_lr_id from learners");
                        boolean next = MaxIdFetch.next();

                    //data stored im ResultSet is known by only its coloumn name, unless u entre the exact coloumn name in the getInt() u wont get any reuslt

                        int max_id =0;
                        if(next){
                            max_id = MaxIdFetch.getInt("max_lr_id");
                            System.out.println("max id is:" + max_id);
                        }
                        max_id ++;
                        System.out.println("entre your name");
                        String insertName = sc.next();
                        System.out.println(max_id);

                        System.out.println("entre your phone no.");
                        String phoneno = sc.next();

                        System.out.println("entre your hobby:");
                        String hobby = sc.next();

                        int rowCount =  st.executeUpdate("insert into learners value("+max_id+" , '"+insertName+"', '"+phoneno+"', '"+hobby+"')");

                        if(rowCount > 0){System.out.println("data inserted");}

                        else{
                            System.out.println("data insertion failed");
                        }
                        break;



                    case 3:
                        System.out.println("entre the id for deleting record:");
                        int id = sc.nextInt();
                        int roww = st.executeUpdate("DELETE from learners where id ="+id);

                        if(roww > 0){
                            System.out.println("data deleted of id "+ id);
                        }
                        else{
                            System.out.println("data deletion failed");
                        }
                        break;

                    default:
                        System.out.println("Entered invalid input");
                }



        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }



    }
}
