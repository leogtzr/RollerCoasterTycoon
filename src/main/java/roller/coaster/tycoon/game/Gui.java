package roller.coaster.tycoon.game;

import roller.coaster.tycoon.guest.GuestMover;
import roller.coaster.tycoon.toolbox.LoadSaveWindow;
import roller.coaster.tycoon.world.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

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

    private void jPanel1MouseDragged(MouseEvent evt) {
        world.setHighlightedTile(evt.getX(), evt.getY());
        world.dragAt(evt.getX(), evt.getY(), evt.getModifiers());
    }

    private void resetButtonActionPerformed(ActionEvent evt) {
        world.reset();
    }

    private void jPanel1MouseClicked(MouseEvent evt) {
        try {
            world.pressAndClickAt(evt.getX(), evt.getY(), evt.getModifiers());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }

    private void jPanel1MousePressed(MouseEvent evt) {
        world.pressAt(evt.getX(), evt.getY(), evt.getModifiers());
    }

    private void jPanel1MouseMoved(MouseEvent evt) {
        world.setHighlightedTile(evt.getX(), evt.getY());
    }

    private void formKeyTyped(KeyEvent evt) {
        if (world.getLoadSaveWindow().isEnabled()) {
            LoadSaveWindow.getWRITER().addToString(evt.getKeyChar());
        } else {
            switch (evt.getKeyChar()) {
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
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        panel.setDoubleBuffered(false);
        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }

            public void mousePressed(MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });
        panel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }

            public void mouseMoved(MouseEvent evt) {
                jPanel1MouseMoved(evt);
            }
        });

        resetButton.setText("Reset");
        resetButton.setFocusable(false);
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

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
