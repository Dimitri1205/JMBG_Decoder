package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Analizer {

    private String jmbg, region, pol;
    private int dan, mesec, godina;
    private ArrayList<String> regioni = new ArrayList<>();


    public Analizer() {
        try {
            BufferedReader buffer = new BufferedReader(new FileReader("Regioni.txt"));
            String line = null;

            while ((line = buffer.readLine()) != null) {
                regioni.add(line);
            }
            buffer.close();

        } catch (IOException ex) {
            System.out.println("Fajl ne postoji");
        }
    }

    private int starost(int godina) {

        LocalDate date = LocalDate.now();
        int trDan = date.getDayOfMonth();
        int trMesec = date.getMonthValue();
        int trGodina = date.getYear();


        int tempGod = (godina > 19 ? godina + 1000 : godina + 2000);

        return ((trMesec > mesec || trDan >= dan && trMesec == mesec) ? (trGodina - tempGod) : (trGodina - tempGod - 1));
    }

    private String pol (int br) {
        return (br < 500 ? "Muskarac" : "Zena");
    }

    public boolean control () {
        int S = 0;
        int c = 7;

        for (int i = 0; i <=11; i++) {
            S += c *(Integer.parseInt(jmbg.substring(i, i+1)));
            if (i == 6)  c = 7;
            else c--;
        }

        int m = S % 11;
        return (m != 1);
    }


    public String prikazi(String jmbg) {
        this.jmbg = jmbg;
        this.dan = Integer.parseInt(jmbg.substring(0, 2));
        this.mesec = Integer.parseInt(jmbg.substring(2, 4));
        this.godina = Integer.parseInt(jmbg.substring(4, 7));
        this.region = regioni.get(Integer.parseInt(jmbg.substring(7, 8)));
        this.pol = pol(Integer.parseInt(jmbg.substring(9, 12)));

        if (control()) {
            return jmbg + " - " + pol + ", " + starost(godina) + " godina. Region rodjenja: " + region;
        } else {
            return "JMBG je neispravan";
        }
    }


}
