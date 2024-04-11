package ch.hearc.votingservice.remote.models;


import java.util.List;

/**
 * DTO (Data Tansfert Object) pour le retour de la liste des campagnes
 */
public class ListCampagnesResponseBody {

    private List<CampagneBody> campagnes;
    private Integer nbCampagnes;

    private ListCampagnesResponseBody(Integer nbCampagnes, List<CampagneBody> campagnes) {
        this.nbCampagnes = nbCampagnes;
        this.campagnes = campagnes;
    }

    public ListCampagnesResponseBody(){}


    public Integer getNbCampagnes() {
        return nbCampagnes;

    }

    public List<CampagneBody> getCampagnes() {
        return campagnes;
    }

    public void setCampagnes(List<CampagneBody> campagnes) {
        this.campagnes = campagnes;
    }

    public void setNbCampagnes(Integer nbCampagnes) {
        this.nbCampagnes = nbCampagnes;
    }
}
