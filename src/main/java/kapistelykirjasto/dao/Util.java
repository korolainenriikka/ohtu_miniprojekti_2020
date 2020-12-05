package kapistelykirjasto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kapistelykirjasto.util.Result;

public class Util {
	
	public static PreparedStatement setObjects(PreparedStatement stmt, Object... objects) throws SQLException {
		for (int i = 0; i < objects.length; i++) {
			stmt.setObject(i + 1, objects[i]);
		}
		
		return stmt;
	}
	
	public static Result<String, Integer> getGeneratedKeyFromStatement(PreparedStatement stmt) throws SQLException {
		ResultSet rs = stmt.getGeneratedKeys();
		
        if (!rs.next()) {
        	return Result.error("Tietokantavirhe (video)");
        }
        
        return Result.value(rs.getInt(1));
	}
}
