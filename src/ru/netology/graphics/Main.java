package ru.netology.graphics;

import ru.netology.graphics.image.ImageToUnicodeConverter;
import ru.netology.graphics.image.TextGraphicsConverter;
import ru.netology.graphics.server.GServer;

/**
 * Запуск приложения.
 * В нём запускается сервер, также в нём можно будет
 * конвертировать картинки в текстовые файлы без сервера.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        TextGraphicsConverter converter = new ImageToUnicodeConverter();
        GServer server = new GServer(converter);

        server.start();

        // TextGraphicsConverter converter = new ImageToUnicodeConverter();
        // converter.setMaxRatio(4);
        // converter.setMaxWidth(300);
        // converter.setMaxHeight(300);
        // String url = "https://raw.githubusercontent.com/netology-code/java-diplom/main/pics/simple-test.png";
        // String url = "https://i.ibb.co/6DYM05G/edu0.jpg";
        // String imgTxt = converter.convert(url);
        // System.out.println(imgTxt);
    }
}
