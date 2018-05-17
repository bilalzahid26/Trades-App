package com.company;


import java.util.ArrayList;

public class Main {
    public static ArrayList<Stock> listOfStock = new ArrayList<>();

    public static void main(String[] args) {
        String[] tickerSymbol = {
                "TsLa", "AAPL", "SNAP", "GOOG", "FB"
//                "AAL", "ABF", "ADM", "AHT", "ANTO", "AV", "AZN", "BA", "BAB", "BARC", "BATS", "BDEV",
//                "BLND", "BLT", "BNZL", "BP", "BRBY", "BT.A", "CCH", "CCL", "CNA", "CPG", "CRDA", "CRH", "CTEC", "DCC",
//                "DGE", "DLG", "EXPN", "EZJ", "FERG", "FRES", "GFS", "GKN", "GLEN", "GSK", "HL", "HMSO", "HSBA", "IAG",
//                "IHG", "III", "IMB", "ITRK", "ITV", "JMAT", "KGF", "LGEN", "LLOY", "LSE", "MCRO", "MDC", "MERL",
//                "MKS", "MNDI", "MRW", "NG", "NXT", "OML", "PFG", "PPB", "PRU", "PSN", "PSON", "RB", "RBS", "RDSA", "REL",
//                "RIO", "RMG", "RR", "RRS", "RSA", "RTO", "SBRY", "SDR", "SGE", "SGRO", "SHP", "SKY", "SLA", "SMIN", "SMT",
//                "SN", "SSE", "STAN", "STJ", "SVT", "TSCO", "TW", "ULVR", "UU", "VOD", "WPG", "WTB", "ABC", "ASHM", "BBA",
//                "BKG", "BVIC", "BYG", "CCC", "CHG", "DEB", "DLN", "DMGT", "DOM", "DRX", "ECM", "ELM", "EVR",
//                "FGP", "FXPO", "GNC", "GNS", "HCM", "HFD", "HWDN", "INCH", "JD", "JDW", "KAZ", "KBT", "LAM", "MONY", "MPE",
//                "NEX", "NXG", "OCDO", "PNN", "QQ", "RMV", "RNK", "SHB", "SHI", "TALK", "TCAP", "VEC", "VSVS",
//                "YOU", "ZYT"
        };

        for (int i = 0; i < tickerSymbol.length; i++) {

            listOfStock.add(new Stock(tickerSymbol[i].toUpperCase()));
            if (listOfStock.get(i).gottenSymbol == true) {

//                System.out.println("" + listOfStock.get(i).getName());
//                System.out.println("Ticker Symbol: " + listOfStock.get(i).getTickerSymbol());
//                System.out.println("Polarity for: " + listOfStock.get(i).getTickerSymbol() + " is " + listOfStock.get(i).getPolarity());
//                System.out.println("Price: " + listOfStock.get(i).getPrice());
//                System.out.println("Exchange: " + listOfStock.get(i).getExchange());
//                System.out.println(" ");


                Scoring score = new Scoring(listOfStock.get(i));
//                System.out.println(listOfStock.get(i).getScore());



            } else {
                System.out.println("Unknown Symbol");
                System.out.println(" ");
            }

        }

        System.out.println(" ");
        int[] sorter = new int[listOfStock.size()];
        for (int i = 0; i < listOfStock.size(); i++) {

        sorter[i] = (int) listOfStock.get(i).getScore();

        }
        sorter = Sorter.sort(sorter);
        for (int i = 0; i < sorter.length; i++) {
            for (int j = 0; j < listOfStock.size(); j++) {
                if (sorter[i] == listOfStock.get(j).getScore()){
                    System.out.println(listOfStock.get(j).getTickerSymbol() + ": " +listOfStock.get(j).getScore());
                }
            }
        }

    }
}
