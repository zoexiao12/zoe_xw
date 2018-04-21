package com.zoe.study.java.effective.chapter06;

/**
 * Created by  on 2017/8/23.
 */
public class Text {
    public static final int STYLE_BOLD   = 1 << 0; //1
    public static final int STYLE_ITALIC   = 1 << 1; //2
    public static final int STYLE_UNDERLINE  = 1 << 2;//4
    public static final int STYLE_STRIKETHROUGH = 1 << 3; //8

    public void applyStyles(int styles) {}

    public static void main(String [] args) {
        Text text = new Text();
        text.applyStyles( STYLE_BOLD | STYLE_ITALIC);
    }
}
