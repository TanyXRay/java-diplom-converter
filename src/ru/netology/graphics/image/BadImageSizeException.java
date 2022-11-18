package ru.netology.graphics.image;

/**
 * Класс-исключение, которое будем выбрасывать при неправильном соотношении сторон
 */
public class BadImageSizeException extends Exception {
    public BadImageSizeException(double ratio, double maxRatio) {
        super("Максимальное соотношение сторон изображения " + maxRatio + ", а у этой " + ratio);
    }
}
