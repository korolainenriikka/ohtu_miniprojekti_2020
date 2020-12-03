package kapistelykirjasto.dao;

import kapistelykirjasto.dao.models.VideoModel;

import java.util.ArrayList;

public interface VideoDao {

    public ArrayList<VideoModel> getVideos();

    public boolean createVideo(String title, String comment, String url, String duration);

    public boolean deleteVideo(int id);

    public boolean editVideo(int id, String title, String comment, String url, String duration);

    public boolean markVideoAsRead(int id);

    public ArrayList<VideoModel> getReadVideos();

    public ArrayList<VideoModel> getNotReadVideos();

    public void close();
}
