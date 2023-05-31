package com.example.filmutleie;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

class Film{
    public String tittel;
    public double utLeiePris;
    public Person leidAv;

    public Film(String tittel, double utLeiePris, Person leidAv){
        this.tittel=tittel; this.leidAv=leidAv;this.utLeiePris=utLeiePris;
    }
    }
class Person{
    String navn;
    String telefonnr;
    public Person(String navn, String telefonnr){
        this.navn=navn;this.telefonnr=telefonnr;
    }
}
class FilmUtLeie {
    public ArrayList<Film> registrer = new ArrayList<>();

    public FilmUtLeie() {
        Film førsteFilm = new Film("Lær Java", 100, null);
        registrer.add(førsteFilm);
        Film andreFilm = new Film("Lær JavaScript", 200, null);
        registrer.add(andreFilm);
    }

    public String leiUt(String navn, String telefonnr, String tittel) {
        String resultat = " ";
        boolean funnet = false;
        for (Film enFilm : registrer) {
            if (enFilm.tittel.equals(tittel)) {
                funnet = true;
                if (enFilm.leidAv == null) {
                    Person enPerson = new Person(navn, telefonnr);
                    enFilm.leidAv = enPerson;
                    resultat = "Filem er nå leid til " + enPerson.navn;
                } else {
                    resultat = "Vi fant filem men dette er utleid. ";
                }
            }
        }
        if (!funnet) {
            resultat = "Vi har desverre ikke denne filmen";
        }
        return resultat;
    }

    public String leverInn(String tittel) {
        String resultat = " ";
        boolean funnet = false;
        for (Film enFilm : registrer) {
            if (enFilm.tittel.equals(tittel)) {
                funnet = true;
                if (enFilm.leidAv != null) {
                    resultat = "Filmen funnet og er leid til " + enFilm.leidAv.navn+"\n";
                    enFilm.leidAv = null;
                    resultat = "Filmen er nå levert inn. Takk! ";
                } else {
                    resultat = "Filmen funnet men den er ikke leid av enda";
                }
            }
        }
        if (!funnet) {
            resultat = "Filmen fants ikke ";
        }
        return resultat;
    }

    public String toString(){
        String oversikt="";
        oversikt+="Disse er alle filmene som vi har:\n";
        for (Film enFilm: registrer){

            oversikt+=enFilm.tittel+" Pris: "+enFilm.utLeiePris+" ";
            if (enFilm.leidAv!=null){
                oversikt+="Den er leid til: "+enFilm.leidAv.navn+" med telefonnr: "+enFilm.leidAv.telefonnr+"\n";
            } else {
                oversikt+=" Den er ikke leid enda\n ";
            }
            oversikt+="----------------\n";
        }
        return oversikt;
    }
}


public class HelloController {

    FilmUtLeie obkekt = new FilmUtLeie();

    @FXML
    private Label lblOversikt;

    @FXML
    private TextField txtKundeNavn;
    @FXML
    private TextField txtKundeTelefon;
    @FXML
    private TextField txtFilmTittel;

    @FXML
    void leiUt(ActionEvent event) {
        String resultat;
        resultat=obkekt.leiUt(txtKundeNavn.getText(), txtKundeTelefon.getText(), txtFilmTittel.getText());
        lblOversikt.setText(resultat);

    }
    @FXML
    void leverInn(ActionEvent event) {
        String resultat;
        resultat = obkekt.leverInn(txtFilmTittel.getText());
        lblOversikt.setText(resultat);
    }

    @FXML
    void visUtleie(ActionEvent event) {
        lblOversikt.setText(obkekt.toString());
    }

}
