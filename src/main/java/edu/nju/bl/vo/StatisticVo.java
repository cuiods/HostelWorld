package edu.nju.bl.vo;

import lombok.Data;

/**
 * describe statistic info
 */
@Data
public class StatisticVo {
    private long reserve;
    private long check;
    private long money;
    private long weekMoney;
    private long[] checks = new long[]{0,0,0,0,0,0,0};
    private long[] reserves = new long[]{0,0,0,0,0,0,0};
    public void setChecks(long index, long data) {
        if (index < checks.length) {
            System.out.println(index+":"+data);
            checks[(int)index] = data;
        }
    }
    public void setReserves(long index, long data) {
        if (index < checks.length) {
            System.out.println(index+":"+data);
            reserves[(int)index] = data;
        }
    }
}
