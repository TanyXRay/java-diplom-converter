package ru.netology.graphics.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class ImageToUnicodeConverter implements TextGraphicsConverter {
    private TextColorSchema schema;
    private int width;
    private int height;
    private double maxRatio;
    private int ratio;
    private int newWidth;
    private int newHeight;

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        if (Objects.isNull(schema)) {
            schema = new UnicodeColorSchema();
        }

        BufferedImage img = ImageIO.read(new URL(url));
        checkMaxRatio(img);
        calcNewWidthAndHeight(img);

        Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);
        BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY); // черно-белая картинка

        Graphics2D graphics = bwImg.createGraphics();

        graphics.drawImage(scaledImage, 0, 0, null);
        WritableRaster bwRaster = bwImg.getRaster();


        String[][] colorImage = new String[newWidth][newHeight];
        for (int i = 0; i < newWidth; i++) {
            for (int j = 0; j < newHeight; j++) {
                int color = bwRaster.getPixel(i, j, new int[4])[0];
                String c = schema.convert(color);
                colorImage[i][j] = c;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < newHeight; y++) {
            for (int x = 0; x < newWidth; x++) {
                sb.append(colorImage[x][y]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public void setMaxWidth(int width) {
        this.width = width;
    }

    @Override
    public void setMaxHeight(int height) {
        this.height = height;

    }

    @Override
    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {
        this.schema = schema;
    }

    private void checkMaxRatio(BufferedImage img) throws BadImageSizeException {
        int gWidth = img.getWidth();
        int gHeight = img.getHeight();
        if ((gWidth / gHeight) > (gHeight / gWidth)) {
            ratio = gWidth / gHeight;
        } else {
            ratio = gHeight / gWidth;
        }
        if (ratio > maxRatio && maxRatio != 0) throw new BadImageSizeException(ratio, maxRatio);
    }

    private void calcNewWidthAndHeight(BufferedImage img) {
        boolean minWidth = img.getWidth() < width;
        boolean minHeight = img.getHeight() < height;
        boolean maxWidth = img.getHeight() > width;
        boolean maxHeight = img.getHeight() > height;
        if (ratio != 0 && ratio < maxRatio) {
            if (minWidth || minHeight) {
                newWidth = (int) (img.getWidth() * maxRatio);
                newHeight = (int) (img.getHeight() * maxRatio);
            } else if (maxWidth || maxHeight) {
                newWidth = (int) (img.getWidth() / maxRatio);
                newHeight = (int) (img.getHeight() / maxRatio);
            }
        }
    }
}


