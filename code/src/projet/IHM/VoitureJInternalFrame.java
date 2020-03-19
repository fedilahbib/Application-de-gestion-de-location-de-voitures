/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.IHM;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.RescaleOp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.plaf.metal.MetalInternalFrameUI;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.table.DefaultTableModel;
import projet.DAo.AssuranceDAO;
import projet.DAo.MarqueDAO;
import projet.DAo.VehiculeDAO;
import projet.DAo.VisiteTechniqueDAO;
import static projet.IHM.ClientJInternalFrame.clientBD;
import static projet.IHM.ClientJInternalFrame.mclient;
import static projet.IHM.ClientJInternalFrame.rowData;
import projet.metier.Assurance;
import projet.metier.Client;
import projet.metier.Marque;
import projet.metier.Vehicule;
import projet.metier.VisiteTechnique;

/**
 *
 * @author fedi
 */
public class VoitureJInternalFrame extends JInternalFrame  {
    //=====================
    public static VehiculeDAO vehiculeDB=new VehiculeDAO();
     public static  MarqueDAO  marqueDB=new MarqueDAO();
     public static VisiteTechniqueDAO vtbd=new VisiteTechniqueDAO();
     public static  AssuranceDAO assbd=new AssuranceDAO();
          //========================== JLabel ==========================================
private JLabel matLab=new JLabel("Matricule Num");
private JLabel  marqueLab=new JLabel("Marque Num");
private JLabel  vtLab=new JLabel("Visite Technique Num");
private JLabel AssLab=new JLabel("Assurance Num");
   //========================== JTextField ==========================================
private JTextField matTex=new JTextField(15);
private JTextField  marqueTex=new JTextField(10);
private JTextField  vtTex=new JTextField(20);
private JTextField AssTex=new JTextField(20);
   //========================== JComboBox ==========================================
public static JComboBox marquecombo=new JComboBox();
   
   //========================== JButton ==========================================
private JButton bconsulter=new JButton("Consulter Véhicule");
private JButton bajouter=new JButton("Ajouter Véhicule");
private JButton bsupprimer=new JButton("Supprimer Véhicule");
//private JButton bmodfier=new JButton("Modifier Véhicule");

//========================== JPanel ==========================================
private JPanel contPane;
private JPanel cont=new JPanel();
private JPanel ligne1=new JPanel();
private JPanel ligne2=new JPanel();
private JPanel ligne3=new JPanel();
private JPanel ligne4=new JPanel();


   //========================== table Voiture  ================================
private JTable tVoiture = new JTable();
public static DefaultTableModel mVoiture = new DefaultTableModel();
private Object[] columnsName = new Object[4];
private Object[] rowData = new Object[4];
 
public  VoitureJInternalFrame (String ch,Boolean b1,Boolean b2,Boolean b3,Boolean b4) {
    super(ch, b1, b2, b3,b4);
     initCont();
   initTableVoiture();
   buttonAction();
   marqueCombo();
}

public static void updateIhmVoiture()
{ Object[] rowData = new Object[5];
   if (mVoiture.getRowCount() > 0) {
    for (int i = mVoiture.getRowCount() - 1; i > -1; i--) {
        mVoiture.removeRow(i);
    }}

        ResultSet rs =vehiculeDB.selectTable();

try {
            while (rs.next())
            {
         rowData[0] =rs.getString("matricule");
  rowData[1] = new MarqueDAO().Select(new Marque(rs.getString("IdMarque"))).getMarque();
String sql="SELECT  `IdVT` FROM  `visitetechnique` WHERE  `DateFinVisite` = ( SELECT MAX(  `DateFinVisite` ) FROM  `visitetechnique` WHERE  `matricule` ='"+(String)rowData[0]+"' ) AND (`matricule` =  '"+(String)rowData[0]+"' )";
String sql2="SELECT  `IdAss` FROM  `assurance` WHERE  `DateFinAss` = ( SELECT MAX(  `DateFinAss` ) FROM  `assurance` WHERE  `matricule` ='"+(String)rowData[0]+"'  ) AND (`matricule` ='"+(String)rowData[0]+"' )";
ResultSet rs2 =new AssuranceDAO().selectCondtion(sql2);
ResultSet rs1 =new VisiteTechniqueDAO().selectCondtion(sql);
                if(rs1!=null && rs1.next())
                {
rowData[2] =  rs1.getString("IdVT");  
                }else{ 
                    rowData[2] ="";
                    }
                
                    if(rs2!=null && rs2.next())
                { rowData[3] =  rs2.getString("IdAss");
                }else{ 
                    rowData[3] ="";
                    }
         mVoiture.addRow(rowData);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
       
   }


boolean  verifierDonne()
{
    

    if((matTex.getText().length()==0)||(matTex.getText().length()>15))
            {
JOptionPane.showMessageDialog(rootPane, "Verfier le matricule ");
            return false; 
            }else{
    
    if(new VehiculeDAO().Select(new Vehicule(matTex.getText()))!=null)
    {
        JOptionPane.showMessageDialog(rootPane, " Voiture est deja existe");
            return false; 

    }
    }

    if(marquecombo.getSelectedIndex()==-1)
    {
     JOptionPane.showMessageDialog(rootPane, " saisir une marque ");
            return false;
    }
if((vtTex.getText().length()==0))
            {
JOptionPane.showMessageDialog(rootPane, "Saisir le numero de visite technique");
            return false; 
            }else{
if(new VisiteTechniqueDAO().Select(new VisiteTechnique(vtTex.getText()))!=null)
 {
 JOptionPane.showMessageDialog(rootPane, "numero d visite technique est deja existe");
             return false;
 }
}

if((AssTex.getText().length()==0))
            {
JOptionPane.showMessageDialog(rootPane, "remplir assurance ");
            return false; 
            }else{

if(new AssuranceDAO().Select(new Assurance(AssTex.getText()))!=null)
 {
 JOptionPane.showMessageDialog(rootPane, "numero d assurance est deja existe");
             return false; 

}
}


    
return true;
}

private void  initTableVoiture(){
      columnsName[0] = "Matricule";
        columnsName[1] = "Marque";
        columnsName[2] = "N°Visite Technique";
        columnsName[3] = "N°Assurance";       
        mVoiture.setColumnIdentifiers(columnsName);
        tVoiture.setModel(mVoiture);
                JScrollPane pane = new JScrollPane(tVoiture);

Dimension f=super.getSize();
         
pane.setPreferredSize(new Dimension(900,500));
        
        ligne4.add(pane,BorderLayout.CENTER);
  }
private void initCont(){
      contPane=(JPanel) this.getContentPane();
    contPane=(JPanel) this.getContentPane();
  contPane.add(cont,BorderLayout.CENTER);
  
 
 cont.add(ligne1);
  cont.add(ligne2);
    cont.add(ligne3);
    cont.add(ligne4);

 ligne1.add(matLab);
 ligne1.add(matTex); 
 marqueTex.setPreferredSize(new Dimension(100,20));
marquecombo.setPreferredSize(new Dimension(200,20));

 ligne1.add(marqueLab); 
 ligne1.add(marquecombo);
 ligne2.add(AssLab);
 ligne2.add(AssTex);
 ligne2.add(vtLab);
 ligne2.add(vtTex);
   ligne3.add(bajouter);
   ligne3.add(bconsulter);
    //ligne3.add(bmodfier);
   ligne3.add(bsupprimer);

     }

public static void marqueCombo()
{
    if (marquecombo.getItemCount()> 0) {
        marquecombo.removeAllItems();
    }

    
ResultSet rs=marqueDB.selectTable();
        try {
            while(rs.next())
            {
        marquecombo.addItem(rs.getString("nomModele"));
            }       
        
        } catch (SQLException ex) {
            Logger.getLogger(VoitureJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
        }


}
public void dbToTableVoiture(){
ResultSet rs =vehiculeDB.selectTable();

try {
            while (rs.next())
            {
         rowData[0] =rs.getString("matricule");
  rowData[1] = new MarqueDAO().Select(new Marque(rs.getString("IdMarque"))).getMarque();
String sql="SELECT  `IdVT` FROM  `visitetechnique` WHERE  `DateFinVisite` = ( SELECT MAX(  `DateFinVisite` ) FROM  `visitetechnique` WHERE  `matricule` ='"+(String)rowData[0]+"' ) AND (`matricule` =  '"+(String)rowData[0]+"' )";
String sql2="SELECT  `IdAss` FROM  `assurance` WHERE  `DateFinAss` = ( SELECT MAX(  `DateFinAss` ) FROM  `assurance` WHERE  `matricule` ='"+(String)rowData[0]+"'  ) AND (`matricule` ='"+(String)rowData[0]+"' )";
ResultSet rs2 =new AssuranceDAO().selectCondtion(sql2);
ResultSet rs1 =new VisiteTechniqueDAO().selectCondtion(sql);
                if(rs1!=null && rs1.next())
                {
rowData[2] =  rs1.getString("IdVT");  
                }else{ 
                    rowData[2] ="";
                    }
                
                    if(rs2!=null && rs2.next())
                { rowData[3] =  rs2.getString("IdAss");
                }else{ 
                    rowData[3] ="";
                    }
         mVoiture.addRow(rowData);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        }
public void buttonAction(){
     
bajouter.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
     
            if(verifierDonne()){
            rowData[0] = matTex.getText();
            rowData[1] =marquecombo.getSelectedItem();
            rowData[2] =   vtTex.getText();
            rowData[3] =   AssTex.getText();
            mVoiture.addRow(rowData);
            ResultSet R=marqueDB.selectCondition("SELECT * FROM  `marque` where nomModele="+"'"+marquecombo.getSelectedItem()+"'");
          
            
           if(R!=null){
                try {
                    R.next();
                    vehiculeDB.insert(new Vehicule(matTex.getText(),
                            R.getString("IdMarque"),vtTex.getText(),AssTex.getText()));
                    assbd.insert(new Assurance(AssTex.getText(), matTex.getText(), "9999/12/31","9999/12/31"));
vtbd.insert(new VisiteTechnique(vtTex.getText(),matTex.getText(), "9999/12/31", "9999/12/31"));
                } catch (SQLException ex) {
                    Logger.getLogger(VoitureJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
            System.out.println(".actionPerformed()");
            }
           
           
            matTex.setText("");
            marqueTex.setText("");
            vtTex.setText("");
            AssTex.setText("");

   
        }}
    });

 bconsulter.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            int i,ind=-1;
           
            if(matTex.getText().length()>1)
            {
                       for(i=0;i<mVoiture.getRowCount();i++)
                         {
                                if(matTex.getText().equals(mVoiture.getValueAt(i, 0))==true)
                            {
                               ind=i;break;
                                  }
                            }
     if(ind>=0)
             tVoiture.setRowSelectionInterval(ind, ind);
     else 
         JOptionPane.showMessageDialog(cont, "Véhicule n existe pas");

     
        }else{           
    JOptionPane.showMessageDialog(cont, "saisir le Matricule pour consulter la Véhicule il vous plait");
            }
        }
    });


 mVoiture.addTableModelListener(new TableModelListener() {
        @Override
        public void tableChanged(TableModelEvent tme) {

                  vehiculeDB.update(getRowSelected()); 
            }
    });
     
 bsupprimer.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
    vehiculeDB.delete(getRowSelected()); 
    mVoiture.removeRow(tVoiture.getSelectedRow()); }});
     

 
     }
 public Vehicule getRowSelected(){
     try{
         return new Vehicule(
        (String) mVoiture.getValueAt(tVoiture.getSelectedRow(), 0),
         (String) mVoiture.getValueAt(tVoiture.getSelectedRow(),1),
         (String) mVoiture.getValueAt(tVoiture.getSelectedRow(),2),
         (String) mVoiture.getValueAt(tVoiture.getSelectedRow(), 3));
     }catch(Exception e)
        {
            return null;
                }
     
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
