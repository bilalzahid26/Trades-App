package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.*;

public class Stock {

    public String tickerSymbol = "";
    public double price;
    public String name = "";
    public String exchange = "";
    public double open = 0.0;
    public double close = 0.0;
    public double high = 0.0;
    public double low = 0.0;
    public String date = "";
    public int volume = 0;
    public double prevClose = 0.0;
    public double priceChange;
    public double priceChangePercent;
    public long mktCap = 0;
    public double peRatio = 0.0;
    public int polarity = 0;
    public boolean gottenSymbol = false;
    public long score = 0;


    public Stock(String symbol) {

        getStock(symbol);
        getNews(symbol);

    }

    public void getStock(String symbol) {
        String content = "";
        URLConnection connection = null;
        try {
            connection = new URL("https://api.iextrading.com/1.0/stock/" + symbol + "/quote?displayPercent=true").openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            while (scanner.hasNextLine()) {

                content = content + scanner.nextLine();
            }

            content = content.replaceAll("\"", "");
            content = content.replaceAll("}", "");
            content = content.replaceAll("\\{", "");

            String[] parts = content.split(",");
            for (int i = 0; i < parts.length; i++) {
                if (parts[i].contains("iex") || parts[i].contains("EMPTY CELL") || parts[i].contains("delayed") || parts[i].contains("week") || parts[i].contains("ytd") || parts[i].contains("avg") || parts[i].contains("latestUpdate") || parts[i].contains("latestSource") || parts[i].contains("closeTime") || parts[i].contains("calculationPrice") || parts[i].contains("sector") || parts[i].contains("openTime")) {
                    parts[i] = "EMPTY CELL";
                } else if (parts[i].contains("latestTime")) {
                    int year = Calendar.getInstance().get(Calendar.YEAR);

                    parts[i] = parts[i] + " " + Integer.toString(year);
                    parts[i + 1] = "EMPTY CELL";

                }

            }
            List<String> list = new ArrayList<String>(Arrays.asList(parts));
            list.removeAll(Arrays.asList("EMPTY CELL"));

            for (int i = 0; i < list.size(); i++) {
                list.set(i, list.get(i).substring(list.get(i).indexOf(":") + 1));
                if (list.get(i).contains("null")) {
                    list.set(i, "0");
                }
            }


            if (list.size() != 0) {
                int listPlace = 0;
                setTickerSymbol(list.get(listPlace));
                listPlace++;
                setName(list.get(listPlace));
                listPlace++;
                setExchange(list.get(listPlace));
                listPlace++;
                setOpen(Double.parseDouble(list.get(listPlace)));
                listPlace++;
                setClose(Double.parseDouble(list.get(listPlace)));
                listPlace++;
                setHigh(Double.parseDouble(list.get(listPlace)));
                listPlace++;
                setLow(Double.parseDouble(list.get(listPlace)));
                listPlace++;
                setPrice(Double.parseDouble(list.get(listPlace)));
                listPlace++;
                setDate(list.get(listPlace));
                listPlace++;
                setVolume(Integer.parseInt(list.get(listPlace)));
                listPlace++;
                setPrevClose(Double.parseDouble(list.get(listPlace)));
                listPlace++;
                setPriceChange(Double.parseDouble(list.get(listPlace)));
                listPlace++;
                setPriceChangePercent(Double.parseDouble(list.get(listPlace)));
                listPlace++;
                setMktCap(Long.parseLong(list.get(listPlace)));
                listPlace++;
                setPeRatio(Double.parseDouble(list.get(listPlace)));
            }
            gottenSymbol = true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            gottenSymbol = false;
        }


    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public double getPrevClose() {
        return prevClose;
    }

    public void setPrevClose(double prevClose) {
        this.prevClose = prevClose;
    }

    public double getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(double priceChange) {
        this.priceChange = priceChange;
    }

    public double getPriceChangePercent() {
        return priceChangePercent;
    }

    public void setPriceChangePercent(double priceChangePercent) {
        this.priceChangePercent = priceChangePercent;
    }

    public long getMktCap() {
        return mktCap;
    }

    public void setMktCap(long mktCap) {
        this.mktCap = mktCap;
    }

    public double getPeRatio() {
        return peRatio;
    }

    public void setPeRatio(double peRatio) {
        this.peRatio = peRatio;
    }

    public int getPolarity() {
        return polarity;
    }

    public void setPolarity(int polarity) {
        this.polarity = polarity;
    }

    public void getNews(String symbol) {
        String content = null;
        URLConnection connection = null;
        try {
            connection = new URL("https://news.google.com/news/search/section/q/" + symbol.toUpperCase()).openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            content = scanner.next();
            String[] split = content.split("https");

            for (int i = 0; i < split.length; i++) {
                if (!split[i].contains("www.")) {
                    split[i] = "EMPTY CELL";
                } else if (split[i].contains("google")) {
                    split[i] = "EMPTY CELL";
                }
                split[i] = split[i].split("\"")[0];
                split[i] = split[i].replaceAll("://", "");
            }
            List<String> list = new ArrayList<String>(Arrays.asList(split));
            list.removeAll(Arrays.asList("EMPTY CELL"));

            int polarity = 0;
            for (int i = 0; i < list.size() / 10; i++) {
//                System.out.println("getting article");
                polarity = polarity + getArticle(list.get(i));

            }
            setPolarity(polarity);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int getArticle(String s) {
        String content = "";
        int finalPolarity = 0;
        if (s.substring(s.length() - 1).equals("/")) {
            s = s.substring(0, s.length() - 1);
        }

        if (s.contains("html")) {
            s = "https://" + s.split("html")[0] + "html";
        } else {
            s = "https://" + s + ".html";
        }

        if (s.contains("aspx")) {

            s = s.split("aspx")[0] + "aspx";

        }
//        System.out.println(s);
        try {
            URLConnection connection1 = new URL(s).openConnection();
            connection1.connect();
            Scanner scanner = new Scanner(connection1.getInputStream());
            while (scanner.hasNextLine()) {

                content = content + scanner.nextLine();
            }

            content = content.substring(content.indexOf("<p>"));
            content = content.substring(0, content.lastIndexOf("</p>"));
            String lines[] = content.split("\\r?\\n");
            ArrayList<String> articles = new ArrayList<String>();

            for (int i = 0; i < lines.length; i++) {

                if (!lines[i].contains("<p>")) {
                    lines[i] = "";
                } else {
                    articles.add(lines[i]);
                }


            }
            String article = "";
            for (int i = 0; i < articles.size(); i++) {
                articles.set(i, articles.get(i).replaceAll("<.*?>", ""));
                articles.set(i, articles.get(i).replaceAll("\\s+", " "));
                article = article + " " + articles.get(i);

            }

            finalPolarity = polarity(article);
//            if (finalPolarity < 0) {
//                System.out.println("NEGATIVE");
//
//            } else if (finalPolarity > 0) {
//                System.out.println("POSITIVE");
//            } else {
//                System.out.println("NEUTRAL");
//            }
        } catch (Exception ex) {
        }
        return finalPolarity;

    }

    public static int polarity(String article1) {
        String article = String.valueOf(article1);

        int polarity = 0;

        String csvFile = "/Users/BilalZahid/Desktop/Computer Science/Github/Stock API Test/positive-words.csv";
        String csvFile1 = "/Users/BilalZahid/Desktop/Computer Science/Github/Stock API Test/negative-words.csv";
        String[] articleWords = article.split(" ");
        String[] negativeWord = new String[0];
        String[] positiveWord = new String[0];
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String str;
            List<String> list = new ArrayList<String>();
            while ((str = br.readLine()) != null) {
                list.add(str);
            }
            positiveWord = list.toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile1))) {

            String str;

            List<String> list = new ArrayList<String>();
            while ((str = br.readLine()) != null) {
                list.add(str);
            }


            negativeWord = list.toArray(new String[0]);


        } catch (IOException e) {

            e.printStackTrace();
        }

        for (int i = 0; i < positiveWord.length; i++) {

            for (int j = 0; j < articleWords.length; j++) {
                if (positiveWord[i].equals(articleWords[j])) {

                    polarity++;
                }
            }

        }

        for (int i = 0; i < negativeWord.length; i++) {

            for (int j = 0; j < articleWords.length; j++) {
                if (negativeWord[i].equals(articleWords[j])) {

                    polarity--;
                }
            }

        }

        return polarity;


    }


    public long getScore() {
        return score;
    }

    public void setScore(double currentScore) {
        currentScore = currentScore/(1000000000.0);

        this.score = (long) currentScore;


    }


}