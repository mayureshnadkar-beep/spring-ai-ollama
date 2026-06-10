package com.spring.ollama.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.*;
import java.security.MessageDigest;
import java.util.Base64;

public class VulnerableApp {

    // 🔴 1. Hardcoded Credentials
    private static final String DB_USER = "admin";
    private static final String DB_PASSWORD = "admin123";   // Vulnerability: Hardcoded password
    private static final String API_KEY = "AIzaSyDUMMYKEY123456789";  // Hardcoded API key

    public static void main(String[] args) {

        String userInput = args.length > 0 ? args[0] : "test";

        // 🔴 2. SQL Injection
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/testdb",
                    DB_USER,
                    DB_PASSWORD
            );

            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM users WHERE username = '" + userInput + "'";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println("User: " + rs.getString("username"));
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace(); // 🔴 3. Information Disclosure (Stack Trace Exposure)
        }

        // 🔴 4. Command Injection
        try {
            Runtime.getRuntime().exec("cmd /c echo " + userInput);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 🔴 5. Weak Hashing (MD5)
        try {
            String password = "mypassword";
            MessageDigest md = MessageDigest.getInstance("MD5");  // Weak algorithm
            byte[] hash = md.digest(password.getBytes());
            System.out.println("MD5 Hash: " + Base64.getEncoder().encodeToString(hash));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 🔴 6. Insecure Deserialization
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.ser"));
            Object obj = ois.readObject();   // Unsafe deserialization
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 🔴 7. Debug Mode Enabled
        boolean debug = true;
        if (debug) {
            System.out.println("Debug mode is ON");  // Should not be enabled in production
        }

        // 🔴 8. Hardcoded File Path
        try {
            File file = new File("C:\\sensitive_data.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            System.out.println(br.readLine());
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}