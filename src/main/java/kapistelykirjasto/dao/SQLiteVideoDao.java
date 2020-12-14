package kapistelykirjasto.dao;

import kapistelykirjasto.dao.models.VideoModel;
import kapistelykirjasto.util.Result;

import java.sql.*;
import java.util.ArrayList;

public class SQLiteVideoDao implements VideoDao {

    private Connection connection;

    public SQLiteVideoDao(String fileName) {
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:" + fileName);

            Statement statement = this.connection.createStatement();
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS video (id INTEGER PRIMARY KEY AUTOINCREMENT"
                            + ", title TEXT UNIQUE, comment TEXT, url TEXT, duration TEXT, read TIMESTAMP DEFAULT NULL);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public SQLiteVideoDao() throws SQLException {
        this("production.db");
    }

    @Override
    public ArrayList<VideoModel> getVideos() {
        try {
            PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM video");
            ResultSet res = statement.executeQuery();
            ArrayList<VideoModel> videos = new ArrayList<>();
            while (res.next()) {
                videos.add(
                        new VideoModel(res.getInt("id"), res.getString("title"), res.getString("comment"),
                                res.getString("url"), res.getString("duration")));
            }
            statement.close();
            return videos;

        } catch (SQLException e) {
            e.getErrorCode();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Result<String, Integer> createVideo(String title, String comment, String url, String duration) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(
            		"INSERT INTO video(title, comment, url, duration) VALUES(?,?,?,?);",
            		Statement.RETURN_GENERATED_KEYS);
            
            Util.setObjects(statement, title, comment, url, duration);
            statement.executeUpdate();
            
            return Util.getGeneratedKeyFromStatement(statement);
        } catch (SQLException e) {
            return Result.error("Tietokantavirhe (video), " + e.getErrorCode());        
        }
    }

    @Override
    public boolean deleteVideo(int id) {
        try {
            if (!videoExists(id)) {
                return false;
            }
            PreparedStatement statement = this.connection.prepareStatement("DELETE FROM video WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.getErrorCode();
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean editVideo(int id, String title, String comment, String url, String duration) {
        try {
            if (!videoExists(id)) {
                return false;
            }
            PreparedStatement statement = this.connection.prepareStatement(
                    "UPDATE video SET title=?, comment=?, url=?, duration=? " + "WHERE id=?");
            statement.setString(1, title);
            statement.setString(2, comment);
            statement.setString(3, url);
            statement.setString(4, duration);
            statement.setString(5, String.valueOf(id));
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            printSQLException(e);
            return false;
        }
        return true;
    }

    private boolean videoExists(int id) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    "SELECT * FROM video WHERE id=?;"
            );
            statement.setInt(1, id);
            ResultSet res = statement.executeQuery();
            boolean exists = res.next(); // If there is an element available in the result set, next() returns true.
            statement.close();
            return exists;
        } catch (SQLException e) {
            e.getErrorCode();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean markVideoAsRead(int id) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String setTime = String.valueOf(timestamp.getTime());
        try {
            if (!videoExists(id)) {
                return false;
            }
            PreparedStatement statement = this.connection.prepareStatement("UPDATE video SET read=? " + "WHERE id=?");
            statement.setString(1, setTime);
            statement.setString(2, String.valueOf(id));
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            printSQLException(e);
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<VideoModel> getReadVideos() {
        try {
            PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM video WHERE read IS NOT NULL");
            ResultSet res = statement.executeQuery();
            ArrayList<VideoModel> videos = new ArrayList<>();
            while (res.next()) {
                videos.add(
                        new VideoModel(res.getInt("id"), res.getString("title"), res.getString("comment"),
                                res.getString("url"), res.getString("duration")));
            }
            statement.close();
            return videos;

        } catch (SQLException e) {
            e.getErrorCode();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<VideoModel> getNotReadVideos() {
        try {
            PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM video WHERE read IS NULL");
            ResultSet res = statement.executeQuery();
            ArrayList<VideoModel> videos = new ArrayList<>();
            while (res.next()) {
                videos.add(
                        new VideoModel(res.getInt("id"), res.getString("title"), res.getString("comment"),
                                res.getString("url"), res.getString("duration")));
            }
            statement.close();
            return videos;

        } catch (SQLException e) {
            e.getErrorCode();
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public ArrayList<VideoModel> getCourseVideos(int courseId) {
        try {
            PreparedStatement statement = this.connection.prepareStatement("SELECT v.title AS title, v.id AS id, v.comment AS comment, v.url AS url, v.duration AS duration FROM video v, courseVideo c WHERE v.id=c.videoId and c.courseId=?;");
            statement.setInt(1, courseId);
            ResultSet res = statement.executeQuery();
            ArrayList<VideoModel> videos = new ArrayList<>();
            while (res.next()) {
                videos.add(
                        new VideoModel(res.getInt("id"), res.getString("title"), res.getString("comment"),
                                res.getString("url"), res.getString("duration"))
                );
            }
            return videos;
        } catch (SQLException e) {
            e.getErrorCode();
            e.printStackTrace();
        }
        return null;
    }

    public void printSQLException(SQLException exception) {
        exception.getErrorCode();
        exception.printStackTrace();
    }

    @Override
    public void close() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
