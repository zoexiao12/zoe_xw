package com.zoe.study.java.effective.chapter06;

/**
 * Created by  on 2017/8/23.
 */
public enum PayrollDay {
//    MONDAY,TUESAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY;
//    private static final int HOURS_PER_SHIFT = 8;
//    /**
//     * 此程序非常危险。
//     * 比如添加一个元素到枚举中,标示假期，
//     * 但忘记=给case添加相应的条件，则计算的工资就成了正常工作日。
//     * @param houseWorked  上班时间；
//     * @param payRate       小时工资
//     */
//    double pay(double houseWorked, double payRate) {
//        double basePay = houseWorked * payRate;  //一天的基础工资
//        double overtimePay;                      //加班工资
//        switch (this) {
//            case SATURDAY:
//            case  SUNDAY:
//                overtimePay = houseWorked * payRate / 2;
//                break;
//            default:
//                overtimePay = houseWorked <= HOURS_PER_SHIFT ? 0 :
//                        (houseWorked - HOURS_PER_SHIFT) * payRate / 2;
//                break;
//        }
//        return  basePay + overtimePay;
//    }

    /**
     * 定义一种枚举常量时，指定一个工资计算的策略。
     * 如果没有对应的策略，则在对应的策略枚举类PayType中添加对应的策略。
     */
    MONDAY(PayType.WEEKDAY),
    TUESAY(PayType.WEEKDAY),
    WEDNESDAY(PayType.WEEKDAY),
    THURSDAY(PayType.WEEKDAY),
    FRIDAY(PayType.WEEKDAY),
    SATURDAY(PayType.WEEKEND),
    SUNDAY(PayType.WEEKEND);
    private final  PayType payType;
    PayrollDay(PayType payType) { this.payType = payType; }
    double pay(double hrs , double payRate) {
        return payType.pay(hrs,payRate);
    }


    //策略枚举
    private enum PayType {
        WEEKDAY{
            @Override
            double overtimePay(double hrs, double payRate) {
                return hrs <= HOURS_PER_SHIFT ? 0 : (hrs - HOURS_PER_SHIFT) * payRate / 2;
            }
        },
        WEEKEND{
            @Override
            double overtimePay(double hrs, double payRate) {
                return hrs * payRate / 2;
            }
        };
        private static final int HOURS_PER_SHIFT = 8;
        abstract double overtimePay(double hrs, double payRate);
        double pay(double hrs, double payRate) {
            double basePay = hrs * payRate;  //一天的基础工资
            return  basePay +  overtimePay(hrs,payRate);
        }
    }
}
