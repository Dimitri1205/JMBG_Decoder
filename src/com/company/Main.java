package com.company;

import java.io.*;

public class Main {

    public static void main(String[] args) {

        Analizer a = new Analizer();

        try {
            BufferedReader buffer = new BufferedReader(new FileReader("ListaJMBG.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("IspravniJMBG.txt"));
            String line = null;
            while ((line = buffer.readLine()) != null) {
                System.out.println(a.prikazi(line));
                if (a.control()) {
                    writer.write(line);
                    writer.newLine();
                }
            }
            buffer.close();
            writer.close();
        } catch (IOException ex) {
            System.out.println("Greska pri citanju fajla");
        }

    }
}
