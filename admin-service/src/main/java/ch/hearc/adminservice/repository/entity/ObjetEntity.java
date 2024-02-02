package ch.hearc.adminservice.repository.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "objet")
public class ObjetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String identifiant;

    @ManyToOne
    @JoinColumn(name = "campagne_id")
    private CampagneEntity campagneEntity;

    public ObjetEntity(String nom, String identifiant) {
        this.nom = nom;
        this.identifiant = identifiant;
    }

    public ObjetEntity(String nom, String identifiant, CampagneEntity campagneEntity){
        this(nom, identifiant);
        this.campagneEntity = campagneEntity;
    }

    public ObjetEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public CampagneEntity getCampagneEntity() {
        return campagneEntity;
    }

    public void setCampagneEntity(CampagneEntity campagneEntity) {
        this.campagneEntity = campagneEntity;
    }
}
