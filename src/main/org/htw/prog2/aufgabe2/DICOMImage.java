package org.htw.prog2.aufgabe2;

import com.pixelmed.dicom.AttributeList;
import com.pixelmed.display.SourceImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DICOMImage {
    private ArrayList<DICOMFrame> frames = new ArrayList<>();
    private String name;

    public DICOMImage(File infile, String name) {
        this.name = name;
        try {
            AttributeList fileattributes = new AttributeList();
            fileattributes.read(infile);
            SourceImage dcImage = new SourceImage(fileattributes);
            for(int i=0; i<dcImage.getNumberOfFrames(); i++) {
                BufferedImage image = dcImage.getBufferedImage(i);
                frames.add(new DICOMFrame(image));
            }
        } catch (Exception e) {
            System.out.println("Error reading image file " + infile.getAbsolutePath() + ": " + e.getMessage());
        }
    }

    public void writeFrames(int from, int to, boolean original, boolean edges, double edgeLightnessCutoff) {
        for(int i=from; i<frames.size() && i<to; i++) {
            if(original) {
                String outfilename = name + "_" + i + ".png";
                writeImage(frames.get(i).getImage(), outfilename);
            }
            if(edges) {
                String outfilename = name + "_" + i + "_edges.png";
                writeImage(frames.get(i).getEdges(edgeLightnessCutoff), outfilename);
            }
        }
    }

    private void writeImage(BufferedImage image, String filename) {
        File outfile = new File(filename);
        try {
            ImageIO.write(image, "png", outfile);
        } catch (IOException e) {
            System.out.println("Could not write file " + filename + ": " + e.getMessage());
        }
    }

    public int getNumFrames() {
        return 0;
    }

    public DICOMFrame getFrame(int num) {
        return null;
    }
}
