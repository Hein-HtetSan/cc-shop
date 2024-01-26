/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Day21;

import java.sql.SQLException;

/**
 *
 * @author acer
 */
public class main {
    public static void main(String args[]) throws ClassNotFoundException, SQLException{
        Manipulation.selectData();
        Manipulation.InsertData();
        Manipulation.updateData();
        Manipulation.deleteData();
    }
}
