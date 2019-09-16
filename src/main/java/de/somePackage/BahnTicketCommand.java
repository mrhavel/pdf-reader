package de.somePackage;

/**
 * Parser
 */
public class BahnTicketCommand implements Command<BahnReise> {

    BahnReise reise;

    @Override
    public BahnReise result() {
        return reise;
    }

    /**
     * Beispiel:
     *
     * 15.9.2019 DB BAHN - Auftrag - Detailsicht
     * https://fahrkarten.bahn.de/privatkunde/buchungsrueckschau/brs_auftrag_details_druck.ignore.go?lang=de&country=DEU 1/1
     * Auftrag - Detailsicht
     * Auftrag Buchungsdatum Buchender Reisender
     * FENT2R 15.09.2019 Marc Brewer Marc
     * Brewer
     *
     * Beleg Reisedatum Beschreibung Beleg-Nr. Preis
     * Fahrschein 15.09.2019 VRS-Tarif, EinzelTicket Erwachsene, Euskirchen (Gleis
     * 2), Euskirchen, Preisstufe 4, gültig von: 15.09.2019
     * 15:59 gültig bis: 15.09.2019 18:59
     * 83390438 7,38 EUR
     * @param text
     */
    @Override
    public void process(String text) {

        reise = new BahnReise();

        String[] lines = text.split("\r?\n");

        reise.setTime(lines[0].split("\\s")[0]);
        reise.setBahncode(lines[4].split("\\s")[0]);
        reise.setPreis(lines[11].split("\\s")[1]);

    }
}
