package edu.nju.bl.strategy;

/**
 * Exchange member score to member remain strategy
 * @author cuihao
 */
public interface ExchangeScoreStrategy {

    /**
     * Exchange member score to member remain
     * @param score member score to exchange
     * @return member remain that can be added
     */
    int exchange(int score);

}
