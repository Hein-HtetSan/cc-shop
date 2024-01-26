/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Day21;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author acer
 */
public class Manipulation {
    
    // read data
    public static void selectData() throws ClassNotFoundException, SQLException{
        Connection con = Config.getConnection();
        Statement stmt = (Statement) con.createStatement();
        
        ResultSet rs = stmt.executeQuery("SELECT * FROM register");
        while(rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            String dob = rs.getString("dob");
            String className = rs.getString("className");
            
            System.out.println(id + " " + name + " " + age + " " + dob + " " + className);
        }
        con.close();
    }
    
    // insert into register table
    public static void InsertData() throws ClassNotFoundException, SQLException {
       Connection con = Config.getConnection(); // get connection
       PreparedStatement stmt = (PreparedStatement) con.prepareStatement("INSERT INTO register "
               + "(name, age, dob, className) "
               + "VALUES (?,?,?,?)");
       stmt.setString(1, "Mg Mg");
       stmt.setInt(2, 10);
       stmt.setString(3, "11-1-24");
       stmt.setString(4, "Java");
       
       stmt.executeUpdate();
       con.close();
       
       System.out.println("Insert Success");
   }
   
    // update data
   public static void updateData() throws ClassNotFoundException, SQLException{
       Connection con = Config.getConnection();
       PreparedStatement stmt = (PreparedStatement) con.prepareStatement("UPDATE register SET name=? WHERE id=?");
       stmt.setString(1, "Aung Aung");
       stmt.setInt(2, 1);
       stmt.executeUpdate();
       con.close();
       System.out.println("Success");
   }
   
   // delete from table
   public static void deleteData() throws SQLException, ClassNotFoundException{
       Connection con = Config.getConnection();
       PreparedStatement stmt = (PreparedStatement) 
               con.prepareStatement("DELETE FROM register WHERE id=?");
       stmt.setInt(1, 2);
       stmt.executeUpdate();
       con.close();
       System.out.println("Done");
   }
}
