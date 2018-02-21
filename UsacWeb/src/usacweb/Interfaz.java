package usacweb;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.Box;
import javax.swing.JPanel;
import usacweb.PanelPrincipal;

/**
 *
 * @author Aroche
 */
public class Interfaz extends javax.swing.JFrame {

    int contPestania = 1;
    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension dim = tk.getScreenSize();
    public static int tam;
    //   JTabbedPane panelPestanias = new JTabbedPane();

    @Override
    public Dimension getSize() {
        return super.getSize(); //To change body of generated methods, choose Tools | Templates.
    }

    public Interfaz() {
        setTitle("USAC WEB");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(null);

        initComponents();
        iniciarComponentes();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Mas = new javax.swing.JButton();
        panelPestanias = new javax.swing.JTabbedPane();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 153, 153));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Mas.setText("+");
        Mas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MasActionPerformed(evt);
            }
        });
        getContentPane().add(Mas, new org.netbeans.lib.awtextra.AbsoluteConstraints(689, 0, -1, 30));

        panelPestanias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelPestaniasMouseClicked(evt);
            }
        });
        getContentPane().add(panelPestanias, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 716, 402));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo1.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -180, 2480, 1080));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void iniciarComponentes() {
        PanelPrincipal panel = new PanelPrincipal();
        panelPestanias.add("Pestania0", panel);
        // add(panelPestanias);
    }

    private void MasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MasActionPerformed
        Box box = Box.createHorizontalBox();
        PanelPrincipal panel = new PanelPrincipal();
        //  panel.setLayout(null);
        panel.setBackground(Color.BLACK);
        panel.setPreferredSize(new Dimension(30000, 30000));
        panel.setMaximumSize(panel.getPreferredSize());
        System.out.println("ancho = " + getSize().width);
        System.out.println("largo = " + getSize().height);

        box.add(panel);
        panelPestanias.addTab("Pestania" + contPestania, box);
        panelPestanias.setSelectedIndex(panelPestanias.getTabCount() - 1);
        contPestania++;
    }//GEN-LAST:event_MasActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized

        if (tam >= 2) {
            System.out.println("JFrame was resized");
            Dimension ventana = getSize();
            panelPestanias.setSize(ventana.width - 80, ventana.height - 130);

            System.out.println("ANCHO = " + getSize().width);
            System.out.println("LARGO = " + getSize().height);

//            for (int i = 0; i < panelPestanias.getTabCount(); i++) {
//                JPanel p = (JPanel) panelPestanias.getComponentAt(i);
//                p.setSize(new Dimension(ventana.width - 100, ventana.height - 150));
//               
//            }
        }
        tam++;
    }//GEN-LAST:event_formComponentResized

    private void panelPestaniasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelPestaniasMouseClicked
        //System.out.println("PESTANIA\n");

        if (tam >= 2) {
            System.out.println("JFrame was resized");
            Dimension ventana = getSize();
            panelPestanias.setSize(ventana.width - 80, ventana.height - 130);
        }
        tam++;
    }//GEN-LAST:event_panelPestaniasMouseClicked

    public void redimensionar() {

    }

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new Interfaz().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Mas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTabbedPane panelPestanias;
    // End of variables declaration//GEN-END:variables
}
