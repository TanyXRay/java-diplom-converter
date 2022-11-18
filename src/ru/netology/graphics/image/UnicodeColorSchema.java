package ru.netology.graphics.image;

public class UnicodeColorSchema implements TextColorSchema {
    /**
     * Перебираем все пиксели изображения, спрашивая у них степень белого
     * (число от 0 до 255, где 0 — это чёрный, а 255 — это светлый).
     * В зависимости от этого числа выбираем символ из заранее подготовленного набора;
     *
     * @param color от 0 до 255, где 0 — это чёрный, а 255 — это светлый
     * @return char str
     */
    @Override
    public String convert(int color) {
        final String str;
        if (color >= 250) {
            str = "```";
        } else if (color >= 200) {
            str = "...";
        } else if (color >= 180) {
            str = "---";
        } else if (color >= 150) {
            str = "***";
        } else if (color >= 120) {
            str = "+++";
        } else if (color >= 100) {
            str = "ooo";
        } else if (color >= 70) {
            str = "###";
        } else if (color >= 40) {
            str = "$$$";
        } else {
            str = "@@@";
        }
        return str;
    }
}
