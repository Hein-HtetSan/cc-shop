package Models;

import java.time.LocalDateTime;

public class OTP {
	
    public enum OTPStatus {
        ACTIVE,
        USED
    }
	
    private int otpId;
	private String email; // Change userId to String email
    private String otp;
    private LocalDateTime expirationTime;
    private String status;
    
    // Constructor
    public OTP(int otpId, String email, String otp, LocalDateTime expirationTime, String status) {
        this.otpId = otpId;
        this.email = email;
        this.otp = otp;
        this.expirationTime = expirationTime;
        this.status = status;
    }
    
    public int getOtpId() {
		return otpId;
	}

	public void setOtpId(int otpId) {
		this.otpId = otpId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public LocalDateTime getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(LocalDateTime expirationTime) {
		this.expirationTime = expirationTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}





    
}
