package com.example.tapsee;

/**
 * La classe Data correspond à toutes les données que l'on récupère à travers les différents formuler
 * L'objectif étant d'utiliser ces données à la fin pour proposer des films
 */
public class Data {

    public static int profilChoice;
    public static boolean[] genreChoices;
    public static boolean[] realChoices;
    public static boolean[] actChoices;
    public static boolean newChoice;
    public static boolean[] platChoices;
    public static boolean dureeChoice;
    public static int[] dureeMinMaxChoices;

    /**
     * Permet de remettre les attributs initiaux
     */
    public static void Reset(){
        profilChoice=-1;
        genreChoices=null;
        realChoices = null;
        actChoices=null;
        newChoice=false;
        platChoices=null;
        dureeChoice=false;
        dureeMinMaxChoices=null;
    }

}
