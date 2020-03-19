/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.IHM;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.plaf.metal.MetalInternalFrameUI;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import projet.DAo.ClientDAO;
import projet.DAo.VehiculeDAO;
import projet.metier.Client;
import projet.metier.Vehicule;
import sun.font.EAttribute;

/**
 *
 * @author fedi
 */

public class ClientJInternalFrame extends JInternalFrame {
    
    //========================  Client Dao
    
    public static ClientDAO clientBD=new ClientDAO();
    
    //============================ JPanel ==========================
  private JPanel contPane;
  private  JPanel cont=new JPanel();
  private JPanel ligne1=new JPanel();
  private JPanel ligne2=new JPanel();
  private  JPanel ligne3=new JPanel();
 private JPanel ligne4=new JPanel();
//============================   JTextField  ==========================
 private JTextField nomText =new JTextField(20);
private JTextField prenomText =new JTextField(20);
private  JTextField adresseText =new JTextField(50);
 private JTextField cinText =new JTextField(20);
private  JTextField telText =new JTextField(12);
//============================     JLabel     ==========================

private JLabel prenomLab =new JLabel("Prenom");
private JLabel cinLab =new JLabel("Cin");
private JLabel adresseLab =new JLabel("Adresse");
private  JLabel telLab =new JLabel("Tel");
private JLabel nomLab =new JLabel("Nom");

//============================     Button     ==========================

private JButton bconsulter=new JButton("Consulter Client");
private JButton bajouter=new JButton("Ajouter Client");
private JButton bsupprimer=new JButton("Supprimer Client");
//private JButton bmodfier=new JButton("Modifier Client");
//===================================== Table   ==========================

public static JTable tclient = new JTable();
public static DefaultTableModel mclient = new DefaultTableModel();
private Object[] columnsName = new Object[5];
public static  Object[] rowData = new Object[5];
private JScrollPane pane = new JScrollPane(tclient);
private Dimension f=super.getSize();


public  ClientJInternalFrame(String ch,Boolean b1,Boolean b2,Boolean b3,Boolean b4) {
    super(ch, b1, b2, b3,b4);
    super.pack();

initCont();
initTable();
buttonAction();
          }

public static void updateIHM()
{
   if (mclient.getRowCount() > 0) {
    for (int i = mclient.getRowCount() - 1; i > -1; i--) {
        mclient.removeRow(i);
    }
} 
        ResultSet rs =clientBD.selectTable();
        try {
            while (rs.next())
            {
                
                   
         rowData[0] =rs.getString("cin");
         rowData[1] =      rs.getString("nom");
         rowData[2] =  rs.getString("prenom");
         rowData[3] =   rs.getString("adresse");
         rowData[4] =   rs.getString("tel");                             
        mclient.addRow(rowData);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

}


boolean  verifierDonne()
{
    

    if((cinText.getText().length()==0)||(cinText.getText().length()>10))
            {
JOptionPane.showMessageDialog(rootPane, "Saisir le cin ");
            return false; 
            }else{
    if(new ClientDAO().Select(new Client(cinText.getText()))!=null)
    {
    JOptionPane.showMessageDialog(rootPane, "Le client est déjà existe");
            return false; 
    }
    
    }

if((nomText.getText().length()==0))
            {
JOptionPane.showMessageDialog(rootPane, "Saisir le nom ");
            return false; 
            }

if((prenomText.getText().length()==0))
            {
JOptionPane.showMessageDialog(rootPane, "Saisir le prenom ");
            return false; 
            }
if((adresseText.getText().length()==0))
            {
JOptionPane.showMessageDialog(rootPane, "Saisir le adresse ");
            return false; 
            }

if((telText.getText().length()==0))
            {
JOptionPane.showMessageDialog(rootPane, "Saisir le tel");
            return false; 
            }

    
return true;
}
     
 public void dbtoTableClient(){
            ResultSet rs =clientBD.selectTable();
 
        try {
            while (rs.next())
            {
                
                   
         rowData[0] =rs.getString("cin");
         rowData[1] =      rs.getString("nom");
         rowData[2] =  rs.getString("prenom");
         rowData[3] =   rs.getString("adresse");
         rowData[4] =   rs.getString("tel");                             
        mclient.addRow(rowData);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
}
     public Client getRowSelected()
     {
     try{
         return new  Client((String) mclient.getValueAt(tclient.getSelectedRow(), 0),
                 (String) mclient.getValueAt(tclient.getSelectedRow(), 1),
                 (String) mclient.getValueAt(tclient.getSelectedRow(), 2),
                 (String) mclient.getValueAt(tclient.getSelectedRow(), 3),
                 (String) mclient.getValueAt(tclient.getSelectedRow(), 4));
     }catch(Exception e)
        {
            return null;
                }
     
     }    
     public void buttonAction()
     {
     
bajouter.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {  
if(verifierDonne()){
         rowData[0] = cinText.getText();
        rowData[1] =  nomText.getText();
        rowData[2] =   prenomText.getText();
        rowData[3] =   adresseText.getText();
        rowData[4] = telText.getText();
        mclient.addRow(rowData);
 
 clientBD.insert(new Client(cinText.getText(),nomText.getText(),
    prenomText.getText(),adresseText.getText(),telText.getText())); 
         
 cinText.setText("");
      nomText.setText("");
       prenomText.setText("");
        adresseText.setText("");
     telText.setText("");
}
        }
    });
 bconsulter.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            int i,ind=-1;
           
            if(cinText.getText().length()>1)
            {
                       for(i=0;i<mclient.getRowCount();i++)
                         {
                                if(cinText.getText().equals(mclient.getValueAt(i, 0))==true)
                            {
                               ind=i;break;
                                  }
                            }
     if(ind>=0)
             tclient.setRowSelectionInterval(ind, ind);
     else 
         JOptionPane.showMessageDialog(cont, "client n existe pas");

     
        }else{              JOptionPane.showMessageDialog(cont, "saisir le cin pour consulter le client il vous plait");

            }
        }
    });
 /*
 bmodfier.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
      
        }
    });
 */

 mclient.addTableModelListener(new TableModelListener() {
        @Override
        public void tableChanged(TableModelEvent tme) {

  clientBD.update(getRowSelected()); 
            }
    });
     
 bsupprimer.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
    clientBD.delete(getRowSelected()); 
    mclient.removeRow(tclient.getSelectedRow()); }});
     
     }
public void initCont()
{

    contPane=(JPanel) this.getContentPane();
  contPane.add(cont,BorderLayout.CENTER);
 
 cont.add(ligne1);
  cont.add(ligne2);
  cont.add(ligne3);
  cont.add(ligne4);
  ligne1.add(cinLab);
   ligne1.add(cinText);
  ligne1.add(nomLab);
   ligne1.add(nomText);
   ligne1.add(prenomLab);
   ligne1.add(prenomText);
    ligne2.add(adresseLab);
   ligne2.add(adresseText);
       ligne2.add(telLab);
   ligne2.add(telText);
   ligne3.add(bajouter);
   ligne3.add(bconsulter);
   ligne3.add(bsupprimer);
      
}     

public void initTable()
{
         columnsName[0] = "Cin";
        columnsName[1] = "Nom";
        columnsName[2] = "Prenom";
        columnsName[3] = "Adresse";
        columnsName[4] = "Tel";   
        mclient.setColumnIdentifiers(columnsName);
       
        tclient.setModel(mclient);      
pane.setPreferredSize(new Dimension(900,500));
        
        ligne4.add(pane,BorderLayout.CENTER);
        
}

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
  
    
}
