package com.company;

/**
 * Created by BilalZahid on 29/03/2018.
 */
public class Scoring {
    public double score = 0;


    public Scoring(Stock stock) {
        getScore(stock);
    }

    public void getScore(Stock stock) {
        double currentScore = 0;

        currentScore = (stock.getPrevClose() / stock.getOpen()) * (stock.getMktCap() / stock.getPrice()) * stock.getPriceChangePercent() * stock.getPeRatio() * stock.getPolarity();
        if (stock.getPrevClose() > stock.getOpen() && stock.getPolarity() > 0) {
            currentScore = currentScore * -1;
        }
        setScore(currentScore);
        stock.setScore(currentScore);
    }

    public void setScore(double score) {
        this.score = score;

    }
}
