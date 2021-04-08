package it.planetarium.gammatrianguli.main;
import it.planetarium.gammatrianguli.classes.*;
import it.unibs.fp.mylib.InputDati;

public class MainPlanetarium {
    private static boolean exit = false;
    private static final int MIN_CHOICE_VALUE = 0;
    private static final int MAX_CHOICE_VALUE = 7;

    public static void main(String[] args) {
        Sistema sistema = MenuPlanetarium.creaSistema();
        while (!exit) {
            MenuPlanetarium.menuHeader(sistema);
            int choice = InputDati.leggiIntero("Si prega di effettuare una scelta: ", MIN_CHOICE_VALUE, MAX_CHOICE_VALUE);
            menuSwitch(sistema ,choice);
        }
    }

    public static void menuSwitch(Sistema sistema, int choice) {
        switch (choice) {
            case 0:
                MenuPlanetarium.exit();
                exit = true;
                break;
            case 1:
                MenuPlanetarium.aggiungiPianeta(sistema);
                break;
            case 2:
                MenuPlanetarium.rimuoviPianeta(sistema);
                break;
            case 3:
                MenuPlanetarium.aggiungiSatellite(sistema);
                break;
            case 4:
                MenuPlanetarium.rimuoviSatellite(sistema);
                break;
            case 5:
                MenuPlanetarium.cercaSatelliti(sistema);
                break;
            case 6:
                MenuPlanetarium.cercaCorpoCeleste(sistema);
                break;
            case 7:
                MenuPlanetarium.calcolaCentroMassa(sistema);
                exit = true;
                break;
            default:
                break;
        }
    }

}
