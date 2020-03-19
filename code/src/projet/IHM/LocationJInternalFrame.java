/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.IHM;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import datechooser.beans.DateChooserCombo;
import datechooser.events.SelectionChangedEvent;
import datechooser.events.SelectionChangedListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import static java.lang.String.format;
import static java.lang.String.format;
import static java.lang.String.format;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
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
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.plaf.metal.MetalInternalFrameUI;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;
import javax.swing.text.MaskFormatter;
import projet.DAo.AssuranceDAO;
import projet.DAo.ClientDAO;
import projet.DAo.CovoiturageDAO;
import projet.DAo.LocationDAO;
import projet.DAo.MarqueDAO;
import projet.DAo.VehiculeDAO;
import projet.DAo.VisiteTechniqueDAO;
import projet.DAo.VoyageurDAO;
import static projet.IHM.VoitureJInternalFrame.mVoiture;
import static projet.IHM.VoitureJInternalFrame.vehiculeDB;
import projet.metier.Client;
import projet.metier.Covoiturage;
import projet.metier.Location;
import projet.metier.Marque;
import projet.metier.Vehicule;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
/**
 *
 * @author fedi
 */
public class LocationJInternalFrame   extends JInternalFrame  {


//====================================================================
public static LocationDAO lacationDB =new LocationDAO();
public static CovoiturageDAO covoiDB=new CovoiturageDAO();
public static MarqueDAO M=new MarqueDAO();
 public  int nbloc; 
 public  int nbcov;
        
//============================ JPanel ==========================

private JPanel contPane;
private JPanel cont=new JPanel();
private JPanel ligne1=new JPanel();
private JPanel ligne2=new JPanel();
private JPanel ligne3=new JPanel();
private JPanel ligne31=new JPanel();
private JPanel ligne32=new JPanel();
private JPanel ligne4=new JPanel();
private JPanel d=new JPanel(new GridLayout(2,1));

//============================ Label ==========================
private JLabel DateDebutLab =new JLabel("Date Debut De location");
private JLabel DateFinLab =new JLabel("Date Fin De location");
private JLabel cinLab =new JLabel("Cin");
private  JLabel matLab =new JLabel("Vehicule Matricule");
private JLabel montLab =new JLabel("Montant Totale");         
private JLabel dateDebutCovLab =new JLabel("date Debut de covoiturage");  
private JLabel datefinCovLab =new JLabel("date fin de covoiturage");     
private JLabel lieuDepartLab =new JLabel("Lieu de Depart");     
private JLabel lieuDistinationLab =new JLabel("Lieu de Distination");     
private JLabel nbPlaceLab =new JLabel("nombre de place disponible");     
private JLabel lab=new JLabel("les voiture disponible");

//============================ Date ==========================
private DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
private DateFormat df2 = new SimpleDateFormat("dd/mm/yyyy hh:mm");
private DateChooserCombo datefintext = new DateChooserCombo();
private DateChooserCombo dateDebuttext = new DateChooserCombo();
private DateChooserCombo datefinCovText = new DateChooserCombo();
private DateChooserCombo dateDebutCovtext = new DateChooserCombo();
//============================ TextField ==========================
  
private JTextField matText =new JTextField(20);
private JTextField cinText =new JTextField(20);
private JTextField montText =new JTextField(12);
private JTextField lieuDepartText =new JTextField(20);
private JTextField lieuDistinationText =new JTextField(20);
private JTextField nbPlace =new JTextField(3);             
private JTextField numText =new JTextField(20);


//============================ JPanel ==========================
private JButton bconsulter=new JButton("Consulter Location");
private JButton bajouter=new JButton("Ajouter Location");
private JButton bsupprimer=new JButton("Supprimer Location");
//private JButton bmodfier=new JButton("Modifier Location");
private JButton bimpr=new JButton("Imprimer contrat");
private JRadioButton covoiOption= new JRadioButton("option de covoiturage",false);

//============================ Table Location ==========================

private JTable tLocation= new JTable();
public static  DefaultTableModel mLocation = new DefaultTableModel();
private Object[] columnsName = new Object[7];
private Object[] rowData = new Object[7];
private JScrollPane pane = new JScrollPane(tLocation);
 
//============================ Table Voiture ==========================


private JTable tVoiture = new JTable();
public static  DefaultTableModel mVoiture  = new DefaultTableModel();
private Object[] columnsName4 = new Object[3];
private Object[] rowData4 = new Object[3];
private JPanel Pvoit=new JPanel();  
private JScrollPane pane2 = new JScrollPane(tVoiture);   
private Dimension f=super.getSize();


public static void updateIhmLocation()
{ Object[] rowData = new Object[7];
   if (mLocation.getRowCount() > 0) {
    for (int i = mLocation.getRowCount() - 1; i > -1; i--) {
        mLocation.removeRow(i);
    }}

        ResultSet rs =lacationDB.selectTable();


            try {
            while (rs.next())
            {
                
         rowData[0] =  rs.getString("IdLocation");
         rowData[1] =  rs.getString("cin");
         rowData[2] = rs.getString("matricule");
         rowData[3] =   rs.getString("DateDebutLocation");
         rowData[4] =    rs.getString("DateFinLocation");
         rowData[5] =        rs.getString("Montant"); 
 
         mLocation.addRow(rowData);
            }
        } catch (SQLException ex) {
            Logger.
 getLogger(ClientJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    
            
            
            
   }



boolean  verifierDonne()
{
 
    if((cinText.getText().length()==0)||(cinText.getText().length()>10))
            {
JOptionPane.showMessageDialog(rootPane, "verfier cin ");
            return false; 
            }else{
        
    if(new ClientDAO().Select(new Client(cinText.getText()))==null)
    {
        JOptionPane.showMessageDialog(rootPane, "client n existe pas  ");
            return false; 

    }   
    }
 

if((matText.getText().length()==0)||(matText.getText().length()>15))
            {
JOptionPane.showMessageDialog(rootPane, "verfier marricule ");
            return false; 
            }else{
    
    if(new VehiculeDAO().Select(new Vehicule(matText.getText()))==null)
    {
        JOptionPane.showMessageDialog(rootPane, " voiture n existe pas  ");
            return false; 

    }}
if(covoiOption.isSelected())
{
if((nbPlace.getText().length()==0)||(nbPlace.getText().length()>9))
            {
JOptionPane.showMessageDialog(rootPane, "verfier nombre de place ");
            return false; 
            }else{
        {
              try  
  {  
  Integer.parseInt(nbPlace.getText());  
  }  
  catch(NumberFormatException nfe)  
  {  
      JOptionPane.showMessageDialog(rootPane, "verfier nombre de place ");

    return false;  
  }    }}


if((lieuDepartText.getText().length()==0))
            {
JOptionPane.showMessageDialog(rootPane, "remplir le lieu de Depart ");
            return false; 
            }

if((lieuDistinationText.getText().length()==0))
            {
JOptionPane.showMessageDialog(rootPane, "remplir le lieu de Distination ");
            return false; 
            }

}





    
return true;
}
public LocationJInternalFrame(String ch,Boolean b1,Boolean b2,Boolean b3,Boolean b4) {
    super(ch, b1, b2, b3,b4);
    super.pack();
    initCont();
initTableVoiture();
  initTableLocation();
buttonAction();
dbtoTableLocation();
intiNbloc();
intiNbcov();
}
  public void buttonAction(){
    bimpr.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                MessageFormat head=new MessageFormat("Report");
                MessageFormat foot=new MessageFormat("Report");
                tLocation.print(JTable.PrintMode.FIT_WIDTH, head, foot);
            } catch (PrinterException ex) {
                Logger.getLogger(LocationJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            /*****/

try {
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream("table.pdf"));
            doc.open();
            PdfPTable pdfTable = new PdfPTable(tLocation.getColumnCount());
            //adding table headers
         
            for (int i = 0; i < tLocation.getColumnCount(); i++) {
                pdfTable.addCell(tLocation.getColumnName(i));
            }
            for (int rows = 0; rows < tLocation.getRowCount() - 1; rows++) {
                for (int cols = 0; cols < tLocation.getColumnCount(); cols++) {
              pdfTable.addCell((String)mLocation.getValueAt(rows, cols));

                }
            }
            doc.add(pdfTable);
            doc.close();
        } catch (DocumentException ex) {
        } catch (FileNotFoundException ex) {
        }
            
            
        }
    });
bajouter.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
              if(verifierDonne()==true){
            rowData[0] = getNumLoacation();   
         rowData[1] = cinText.getText();
       rowData[2] =   matText.getText();
        rowData[3] =  getDateDebut();
        rowData[4] = getDatedatefint();
        rowData[5] =  montText.getText();
        rowData[6]="";
        lacationDB.insert(new Location(
 ( String) rowData[0],( String)rowData[1],( String)rowData[2],( String)rowData[3],
   ( String)rowData[4],( String)rowData[5])); 
         
     if(covoiOption.isSelected())
        {
            rowData[6] =  getNumCoviturage();
            Covoiturage cov=new Covoiturage((String)rowData[6], ( 
            String)rowData[0], getDateDebutCovtext(), getDatefinCov()
        ,lieuDepartText.getText() ,lieuDistinationText.getText(), nbPlace.getText());
            covoiDB.insert(cov);
            lieuDistinationText.setText("");
            nbPlace.setText("");
            lieuDepartText.setText("");
        }
        mLocation.addRow(rowData);
 

    cinText.setText("");
    matText.setText("");
    montText.setText("");
             }

        }
    });

 bconsulter.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            int i,ind=-1;
        String numText =JOptionPane.showInputDialog("Saisir numero de location");
         if(numText.length()>0)
            {
                       for(i=0;i<mLocation.getRowCount();i++)
                         {
                                if(numText.equals(mLocation.getValueAt(i, 0))==true)
                            {
                               ind=i;break;
                                  }
                            }
     if(ind>=0)
             tLocation.setRowSelectionInterval(ind, ind);
     else 
         JOptionPane.showMessageDialog(cont, "location n existe pas");

     
        }else{              
JOptionPane.showMessageDialog(cont,"saisir le Numero de location pour consulter le location s'il vous plait");

            }
        }
    });

 mLocation.addTableModelListener(new TableModelListener() {
        @Override
        public void tableChanged(TableModelEvent tme) {

                    lacationDB.update(getRowSelected()); 
            }
    });
  dateDebuttext.addSelectionChangedListener(new SelectionChangedListener() {
    @Override
    public void onSelectionChange(SelectionChangedEvent sce) {
    Calendar s=dateDebuttext.getSelectedDate();
    	Calendar cal = Calendar.getInstance();
        dateDebuttext.setMinDate(cal);
        datefintext.setMinDate(s);
        if(new VehiculeDAO().Select(new Vehicule(matText.getText()))!=null)
{
montText.setText(getMontant());
}
        else{
        montText.setText("0");

        }
        for(int i=0;i<mVoiture.getRowCount();i++)
{
mVoiture.removeRow(i);

}
                dbtoTableVoiture();

    }
});
  datefintext.addSelectionChangedListener(new SelectionChangedListener() {
    @Override
    public void onSelectionChange(SelectionChangedEvent sce) {
        Calendar cal = Calendar.getInstance();
        dateDebuttext.setMinDate(cal);
       Calendar s=dateDebuttext.getSelectedDate();
        datefintext.setMinDate(s);
        if(new VehiculeDAO().Select(new Vehicule(matText.getText()))!=null)
{
montText.setText(getMontant());
}
        else{
        montText.setText("0");

        }
 for(int i=0;i<mVoiture.getRowCount();i++)
{
mVoiture.removeRow(i);

}
        dbtoTableVoiture();
    }
});
 
  dateDebutCovtext.addSelectionChangedListener(new SelectionChangedListener() {
    @Override
    public void onSelectionChange(SelectionChangedEvent sce) {
    Calendar s=dateDebutCovtext.getSelectedDate();
            datefinCovText.setMinDate(s);	
    Calendar cal = Calendar.getInstance();
        dateDebutCovtext.setMinDate(cal);
        if(new VehiculeDAO().Select(new Vehicule(matText.getText()))!=null)
{
montText.setText(getMontant());
}
        else{
        montText.setText("0");

        }
        for(int i=0;i<mVoiture.getRowCount();i++)
{
mVoiture.removeRow(i);

}
                dbtoTableVoiture();

    }
});
  datefinCovText.addSelectionChangedListener(new SelectionChangedListener() {
    @Override
    public void onSelectionChange(SelectionChangedEvent sce) {
        Calendar cal = Calendar.getInstance();
        dateDebutCovtext.setMinDate(cal);
       Calendar s=dateDebutCovtext.getSelectedDate();
        datefinCovText.setMinDate(s);
        if(new VehiculeDAO().Select(new Vehicule(matText.getText()))!=null)
{
montText.setText(getMontant());
}
        else{
        montText.setText("0");

        }
 for(int i=0;i<mVoiture.getRowCount();i++)
{
mVoiture.removeRow(i);

}
        dbtoTableVoiture();
    }
});
   

  
 bsupprimer.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
    lacationDB.delete(getRowSelected()); 
    mLocation.removeRow(tLocation.getSelectedRow()); }});
  matText.getDocument().addDocumentListener(new DocumentListener() {
    @Override
    public void insertUpdate(DocumentEvent e) {
if(new VehiculeDAO().Select(new Vehicule(matText.getText()))!=null)
{
montText.setText(getMontant());
}else{
        montText.setText("0");

        }

dbtoTableVoiture();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    }
});
  }
public void intiNbloc()
{
    ResultSet nb=lacationDB.selectCondtion("SELECT IdLocation FROM Location order by IdLocation ");
    try {
                    nbloc=0;
        while(nb!=null&&nb.next())
        {
        String ch=nb.getString("IdLocation");           
try{
            if(Integer.parseInt(ch.substring(4, ch.length()))>nbloc)
            { System.err.println(ch);
                nbloc=Integer.parseInt(ch.substring(4, ch.length()));             
            }}catch(NumberFormatException e){ 
            nbloc=0;}
        
        }
        /*
        if(nb!=null&&nb.next())
        {String ch=nb.getString("nb");
try{
            if(ch==null)
            nbloc=0;
            else
            {
                System.err.println(ch);
                nbloc=Integer.parseInt(ch.substring(4, ch.length()));            } 
        }catch(NumberFormatException e){ 
            nbloc=0;}}
    } catch (SQLException ex) {
        Logger.getLogger(LocationJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
    */} catch (SQLException ex) {
        Logger.getLogger(LocationJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
    }
}
public void intiNbcov()
{
    ResultSet nb=covoiDB.selectCondtion("SELECT IdCovoiturage FROM Covoituragee");
 
       try {
                    nbcov=0;
        while(nb!=null&&nb.next())
        {
        String ch=nb.getString("IdCovoiturage");           
try{
            if(Integer.parseInt(ch.substring(4, ch.length()))>nbcov)
            { System.err.println(ch);
                nbcov=Integer.parseInt(ch.substring(4, ch.length()));             
            }}catch(NumberFormatException e){ 
            nbcov=0;}
        
        }
           System.err.println(nbcov);
        /*   try {
        if(nb!=null&&nb.next())
        {String ch=nb.getString("nb");
try{
            if(ch==null)
            nbcov=0;
            else
            {
                nbcov=Integer.parseInt(ch.substring(4, ch.length()));            } 
        }catch(NumberFormatException e){ 
            nbcov=0;}}
    } catch (SQLException ex) {
        Logger.getLogger(LocationJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
    }*/
}   catch (SQLException ex) {
        Logger.getLogger(LocationJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
    }
}
public void dbtoTableLocation(){
            ResultSet rs =lacationDB.selectTable();


            try {
            while (rs.next())
            {
                
         rowData[0] =  rs.getString("IdLocation");
         rowData[1] =  rs.getString("cin");
         rowData[2] = rs.getString("matricule");
         rowData[3] =   rs.getString("DateDebutLocation");
         rowData[4] =    rs.getString("DateFinLocation");
         rowData[5] =        rs.getString("Montant"); 
 
         mLocation.addRow(rowData);
            }
        } catch (SQLException ex) {
            Logger.
 getLogger(ClientJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
public void dbtoTableVoiture()
{
String sql="SELECT DISTINCT(matricule),IdMarque FROM `vehicule` WHERE  `matricule`  NOT IN(SELECT DISTINCT(matricule)  FROM `location` WHERE (`DateDebutLocation`>='"+getDateDebut()+"')and(`DateFinLocation`<='"+getDatedatefint()+"')) ";
ResultSet rs=new VehiculeDAO().selectCondtion(sql);  
//mVoiture.getDataVector().removeAllElements();
for(int i=0;i<mVoiture.getRowCount();i++)
{
mVoiture.removeRow(i);

}

try {
        while (rs.next())
        {
            rowData4[0]=rs.getString("matricule");
             Marque d=M.Select(new Marque(rs.getString("IdMarque")));
   rowData4[1] =d.getMarque();
         rowData4[2] =d.getTarifJourString();
                   mVoiture.addRow(rowData4);
            
        }   } catch (SQLException ex) {
        Logger.getLogger(LocationJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
    }  
    
}

private void initTableVoiture(){

        columnsName4[0] = "matricule";
        columnsName4[1] = "Marque";
        columnsName4[2] = "Tarif";
tVoiture.setEnabled(false);
    mVoiture.setColumnIdentifiers(columnsName4);
  
    tVoiture.setModel(mVoiture);          
    tVoiture.setPreferredSize(new Dimension(100,500));
    pane2.setPreferredSize(new Dimension(100,500));

    Pvoit.setLayout(new BoxLayout(Pvoit, BoxLayout.Y_AXIS));
          Pvoit.setPreferredSize(new Dimension(100,300));
 
      Pvoit.add(lab);
      Pvoit.add(pane2);
}
 private void initCont() {
     
    contPane=(JPanel) this.getContentPane();
  contPane.add(cont,BorderLayout.CENTER);
 
 cont.add(ligne1);
  cont.add(ligne2);
  cont.add(ligne3);
    cont.add(ligne31);
    cont.add(ligne32);

  cont.add(ligne4);
  ligne1.add(cinLab);
   ligne1.add(cinText);
  ligne1.add(DateDebutLab);
   ligne1.add(dateDebuttext);
   ligne1.add(DateFinLab);
   ligne1.add(datefintext);
    ligne2.add(matLab);
   ligne2.add(matText);
       ligne2.add(montLab);
   ligne2.add(montText);
   ligne3.add(covoiOption);
   

      ligne3.add(dateDebutCovLab);
   ligne3.add(dateDebutCovtext);
   ligne3.add(lieuDepartLab);
     ligne3.add(lieuDepartText);
   ligne31.add(datefinCovLab);
   ligne31.add(datefinCovText);
 ligne31.add(lieuDistinationLab);
  ligne31.add(lieuDistinationText);

     ligne31.add(nbPlaceLab);
     ligne31.add(nbPlace);

      ligne32.add(bajouter);
   ligne32.add(bconsulter);
  // ligne32.add(bmodfier);
   ligne32.add(bsupprimer);
   ligne32.add(bimpr);
           datefinCovText.setEnabled(false);
dateDebutCovtext.setEnabled(false);

    lieuDepartText.setEnabled(false);
    lieuDistinationText.setEnabled(false);
    nbPlace.setEnabled(false);           

covoiOption.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
       if(covoiOption.isSelected())
        {
datefinCovText.setEnabled(true);
dateDebutCovtext.setEnabled(true);

    lieuDepartText.setEnabled(true);
    lieuDistinationText.setEnabled(true);
    nbPlace.setEnabled(true);           
        }else{
           datefinCovText.setEnabled(false);
dateDebutCovtext.setEnabled(false);

    lieuDepartText.setEnabled(false);
    lieuDistinationText.setEnabled(false);
    nbPlace.setEnabled(false);           

       
       }
        }
    });
        
 
 }   
public String getDatefinCov()
{
    Calendar s=datefinCovText.getSelectedDate();
Date date =  s.getTime();
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
System.out.println(dateFormat.format(date));
return dateFormat.format(date);
}
public String getDateDebutCovtext()
{
    Calendar s=dateDebutCovtext.getSelectedDate();
Date date =  s.getTime();
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
System.out.println(dateFormat.format(date));
return dateFormat.format(date);
}
public String getDateDebut()
{
    Calendar s=dateDebuttext.getSelectedDate();
Date date =  s.getTime();
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
System.out.println(dateFormat.format(date));
return dateFormat.format(date);
}
public String getDatedatefint()
{
    Calendar s=datefintext.getSelectedDate();
Date date =  s.getTime();
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
System.out.println(dateFormat.format(date));
return dateFormat.format(date);
}
private  void initTableLocation(){
 
 
         columnsName[0] = "N°Location";
         columnsName[1] = "Cin Client";
                 columnsName[2] = "Matricule";

        columnsName[3] = "Date Debut";
        columnsName[4] = "Date Fin";
        columnsName[5] = "Montant";
        columnsName[6] = "N°Covoiturage";
              
                
        mLocation.setColumnIdentifiers(columnsName);
      
        
        tLocation.setModel(mLocation);

d.setPreferredSize(new Dimension(1000,500));
        pane.setPreferredSize(new Dimension(590,500));
         d.add(pane);
         d.add(Pvoit);        

                 ligne4.add(d,BorderLayout.CENTER);

 }
public Location getRowSelected(){
     try{
         return new Location(
        (String) mLocation.getValueAt(tLocation.getSelectedRow(), 0),
        (String) mLocation.getValueAt(tLocation.getSelectedRow(),1),
         (String) mLocation.getValueAt(tLocation.getSelectedRow(),2),
         (String) mLocation.getValueAt(tLocation.getSelectedRow(),3),
         (String) mLocation.getValueAt(tLocation.getSelectedRow(), 4),
         (String) mLocation.getValueAt(tLocation.getSelectedRow(), 5)
      
         );
     }catch(Exception e)
        {
            return null;
                }
    
}
private String getNumLoacation() {
nbloc++;
String ch="Loc-"+String.valueOf(nbloc);
    return ch;

    }
private String getMontant() {
     Calendar s1=dateDebuttext.getSelectedDate();
    Calendar s2=datefintext.getSelectedDate();    
    long diffInMillies = Math.abs(s2.getTime().getTime() - s1.getTime().getTime());
    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
Vehicule ve=new VehiculeDAO().Select(new Vehicule(matText.getText()));

Marque de=new MarqueDAO().Select(new Marque(ve.getidMarque()));
  float mont=de.getTarifJourFloat()*(diff+1);
return String.valueOf(mont);}
private String getNumCoviturage(){
nbcov++;
String ch="Cov-"+String.valueOf(nbcov);
    return ch;

} 

@Override 
public void updateUI(){
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
