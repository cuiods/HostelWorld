package edu.nju.bl.strategy;

/**
 * Discount strategy according to member level
 */
public interface DiscountStrategy {

    /**
     * Get room discount according to level
     * @param level level of member
     * @param price original price
     * @return discount of the room
     */
    double getDiscount(int level, int price);

}
