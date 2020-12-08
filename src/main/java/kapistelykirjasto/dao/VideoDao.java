package kapistelykirjasto.dao;

import kapistelykirjasto.dao.models.VideoModel;
import kapistelykirjasto.util.Result;

import java.util.ArrayList;

public interface VideoDao {

    public ArrayList<VideoModel> getVideos();

    public Result<String, Integer> createVideo(String title, String comment, String url, String duration);

    public boolean deleteVideo(int id);

    public boolean editVideo(int id, String title, String comment, String url, String duration);

    public boolean markVideoAsRead(int id);

    public ArrayList<VideoModel> getReadVideos();

    public ArrayList<VideoModel> getNotReadVideos();
    
    public ArrayList<VideoModel> getCourseVideos(int courseId);

    public void close();
}
