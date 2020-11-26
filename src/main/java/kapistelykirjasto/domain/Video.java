package kapistelykirjasto.domain;

public class Video extends Entry {
    /* SPEKSIN ESIM
        Otsikko: Merge sort algorithm /entry
        Url: https://www.youtube.com/watch?v=TzeBrDU-JaY /vain video
        Related courses: TKT20001 Tietorakenteet ja algoritmit /entry (myöhemmin)
        Tyyppi: video
        Kommentti: Hyvä selitys merge sortin toiminnasta esimerkin avulla /entry
     */

    private String url;

    public Video(String title, String comment, String url) {
        super(title, comment);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}