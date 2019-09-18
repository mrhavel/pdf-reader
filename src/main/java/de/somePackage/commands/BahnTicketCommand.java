package de.somePackage.commands;

import de.somePackage.beans.BahnReise;

/**
 * Parser
 */
public class BahnTicketCommand implements Command<String, BahnReise> {

    private BahnReise reise;

    @Override
    public BahnReise result() {
        return reise;
    }

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
            reise.setReisender(lines[4].split("\\s")[2] + "" + lines[4].split("\\s")[3]);
        } catch (Exception exp) {
            System.out.println("Fehler : " + exp.getMessage());
        }
        return this;
    }
}
