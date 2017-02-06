package edu.nju.bl.strategy.impl;

import edu.nju.bl.strategy.ExchangeScoreStrategy;
import org.springframework.stereotype.Component;

/**
 * Exchange score to remain
 * @author cuihao
 */
@Component
public class ExchangeScoreStrategyImpl implements ExchangeScoreStrategy {
    /**
     * Exchange member score to member remain
     *
     * @param score member score to exchange
     * @return member remain that can be added
     */
    @Override
    public int exchange(int score) {
        return 0;
    }
}
