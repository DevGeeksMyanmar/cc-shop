package Config;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class Hash {

    // Hash a password
	public static String hashPassword(String plainTextPassword) {
	    return BCrypt.withDefaults().hashToString(12, plainTextPassword.toCharArray());
	}

    // Verify a hashed password
	public static boolean verifyPassword(String plainTextPassword, String hashedPassword) {
	    BCrypt.Result result = BCrypt.verifyer().verify(plainTextPassword.toCharArray(), hashedPassword);
	    return result.verified;
	}  
}