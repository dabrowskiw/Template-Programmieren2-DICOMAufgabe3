package org.htw.prog2.aufgabe2.gui;

import org.htw.prog2.aufgabe2.DICOMImage;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

public class ImageListPanel extends JPanel {
    private DICOMImage image;
    private JPanel thumbnailPanel;
    private LinkedList<Integer> markedFrames;
    private MainFrame mainFrame;

    public ImageListPanel(int width, MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        JLabel name = new JLabel("Bilder");
        thumbnailPanel = new JPanel();
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layout);
        add(name);
        add(Box.createVerticalStrut(10));
        name.setAlignmentX(0);
        setPreferredSize(new Dimension(width, 400));
    }

    public void setImage(DICOMImage newImage) {
        image = newImage;
        markedFrames = new LinkedList<>();
        SeriesThumbnail seriesThumbnail = new SeriesThumbnail(image, getWidth(), 5);
        seriesThumbnail.setAlignmentX(0);
        seriesThumbnail.setAlignmentY(0);
        seriesThumbnail.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                seriesThumbnail.setSelected(true);
                mainFrame.setDetailSeries(image);
//                repaint();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) { }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) { }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) { }

            @Override
            public void mouseExited(MouseEvent mouseEvent) { }
        });
        add(seriesThumbnail, BorderLayout.LINE_START);
        add(Box.createVerticalStrut(5));
    }
}
