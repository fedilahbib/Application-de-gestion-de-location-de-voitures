/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.IHM;

/**
 *
 * @author fedi
 */

import datechooser.beans.DateChooserCombo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
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
import javax.swing.text.MaskFormatter;
import projet.DAo.AssuranceDAO;
import projet.DAo.MarqueDAO;
import projet.DAo.VehiculeDAO;
import projet.DAo.VisiteTechniqueDAO;
import static projet.IHM.LocationJInternalFrame.mLocation;
import projet.metier.Assurance;
import projet.metier.Client;
import projet.metier.Marque;
import projet.metier.Vehicule;
import projet.metier.VisiteTechnique;
import projet.metier.Voyageur;

public class ValidJInternalFrame   extends JInternalFrame  {
 public static   VisiteTechniqueDAO vtDAO =new VisiteTechniqueDAO();
  public static    AssuranceDAO assuDAO=new AssuranceDAO();
   public static  MarqueDAO marqueDAO = new MarqueDAO();
   int i=0;
   int idmarque;
  //========================== JLabeL AND JTextField ==========================================

    private  JLabel marqueIdf;
  private  JLabel Lcaracteristiques=new JLabel("caractéristiques");
  private  JLabel LtarifJour=new JLabel("tarif de Jour");
  private  JLabel LnomMarque=new JLabel("nom de Marque");
  
  private  JLabel LnumVT=new JLabel("N° Visite Technique");
     private  JLabel LdateDebutVisite=new JLabel("date debut de Visite");
    private  JLabel LdateFinVisite=new JLabel("date fin de Visite");
  private  JLabel Lmatr=new JLabel("matricule");
  
  private  JLabel LnomModele=new JLabel("nom de Modele");
    private  JLabel vide=new JLabel("                  ");
    private  JLabel vide1=new JLabel("                  ");
    private  JLabel vide2=new JLabel("                  ");
  
    private  JLabel LmatrVt=new JLabel("matricule");
    private  JLabel LnumAss =new JLabel("N°Assurance");
     private  JLabel LdateDebutAss=new JLabel("date debut de Assurance");
    private  JLabel LdateFinAss=new JLabel("date fin de Assurance");
    private  JTextField TmatVT=new JTextField(10);
    
    private  JTextField Tmat=new JTextField(10);
    private  JTextField TmatrAss=new JTextField(10);

    private  JTextField TnumAss=new JTextField(10);
    private  JTextField TmatAss=new JTextField(10);
    private  JTextField TnumVT=new JTextField(10);
  private  JTextField TnomMarque=new JTextField(10);
  //private  JTextField TnomModele=new JTextField(10);
  private  JTextField TtarifJour=new JTextField(10);
  private  JTextField Tcaracteristiques=new JTextField(10);

    
  //========================== date ==========================================
private DateFormat df = new SimpleDateFormat("dd/mm/yyyy");

 private  DateChooserCombo TdateDebutAss = new DateChooserCombo();
private  DateChooserCombo TdatefinAss = new DateChooserCombo();
private  DateChooserCombo TdateDebutVisite = new DateChooserCombo();
private  DateChooserCombo TdateFinVisite = new DateChooserCombo();
 
//========================== panel ==========================================

  private  JPanel cont;    public JPanel ligne=new JPanel();
    private  JPanel ligne1=new JPanel();
    private   JPanel ligne2=new JPanel();
    private  JPanel ligne3=new JPanel();    
    private  JPanel ligne4=new JPanel(new GridLayout(5,2));
    private  JPanel ligne5=new JPanel(new GridLayout(5,2));
    private  JPanel ligne7=new JPanel(new GridLayout(10,2));
  private  JPanel ligne8=new JPanel(); 
    private  JPanel ligne6=new JPanel();    
//========================== TABLE ==========================================

public static JTable tMarque = new JTable();
public static DefaultTableModel mMarque = new DefaultTableModel();
private Object[] columnsName2 = new Object[4];
private Object[] rowData2 = new Object[4];

//========================== TABLE ==========================================

public  static  JTable tVisite= new JTable();
public  static  DefaultTableModel mVisite = new DefaultTableModel();
private Object[] columnsName3 = new Object[4];
private Object[] rowData3 = new Object[4];

//========================== TABLE ==========================================


public  static  JTable TAssu = new JTable();
public  static  DefaultTableModel mAssu= new DefaultTableModel();
private Object[] columnsName4 = new Object[4];
private Object[] rowData4 = new Object[4];

//========================== JButton ==========================================

private JButton bValdier =new JButton("Valider Marque      ");
private JButton bsup =new JButton("Supprimer Marque ");
private JButton bmod =new JButton("Modfier Marque     ");
private JButton bValdVist =new JButton("Valide Visite");
private JButton bValdAss =new JButton("Valide Ass");
 
  
  //======================= color ================================

    
public  ValidJInternalFrame(String ch,Boolean b1,Boolean b2,Boolean b3,Boolean b4) {
    super(ch, b1, b2, b3,b4);
initCont();
TableAss();
TableMarque();
TableVisite();  
buttonAction();
dbtoTableMarque();     
dbtoTableAss();
dbtoTableVisite();
intiNbloc();
}

/*==========================================================================


  ========================================================================*/ 

boolean  verifierDonneMarque()
{
    if((TnomMarque.getText().length()==0))
            {
JOptionPane.showMessageDialog(rootPane, "le nom de marque");
            return false; 
            }
   if((Tcaracteristiques.getText().length()==0))
            {
JOptionPane.showMessageDialog(rootPane, "remplir le caracteristiques de marque");
            return false; 
            } 
   
     if(TtarifJour.getText().length()==0)
            {
JOptionPane.showMessageDialog(rootPane, "verfier tarif de jour");
            return false; 
            }else{
try{
  Float.parseFloat(TtarifJour.getText());
}catch(NumberFormatException e){
                return false; 
}          
}
       
return true;
}
boolean  verifierDonneVT()
{ 
    if((TnumVT.getText().length()==0)||(TnumVT.getText().length()>15))
            {
JOptionPane.showMessageDialog(rootPane, "numero d visite technique");
            return false; 
            }else{
 if(new VisiteTechniqueDAO().Select(new VisiteTechnique(TnumVT.getText()))!=null)
 {
 JOptionPane.showMessageDialog(rootPane, "numero d visite technique est deja existe");
             return false;
 }
    }
 
 if((TmatVT.getText().length()==0))
            {
JOptionPane.showMessageDialog(rootPane, "verfier le matricule de voiture assurer");
            return false; 
            }else{
   if(new VehiculeDAO().Select(new Vehicule(TmatVT.getText()))==null)
    {
        JOptionPane.showMessageDialog(rootPane, " voiture n existe pas  ");
            return false; 

    }
 
 }
return true;
}
boolean  verifierDonneAss()
{
 if((TnumAss.getText().length()==0)||(TnumAss.getText().length()>15))
  {
JOptionPane.showMessageDialog(rootPane, "numer d assurance");
            return false; 
  }else{
 if(new AssuranceDAO().Select(new Assurance(TnumAss.getText()))!=null)
 {
 JOptionPane.showMessageDialog(rootPane, "numer d assurance est deja existe");
             return false; 

 }
 
 
 }
 if((TmatAss.getText().length()==0))
            {
JOptionPane.showMessageDialog(rootPane, "verfier le matricule de voiture assurer");
            return false; 
            }else{
if(new VehiculeDAO().Select(new Vehicule(TmatAss.getText()))==null)
    {
        JOptionPane.showMessageDialog(rootPane, " voiture n existe pas  ");
            return false; 

    }
 }
return true;
}



public static void dbtoTableMarque(){
Object[] rowData2 = new Object[4];
  
 if (mMarque.getRowCount() > 0) {
    for (int i = mMarque.getRowCount() - 1; i > -1; i--) {
        mMarque.removeRow(i);
    }}



    ResultSet rs=marqueDAO.selectTable();
          try {
            while (rs.next())
            {
                
         rowData2[0] =  rs.getString("IdMarque");
         rowData2[1] =  rs.getString("nomModele");
         rowData2[2] = rs.getString("tarifJour");
         rowData2[3] =   rs.getString("caracteristiques");
         mMarque.addRow(rowData2);
            }
        } catch (SQLException ex) {
            Logger.
 getLogger(ClientJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
}
private void TableMarque(){
        columnsName2[0] = "marque Idf";
        columnsName2[1] = "nom de Marque et Modele";
        columnsName2[2] = "Tarif de Jour";
        columnsName2[3] = "Caractéristiques";         
    mMarque.setColumnIdentifiers(columnsName2);
  
    tMarque.setModel(mMarque);          
    JScrollPane pane2 = new JScrollPane(tMarque);   
      pane2.setPreferredSize(new Dimension(600,300));   

    ligne1.add(pane2);
}   
public Marque getRowSelectedMar(){
     try{
         return new  Marque(
                 (String) mMarque.getValueAt(tMarque.getSelectedRow(), 0),
                 (String) mMarque.getValueAt(tMarque.getSelectedRow(), 3),
                 (String) mMarque.getValueAt(tMarque.getSelectedRow(), 2),
                 (String) mMarque.getValueAt(tMarque.getSelectedRow(), 1)
                );
     }catch(Exception e)
        {
            return null;
                }
     }
     
public void buttonAction(){
    
 bValdAss.addActionListener(new ActionListener() 
 { @Override
        public void actionPerformed(ActionEvent ae) {
       if(verifierDonneAss()){
            rowData4[0] = TnumAss.getText();
        rowData4[1] =  TmatAss.getText();
        rowData4[2] =getDateDebutAss();
        rowData4[3] =getDateFinAss();

        mAssu.addRow(rowData4);
 
        assuDAO.insert(new Assurance(
            TnumAss.getText(),
            TmatAss.getText(),
            getDateDebutAss(),
            getDateFinAss()
        ));
        
       TnumAss.setText("");
       TmatAss.setText("");            
        }}
});
 
 mAssu.addTableModelListener(new TableModelListener() {
        @Override
        public void tableChanged(TableModelEvent tme) {
                 assuDAO.update(getRowSelectedAssu());    
        }
 });
 mMarque.addTableModelListener(new TableModelListener() {
        @Override
        public void tableChanged(TableModelEvent tme) {
                 marqueDAO.update(getRowSelectedMar()); 
        }
 });
  mVisite.addTableModelListener(new TableModelListener() {
        @Override
        public void tableChanged(TableModelEvent tme) {
                 vtDAO.update(getRowSelectedVt());
        }
 });
 bValdier.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
       if(verifierDonneMarque()){
            rowData2[0] = getIdMarque();
        rowData2[1] =  TnomMarque.getText();
        rowData2[2] =TtarifJour.getText();
        rowData2[3] =Tcaracteristiques.getText();
        mMarque.addRow(rowData2);
        marqueDAO.insert(new Marque(
             (String)rowData2[0],
                          Tcaracteristiques.getText()
                   ,TtarifJour.getText()
                ,TnomMarque.getText() 
        ));
        
       TnomMarque.setText("");
       TtarifJour.setText("");
       Tcaracteristiques.setText("");
       
}
        }
 });
 bsup.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            marqueDAO.delete(getRowSelectedMar()); 
    mMarque.removeRow(tMarque.getSelectedRow());
     
        }
});
bValdVist.addActionListener(new ActionListener() {
     @Override
     public void actionPerformed(ActionEvent ae) {
        if(verifierDonneVT()){
                rowData3[0] = TnumVT.getText();
        rowData3[1] =  TmatVT.getText();
        
        rowData3[2] =getDateDebutVisite();
        rowData3[3] =getDateFinVisite();

        mVisite.addRow(rowData3);

        vtDAO.insert(new VisiteTechnique(
            TnumVT.getText(),
            TmatVT.getText(),
           getDateDebutVisite(),
            getDateFinVisite()
        ));
       
       TnumVT.setText("");
       TmatVT.setText("");
    
     }}
 });
}

public void intiNbloc()
{
    ResultSet nb=marqueDAO.selectCondition("SELECT IdMarque FROM Marque ");
    try {
                    idmarque=0;
        while(nb!=null&&nb.next())
        {
        String ch=nb.getString("IdMarque");           
try{
            if(Integer.parseInt(ch.substring(4, ch.length()))>idmarque)
            { System.err.println(ch);
                idmarque=Integer.parseInt(ch.substring(4, ch.length()));             
            }}catch(NumberFormatException e){ 
            idmarque=0;}
        
        }
        
    } catch (SQLException ex) {
        Logger.getLogger(LocationJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
    }
}
     private Object getIdMarque() {
    
         idmarque++;
String ch="Mar-"+String.valueOf(idmarque);
    return ch;
        
     }
/*===========================================================

           *********   Assurance
*/
private void TableAss(){        columnsName4[0] = "N°Assurance";
        columnsName4[1] = "matricule";
        columnsName4[2] = "date debut de Assurance";
        columnsName4[3] = "date fin de Assurance";
    mAssu.setColumnIdentifiers(columnsName4);  
    TAssu.setModel(mAssu);          
    JScrollPane pane2 = new JScrollPane(TAssu);   
      pane2.setPreferredSize(new Dimension(700,300));   
    ligne8.add(pane2);
}
public  static void   dbtoTableAss(){
    Object[] rowData4 = new Object[4];
  
 if (mAssu.getRowCount() > 0) {
    for (int i = mAssu.getRowCount() - 1; i > -1; i--) {
        mAssu.removeRow(i);
    }}


    
          ResultSet rs=assuDAO.selectTable();
    
            try {
            while (rs.next())
            {
                
         rowData4[0] =  rs.getString("IdAss");
         rowData4[1] =  rs.getString("matricule");
         rowData4[2] = rs.getString("DateDebutAss");
         rowData4[3] =   rs.getString("DateFinAss");
         mAssu.addRow(rowData4);
            }
        } catch (SQLException ex) {
            Logger.
 getLogger(ClientJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
}
public String getDateDebutAss()
{
    Calendar s=TdateDebutAss.getSelectedDate();
Date date =  s.getTime();
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
return dateFormat.format(date);
}
public String getDateFinAss()
{
    Calendar s=TdatefinAss.getSelectedDate();
Date date =  s.getTime();
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
return dateFormat.format(date);
}
public Assurance getRowSelectedAssu() {
 try{
         return new  Assurance(
                 (String) mAssu.getValueAt(TAssu.getSelectedRow(), 0),
                 (String) mAssu.getValueAt(TAssu.getSelectedRow(), 1),
                 (String) mAssu.getValueAt(TAssu.getSelectedRow(), 2),
                 (String) mAssu.getValueAt(TAssu.getSelectedRow(), 3)
                );
     }catch(Exception e)
        {
            return null;
                }
 
 
 }
//=========================================
public static  void dbtoTableVisite(){
          ResultSet rs=vtDAO.selectTable();    
    Object[] rowData3 = new Object[4];
  
 if (mVisite.getRowCount() > 0) {
    for (int i = mVisite.getRowCount() - 1; i > -1; i--) {
        mVisite.removeRow(i);
    }}        
          
          try {
            while (rs.next())
            {
                
         rowData3[0] =  rs.getString("IdVT");
         rowData3[1] =  rs.getString("matricule");
         rowData3[2] = rs.getString("DateDebutVisite");
         rowData3[3] =   rs.getString("DateFinVisite");
         mVisite.addRow(rowData3);
            }
        } catch (SQLException ex) {
            Logger.
 getLogger(ClientJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
}
public String getDateDebutVisite()
{
    Calendar s=TdateDebutVisite.getSelectedDate();
Date date =  s.getTime();
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
return dateFormat.format(date);
}
public String getDateFinVisite()
{
Calendar s=TdateFinVisite.getSelectedDate();
Date date =  s.getTime();
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
return dateFormat.format(date);
}


public VisiteTechnique getRowSelectedVt() {
 try{
         return new  VisiteTechnique(
                 (String) mVisite.getValueAt(tVisite.getSelectedRow(), 0),
                 (String) mVisite.getValueAt(tVisite.getSelectedRow(), 1),
                 (String) mVisite.getValueAt(tVisite.getSelectedRow(), 2),
                 (String) mVisite.getValueAt(tVisite.getSelectedRow(), 3)
                );
     }catch(Exception e)
        {
            return null;
                }
 }
private void TableVisite(){
        columnsName2[0] = "N° Visite Technique";
        columnsName2[1] = "matricule";
        columnsName2[2] = "date debut de Visite";
        columnsName2[3] = "date fin de Visite";
        mVisite.setColumnIdentifiers(columnsName2);
       
      tVisite.setModel(mVisite);          
      JScrollPane pane3 = new JScrollPane(tVisite);   
      pane3.setPreferredSize(new Dimension(600,300));   

      ligne2.add(pane3);
 

}    

//=================================================================================
private void initCont(){
cont=(JPanel) getContentPane();
 cont.add(ligne,BorderLayout.CENTER);
 ligne.add(ligne1);
  ligne.add(ligne2);
 ligne.add(ligne3);
  ligne.add(ligne8);

ligne1.setLayout(new BoxLayout(ligne1, BoxLayout.Y_AXIS));
  ligne2.setLayout(new BoxLayout(ligne2, BoxLayout.Y_AXIS));
ligne1.add(ligne4);

ligne4.add(LnomMarque);
ligne4.add(TnomMarque);

ligne4.add(Lcaracteristiques);
ligne4.add(Tcaracteristiques);
ligne4.add(LtarifJour);
ligne4.add(TtarifJour);
ligne4.add(new JLabel(""));
ligne4.add(new JLabel(""));
ligne4.add(bValdier);
ligne4.add(bsup);

ligne2.add(ligne5);
ligne5.add(LnumVT);
ligne5.add(TnumVT);
ligne5.add(LmatrVt);
ligne5.add(TmatVT);
ligne5.add(LdateDebutVisite);
ligne5.add(TdateDebutVisite);
ligne5.add(LdateFinVisite);
ligne5.add(TdateFinVisite);
ligne5.add(vide);
ligne5.add(bValdVist);
ligne8.add(ligne7);
ligne7.add(LnumAss);
ligne7.add(TnumAss);
ligne7.add(Lmatr);
ligne7.add(TmatAss);
ligne7.add(LdateDebutAss);
ligne7.add(TdateDebutAss);
ligne7.add(LdateFinAss);
ligne7.add(TdatefinAss);
ligne7.add(vide);
ligne7.add(bValdAss);
ligne7.add(vide2);
ligne7.add(vide1);

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
