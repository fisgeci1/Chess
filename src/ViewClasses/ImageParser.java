package ViewClasses;

import Pieces.TypeOfPiece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageParser {




    public JLabel getLabelIconOfPiece(TypeOfPiece type){
        switch (type) {
            case BLACK_PAWN:
                return makeJLabelIcon(type.imagePath);
            case BLACK_ROOK:
                return makeJLabelIcon(type.imagePath);
            case BLACK_BISHOP:
                return makeJLabelIcon(type.imagePath);
            case BLACK_KNIGHT:
                return makeJLabelIcon(type.imagePath);
            case BLACK_QUEEN:
                return makeJLabelIcon(type.imagePath);
            case BLACK_KING:
                return makeJLabelIcon(type.imagePath);
            case WHITE_PAWN:
                return makeJLabelIcon(type.imagePath);
            case WHITE_ROOK:
                return makeJLabelIcon(type.imagePath);
            case WHITE_BISHOP:
                return makeJLabelIcon(type.imagePath);
            case WHITE_KNIGHT:
                return makeJLabelIcon(type.imagePath);
            case WHITE_QUEEN:
                return makeJLabelIcon(type.imagePath);
            case WHITE_KING:
                return makeJLabelIcon(type.imagePath);
        }

        //never should get here cause the enums are defined always!
        return null;
    }

    private JLabel makeJLabelIcon(String imagePath){
        JLabel imageLabel = null;
        try{
            BufferedImage image = ImageIO.read(new File(imagePath));

            imageLabel = new JLabel(new ImageIcon(image));
        }catch (IOException e ){
            System.out.println("image not found");
        }
        return imageLabel;
    }


}
