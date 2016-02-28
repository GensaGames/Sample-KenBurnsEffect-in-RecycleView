package example.genka.recycleviewkenburnview.model;


/**
 * Created by Genka on 06.10.2015.
 */
public class PersonCard {

    private String shortDescrip;
    private String imageUrl;
    private String title;

    public PersonCard (String _title, String _shortDescrip, String _imageUrl) {
        shortDescrip = _shortDescrip;
        title = _title;
        imageUrl = _imageUrl;
    }

    public String getShortDescrip() {
        return shortDescrip;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

}
