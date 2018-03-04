/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usacweb;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Aroche
 */
public class Redimension extends javax.swing.JPanel {

    /**
     * Creates new form SSCCE
     */
     public Redimension()
    {
        initComponents();
        JPanel blue = new JPanel();
        blue.setBackground( Color.BLUE );
        blue.setPreferredSize( new Dimension(500, 100) );

        Box box = Box.createHorizontalBox();

        JPanel green = new JPanel();
        green.setBackground( Color.GREEN );
        green.setPreferredSize( new Dimension(200, 100) );
        green.setMaximumSize( green.getPreferredSize() );
        box.add( green );

        JPanel cyan = new JPanel();
        cyan.setBackground( Color.CYAN );
        cyan.setPreferredSize( new Dimension(300, 100) );
        cyan.setMinimumSize( cyan.getPreferredSize() );
        box.add( cyan );

        setLayout( new BorderLayout() );
        add(blue, BorderLayout.NORTH);
        add(box, BorderLayout.CENTER);
    }

    private static void createAndShowGUI()
    {
        JFrame frame = new JFrame("SSCCE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Redimension());
        frame.setLocationByPlatform( true );
        frame.pack();
        frame.setVisible( true );
    }

    public static void main(String[] args)
    {
        EventQueue.invokeLater(Redimension::createAndShowGUI);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
