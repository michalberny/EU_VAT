package com.engeto.eu_vat;


import java.text.ParseException;
import java.util.Objects;

public class NationVat {
    private String shortcutOfNation;
    private String nation;
    private double vat;
    private double loweredVat;
    private boolean hasSpecialVat;

    public NationVat(String shortcutOfNation, String nation, double vat, double loweredVat, boolean hasSpecialVat) {
        this.shortcutOfNation = shortcutOfNation;
        this.nation = nation;
        this.vat = vat;
        this.loweredVat = loweredVat;
        this.hasSpecialVat = hasSpecialVat;
    }

    public String printInfo() {
        String info;
        info = nation + " (" + shortcutOfNation + "): "+vat+" %";
        return info;
    }

    public String printInfoVatOver20 () {
        if (vat > 20.0 && !hasSpecialVat) {
            String info;
            info = nation + " (" + shortcutOfNation + "): "+vat+" %";
            return info;
        }
        else return "";
    }

    public String printOthers () {
        if (vat <= 20.0 || hasSpecialVat){
            String info;
            info = shortcutOfNation+", ";
            return info;
        }
        else return "";
    }

    public String printVatAboveUserInput(int userInput) {
        if (vat > userInput){
            String info;
            info = nation + " (" + shortcutOfNation + "): "+vat+" %";
            return info;
        }
        else return "";
    }

    public static NationVat parse(String text, String delimiter) throws ParseException {
        String[] items = text.split(delimiter);

        int numberOfItems = items.length;
        if (numberOfItems != 5) throw new ParseException("Nesprávný počet položek na řádku", 0);

        String shortcutOfNation = items[0];
        String nation = items[1];
        double vat = Double.parseDouble(items[2]);
        double loweredVat = Double.parseDouble(items[3]);
        boolean hasSpecialVat = Boolean.parseBoolean(items[4]);

        return new NationVat(shortcutOfNation, nation, vat, loweredVat, hasSpecialVat);
    }

    public String prepareOutput(String delimiter) {
        return getShortcutOfNation()+delimiter+getNation()+delimiter+getVat()+delimiter
                +getLoweredVat()+delimiter+isHasSpecialVat();
    }

    public String getShortcutOfNation() {
        return shortcutOfNation;
    }

    public void setShortcutOfNation(String shortcutOfNation) {
        this.shortcutOfNation = shortcutOfNation;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public double getLoweredVat() {
        return loweredVat;
    }

    public void setLoweredVat(double loweredVat) {
        this.loweredVat = loweredVat;
    }

    public boolean isHasSpecialVat() {
        return hasSpecialVat;
    }

    public void setHasSpecialVat(boolean hasSpecialVat) {
        this.hasSpecialVat = hasSpecialVat;
    }

}
