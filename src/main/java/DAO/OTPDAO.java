package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import Models.OTP;

public class OTPDAO {
	
	Connection conn = null;
	
	public OTPDAO() throws ClassNotFoundException, SQLException {
		conn = Config.config.getConnections();
	}
	
    // Insert OTP record into the database
    public boolean insertOTP(String email, String otp, LocalDateTime expirationTime) {
    	boolean flag = false;
        PreparedStatement stmt = null;
        try {
            String sql = "INSERT INTO otp (User_ID, OTP, Expiration_Time, Status) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, otp);
            stmt.setObject(3, expirationTime);
            stmt.setString(4, "ACTIVE");
            int inserted = stmt.executeUpdate();
            if(inserted > 0) flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    // Update OTP status to 'USED' after OTP is used
    public void updateOTPStatus(int otpId) {
        PreparedStatement stmt = null;
        try {
            String sql = "UPDATE otp SET Status = 'USED' WHERE OTP_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, otpId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete expired OTP records from the database
    public void deleteExpiredOTP(LocalDateTime currentTime) {
        PreparedStatement stmt = null;
        try {
            String sql = "DELETE FROM otp WHERE Expiration_Time < ?";
            stmt = conn.prepareStatement(sql);
            stmt.setObject(1, currentTime);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get OTP by email
    public OTP getOTPByEmail(String email) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        OTP otp = null;
        try {
            String sql = "SELECT * FROM otp WHERE User_ID = ? AND Status = 'ACTIVE' ORDER BY Expiration_Time DESC LIMIT 1";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int otpId = rs.getInt("OTP_ID");
                String otpCode = rs.getString("OTP");
                LocalDateTime expirationTime = rs.getObject("Expiration_Time", LocalDateTime.class);
                String status = rs.getString("Status");
                otp = new OTP(otpId, email, otpCode, expirationTime, status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return otp;
    }
    
 // Method to update OTP in the database
    public void updateOTPInDatabase(int otpId, String otp, LocalDateTime expirationTime) throws SQLException {
        PreparedStatement stmt = null;
        try {
            String sql = "UPDATE otp SET OTP = ?, Expiration_Time = ? WHERE OTP_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, otp);
            stmt.setObject(2, expirationTime);
            stmt.setInt(3, otpId);
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
