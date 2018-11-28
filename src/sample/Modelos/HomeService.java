package sample.Modelos;

public class HomeService {
    int id_HomeService;
    TypeHomeService id_TypeHS;
    Company id_company;
    PlanHS id_planHS;

    public HomeService(int id_HomeService, TypeHomeService id_TypeHS, Company id_company, PlanHS id_planHS) {
        this.id_HomeService = id_HomeService;
        this.id_TypeHS = id_TypeHS;
        this.id_company = id_company;
        this.id_planHS = id_planHS;
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

    public Company getId_company() {
        return id_company;
    }

    public void setId_company(Company id_company) {
        this.id_company = id_company;
    }

    public PlanHS getId_planHS() {
        return id_planHS;
    }

    public void setId_planHS(PlanHS id_planHS) {
        this.id_planHS = id_planHS;
    }
}
