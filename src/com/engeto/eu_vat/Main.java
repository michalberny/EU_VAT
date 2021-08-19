package com.engeto.eu_vat;


import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;

public class Main {

    private static final String INPUT_FILE = "vat-eu.csv";

    public static void main(String[] args) throws FileNotFoundException {
        VatList list = new VatList();

        File file1 = new File("vat-over-20.txt");
        PrintWriter outputfile = null;
        try {
            outputfile = new PrintWriter(new FileWriter(file1));
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            list.importFromTextFile(INPUT_FILE);
        } catch (VatException e) {
            System.err.println(e.getLocalizedMessage());
        }

        for (int i = 0; i < list.getSize(); i++) {
            System.out.println(list.getNationIndex(i).printInfo());
            outputfile.println(list.getNationIndex(i).printInfo());
        }

        System.out.println("-------------------------");
        System.out.println("Vat over 20:");
        System.out.println("-------------------------");
        outputfile.println("-------------------------");
        outputfile.println("Vat over 20:");
        outputfile.println("-------------------------");

        for (int i = 0; i < list.getSize(); i++) {
            System.out.println(list.getNationIndex(i).printInfoVatOver20());
            outputfile.println(list.getNationIndex(i).printInfoVatOver20());
        }

        System.out.println("=======================");
        System.out.print("Sazba VAT 20% nebo nižší nebo používají speciální sazbu: ");
        outputfile.println("=======================");
        outputfile.print("Sazba VAT 20% nebo nižší nebo používají speciální sazbu: ");
        for (int i = 0; i < list.getSize(); i++) {
            System.out.print(list.getNationIndex(i).printOthers());
            outputfile.print(list.getNationIndex(i).printOthers());
        }

        System.out.println("");
        System.out.print("Zadejte výši DPH: ");
        outputfile.println("");
        outputfile.print("Zadejte výši DPH: ");

        Scanner in = new Scanner(System.in);
        String readString = in.nextLine();
        int a;
        if (readString.equals("")){
            a = 20;
            System.out.println("20");
            outputfile.println("20");
        }
        else a = Integer.parseInt(readString);

        for (int i = 0; i < list.getSize(); i++) {
            System.out.println(list.getNationIndex(i).printVatAboveUserInput(a));
            outputfile.println(list.getNationIndex(i).printVatAboveUserInput(a));
        }

        outputfile.close();
        file1.renameTo(new File("vat-over-" + a + ".txt"));
    }
}
