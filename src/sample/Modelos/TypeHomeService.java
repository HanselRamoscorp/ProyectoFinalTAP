package sample.Modelos;

public class TypeHomeService {
    int id_TypeHS;
    String type;

    public TypeHomeService(int id_TypeHS, String type) {
        this.id_TypeHS = id_TypeHS;
        this.type = type;
    }

    public int getId_TypeHS() {
        return id_TypeHS;
    }

    public void setId_TypeHS(int id_TypeHS) {
        this.id_TypeHS = id_TypeHS;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
