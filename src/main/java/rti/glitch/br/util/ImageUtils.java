package rti.glitch.br.util;

import lombok.experimental.UtilityClass;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@UtilityClass
public class ImageUtils {

    public static BufferedImage decreaseImage(InputStream inputStream, int largura, int altura) throws IOException {
        BufferedImage imagem = ImageIO.read(inputStream);
        Thumbnails.of(imagem)
                .size(largura, altura)
                .asBufferedImage();
        return imagem;
    }

    public static void saveNewImage(BufferedImage imagem, String path, String name) throws IOException {
        ImageIO.write(imagem, "png", new File(path + "/" + name));
    }


}
