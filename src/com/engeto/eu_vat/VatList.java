package com.engeto.eu_vat;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.util.*;

public class VatList {

    public static final String DELIMITER = "\t";

    private List<NationVat> list = new ArrayList();

    public void addNation (NationVat nation) {list.add(nation);}

    public NationVat getNationIndex (int index) {return list.get(index);}

    public void removeNation (int index) {list.remove(index);}

    public int getSize() {return list.size();}

    public void importFromTextFile(String fileName) throws VatException {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))){
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine().replace(',', '.');
                try {
                    this.addNation(NationVat.parse(input, DELIMITER));
                }   catch (ParseException e) {
                    throw new VatException("Invalid input file: "+fileName);
                }
            }
            list.sort(Comparator.comparing(NationVat::getVat).reversed());
        }   catch (FileNotFoundException ex) {
            throw new VatException("Soubor " + fileName + " nenalezen");
        }
    }

    public void exportToFile(String fileName) throws VatException {

    }

}
