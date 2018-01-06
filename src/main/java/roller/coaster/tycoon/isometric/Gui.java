package roller.coaster.tycoon.isometric;

import roller.coaster.tycoon.guest.GuestMover;
import roller.coaster.tycoon.toolbox.LoadSaveWindow;
import roller.coaster.tycoon.world.World;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Gui extends javax.swing.JFrame implements Runnable {

    private World world;
    private Image bufferImage;
    private Graphics bufferGraphics;
    private int bufferWidth, bufferHeight;


    public Gui() {
        initComponents();
        try {
            world = new World();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }

        Thread thread = new Thread(new GuestMover(world));
        thread.setDaemon(true);
        thread.start();

    }

    private void draw(Graphics g) {

        if (bufferWidth != getSize().width || bufferHeight != getSize().height || bufferImage == null || bufferGraphics
                == null) {
            resetBuffer();
        }

        if (bufferGraphics != null) {

            //this clears the offscreen image, not the onscreen one
            bufferGraphics.clearRect(0, 0, bufferWidth, bufferHeight);

            //calls the paintbuffer method with
            //the offscreen graphics as a param
            paintBuffer(bufferGraphics);

            //we finaly paint the offscreen image onto the onscreen image
            g.drawImage(bufferImage, 0, 0, this);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel() {
            public void paint(Graphics g) {
                super.paint(g);
                draw(g);
            }
        };
        resetButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Not RollerCoaster");
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        jPanel1.setDoubleBuffered(false);
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }

            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanel1MouseMoved(evt);
            }
        });

        resetButton.setText("Reset");
        resetButton.setFocusable(false);
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(634, Short.MAX_VALUE)
                                .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(resetButton)
                                .addContainerGap(547, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jPanel1MouseDragged
    {//GEN-HEADEREND:event_jPanel1MouseDragged
        world.setHighlightedTile(evt.getX(), evt.getY());

        world.dragAt(evt.getX(), evt.getY(), evt.getModifiers());
    }//GEN-LAST:event_jPanel1MouseDragged

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_resetButtonActionPerformed
    {//GEN-HEADEREND:event_resetButtonActionPerformed
        world.reset();
    }//GEN-LAST:event_resetButtonActionPerformed

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jPanel1MouseClicked
    {//GEN-HEADEREND:event_jPanel1MouseClicked
        try {
            world.pressAndClickAt(evt.getX(), evt.getY(), evt.getModifiers());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jPanel1MousePressed
    {//GEN-HEADEREND:event_jPanel1MousePressed
        world.pressAt(evt.getX(), evt.getY(), evt.getModifiers());
    }//GEN-LAST:event_jPanel1MousePressed

    private void jPanel1MouseMoved(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jPanel1MouseMoved
    {//GEN-HEADEREND:event_jPanel1MouseMoved
        world.setHighlightedTile(evt.getX(), evt.getY());
    }//GEN-LAST:event_jPanel1MouseMoved

    private void formKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_formKeyTyped
    {//GEN-HEADEREND:event_formKeyTyped
        if (world.getLoadSaveWindow().isEnabled()) {
            LoadSaveWindow.getWRITER().addToString(evt.getKeyChar());
        } else {
            switch (evt.getKeyChar()) {
                case ('1'): {
                    world.clickToolBox(15, 10);
                    break;
                }
                case ('2'): {
                    world.clickToolBox(45, 10);
                    break;
                }
                case ('3'): {
                    world.clickToolBox(75, 10);
                    break;
                }
                case ('4'): {
                    world.clickToolBox(105, 10);
                    break;
                }
                case ('5'): {
                    world.clickToolBox(135, 10);
                    break;
                }
            }
        }
    }//GEN-LAST:event_formKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton resetButton;
    // End of variables declaration//GEN-END:variables

    public void run() {
        while (true) {
            synchronized (jPanel1) {
                jPanel1.repaint();
                jPanel1.getGraphics().dispose();
                try {
                    Thread.sleep(20);
                } catch (InterruptedException ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                }
            }
        }
    }

    private void resetBuffer() {
        // always keep track of the image size

        bufferWidth = getSize().width;
        bufferHeight = getSize().height;

        //    clean up the previous image

        if (bufferGraphics != null) {
            bufferGraphics.dispose();
            bufferGraphics = null;
        }
        if (bufferImage != null) {
            bufferImage.flush();
            bufferImage = null;
        }
        System.gc();

        //    create the new image with the size of the panel

        bufferImage = createImage(bufferWidth, bufferHeight);
        bufferGraphics = bufferImage.getGraphics();
    }

    private void paintBuffer(Graphics bufferGraphics) {
        world.draw(bufferGraphics);
    }
}
