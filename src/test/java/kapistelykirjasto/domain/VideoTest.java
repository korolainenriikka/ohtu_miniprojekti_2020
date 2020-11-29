package kapistelykirjasto.domain;

import org.junit.Test;

import kapistelykirjasto.dao.models.VideoModel;

import static org.junit.Assert.*;

public class VideoTest {

    @Test
    public void createdVideoHasAllGivenAttributes() {
        VideoModel testVideo = new VideoModel(0, "testVid", "watch this!", "www.videohub.net", "2 min");
        assertEquals(0, testVideo.getId());
        assertEquals("testVid", testVideo.getTitle());
        assertEquals("watch this!", testVideo.getComment());
        assertEquals("www.videohub.net", testVideo.getUrl());
        assertEquals("2 min", testVideo.getDuration());
    }

}
