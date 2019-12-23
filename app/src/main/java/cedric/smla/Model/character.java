package cedric.smla.Model;

public class character {
    private String name;
    private String species;
    private String status;
    private String series;
    private String gender;
    private String url;
    private String image;
    private String origin_planet;

    public character(String name, String species, String status, String series,
                     String gender, String url, String image, String origin_planet){
        this.gender = gender;
        this.image = image;
        this.name = name;
        this.origin_planet = origin_planet;
        this.series = series;
        this.species = species;
        this.status = status;
        this.url = url;
    }

    public character(){}

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public String getStatus() {
        return status;
    }

    public String getSeries() {
        return series;
    }

    public String getGender() {
        return gender;
    }

    public String getUrl() {
        return url;
    }

    public String getImage() {
        return image;
    }

    public String getOrigin_planet() {
        return origin_planet;
    }
}
