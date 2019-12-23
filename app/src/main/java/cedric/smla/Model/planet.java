package cedric.smla.Model;

public class planet {
    private String url;
    private String name;
    private String residents;
    private String image;

    public planet(String name, String residents,
                  String url, String image){
        this.name = name;
        this.residents = residents;
        this.url = url;
        this.image = image;
    }

    public planet(){}

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
