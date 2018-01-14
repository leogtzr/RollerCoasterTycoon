package com.craftinginjava.rollercoastertycoon.game;

import com.craftinginjava.rollercoastertycoon.toolbox.LoadSaveWindow;
import com.craftinginjava.rollercoastertycoon.world.World;
import com.craftinginjava.rollercoastertycoon.guest.GuestMover;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Gui extends JFrame implements Runnable {

    private World world;
    private Image bufferImage;
    private Graphics bufferGraphics;
    private int bufferWidth;
    private int bufferHeight;
    private JPanel panel;
    private JButton resetButton;

    Gui(World world) {
        initComponents();
        this.world = world;
        startGuiThread();
    }

    private void startGuiThread() {
        Thread thread = new Thread(new GuestMover(world));
        thread.setDaemon(true);
        thread.start();
    }

    private void draw(Graphics g) {
        if (shouldBufferBeReset()) {
            resetBuffer();
        }

        if (bufferGraphics != null) {
            clearOffscreenImage();
            paintBuffer(bufferGraphics);
            paintOffscreenImageOntoOnscreenImage(g);
        }
    }

    private boolean shouldBufferBeReset() {
        return bufferWidth != getSize().width || bufferHeight != getSize().height || bufferImage == null || bufferGraphics
                == null;
    }

    private void paintOffscreenImageOntoOnscreenImage(Graphics g) {
        g.drawImage(bufferImage, 0, 0, this);
    }

    private void clearOffscreenImage() {
        bufferGraphics.clearRect(0, 0, bufferWidth, bufferHeight);
    }

    private void jPanel1MouseDragged(MouseEvent e) {
        world.setHighlightedTile(e.getX(), e.getY());
        world.dragAt(e.getX(), e.getY(), e.getModifiers());
    }

    private void resetButtonActionPerformed(ActionEvent e) {
        world.reset();
    }

    private void jPanel1MouseClicked(MouseEvent e) {
        try {
            world.pressAndClickAt(e.getX(), e.getY(), e.getModifiers());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }

    private void jPanel1MousePressed(MouseEvent e) {
        world.pressAt(e.getX(), e.getY(), e.getModifiers());
    }

    private void jPanel1MouseMoved(MouseEvent e) {
        world.setHighlightedTile(e.getX(), e.getY());
    }

    private void formKeyTyped(KeyEvent e) {
        if (world.getLoadSaveWindow().isEnabled()) {
            LoadSaveWindow.getWRITER().addToString(e.getKeyChar());
        } else {
            switch (e.getKeyChar()) {
                case ('1'): {
                    world.clickOnToolBox(15, 10);
                    break;
                }
                case ('2'): {
                    world.clickOnToolBox(45, 10);
                    break;
                }
                case ('3'): {
                    world.clickOnToolBox(75, 10);
                    break;
                }
                case ('4'): {
                    world.clickOnToolBox(105, 10);
                    break;
                }
                case ('5'): {
                    world.clickOnToolBox(135, 10);
                    break;
                }
            }
        }
    }

    public void run() {
        while (true) {
            synchronized (panel) {
                paintGame();
                takeABreakFromPainting();
            }
        }
    }

    private void takeABreakFromPainting() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }

    private void paintGame() {
        panel.repaint();
        panel.getGraphics().dispose();
    }

    private void resetBuffer() {
        updateImageSize();
        cleanUpPreviousImage();
        createNewPanelImage();
    }

    private void cleanUpPreviousImage() {
        if (bufferGraphics != null) {
            bufferGraphics.dispose();
            bufferGraphics = null;
        }
        if (bufferImage != null) {
            bufferImage.flush();
            bufferImage = null;
        }
    }

    private void updateImageSize() {
        bufferWidth = getSize().width;
        bufferHeight = getSize().height;
    }

    private void createNewPanelImage() {
        bufferImage = createImage(bufferWidth, bufferHeight);
        bufferGraphics = bufferImage.getGraphics();
    }

    private void paintBuffer(Graphics bufferGraphics) {
        world.draw(bufferGraphics);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        panel = new JPanel() {
            public void paint(Graphics g) {
                super.paint(g);
                draw(g);
            }
        };
        resetButton = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Not RollerCoaster");
        addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                formKeyTyped(e);
            }
        });

        panel.setDoubleBuffered(false);
        panel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                jPanel1MouseClicked(e);
            }

            public void mousePressed(MouseEvent e) {
                jPanel1MousePressed(e);
            }
        });
        panel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                jPanel1MouseDragged(e);
            }

            public void mouseMoved(MouseEvent e) {
                jPanel1MouseMoved(e);
            }
        });

        resetButton.setText("Reset");
        resetButton.setFocusable(false);
        resetButton.addActionListener(e -> resetButtonActionPerformed(e));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(jPanel1Layout);
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
                        .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pack();
        setVisible(true);
    }
}
