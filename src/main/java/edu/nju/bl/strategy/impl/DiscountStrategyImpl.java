package edu.nju.bl.strategy.impl;

import edu.nju.bl.strategy.DiscountStrategy;
import org.springframework.stereotype.Component;

/**
 * Discount strategy impl
 * @author cuihao
 */
@Component
public class DiscountStrategyImpl implements DiscountStrategy {
    /**
     * Get room discount according to level
     *
     * @param level level of member
     * @param price original price
     * @return discount of the room
     */
    @Override
    public double getDiscount(int level, int price) {
        int levelLine[] = new int[]{1000,5000,20000};
        double discount[] = new double[]{0.98, 0.95, 0.92};
        for (int i = 0; i < levelLine.length; i++) {
            if (level<levelLine[i]) return discount[i]*level;
        }
        return 0.9 * price;
    }
}
