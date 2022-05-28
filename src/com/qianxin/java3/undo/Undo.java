package com.qianxin.java3.undo;

import java.util.Stack;

/**
 * @author ym
 * @date 2022/05/28 21:10:11
 * @description 链接：https://www.nowcoder.com/questionTerminal/46badc29891b4294a3b9cc235a96631a?f=discussion
 * 来源：牛客网
 *
 * 撤销/恢复操作具有广泛的用途，比如word文档中输入一个单词，可以点撤销，然后可以再恢复。
 * 编程实现如下功能：  从标准输入读取到一个字符串，字符串可包含0个或多个单词，单词以空格或者tab分隔；
 * 如果遇到 "undo" 字符串，表示"撤销"操作，前一个字符串被撤销掉； 如果遇到"redo"字符串，表示恢复刚才撤销掉的字符串.
 * 例如:   输入字符串 "hello undo redo world."，  对字符串中的 undo 和 redo 处理后， 最终输出的结果为 "hello world."
 *
 * 分析：
 * 两个栈，正常输入栈和撤销内容存储栈
 * 当遇到撤销时，正常输入栈弹出栈顶元素并且存入撤销内容栈
 * 当遇到恢复时，撤销内容栈弹出栈顶元素并且存入正常输入栈
 **/
public class Undo {
    public static void main(String[] args) {
        String s = "hello undo redo world.";
        System.out.println(new Undo().undoAction(s));
    }

    public String undoAction(String str) {
        //参数合法性判断
        if (str == null || str.isEmpty()) {
            return "";
        }
        //字符串是以空格或者tab分隔的，首先需要切分
        String[] split = str.split(" ");
        //声明正常输入栈
        Stack<String> commonStrings = new Stack<>();
        //声明撤销内容栈
        Stack<String> undoStrings = new Stack<>();
        //依次读取字符串栈里的内容
        for (int i = 0; i < split.length; i++) {
            //判断字符串里的内容是不是撤销或者恢复
            if (split[i].equals("undo")) {
                //如果是撤销，撤销之前判断栈是不是空
                if (commonStrings.isEmpty() == false) {
                    undoStrings.push(commonStrings.pop());
                    continue;
                }
            }
            if (split[i].equals("redo")) {
                //如果是恢复，撤销之前判断栈是不是空
                if (undoStrings.isEmpty() == false) {
                    commonStrings.push(undoStrings.pop());
                    continue;
                }
            }
            //如果既不是undo，也不是redo，那么就添加进正常栈
            commonStrings.push(split[i]);
        }
        //返回正常输入栈
        StringBuilder stringBuilder = new StringBuilder();
        for (String s :
                commonStrings) {
            stringBuilder.append(s + " ");
        }
        return stringBuilder.toString();
    }
}
