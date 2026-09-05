package skybook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    // ==============================
    // REGISTER USER
    // ==============================
    public static boolean registerUser(
            String name,
            String email,
            String phone,
            String password) {

        String sql = "INSERT INTO users (name, email, phone, password) VALUES (?, ?, ?, ?)";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, password);

            ps.executeUpdate();

            ps.close();
            con.close();

            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }


    // ==============================
    // LOGIN USER
    // ==============================
    public static boolean loginUser(
            String email,
            String password) {

        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            boolean loginSuccessful = rs.next();

            rs.close();
            ps.close();
            con.close();

            return loginSuccessful;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }


    // ==============================
    // GET USER PROFILE
    // ==============================
    public static String[] getUserProfile(String email) {

        String sql = "SELECT name, email, phone, password FROM users WHERE email = ?";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String[] user = {
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("password")
                };

                rs.close();
                ps.close();
                con.close();

                return user;
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }


    // ==============================
    // UPDATE USER PROFILE
    // ==============================
    public static boolean updateProfile(
            String email,
            String name,
            String phone,
            String password) {

        String sql = "UPDATE users SET name = ?, phone = ?, password = ? WHERE email = ?";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setString(3, password);
            ps.setString(4, email);

            int rows = ps.executeUpdate();

            ps.close();
            con.close();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }
}