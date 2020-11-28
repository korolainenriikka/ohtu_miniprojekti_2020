package kapistelykirjasto.domain;

public class Video extends Entry {
    /* SPEKSIN ESIM
        Otsikko: Merge sort algorithm /entry
        Url: https://www.youtube.com/watch?v=TzeBrDU-JaY /vain video
        Videon kesto: 7:50 min
        Related courses: TKT20001 Tietorakenteet ja algoritmit /entry (myöhemmin)
        Tyyppi: video
        Kommentti: Hyvä selitys merge sortin toiminnasta esimerkin avulla /entry
     */

    private String url;
    private String duration;

    public Video(String title, String comment, String url, String duration) {
        super(title, comment);
        this.url = url;
        this.duration = duration;
    }

    public String getUrl() {
        return url;
    }
    
    public String getDuration() {
        return this.duration;
    }

	@Override
	public Type getType() {
		return Type.VIDEO;
	}
}