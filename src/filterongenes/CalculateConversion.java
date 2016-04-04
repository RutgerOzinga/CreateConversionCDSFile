/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filterongenes;

import static java.util.Date.parse;
import java.util.List;

/**
 *
 * @author Rutger
 */
public class CalculateConversion {

    private double transcriptModifier;
    private double proteinModifier;
    private boolean firstLine = true;
    /**
     * calculates the conversion factor from the log values
     * and the max proteins and max transcripts.
     * @param transcriptMax longest transcript
     * @param proteinMax longest protein
     * @param logList list with the CPM log values of the transcript and proteins.
     * @return a conversion factor.
     */
    public double calculateConversion(double transcriptMax, double proteinMax, List logList) {
        transcriptModifier = transcriptMax / 1000;
        proteinModifier = proteinMax / 1000;
        double protLogValue = Double.parseDouble(logList.get(1).toString());
        double transLogValue = Double.parseDouble(logList.get(0).toString());
        double trueProtValue = Math.pow(10, protLogValue);
        double trueTransValue = Math.pow(10, transLogValue);

        double normalizedProtValue = trueProtValue / proteinModifier;
        double normalizedTransValue = trueTransValue / transcriptModifier;

        double conversionFactor = normalizedProtValue / normalizedTransValue;

        double logConversionValue = Math.log10(conversionFactor);

        return logConversionValue;

    }

}

