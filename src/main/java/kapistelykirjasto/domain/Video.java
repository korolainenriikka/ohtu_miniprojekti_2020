package kapistelykirjasto.domain;

import kapistelykirjasto.dao.models.VideoModel;

public class Video extends Entry {

	private VideoModel model;
	
	public Video(VideoModel model) {
		this.model = model;
	}

	public int getId() {
		return model.getId();
	}

	public String getTitle() {
		return model.getTitle();
	}

	public String getComment() {
		return model.getComment();
	}

	public String getUrl() {
		return model.getUrl();
	}

	public String getDuration() {
		return model.getDuration();
	}

	
}
