package com.qianxin.java3.bonus;

/**
 * @author ym
 * @date 2022/05/28 20:49:20
 * @description
 * 老板一共需要给某个员工发奖金n元，
 * 可以选择一次发1元，也可以选择一次发2元，也可以选择一次发3元。
 * 请问老板给这位员工发放完n元奖金共有多少种不同的方法？
 * 数据范围：1 <= n <= 10
 *
 * 分析：f(1)=1   一元钱，直接给
 *      f(2)=2   两元钱：1）先给一元，再给一元   f(1)+1
 *                      2）直接给两元
 *      f(3)=4   三元钱：1）先给一元，再给一元，再给一元  f(2)+f(1)+1
 *                      2）先给一元，再给两元
 *                      3）先给两元，再给一元
 *                      4）直接给三元
 *      f(4)=7    四元钱：1）先给一元，再给一元，再给一元，再给一元  f(3)+f(2)+f(1)
 *                      2）先给一元，再给一元，再给两元
 *                      3）先给一元，再给两元，再给一元
 *                      4）先给一元，再给三元
 *                      5）先给两元，再给一元，再给一元
 *                      6）先给两元，再给两元
 *                      7）先给三元，再给一元
 *
 *     以f(4)为例，   当先给一元的情况下，剩余金额就是f(3)，
 *                  当先给两元的情况下，剩余金额就是f(2)，
 *                  当先给三元的情况下，剩余金额就是f(1)
 *                  那么f(n)=f(n-1)+f(n-2)+...+f(1)
 **/
public class Bonus {
    public static void main(String[] args) {
        System.out.println(new Bonus().getBonus(5));
    }

    public int getBonus(int number) {
        //参数合法性判断
        if (number < 0 || number > 10) {
            return -1;
        }
        //如果是 1 2 3元的情况
        switch (number) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 4;
        }
        //如果是其他情况
        int count = 0;
        for (int i = 2; i <= number; i++) {
            count += getBonus(i - 1);
        }
        return count;
    }
}
