package kapistelykirjasto.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class VideoTest {

    @Test
    public void createdVideoHasAllGivenAttributes() {
        Video testVideo = new Video("testVid", "watch this!", "www.videohub.net", "2 min");
        assertEquals("testVid", testVideo.getTitle());
        assertEquals("watch this!", testVideo.getComment());
        assertEquals("www.videohub.net", testVideo.getUrl());
        assertEquals("2 min", testVideo.getDuration());
    }

}
