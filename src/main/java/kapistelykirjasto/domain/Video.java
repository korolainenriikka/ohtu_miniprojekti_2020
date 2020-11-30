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

	@Override
	public Type getType() {
		return Type.VIDEO;
	}

	@Override
	public String toString() {
		String s = getTitle();
		String duration = getDuration();
		if (!duration.equals("")) {
			s += " (" + duration + ")";
		}
		String url = getUrl();
		if (!url.equals("")) {
			s += "\n\t" + url;
		}
		String comment = getComment();
		if (!comment.equals("")) {
			s += "\n\t" + comment;
		}
		return s;
	}
}
