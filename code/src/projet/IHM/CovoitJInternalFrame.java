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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.plaf.metal.MetalInternalFrameUI;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import projet.DAo.CovoiturageDAO;
import projet.DAo.VoyageurDAO;
import static projet.IHM.LocationJInternalFrame.mLocation;
import projet.metier.Covoiturage;
import projet.metier.Vehicule;
import projet.metier.Voyageur;

/**
 *
 * @author fedi
 */
public class CovoitJInternalFrame   extends JInternalFrame  {
  //=======================
  public static  CovoiturageDAO covoiDB=new CovoiturageDAO();
   public static  VoyageurDAO  voyaDB=new VoyageurDAO();
    
    //============================     Panel     ==========================
private JPanel contPane;
private JPanel cont=new JPanel();
private JPanel ligne1=new JPanel();
private JPanel ligne2=new JPanel();
private JPanel ligne3=new JPanel();

//============================     JLabel     ==========================

private JLabel numCovLab=new JLabel("N°Covoiturage");
private JLabel voyageurLab =new JLabel("Nom et prenom de  voyageur");
private JLabel voyageurTelLab =new JLabel("N° Telvoyageur");

//============================   JTextField  ==========================

private JTextField numCovtext =new JTextField(20);
private  JTextField voyageurText =new JTextField(20);
private  JTextField voyageurTelText =new JTextField(12);

//============================     Button     ==========================

private JButton bconsulter=new JButton("Consulter Covoiturage ");
private JButton bsupprimer=new JButton("Supprimer Covoiturage");
private JButton bsupprimerVoy=new JButton("Supprimer voyageur");
private JButton bAjouterVoy=new JButton("Ajouter voyageur");
private JButton bconsulterVoy=new JButton("Consulter voyageur");
//===================================== Table 1 ==========================

private JTable tcovoi = new JTable();
public static  DefaultTableModel mcovoi = new DefaultTableModel();
private Object[] columnsName1 = new Object[7];
private Object[] rowData1 = new Object[7];
private JScrollPane pane1 = new JScrollPane(tcovoi);

//===================================== Table 2  ==========================

private JTable tVoyageur = new JTable();
public static  DefaultTableModel mVoyageur= new DefaultTableModel();
private Object[] columnsName2 = new Object[3];
private Object[] rowData2 = new Object[3];
private JScrollPane pane2 = new JScrollPane(tVoyageur);   

public  CovoitJInternalFrame(String ch,Boolean b1,Boolean b2,Boolean b3,Boolean b4) {
    super(ch, b1, b2, b3,b4);
initCont();
  initTable1();
  initTable2();
  buttonAction();
    }
boolean  verifierDonne()
{  
    if((numCovtext.getText().length()==0))
            {
JOptionPane.showMessageDialog(rootPane, "remplir numero covoiturage ");
            return false; 
            }else{
if(new CovoiturageDAO().Select(new Covoiturage(numCovtext.getText()))==null){
    {
        JOptionPane.showMessageDialog(rootPane, "le  covoiturage n existe pas");
        return false; 
    }
}
}

    
    
    if(voyageurText.getText().length()==0)
            {
JOptionPane.showMessageDialog(rootPane, "verfier le nom et le prenom de utilisateur ");
            return false; 
            }


if((voyageurTelText.getText().length()==0)&&(voyageurTelText.getText().length()<15))
            {
JOptionPane.showMessageDialog(rootPane, "verfier le tel de voyageur");
            return false; 
            }

if(new VoyageurDAO().Select(new Voyageur(numCovtext.getText(), voyageurTelText.getText()))!=null)
    {
        JOptionPane.showMessageDialog(rootPane, "le voyageur est deja existe ");
        return false; 
    }else{
    int nbplace=0;
         String sql = "SELECT count(*) as nb FROM Voyageur where  IdCovoiturage =" +"'"+numCovtext.getText()+"'";
    ResultSet rs1=new VoyageurDAO().selectCondtion(sql);
        try {
            rs1.next();
                nbplace=rs1.getInt("nb");
        } catch (SQLException ex) {
            Logger.getLogger(CovoitJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
Covoiturage rs=new CovoiturageDAO().Select(new Covoiturage(numCovtext.getText()));
if(rs.GetnbPlaceDispoInteger()==nbplace)
{            JOptionPane.showMessageDialog(rootPane, "le nombre des place est saturee ");
return false;
}

}

return true;
}
     
public static void dbtoTableCovoi(){
           
   if (mcovoi.getRowCount() > 0) {
    for (int i = mcovoi.getRowCount() - 1; i > -1; i--) {
        mcovoi.removeRow(i);
    }}
   if (mVoyageur.getRowCount() > 0) {
    for (int i = mVoyageur.getRowCount() - 1; i > -1; i--) {
        mVoyageur.removeRow(i);
    }}

    
    
    ResultSet rs =covoiDB.selectTable();
            ResultSet rs2 =voyaDB.selectTable();
            Object[] rowData1 = new Object[7];
             Object[] rowData2 = new Object[3];

        try {
            while (rs.next())
            {
         rowData1[0] =rs.getString("IdCovoiturage");
         rowData1[1] =   rs.getString("IdLocation");
         rowData1[2] =  rs.getString("DateDebutLocation");
         rowData1[3] = rs.getString("DateFinLocation");
         rowData1[4] =     rs.getString("lieuDepart"); 
         rowData1[5] =   rs.getString("lieuDestination"); 
         rowData1[6] =     rs.getString("nbPlaceDispo"); 
         mcovoi.addRow(rowData1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                 
 
        try {
            while (rs2.next())
            {
         rowData2[0] =rs2.getString("IdCovoiturage");
         rowData2[1] =rs2.getString("NomEtPrenom");
         rowData2[2] =rs2.getString("tel");
         mVoyageur.addRow(rowData2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
        }        
}
public void buttonAction(){
 bconsulter.addActionListener(new ActionListener() 
 {
        @Override
        public void actionPerformed(ActionEvent ae) {
            int i,ind=-1;
           
            if(numCovtext.getText().length()>0)
            {
                       for(i=0;i<mcovoi.getRowCount();i++)
                         {
                                if(numCovtext.getText().equals(mcovoi.getValueAt(i, 0))==true)
                            {
                               ind=i;break;
                            }
                         }
            if(ind>=0)
                 tcovoi.setRowSelectionInterval(ind, ind);
             else 
                 JOptionPane.showMessageDialog(cont, "Covoiturage n existe pas");
            }else{           
                 JOptionPane.showMessageDialog(cont, "saisir le numero de Covoiturage pour consulter le Covoiturage il vous plait");
                 }
        }
});

 

 mcovoi.addTableModelListener(new TableModelListener() {
        @Override
        public void tableChanged(TableModelEvent tme) {
                 covoiDB.update(getRowSelected()); 
            }
});
     
 bsupprimer.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
           covoiDB.delete(getRowSelected()); 
           int ind=tcovoi.getSelectedRow();
                if( ind>-1)
                    mcovoi.removeRow(ind);
            else
                    JOptionPane.showMessageDialog(cont, "selectionner un coviturage ");
}});

 bconsulterVoy.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            int i,ind=-1;
           
            if(voyageurTelText.getText().length()>0)
            {
                       for(i=0;i<mVoyageur.getRowCount();i++)
                         {
                                if(voyageurTelText.getText().equals(mVoyageur.getValueAt(i, 0))==true)
                            {
                               ind=i;break;
                                  }
            }
                    if(ind>=0)
                        tVoyageur.setRowSelectionInterval(ind, ind);
                    else 
                        JOptionPane.showMessageDialog(cont, "voyageur n existe pas");
            }else
            {           
    JOptionPane.showMessageDialog(cont, "saisir le numero tel de voyageur pour consulter le voyageur il vous plait");
            }
        }
});
bsupprimerVoy.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
     voyaDB.delete(getRowSelected2()); 
int ind=tVoyageur.getSelectedRow();
if( ind>-1)
    mVoyageur.removeRow(ind);
else
    JOptionPane.showMessageDialog(cont, "selectionner un coviturage ");
        
     
     }});
bAjouterVoy.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if(verifierDonne()){
         rowData2[0] = numCovtext.getText();
        rowData2[1] =  voyageurText.getText();
        rowData2[2] =   voyageurTelText.getText();
       
        mVoyageur.addRow(rowData2);
 
        voyaDB.insert(new Voyageur(numCovtext.getText(), 
        voyageurText.getText(), voyageurTelText.getText()));
        
       numCovtext.setText("");
       voyageurText.setText("");
       voyageurTelText.setText("");
        }}
    });
mVoyageur.addTableModelListener(new TableModelListener() {
        @Override
        public void tableChanged(TableModelEvent tme) {

                 voyaDB.update(getRowSelected2()); 
            }
    });    

}
public Covoiturage getRowSelected(){
     
    try{
         return new Covoiturage(
        (String) mcovoi.getValueAt(tcovoi.getSelectedRow(), 0),
        (String) mcovoi.getValueAt(tcovoi.getSelectedRow(),1),
         (String) mcovoi.getValueAt(tcovoi.getSelectedRow(),2),
         (String) mcovoi.getValueAt(tcovoi.getSelectedRow(),3),
         (String) mcovoi.getValueAt(tcovoi.getSelectedRow(), 4),
         (String) mcovoi.getValueAt(tcovoi.getSelectedRow(), 5),
         (String) mcovoi.getValueAt(tcovoi.getSelectedRow(),6)
         );
     }catch(Exception e)
        {
            return null;
                }
     }    
public Voyageur getRowSelected2(){
     try{
         return new Voyageur(
        (String) mVoyageur.getValueAt(tVoyageur.getSelectedRow(), 0),
         (String) mVoyageur.getValueAt(tVoyageur.getSelectedRow(),1),
         (String) mVoyageur.getValueAt(tVoyageur.getSelectedRow(),2)
         );
     }catch(Exception e)
        {
            return null;
                }
     
     }   
public void initTable2(){
      
        columnsName2[0] = "N°Covoiturage";
        columnsName2[1] = "Nom et prenom de  voyageur";
        columnsName2[2] = "N° Telvoyageur";
        
     
                
        mVoyageur.setColumnIdentifiers(columnsName2);
 
      tVoyageur.setModel(mVoyageur);          
    
      pane2.setPreferredSize(new Dimension(500,300));   
      ligne3.add(pane2,BorderLayout.CENTER);
}
public void initCont(){
           contPane=(JPanel) this.getContentPane();
  contPane.add(cont,BorderLayout.CENTER);
  
 cont.add(ligne1);
  cont.add(ligne2);
  cont.add(ligne3);
   

ligne1.add(numCovLab);
ligne1.add(numCovtext);
ligne1.add(voyageurLab);
ligne1.add(voyageurText);
ligne1.add(voyageurTelLab);
ligne1.add(voyageurTelText);



      
   ligne2.add(bconsulter);
   ligne2.add(bsupprimer);   
   ligne2.add(bAjouterVoy);
      ligne2.add(bconsulterVoy);
   ligne2.add(bsupprimerVoy);

       }
public void initTable1(){

        columnsName1[0] = "N°Covoiturage";
        columnsName1[1] = "Cin Client";
        columnsName1[2] = "Date Debut";
        columnsName1[3] = "Date Fin";
        columnsName1[4] = "Depart";
        columnsName1[5] = "Destination";    
        columnsName1[6] = "N°de place";    

                              
        mcovoi.setColumnIdentifiers(columnsName1);
   
        tcovoi.setModel(mcovoi);
                

         
pane1.setPreferredSize(new Dimension(700,300));
        ligne3.add(pane1,BorderLayout.CENTER);
  

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
