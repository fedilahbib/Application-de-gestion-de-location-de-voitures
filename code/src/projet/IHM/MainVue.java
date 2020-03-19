/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.IHM;

import projet.IHM.VoitureJInternalFrame;
import projet.IHM.ValidJInternalFrame;
import projet.IHM.LocationJInternalFrame;
import projet.IHM.CovoitJInternalFrame;
import projet.IHM.ClientJInternalFrame;
import com.sun.corba.se.spi.ior.MakeImmutable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.IconUIResource;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.plaf.metal.MetalInternalFrameUI;
import javax.swing.plaf.metal.MetalLookAndFeel;

/**
 *
 * @author fedi
 */
public class MainVue extends JFrame {
      //========================== InternalFrame =====================================
  public ClientJInternalFrame frameClient=new ClientJInternalFrame("Gestion de client"
          ,true,true,true,true);
private VoitureJInternalFrame frameVoiture= new VoitureJInternalFrame("Gestion de voiture"
        ,true,true,true,true);
   private LocationJInternalFrame frameLocation= new LocationJInternalFrame("Gestion de location "
           ,true,true,true,true);
   private CovoitJInternalFrame frameCovoit=new CovoitJInternalFrame("Gestion de covoiturage"
           ,true,true,true,true);
   private ValidJInternalFrame frameValid= new ValidJInternalFrame("Validation"
           ,true,true,true,true);
   //========================== Color ==========================================

  public static ImageIcon Ivalide = new ImageIcon("b6.png");
   public static ImageIcon Iclient = new ImageIcon("b1.png");   
   public static ImageIcon Ivoiture = new ImageIcon("b2.png");
   public static ImageIcon Ilocation = new ImageIcon("b3.png");
   public static ImageIcon Icoviturage = new ImageIcon("b4.png");
public static JButton bclient=new JButton(Iclient);
public static JButton blocation=new JButton(Ilocation);
public static JButton bcoviturage=new JButton(Icoviturage);
public static JButton bvalide=new JButton(Ivalide);  
public static JButton bvoiture=new JButton(Ivoiture);
   
      //========================== Color =====================================


   //========================== Panel  ========================================

private JPanel cont;
private JPanel westCont=new JPanel();
private JPanel eastCont=new JPanel();
private JPanel northCont=new JPanel();
private JPanel southCont=new JPanel();
private JPanel panelVide=new JPanel();
   //========================== Layout ========================================

private GridLayout grid=new GridLayout(7,1);
   //========================== DesktopPane ===================================

private JDesktop centreCont=new JDesktop();

   //========================== Button  =====================================

//========================== Dimension ======================================
private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
private Dimension dimWest=new Dimension(screenSize.width/10,screenSize.height);
private Dimension dim=new Dimension(250, 185);
private Pane pane=new Pane();  
public  MainVue ()
    {
    super("location et covoiturage");
   intialeFrame(); 
   
Buttonzone();
intialInternalFrame(); 
   
        
centreCont.setBackground(new Color(245,245,245));
this.setVisible(true);
    }

private void Buttonzone()
    {
    
bclient.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                frameClient.setClosed(true);
                centreCont.remove(frameClient);
                if(!frameClient.isSelected())
                {
                    centreCont.add(frameClient);
                    frameClient.updateIHM();
                    
                    frameClient.setVisible(true);
                }
            } catch (PropertyVetoException ex) {
                Logger.getLogger(MainVue.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        
        }
    });
bvalide.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                frameValid.setClosed(true);
                centreCont.remove(frameValid);
                if(!frameValid.isSelected())
                {
                 ValidJInternalFrame.dbtoTableMarque();
                ValidJInternalFrame.dbtoTableVisite();
                ValidJInternalFrame.dbtoTableAss();


                    centreCont.add(frameValid);
                    frameValid.setVisible(true);
                }
            } catch (PropertyVetoException ex) {
                Logger.getLogger(MainVue.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
    });
blocation.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {

            try {
                                frameLocation.setClosed(true);
                                centreCont.remove(frameLocation);
                if(!frameLocation.isSelected())
                {                 
                    LocationJInternalFrame.updateIhmLocation();
 centreCont.add(frameLocation);
                frameLocation.setVisible(true);
                }
            } catch (PropertyVetoException ex) {
                Logger.getLogger(MainVue.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    });
    
bvoiture.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                frameVoiture.setClosed(true);
                centreCont.remove(frameVoiture);
                if(!frameVoiture.isSelected())
                {
                    VoitureJInternalFrame.updateIhmVoiture();
                   VoitureJInternalFrame. marqueCombo();
                    centreCont.add(frameVoiture);
                    frameVoiture.setVisible(true);
                }
            } catch (PropertyVetoException ex) {
                Logger.getLogger(MainVue.class.getName()).log(Level.SEVERE, null, ex);
            }
}
    });
bcoviturage.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {

            try {
                frameCovoit.setClosed(true);
                centreCont.remove(frameCovoit);
                if(!frameCovoit.isSelected())
                {
                    CovoitJInternalFrame.dbtoTableCovoi();
                    centreCont.add(frameCovoit);
                    frameCovoit.setVisible(true);
                }
            } catch (PropertyVetoException ex) {
                Logger.getLogger(MainVue.class.getName()).log(Level.SEVERE, null, ex);
            }
                             
        }

        
    });


    }
private void intialeFrame()
  {
  
   this.setSize(screenSize.width,screenSize.height);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         cont=(JPanel) this.getContentPane();
   cont.add(centreCont,BorderLayout.CENTER);
    cont.add(pane,BorderLayout.WEST);
    pane.setPreferredSize(new Dimension(200, 900));
    //cont.add(northCont,BorderLayout.NORTH);
   // cont.add(southCont,BorderLayout.SOUTH);
    //cont.add(eastCont,BorderLayout.EAST);
  
  } 

private void intialInternalFrame() {
 //centreCont.add(frameClient);
  //centreCont.add(frameVoiture);
 centreCont.add(frameLocation);
 //centreCont.add(frameCovoit);
   //centreCont.add(frameValid);

     frameClient.setBounds(0 * 25, 0 * 25, 950, 450);
   frameVoiture.setBounds(1* 25, 1 * 25, 950, 450);
      frameLocation.setBounds(2* 25, 2* 25, 950, 450);
   frameCovoit.setBounds(3* 25, 3* 25, 950, 450);
   frameValid.setBounds(4* 25, 4* 25, 950, 450);


frameLocation.setVisible(true);    
  // frame2.setVisible(true);

    }
private JInternalFrame makeInternalFrame(String ch,Boolean b1,Boolean b2,Boolean b3,Boolean b4) {
   /* ***************************************************************************
=====>>> makeInternalFrame permet de créer Internal Frame
=====>>> source code  :
 https://stackoverflow.com/questions/45069808/removing-dots-in-jinternalframe-title-bar
  */  

    
    return new JInternalFrame(ch, b1, b2, b3,b4) {
     
        @Override public void updateUI() {
        super.updateUI();
        setUI(new MetalInternalFrameUI(this) {
          @Override protected JComponent createNorthPane(JInternalFrame w) {
            BasicInternalFrameTitlePane p = new BasicInternalFrameTitlePane(w) {
              @Override public Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                d.height = 24;
                return d;
              }
              
              @Override public void createButtons() {
                super.createButtons();
                Arrays.asList(closeButton, maxButton, iconButton).forEach(b -> {
                  b.setContentAreaFilled(false);
                  b.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
                });
              }
            };
            p.setBorder(BorderFactory.createMatteBorder(
                0, 0, 1, 0, MetalLookAndFeel.getPrimaryControlDarkShadow()));
            return p;
          }
        });
      }
  
    };
  }
    
public class Pane extends JPanel{



    public  Pane()
    {
        bclient.setPreferredSize(new Dimension(200, 160));
            bvoiture.setPreferredSize(new Dimension(200, 160));
         blocation.setPreferredSize(new Dimension(200, 160));
  bcoviturage.setPreferredSize(new Dimension(200, 160));
   bvalide.setPreferredSize(new Dimension(200, 160));
        FlowLayout f=new FlowLayout();
        f.setVgap(0);
        this.setLayout(f);
         this.add(bclient);   
      this.add( bvoiture);   
   this.add(blocation);  

   this.add(bcoviturage);  
         this.add(bvalide);    

    }
  
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g.create();

        Image img = new ImageIcon(getClass().getResource("/projet/bar.png")).getImage();

        graphics.drawImage(img, 0, 0, getWidth(), getHeight(), null);


        graphics.dispose();
    }
   
}


}
