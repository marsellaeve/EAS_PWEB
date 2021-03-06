/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marsellaeve
 */
public class UserDAO {
    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empmanagement", "root", "");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

    public static int save(UserBean u) {
        int status = 0;
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("insert into employees(name,password,email,gender,position,salary) values(?,?,?,?,?,?)");
            ps.setString(1, u.getName());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getSex());
            ps.setString(5, u.getPosition());
            ps.setInt(6,  u.getSalary());
            status = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

    public static int update(UserBean u) {
        int status = 0;
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("update employees set name=?,password=?,email=?,gender=?,position=?,salary=? where id=?");
            ps.setString(1, u.getName());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getSex());
            ps.setString(5, u.getPosition());         
            ps.setInt(6, u.getSalary());
            ps.setInt(7, u.getId());
            status = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

    public static int delete(UserBean u) {
        int status = 0;
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("delete from employees where id=?");
            ps.setInt(1, u.getId());
            status = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }

        return status;
    }

    public static List<UserBean> getAllRecords() {
        List<UserBean> list = new ArrayList<UserBean>();

        try {
            Connection con = getConnection();
//            if(user!=""){
//                PreparedStatement ps = con.prepareStatement("select * from employees where name=?");    
//            }
//            else{
//            }
            
                PreparedStatement ps = con.prepareStatement("select * from employees");    
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UserBean u = new UserBean();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setPassword(rs.getString("password"));
                u.setEmail(rs.getString("email"));
                u.setSex(rs.getString("gender"));
                u.setPosition(rs.getString("position"));
                u.setSalary(rs.getInt("salary"));
                list.add(u);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    

    public static UserBean getRecordById(int id) {
        UserBean u = null;
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("select * from employees where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                u = new UserBean();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setPassword(rs.getString("password"));
                u.setEmail(rs.getString("email"));
                u.setSex(rs.getString("gender"));
                u.setPosition(rs.getString("position"));
                u.setSalary(rs.getInt("salary"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return u;
    }
    
    public static UserBean getRecordByName(String name) {
        UserBean u = null;
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("select * from employees where name=?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                u = new UserBean();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setPassword(rs.getString("password"));
                u.setEmail(rs.getString("email"));
                u.setSex(rs.getString("gender"));
                u.setPosition(rs.getString("position"));
                u.setSalary(rs.getInt("salary"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return u;
    }

    public static List<UserBean> getRecords(int start, int total) {
        List<UserBean> list = new ArrayList<UserBean>();
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "select * from employees limit " + (start - 1) + "," + total);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UserBean e = new UserBean();
                e.setId(rs.getInt(1));
                e.setName(rs.getString(2));
                e.setSalary(rs.getInt(7));
                list.add(e);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
}
