package org.htw.prog2.aufgabe2.gui;

import org.htw.prog2.aufgabe2.DICOMImage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SeriesThumbnail extends JPanel {
    private DICOMImage dicomimage;
    private BufferedImage image;
    private int border;
    private boolean selected;

    public SeriesThumbnail(DICOMImage dicomimage, int size, int border) {
        this.dicomimage = dicomimage;
        this.selected = false;
        this.border = border;
        this.image = new BufferedImage(size-2*border, size-2*border, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = this.image.getGraphics();
        g.drawImage(dicomimage.getFrame(0).getImage().getScaledInstance(size-2*border, size-2*border, BufferedImage.SCALE_SMOOTH), 0, 0, null);
        g.dispose();
        setPreferredSize(new Dimension(size, size));
        setMaximumSize(new Dimension(size, size));
    }

    public void paintComponent(Graphics g) {
        g.drawImage(image, border, border, null);
        g.setColor(Color.LIGHT_GRAY);
        g.drawString(getDescription(), border+2, image.getHeight()-2);
        if(selected) {
            g.setColor(Color.BLUE); {
                g.drawRect(border-1, border-1, image.getWidth()+1, image.getHeight()+1);
                g.drawRect(border-2, border-2, image.getWidth()+3, image.getHeight()+3);
            }
        }
    }

    public String getDescription() {
        return "Serie (" + dicomimage.getNumFrames() + ")";
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
