package com.zoe.study.java.effective.chapter06;

/**
 * Created by  on 2017/8/23.
 */
public enum Ensemble {
//    SOLO, DUET, TRIO, QUARTET, QUINTET, SEXTET, SEPTET, OCTET, NONET, DECTET;
//    /**
//     * 此方法坑非常多
//     *   1 常量重新排序，则此方法返回的值就会遭到破坏。
//     *   2 添加一个与已经用过的int值关联的枚举常量，可能都没有办法做到。
//     *   3 无法给某个int值添加常量。
//     */
//    public int numOfMusic() {
//        return ordinal() + 1;
//    }

    /**
     * 正确做法。
     * 将关联序列值保存在一个实例域中
     */
    SOLO(1), DUET(2), TRIO(3), QUARTET(4), QUINTET(5), SEXTET(6),
    SEPTET(7), OCTET(8), NONET(9), DECTET(10),TRIPLE_QUARTET(12);
    private final int numOfMusic;
    Ensemble(int numOfMusic) { this.numOfMusic = numOfMusic; }
    public int numOfMusic() { return numOfMusic; }
}
