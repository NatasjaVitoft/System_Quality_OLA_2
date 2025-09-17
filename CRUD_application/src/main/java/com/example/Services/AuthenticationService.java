package com.example.Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.DB.db_con;

public class AuthenticationService {
    

// AUTHENTICATION METHOD FOR POSTMAN

    public Long authenticateUser (io.javalin.http.Context ctx) throws SQLException {
        String authHeader = ctx.header("Authorization");
        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            ctx.status(401).json("{\"error\":\"Missing or invalid Authorization header\"}");
            return null;
        }

        String base64Credentials = authHeader.substring("Basic ".length());
        String credentials = new String(java.util.Base64.getDecoder().decode(base64Credentials));
        String[] values = credentials.split(":", 2);
        String username = values[0];
        String password = values[1];

        try(Connection conn = db_con.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT id, password FROM user WHERE username=?")) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next() && rs.getString("password").equals(password)) {
                return rs.getLong("id");
            } else {
                ctx.status(401).json("{\"error\":\"Invalid credentials\"}");
                return null;
            }
        }
    }
}
