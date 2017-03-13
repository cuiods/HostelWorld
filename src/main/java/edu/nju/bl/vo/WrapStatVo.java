package edu.nju.bl.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class WrapStatVo {
    private long reserve;
    private long check;
    private long money;
    private long weekMoney;
    private List<LineVo> lines = new ArrayList<>(7);
    public WrapStatVo(StatisticVo statisticVo) {
        reserve = statisticVo.getReserve();
        check = statisticVo.getCheck();
        money = statisticVo.getMoney();
        weekMoney = statisticVo.getWeekMoney();
        long[] checks = statisticVo.getChecks();
        long[] reserves = statisticVo.getReserves();
        for (int i = 0; i < checks.length; i++) {
            LineVo  lineVo = new LineVo("第"+(i+1)+"天",reserves[i],checks[i]);
            lines.add(lineVo);
        }
    }
}
