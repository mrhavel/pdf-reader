package de.somePackage.commands;

import de.somePackage.BahnReise;

/**
 * Parser
 */
public class BahnTicketCommand implements Command<String, BahnReise> {

    BahnReise reise;

    @Override
    public BahnReise result() {
        return reise;
    }

    /**
     * Beispiel:
     * <p>
     * 15.9.2019 DB BAHN - Auftrag - Detailsicht
     * https://fahrkarten.bahn.de/privatkunde/buchungsrueckschau/brs_auftrag_details_druck.ignore.go?lang=de&country=DEU 1/1
     * Auftrag - Detailsicht
     * Auftrag Buchungsdatum Buchender Reisender
     * FENT2R 15.09.2019 Marc Brewer Marc
     * Brewer
     * <p>
     * Beleg Reisedatum Beschreibung Beleg-Nr. Preis
     * Fahrschein 15.09.2019 VRS-Tarif, EinzelTicket Erwachsene, Euskirchen (Gleis
     * 2), Euskirchen, Preisstufe 4, gültig von: 15.09.2019
     * 15:59 gültig bis: 15.09.2019 18:59
     * 83390438 7,38 EUR
     *
     * @param text
     */
    @Override
    public Command process(String text) {
        reise = new BahnReise();
        try {
            String[] lines = text.split("\r?\n");
            reise.setTime(lines[0].split("\\s")[0]);
            reise.setBahncode(lines[4].split("\\s")[0]);

            if (lines.length == 12) {
                reise.setPreis(lines[11].split("\\s")[1]);
            } else if (lines.length == 11){
                reise.setPreis(lines[10].split("\\s")[1]);
            }

            System.out.println(lines.length);

            reise.setReisender(lines[4].split("\\s")[2] + "" + lines[4].split("\\s")[3]);

        } catch (Exception exp) {
            System.out.println("Fehler : " + exp.getMessage());
        }
        return this;
    }
}
