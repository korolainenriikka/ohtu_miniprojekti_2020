package kapistelykirjasto.domain;

import org.junit.Test;

import kapistelykirjasto.dao.models.VideoModel;

import static org.junit.Assert.*;

public class VideoTest {

    @Test
    public void createdVideoHasAllGivenAttributes() {
        VideoModel testVideo = new VideoModel(0, "Crash Course Computer Science Preview",
                "Mielenkiintoinen",
                "https://youtu.be/tpIctyqH29Q",
                "2:44");
        assertEquals(0, testVideo.getId());
        assertEquals("Crash Course Computer Science Preview", testVideo.getTitle());
        assertEquals("Mielenkiintoinen", testVideo.getComment());
        assertEquals("https://youtu.be/tpIctyqH29Q", testVideo.getUrl());
        assertEquals("2:44", testVideo.getDuration());
    }

}
