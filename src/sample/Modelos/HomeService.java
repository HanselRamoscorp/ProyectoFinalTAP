package sample.Modelos;

public class HomeService {
    int id_HomeService;
    TypeHomeService id_TypeHS;
    //Company id_company

    public HomeService(int id_HomeService, TypeHomeService id_TypeHS) {
        this.id_HomeService = id_HomeService;
        this.id_TypeHS = id_TypeHS;
    }

    public int getId_HomeService() {
        return id_HomeService;
    }

    public void setId_HomeService(int id_HomeService) {
        this.id_HomeService = id_HomeService;
    }

    public TypeHomeService getId_TypeHS() {
        return id_TypeHS;
    }

    public void setId_TypeHS(TypeHomeService id_TypeHS) {
        this.id_TypeHS = id_TypeHS;
    }
}
