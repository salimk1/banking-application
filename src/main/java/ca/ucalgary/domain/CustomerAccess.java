package ca.ucalgary.domain;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

/**
 * This Customer Access is a representation of keeping track of the user login
 */
public class CustomerAccess {

	// Declare Variables 
    private static String salt = "somesalt"; // salt for hash
    private String id;
    private String customerId;
    private String email;
    private String hash;

    /**
     * CustomerAccess constructor
     */
    public CustomerAccess(){

    }

    /**
     *
     * @param customerId id of a user
     * @param email email of a user
     * @param password password of a user
     */
    public CustomerAccess(String customerId, String email, String password){
        this.id = UUID.randomUUID().toString();
        this.customerId = customerId;
        this.email = email;
        /* hashing password */
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String pwd = password + salt;
            byte[] h = digest.digest(pwd.getBytes(StandardCharsets.UTF_8));
            this.hash = Base64.getEncoder().encodeToString(h);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @return a customer id
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * verifies that credentials are correct (for login page)
     * @param email user's email (entered by user)
     * @param password user's password (entered by user)
     * @return true / false depending on if credentials exist in data-store
     */
    public boolean verifyCredentials(String email, String password) {
        String hashed;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String pwd = password + salt;
            byte[] h = digest.digest(pwd.getBytes(StandardCharsets.UTF_8));
            hashed = Base64.getEncoder().encodeToString(h);

            return this.email.equals(email) && this.hash.equals(hashed);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return false;
    }
}
