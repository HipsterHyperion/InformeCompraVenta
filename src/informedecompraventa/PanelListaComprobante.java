/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package informedecompraventa;

import com.toedter.calendar.JDateChooser;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Martin
 */
public class PanelListaComprobante extends javax.swing.JPanel {

    private PanelCargaComprobante pan;
    private String clave;
    private Archivos f;
    
    /**
     * Creates new form PanelListaComprobante
     */
    
    public PanelListaComprobante(String clave) {
        this.clave=clave;
        initComponents();
        initComp2(this.clave);
        setName("Lista de Comprobante");
    }
 
    public HashSet<Comprobante> compararFechas (Cliente c){
      HashSet<Comprobante> entre=new HashSet<>();
        for (Comprobante comp: c.getComprobante()){
            int anio = 1900 + StringADate(comp.getFechaEmicion()).getYear();
            int mes =StringADate(comp.getFechaEmicion()).getMonth();
            if (jYearChooserDesde.getYear()< jYearChooserHasta.getYear()||
              (jYearChooserDesde.getYear()== jYearChooserHasta.getYear() && jMonthChooserDesde.getMonth() <=jMonthChooserHasta.getMonth()) ){
                 if (jYearChooserDesde.getYear()<anio && anio< jYearChooserHasta.getYear() || ((jYearChooserDesde.getYear()==anio && jMonthChooserDesde.getMonth()<=mes) && (jYearChooserDesde.getYear()==anio && jYearChooserHasta.getYear()>anio) ) ||
                         ((jYearChooserHasta.getYear()==anio && jMonthChooserHasta.getMonth()>=mes) && (jYearChooserHasta.getYear()==anio && jYearChooserDesde.getYear()<anio) )  
                         ||( jYearChooserDesde.getYear()== anio && jYearChooserHasta.getYear()== anio &&(jMonthChooserDesde.getMonth()<=mes&&jMonthChooserHasta.getMonth()>=mes)))
                 entre.add(comp);    
            }
       }
    return entre;    
    }//retornar el set comprobantes y pasarlo por parametros al init tabla 
    
  public java.util.Date StringADate(String fecha){
        SimpleDateFormat formatoTexto = new SimpleDateFormat("dd-MM-yyy");
        Date fechaE=null;
        try{
            fechaE = formatoTexto.parse(fecha); 
            return fechaE;
        }catch (ParseException ex){
              return null;
        }
    }
    
     
    
    
    public void initComp2(String clave){   
        if (FramePrincipal.getTClientes().get(clave) instanceof ClienteComercio){
            ClienteComercio aux = (ClienteComercio) FramePrincipal.getTClientes().get(clave);
            nombreCliente.setText("Comercio: "+ aux.getNombreComercio());
            cuitCliente.setText(String.valueOf(aux.getCuitComercio()));
            initTabla(aux.getComprobante());
        }else{
            nombreCliente.setText(FramePrincipal.getTClientes().get(clave).getNombre() +" "+ FramePrincipal.getTClientes().get(clave).getApellido());
            cuitCliente.setText(String.valueOf(FramePrincipal.getTClientes().get(clave).getCuit()));
            initTabla(FramePrincipal.getTClientes().get(clave).getComprobante());
        }
        
    }
    public void sumaMontoAlicuota(){
        double monto=0;
        int i;
        for (i=0;i<jTableListComps.getRowCount();i++){
            monto = monto + (double)jTableListComps.getValueAt(i, 2);
        }
        jLabelMontoTotalPeriodo.setText(String.valueOf(monto));
    }
    public void iniciarDeCeroTabla(){
        jTableListComps.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Nro Comprobante", "Total", "Cantidad de Alicuota"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }
    public void initTabla(Set<Comprobante> comprobante){
        jButtonVerDet.setEnabled(false);
        int fila=0;
        DefaultTableModel tabla;
        tabla = (DefaultTableModel)jTableListComps.getModel();
        for(Comprobante comp : comprobante){
            tabla.addRow(new Object [4] );
            jTableListComps.setValueAt(comp.getFechaEmicion(), fila, 0);
            jTableListComps.setValueAt(comp.getNumeroComprobante(), fila, 1);
            jTableListComps.setValueAt(comp.getImporteTotal(), fila, 2);
            jTableListComps.setValueAt(comp.getCantAlicuotas(), fila, 3);
            fila++;
        }
        sumaMontoAlicuota();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableListComps = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        nombreCliente = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cuitCliente = new javax.swing.JLabel();
        jButtonVerDet = new javax.swing.JButton();
        jButtVolver = new javax.swing.JButton();
        jButtExpComps = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButtExpAlis1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jYearChooserDesde = new com.toedter.calendar.JYearChooser();
        jMonthChooserDesde = new com.toedter.calendar.JMonthChooser();
        jYearChooserHasta = new com.toedter.calendar.JYearChooser();
        jMonthChooserHasta = new com.toedter.calendar.JMonthChooser();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabelMontoTotalPeriodo = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButtonAplicar = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)), "Listado de Comprobante", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Trebuchet MS", 3, 14), new java.awt.Color(0, 0, 255))); // NOI18N
        setPreferredSize(new java.awt.Dimension(640, 480));
        setLayout(null);

        jTableListComps.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Nro Comprobante", "Total", "Cantidad de Alicuota"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableListComps.getTableHeader().setReorderingAllowed(false);
        jTableListComps.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableListCompsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableListComps);

        add(jScrollPane1);
        jScrollPane1.setBounds(10, 130, 620, 220);

        jLabel1.setText("Cliente");
        add(jLabel1);
        jLabel1.setBounds(30, 30, 60, 20);

        nombreCliente.setText("Nombre del cliente");
        add(nombreCliente);
        nombreCliente.setBounds(110, 30, 160, 20);

        jLabel3.setText("CUIL/CUIT");
        add(jLabel3);
        jLabel3.setBounds(280, 30, 60, 20);

        cuitCliente.setText("Numero de Cuil");
        add(cuitCliente);
        cuitCliente.setBounds(370, 30, 160, 20);

        jButtonVerDet.setText("Modificar Comprobante");
        jButtonVerDet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerDetActionPerformed(evt);
            }
        });
        add(jButtonVerDet);
        jButtonVerDet.setBounds(10, 380, 190, 40);

        jButtVolver.setText("Volver");
        add(jButtVolver);
        jButtVolver.setBounds(10, 440, 190, 40);

        jButtExpComps.setText("Exportar Comprobantes (.csv)");
        jButtExpComps.setEnabled(false);
        jButtExpComps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtExpCompsActionPerformed(evt);
            }
        });
        add(jButtExpComps);
        jButtExpComps.setBounds(420, 440, 210, 40);

        jButton4.setText("Agregar Comprobante");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        add(jButton4);
        jButton4.setBounds(210, 380, 200, 40);

        jLabel2.setText("Seleccionar periodo fiscal a mostrar:");
        jLabel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        add(jLabel2);
        jLabel2.setBounds(20, 60, 190, 20);

        jButtExpAlis1.setText("Exportar Alicuotas (.csv)");
        jButtExpAlis1.setEnabled(false);
        jButtExpAlis1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtExpAlis1ActionPerformed(evt);
            }
        });
        add(jButtExpAlis1);
        jButtExpAlis1.setBounds(210, 440, 200, 40);

        jLabel4.setText("Desde: ");
        add(jLabel4);
        jLabel4.setBounds(270, 60, 50, 20);

        jYearChooserDesde.setEndYear(2018);
        add(jYearChooserDesde);
        jYearChooserDesde.setBounds(480, 60, 60, 20);
        add(jMonthChooserDesde);
        jMonthChooserDesde.setBounds(330, 60, 120, 20);

        jYearChooserHasta.setEndYear(2018);
        add(jYearChooserHasta);
        jYearChooserHasta.setBounds(480, 90, 60, 20);
        add(jMonthChooserHasta);
        jMonthChooserHasta.setBounds(330, 90, 130, 20);

        jLabel5.setText("Hasta: ");
        add(jLabel5);
        jLabel5.setBounds(270, 90, 60, 20);

        jLabel6.setText("Monto Total de Alicuotas  ");
        add(jLabel6);
        jLabel6.setBounds(420, 360, 160, 20);

        jLabelMontoTotalPeriodo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelMontoTotalPeriodo.setText("MONTO TOTAL");
        jLabelMontoTotalPeriodo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        add(jLabelMontoTotalPeriodo);
        jLabelMontoTotalPeriodo.setBounds(420, 400, 210, 20);

        jLabel7.setText("en periodo fiscal:");
        add(jLabel7);
        jLabel7.setBounds(420, 380, 160, 20);

        jButtonAplicar.setText("Aplicar");
        jButtonAplicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAplicarActionPerformed(evt);
            }
        });
        add(jButtonAplicar);
        jButtonAplicar.setBounds(20, 90, 190, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        FramePrincipal.getEscritorio().removeAll();
        FramePrincipal.getEscritorio().repaint();
         VistaListaCliente vl= new VistaListaCliente(new PanelCargaComprobante(this.clave));
         vl.setVisible(true);
        FramePrincipal.getEscritorio().add(vl);
        FramePrincipal.getEscritorio().moveToFront(vl);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButtonVerDetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerDetActionPerformed
        pan = new PanelCargaComprobante(this.clave);
        pan.setName("Modificar Comprobante");
        FramePrincipal.getEscritorio().removeAll();
        FramePrincipal.getEscritorio().repaint();
         VistaListaCliente vl= new VistaListaCliente(pan);
         vl.setVisible(true);
        FramePrincipal.getEscritorio().add(vl);
        FramePrincipal.getEscritorio().moveToFront(vl);
    }//GEN-LAST:event_jButtonVerDetActionPerformed

    private void jButtExpCompsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtExpCompsActionPerformed
         if (JOptionPane.showConfirmDialog(null, "Se exportaran los comprobantes del periodo indicado, en formato .csv\n ¿Desea Continuar?", "Confirm closing",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            try{   
            //String nombArch=FramePrincipal.getTClientes().get(clave).
            //f.exportarComprobantes(compararFechas(),"");
            }catch(Exception ex){
            }
            System.exit(0);
                
        }
      
    }//GEN-LAST:event_jButtExpCompsActionPerformed

    private void jTableListCompsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableListCompsMouseClicked
        jButtonVerDet.setEnabled(true); 
    }//GEN-LAST:event_jTableListCompsMouseClicked

    private void jButtExpAlis1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtExpAlis1ActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Se exportaran las alicuotas del periodo indicado, en formato .csv\n ¿Desea Continuar?", "Confirm closing",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            try{   
            //f.exportarAlicuotas(compararFechas(),"");
            }catch(Exception ex){
            }
            System.exit(0);
                
        }
    }//GEN-LAST:event_jButtExpAlis1ActionPerformed

    private void jButtonAplicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAplicarActionPerformed
        iniciarDeCeroTabla();
        initTabla(compararFechas(FramePrincipal.getTClientes().get(clave)));
    }//GEN-LAST:event_jButtonAplicarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cuitCliente;
    private javax.swing.JButton jButtExpAlis1;
    private javax.swing.JButton jButtExpComps;
    private javax.swing.JButton jButtVolver;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButtonAplicar;
    private javax.swing.JButton jButtonVerDet;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelMontoTotalPeriodo;
    private com.toedter.calendar.JMonthChooser jMonthChooserDesde;
    private com.toedter.calendar.JMonthChooser jMonthChooserHasta;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableListComps;
    private com.toedter.calendar.JYearChooser jYearChooserDesde;
    private com.toedter.calendar.JYearChooser jYearChooserHasta;
    private javax.swing.JLabel nombreCliente;
    // End of variables declaration//GEN-END:variables
}
