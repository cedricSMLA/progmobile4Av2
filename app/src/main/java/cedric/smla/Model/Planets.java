package cedric.smla.Model;

public class Planets {
    private String url;
    private String name;
    private String residents;
    private String image;

    public Planets(){}
    public Planets(String name, String residents,
                  String url, String image){
        this.name = name;
        this.residents = residents;
        this.url = url;
        this.image = image;
    }



    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getResidents() {
        return residents;
    }

    public String getImage() {
        return image;
    }
}
