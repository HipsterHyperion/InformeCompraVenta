/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package informedecompraventa;


import java.util.Collections;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class PanelListaClientes extends javax.swing.JPanel {

   
    
    private int fila;
    private int filaSelec;
    private DefaultTableModel tabla;
    private Cliente clientAux;
    //private TreeMap <String,Cliente> Tclientes;
    
    private PanelCargaCliente panAddCliente;

    
    
    public PanelListaClientes() {
       // Tclientes = FramePrincipal.getTClientes();
        initComponents();
        initComponents2();
        
        //Tclientes.put(c1.getCuit(), c1);
        //Tclientes.put(c2.getCuit(), c2);
        //Tclientes.put(c3.getCuit(), c3);
        //Tclientes.put(c4.getCuitComercio(), c4);
        
    }
    
    public void initComponents2(){
        jButtonBajaClte.setEnabled(false);
        jButtonModCliente.setEnabled(false);
        jButListaComp.setEnabled(false);
        jTablaClientes.getTableHeader().setReorderingAllowed(false);
        jTogglePer.setSelected(true);
        jToggleComer.setSelected(true);
    }
    //METODO PARA QUE SOLO SE INGRESEN NUMEROS 
        public boolean numInvalido(char num){
            return (num<'0' || num>'9');
        }
    //METODO PARA QUE SOLO SE INGRESEN LETRAS
        public boolean letraInvalida(char letra){
        return ((letra<'a'||letra>'z'))&&((letra<'A'||letra>'Z')&& !(letra=='ñ' || letra =='Ñ') && letra!=' ');
        }
    //Se invoca desde check box 
    public void initTablaClientes(TreeMap<String,Cliente> Tc,boolean persona, boolean comercio){
        fila=0;
        jButtonBajaClte.setEnabled(false);
        jButtonModCliente.setEnabled(false);
        jButListaComp.setEnabled(false);
        iniciarTabla();
        tabla = (DefaultTableModel)jTablaClientes.getModel();
        if(persona || comercio){
        for (Cliente c: Tc.values()){
            if (comercio && (c instanceof ClienteComercio)){
            tabla.addRow(new Object [7] );
            ClienteComercio c2 = (ClienteComercio)c;
            jTablaClientes.setValueAt(c2.getNombre()+" "+c.getApellido(),fila, 0);
            jTablaClientes.setValueAt(c2.getCuit(),fila, 2);
            jTablaClientes.setValueAt(c2.getTelefono(),fila, 3);
            jTablaClientes.setValueAt(c2.getNombreComercio(),fila, 1);
            jTablaClientes.setValueAt(c2.getCuitComercio(),fila, 4);
            jTablaClientes.setValueAt(c2.getTelComercio(),fila, 5);
            jTablaClientes.setValueAt(c2.getDireComercio(),fila, 6);
            fila++;
            }
            else if (persona && (c instanceof Cliente) && !(c instanceof ClienteComercio)){
            tabla.addRow(new Object [7]);
                jTablaClientes.setValueAt(c.getNombre()+" "+c.getApellido(),fila, 0);
                jTablaClientes.setValueAt(c.getCuit(),fila, 2);
                jTablaClientes.setValueAt(c.getTelefono(),fila, 3);
                fila++;
            }
        }
        }
    }
    //se invoca desde tabla
    public void iniciarTabla (){
        
        jTablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cliente", "Nombre comercio", "CUIT", "Telefono", "CUIT Comercio", "Telefono Comercio", "Direccion Comercio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Long.class, java.lang.Long.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }
    // REVISARRR---------------------------///////
    public TreeMap<String,Cliente>  buscarPorNombre (TreeMap<String,Cliente>  Arbol,String busqueda){
        TreeMap<String,Cliente> ArbolNuevo = new TreeMap<>() ;
        busqueda.toLowerCase();
        for(Cliente c: Arbol.values()){
            if (c instanceof ClienteComercio){
                ClienteComercio c2 = (ClienteComercio)c;
                String nomb=c2.getNombre().toLowerCase() +" "
                 +c2.getApellido().toLowerCase()+" "
                 +c2.getNombreComercio().toLowerCase();
                if(nomb.contains(busqueda)){
                    ArbolNuevo.put(c2.getCuitComercio(),c2);
                }
            }else{ String nomb=c.getNombre().toLowerCase()+" "+ c.getApellido().toLowerCase();
                if (nomb.contains(busqueda) && !(c instanceof ClienteComercio))
                    ArbolNuevo.put(c.getCuit(),c);
            }
        }
    return ArbolNuevo;
    }


    
    
   /* public Cliente crearCliente (int filaSelec,TreeMap<String,Cliente> Tc){
      String nombComp =(String) jTablaClientes.getValueAt(filaSelec, 0);
        String nombApell []=nombComp.split(" ");
        if (jTablaClientes.getValueAt(filaSelec, 1)!=null){
        ClienteComercio aux = new ClienteComercio(
                     (String)  jTablaClientes.getValueAt(filaSelec,1),
                     (String)  jTablaClientes.getValueAt(filaSelec, 6),
                     (String) jTablaClientes.getValueAt(filaSelec, 4),
                     (String) jTablaClientes.getValueAt(filaSelec, 5),
                     nombApell[0],
                     nombApell[1],
                     (String) jTablaClientes.getValueAt(filaSelec, 2),
                     (String) jTablaClientes.getValueAt(filaSelec, 3));
            return aux;         
        }else {
            Cliente aux = new Cliente(nombApell[0], nombApell[1],String.valueOf(jTablaClientes.getValueAt(filaSelec, 2)),
            String.valueOf(jTablaClientes.getValueAt(filaSelec, 3)));
            return aux;
        }
    }*/
    
    
    public void modificarCliente (int filaSelec, TreeMap<String,Cliente>  Tc){
        String nombComp =(String) jTablaClientes.getValueAt(filaSelec, 0);
        String nombApell []=nombComp.split(" ");
        if (jTablaClientes.getValueAt(filaSelec, 1)!=null){
            //--------SETEO LOS TEXT FIELDS
                    jTextNomb.setText(nombApell[0]); 
                    jTextApell.setText(nombApell[1]);
                    jTextCuitClien.setText(String.valueOf(jTablaClientes.getValueAt(filaSelec, 2)));
                    jTeTelClien.setText( String.valueOf(jTablaClientes.getValueAt(filaSelec, 3)));
                    jTextnombreCom.setText((String)  jTablaClientes.getValueAt(filaSelec,1));
                    jTextDirecc.setText((String)  jTablaClientes.getValueAt(filaSelec, 6));
                    jTextCuitCom.setText(String.valueOf(jTablaClientes.getValueAt(filaSelec, 4)));
                    jTeTelComer.setText( String.valueOf(jTablaClientes.getValueAt(filaSelec, 5)));
                    jDialog1.setSize(450,400);
                    jDialog1.setModal(true);
                    jDialog1.setVisible(true);
                }else {
           //SETEO TEXT FIELDS
                    jTextNomb1.setText(nombApell[0]); 
                    jTextApell1.setText(nombApell[1]);
                    jTextCuitClien1.setText(String.valueOf(jTablaClientes.getValueAt(filaSelec, 2)));
                    jTeTelClien1.setText( String.valueOf( jTablaClientes.getValueAt(filaSelec, 3)));
                    jDialog2.setSize(380,280);
                    jDialog2.setModal(true);
                    jDialog2.setVisible(true);
                    //FALTA CREAR EL MODIFICADO---- GUARDAR O NO LOS CAMBIOS --- Y JOPTION PANE POR SI NO QUIERE ATINAR
                    //Y FLASHEAR CON LOS BOTONESTOOGLE
        } 
    }
   
    public void eliminarCliente (int filaSelec, TreeMap<String,Cliente>  Tc){
               if (jTablaClientes.getValueAt(filaSelec, 1)!=null){
                   Tc.remove(jTablaClientes.getValueAt(filaSelec, 4));
                }else {
                   Tc.remove(jTablaClientes.getValueAt(filaSelec, 2));
               }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jPanelModCliente = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextCuitCom = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButtomSaveChanges = new javax.swing.JButton();
        jTextApell = new javax.swing.JTextField();
        jTextNomb = new javax.swing.JTextField();
        jTeTelClien = new javax.swing.JTextField();
        jTextCuitClien = new javax.swing.JTextField();
        jTextDirecc = new javax.swing.JTextField();
        jTextnombreCom = new javax.swing.JTextField();
        jTeTelComer = new javax.swing.JTextField();
        jButtonDescartar1 = new javax.swing.JButton();
        jDialog2 = new javax.swing.JDialog();
        jPanelModCliente1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButtomSaveChanges1 = new javax.swing.JButton();
        jTextApell1 = new javax.swing.JTextField();
        jTextNomb1 = new javax.swing.JTextField();
        jTeTelClien1 = new javax.swing.JTextField();
        jTextCuitClien1 = new javax.swing.JTextField();
        jButtonDescartar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablaClientes = new javax.swing.JTable();
        jTextBuscarCliente = new javax.swing.JTextField();
        jButtonBajaClte = new javax.swing.JButton();
        jButtonModCliente = new javax.swing.JButton();
        jTogglePer = new javax.swing.JToggleButton();
        jButton1 = new javax.swing.JButton();
        jButListaComp = new javax.swing.JButton();
        jToggleComer = new javax.swing.JToggleButton();
        jLabel13 = new javax.swing.JLabel();

        jPanelModCliente.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)), "Modificar Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Trebuchet MS", 3, 14), new java.awt.Color(0, 0, 255))); // NOI18N
        jPanelModCliente.setToolTipText("");
        jPanelModCliente.setAlignmentX(0.0F);
        jPanelModCliente.setAlignmentY(0.0F);
        jPanelModCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanelModCliente.setFont(new java.awt.Font("Candara", 3, 12)); // NOI18N
        jPanelModCliente.setName(""); // NOI18N
        jPanelModCliente.setPreferredSize(new java.awt.Dimension(400, 300));

        jLabel1.setText("Nombre: ");

        jLabel2.setText("Apellido: ");

        jLabel3.setText("CUIT: ");

        jTextCuitCom.setEditable(false);
        jTextCuitCom.setMinimumSize(new java.awt.Dimension(25, 25));
        jTextCuitCom.setPreferredSize(new java.awt.Dimension(25, 25));
        jTextCuitCom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextCuitComKeyTyped(evt);
            }
        });

        jLabel4.setText("Telefono:");

        jLabel5.setText("Nombre del Comercio: ");

        jLabel6.setText("Direccion del Comercio: ");

        jLabel7.setText("CUIT del Comercio: ");

        jLabel8.setText("Telefono del Comercio: ");

        jButtomSaveChanges.setText("Guardar Cambios");
        jButtomSaveChanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtomSaveChangesActionPerformed(evt);
            }
        });

        jTextApell.setMinimumSize(new java.awt.Dimension(25, 25));
        jTextApell.setPreferredSize(new java.awt.Dimension(25, 25));
        jTextApell.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextApellKeyTyped(evt);
            }
        });

        jTextNomb.setMinimumSize(new java.awt.Dimension(25, 25));
        jTextNomb.setPreferredSize(new java.awt.Dimension(25, 25));
        jTextNomb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextNombKeyTyped(evt);
            }
        });

        jTeTelClien.setMinimumSize(new java.awt.Dimension(25, 25));
        jTeTelClien.setPreferredSize(new java.awt.Dimension(25, 25));
        jTeTelClien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTeTelClienKeyTyped(evt);
            }
        });

        jTextCuitClien.setEditable(false);
        jTextCuitClien.setMinimumSize(new java.awt.Dimension(25, 25));
        jTextCuitClien.setPreferredSize(new java.awt.Dimension(25, 25));
        jTextCuitClien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextCuitClienActionPerformed(evt);
            }
        });
        jTextCuitClien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextCuitClienKeyTyped(evt);
            }
        });

        jTextDirecc.setMinimumSize(new java.awt.Dimension(25, 25));
        jTextDirecc.setPreferredSize(new java.awt.Dimension(25, 25));
        jTextDirecc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextDireccKeyTyped(evt);
            }
        });

        jTextnombreCom.setMinimumSize(new java.awt.Dimension(25, 25));
        jTextnombreCom.setPreferredSize(new java.awt.Dimension(25, 25));
        jTextnombreCom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextnombreComKeyTyped(evt);
            }
        });

        jTeTelComer.setMinimumSize(new java.awt.Dimension(25, 25));
        jTeTelComer.setPreferredSize(new java.awt.Dimension(25, 25));
        jTeTelComer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTeTelComerKeyTyped(evt);
            }
        });

        jButtonDescartar1.setText("Descartar Cambios");
        jButtonDescartar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDescartar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelModClienteLayout = new javax.swing.GroupLayout(jPanelModCliente);
        jPanelModCliente.setLayout(jPanelModClienteLayout);
        jPanelModClienteLayout.setHorizontalGroup(
            jPanelModClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelModClienteLayout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addGroup(jPanelModClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelModClienteLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(137, 137, 137)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelModClienteLayout.createSequentialGroup()
                        .addComponent(jTextNomb, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jTextnombreCom, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelModClienteLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(143, 143, 143)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelModClienteLayout.createSequentialGroup()
                        .addComponent(jTextApell, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jTextDirecc, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelModClienteLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(156, 156, 156)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelModClienteLayout.createSequentialGroup()
                        .addComponent(jTextCuitClien, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jTextCuitCom, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelModClienteLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(141, 141, 141)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelModClienteLayout.createSequentialGroup()
                        .addGroup(jPanelModClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTeTelClien, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonDescartar1))
                        .addGap(47, 47, 47)
                        .addGroup(jPanelModClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTeTelComer, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtomSaveChanges)))))
        );
        jPanelModClienteLayout.setVerticalGroup(
            jPanelModClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelModClienteLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanelModClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5))
                .addGap(6, 6, 6)
                .addGroup(jPanelModClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextNomb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextnombreCom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanelModClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6))
                .addGap(4, 4, 4)
                .addGroup(jPanelModClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelModClienteLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jTextApell, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextDirecc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanelModClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelModClienteLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel3))
                    .addComponent(jLabel7))
                .addGap(6, 6, 6)
                .addGroup(jPanelModClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelModClienteLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jTextCuitClien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextCuitCom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanelModClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanelModClienteLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel8)))
                .addGap(5, 5, 5)
                .addGroup(jPanelModClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTeTelClien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelModClienteLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jTeTelComer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanelModClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtomSaveChanges)
                    .addComponent(jButtonDescartar1))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelModCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelModCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanelModCliente1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)), "Modificar Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Trebuchet MS", 3, 14), new java.awt.Color(0, 0, 255))); // NOI18N
        jPanelModCliente1.setToolTipText("");
        jPanelModCliente1.setAlignmentX(0.0F);
        jPanelModCliente1.setAlignmentY(0.0F);
        jPanelModCliente1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanelModCliente1.setFont(new java.awt.Font("Candara", 3, 12)); // NOI18N
        jPanelModCliente1.setName(""); // NOI18N
        jPanelModCliente1.setPreferredSize(new java.awt.Dimension(380, 200));

        jLabel9.setText("Nombre: ");

        jLabel10.setText("Apellido: ");

        jLabel11.setText("CUIT: ");

        jLabel12.setText("Telefono:");

        jButtomSaveChanges1.setText("Guardar Cambios");
        jButtomSaveChanges1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtomSaveChanges1ActionPerformed(evt);
            }
        });

        jTextApell1.setMinimumSize(new java.awt.Dimension(25, 25));
        jTextApell1.setPreferredSize(new java.awt.Dimension(25, 25));
        jTextApell1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextApell1KeyTyped(evt);
            }
        });

        jTextNomb1.setMinimumSize(new java.awt.Dimension(25, 25));
        jTextNomb1.setPreferredSize(new java.awt.Dimension(25, 25));
        jTextNomb1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextNomb1KeyTyped(evt);
            }
        });

        jTeTelClien1.setMinimumSize(new java.awt.Dimension(25, 25));
        jTeTelClien1.setPreferredSize(new java.awt.Dimension(25, 25));
        jTeTelClien1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTeTelClien1KeyTyped(evt);
            }
        });

        jTextCuitClien1.setEditable(false);
        jTextCuitClien1.setMinimumSize(new java.awt.Dimension(25, 25));
        jTextCuitClien1.setPreferredSize(new java.awt.Dimension(25, 25));
        jTextCuitClien1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextCuitClien1KeyTyped(evt);
            }
        });

        jButtonDescartar.setText("Descartar Cambios");
        jButtonDescartar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDescartarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelModCliente1Layout = new javax.swing.GroupLayout(jPanelModCliente1);
        jPanelModCliente1.setLayout(jPanelModCliente1Layout);
        jPanelModCliente1Layout.setHorizontalGroup(
            jPanelModCliente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelModCliente1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanelModCliente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextNomb1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jTextCuitClien1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDescartar))
                .addGap(14, 14, 14)
                .addGroup(jPanelModCliente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtomSaveChanges1)
                    .addComponent(jTeTelClien1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jTextApell1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanelModCliente1Layout.setVerticalGroup(
            jPanelModCliente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelModCliente1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanelModCliente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(6, 6, 6)
                .addGroup(jPanelModCliente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextNomb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextApell1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelModCliente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelModCliente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextCuitClien1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTeTelClien1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanelModCliente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtomSaveChanges1)
                    .addComponent(jButtonDescartar))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelModCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelModCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 5, 255)), "Listado de Clientes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Trebuchet MS", 3, 14), new java.awt.Color(0, 5, 255))); // NOI18N
        setPreferredSize(new java.awt.Dimension(640, 480));
        setLayout(null);

        jTablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cliente", "Nombre comercio", "CUIT", "Telefono", "CUIT Comercio", "Telefono Comercio", "Direccion Comercio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Long.class, java.lang.Long.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTablaClientes.setEditingColumn(0);
        jTablaClientes.setEditingRow(0);
        jTablaClientes.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTablaClientes.getTableHeader().setReorderingAllowed(false);
        jTablaClientes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTablaClientesFocusLost(evt);
            }
        });
        jTablaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablaClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTablaClientes);
        jTablaClientes.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTablaClientes.getColumnModel().getColumnCount() > 0) {
            jTablaClientes.getColumnModel().getColumn(0).setResizable(false);
            jTablaClientes.getColumnModel().getColumn(1).setResizable(false);
            jTablaClientes.getColumnModel().getColumn(2).setResizable(false);
            jTablaClientes.getColumnModel().getColumn(3).setResizable(false);
            jTablaClientes.getColumnModel().getColumn(4).setResizable(false);
            jTablaClientes.getColumnModel().getColumn(5).setResizable(false);
            jTablaClientes.getColumnModel().getColumn(6).setResizable(false);
        }

        add(jScrollPane1);
        jScrollPane1.setBounds(20, 130, 600, 260);

        jTextBuscarCliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextBuscarCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextBuscarCliente.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextBuscarClienteActionPerformed(evt);
            }
        });
        jTextBuscarCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextBuscarClienteKeyPressed(evt);
            }
        });
        add(jTextBuscarCliente);
        jTextBuscarCliente.setBounds(130, 20, 260, 40);

        jButtonBajaClte.setFont(new java.awt.Font("Segoe Print", 1, 12)); // NOI18N
        jButtonBajaClte.setText("Eliminar Cliente ");
        jButtonBajaClte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBajaClteActionPerformed(evt);
            }
        });
        add(jButtonBajaClte);
        jButtonBajaClte.setBounds(20, 400, 180, 40);

        jButtonModCliente.setFont(new java.awt.Font("Segoe Print", 1, 12)); // NOI18N
        jButtonModCliente.setText("Modificar Cliente");
        jButtonModCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModClienteActionPerformed(evt);
            }
        });
        add(jButtonModCliente);
        jButtonModCliente.setBounds(230, 400, 180, 40);

        jTogglePer.setFont(new java.awt.Font("Segoe Print", 1, 12)); // NOI18N
        jTogglePer.setText("Personas");
        jTogglePer.setHideActionText(true);
        jTogglePer.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jTogglePerItemStateChanged(evt);
            }
        });
        jTogglePer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTogglePerActionPerformed(evt);
            }
        });
        add(jTogglePer);
        jTogglePer.setBounds(20, 70, 180, 40);

        jButton1.setFont(new java.awt.Font("Segoe Print", 1, 12)); // NOI18N
        jButton1.setText("Nuevo Cliente");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1);
        jButton1.setBounds(440, 20, 180, 40);

        jButListaComp.setFont(new java.awt.Font("Segoe Print", 1, 12)); // NOI18N
        jButListaComp.setText("Lista Comprobantes");
        jButListaComp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButListaCompActionPerformed(evt);
            }
        });
        add(jButListaComp);
        jButListaComp.setBounds(440, 400, 180, 40);

        jToggleComer.setFont(new java.awt.Font("Segoe Print", 1, 12)); // NOI18N
        jToggleComer.setText("Comercios");
        jToggleComer.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jToggleComerItemStateChanged(evt);
            }
        });
        jToggleComer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleComerActionPerformed(evt);
            }
        });
        add(jToggleComer);
        jToggleComer.setBounds(210, 70, 180, 40);

        jLabel13.setFont(new java.awt.Font("Segoe Print", 1, 12)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("BUSCADOR");
        jLabel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel13);
        jLabel13.setBounds(20, 20, 100, 40);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextBuscarClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextBuscarClienteActionPerformed

    private void jTextBuscarClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextBuscarClienteKeyPressed
        initTablaClientes(buscarPorNombre(FramePrincipal.getTClientes(),jTextBuscarCliente.getText()),
                   jTogglePer.isSelected(), jToggleComer.isSelected()); 
    }//GEN-LAST:event_jTextBuscarClienteKeyPressed
 
    private void jButtonModClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModClienteActionPerformed
        modificarCliente(filaSelec, FramePrincipal.getTClientes());
    }//GEN-LAST:event_jButtonModClienteActionPerformed

    private void jTablaClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablaClientesMouseClicked
        //jButtonMostrCompr.setEnabled(true);
        jButtonBajaClte.setEnabled(true);
        jButtonModCliente.setEnabled(true);
        jButListaComp.setEnabled(true);
        filaSelec = jTablaClientes.rowAtPoint(evt.getPoint());
    }//GEN-LAST:event_jTablaClientesMouseClicked
   
    
    private void jButtonBajaClteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBajaClteActionPerformed
       if (JOptionPane.showConfirmDialog(null,"¿Esta seguro de que quiere eliminar este cliente?")== 0){
       eliminarCliente(filaSelec, FramePrincipal.getTClientes());
       initTablaClientes(FramePrincipal.getTClientes(),jTogglePer.isSelected(), jToggleComer.isSelected());
       //jButtonMostrCompr.setEnabled(false);
       jButtonBajaClte.setEnabled(false);
       jButtonModCliente.setEnabled(false);
       jButListaComp.setEnabled(false);
       }
    }//GEN-LAST:event_jButtonBajaClteActionPerformed

    private void jTeTelComerKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTeTelComerKeyTyped
        if(numInvalido(evt.getKeyChar()))
        evt.consume();
    
    }//GEN-LAST:event_jTeTelComerKeyTyped

    private void jTextnombreComKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextnombreComKeyTyped
        if(letraInvalida(evt.getKeyChar()))
        evt.consume();
    }//GEN-LAST:event_jTextnombreComKeyTyped

    private void jTextDireccKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextDireccKeyTyped
        if(letraInvalida(evt.getKeyChar()) && numInvalido(evt.getKeyChar()))
        evt.consume();
    }//GEN-LAST:event_jTextDireccKeyTyped

    private void jTextCuitClienKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextCuitClienKeyTyped
        if(numInvalido(evt.getKeyChar()))
        evt.consume();
    }//GEN-LAST:event_jTextCuitClienKeyTyped

    private void jTextCuitClienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextCuitClienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextCuitClienActionPerformed

    private void jTeTelClienKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTeTelClienKeyTyped
        if(numInvalido(evt.getKeyChar()))
        evt.consume();
    }//GEN-LAST:event_jTeTelClienKeyTyped

    private void jTextNombKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextNombKeyTyped
        if(letraInvalida(evt.getKeyChar()))
        evt.consume();
    }//GEN-LAST:event_jTextNombKeyTyped

    private void jTextApellKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextApellKeyTyped
        if(letraInvalida(evt.getKeyChar()))
        evt.consume();
    }//GEN-LAST:event_jTextApellKeyTyped

    private void jButtomSaveChangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtomSaveChangesActionPerformed
       if (JOptionPane.showConfirmDialog(null,"¿Esta seguro de que quiere modificar este cliente?")== 0){
            ClienteComercio edit =(ClienteComercio) FramePrincipal.getTClientes().get((String)jTablaClientes.getValueAt(filaSelec, 4));
            edit.setNombre(jTextNomb.getText());
            edit.setApellido(jTextApell.getText());
            edit.setTelefono(jTeTelClien.getText());
            edit.setDireComercio(jTextDirecc.getText());
            edit.setTelComercio(jTeTelComer.getText());
            edit.setNombreComercio(jTextnombreCom.getText());
            initTablaClientes(FramePrincipal.getTClientes(),jTogglePer.isSelected(), jToggleComer.isSelected());
            jDialog1.setVisible(false);
            jButtonBajaClte.setEnabled(false);
            jButtonModCliente.setEnabled(false);
            jButListaComp.setEnabled(false);
             
       }  
    }//GEN-LAST:event_jButtomSaveChangesActionPerformed

    private void jTextCuitComKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextCuitComKeyTyped
        if(numInvalido(evt.getKeyChar()))
        evt.consume();
    }//GEN-LAST:event_jTextCuitComKeyTyped

    private void jButtomSaveChanges1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtomSaveChanges1ActionPerformed
         if (JOptionPane.showConfirmDialog(null,"¿Esta seguro de que quiere modificar este cliente?")== 0){
            Cliente edit = FramePrincipal.getTClientes().get((String)jTablaClientes.getValueAt(filaSelec, 2));
            edit.setNombre(jTextNomb1.getText());
            edit.setApellido(jTextApell1.getText());
            edit.setTelefono(jTeTelClien1.getText());
            initTablaClientes(FramePrincipal.getTClientes(),jTogglePer.isSelected(), jToggleComer.isSelected());
            jDialog2.setVisible(false);
            jButtonBajaClte.setEnabled(false);
            jButtonModCliente.setEnabled(false);
            jButListaComp.setEnabled(false);
       }  
    }//GEN-LAST:event_jButtomSaveChanges1ActionPerformed

    private void jTextApell1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextApell1KeyTyped
        if(letraInvalida(evt.getKeyChar()))
        evt.consume();
        
    }//GEN-LAST:event_jTextApell1KeyTyped

    private void jTextNomb1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextNomb1KeyTyped
       if(letraInvalida(evt.getKeyChar()))
        evt.consume();
    }//GEN-LAST:event_jTextNomb1KeyTyped

    private void jTeTelClien1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTeTelClien1KeyTyped
       if(numInvalido(evt.getKeyChar()))
        evt.consume();
    }//GEN-LAST:event_jTeTelClien1KeyTyped

    private void jTextCuitClien1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextCuitClien1KeyTyped
        if(numInvalido(evt.getKeyChar()))
        evt.consume();
    }//GEN-LAST:event_jTextCuitClien1KeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        panAddCliente = new PanelCargaCliente();
        FramePrincipal.getEscritorio().removeAll();
        FramePrincipal.getEscritorio().repaint();
         VistaListaCliente vl= new VistaListaCliente(new PanelCargaCliente());
         vl.setVisible(true);
        FramePrincipal.getEscritorio().add(vl);
        FramePrincipal.getEscritorio().moveToFront(vl);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonDescartar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDescartar1ActionPerformed
            jDialog1.setVisible(false);
    }//GEN-LAST:event_jButtonDescartar1ActionPerformed

    private void jButtonDescartarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDescartarActionPerformed
        jDialog2.setVisible(false);
    }//GEN-LAST:event_jButtonDescartarActionPerformed

    private void jTogglePerItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jTogglePerItemStateChanged
        initTablaClientes(FramePrincipal.getTClientes(), jTogglePer.isSelected(), jToggleComer.isSelected());
    }//GEN-LAST:event_jTogglePerItemStateChanged

    private void jToggleComerItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jToggleComerItemStateChanged
        initTablaClientes(FramePrincipal.getTClientes(), jTogglePer.isSelected(), jToggleComer.isSelected());
    }//GEN-LAST:event_jToggleComerItemStateChanged

    private void jButListaCompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButListaCompActionPerformed
        FramePrincipal.getEscritorio().removeAll();
        FramePrincipal.getEscritorio().repaint();//Falta por para el caso de cliente comercio
        if (jTablaClientes.getValueAt(filaSelec, 4)!=null){
            VistaListaCliente vl= new VistaListaCliente(new PanelListaComprobante
            (String.valueOf(jTablaClientes.getValueAt(jTablaClientes.getSelectedRow(), 4))));
            vl.setVisible(true);
        FramePrincipal.getEscritorio().add(vl);
        FramePrincipal.getEscritorio().moveToFront(vl);
        }else{
            VistaListaCliente vl= new VistaListaCliente(new PanelListaComprobante
            (String.valueOf(jTablaClientes.getValueAt(jTablaClientes.getSelectedRow(), 2))));
            vl.setVisible(true);
            FramePrincipal.getEscritorio().add(vl);
            FramePrincipal.getEscritorio().moveToFront(vl);
        }
        
    }//GEN-LAST:event_jButListaCompActionPerformed

    private void jTablaClientesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTablaClientesFocusLost
            
    }//GEN-LAST:event_jTablaClientesFocusLost

    private void jTogglePerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTogglePerActionPerformed
            jButtonBajaClte.setEnabled(false);
            jButtonModCliente.setEnabled(false);
            jButListaComp.setEnabled(false);
    }//GEN-LAST:event_jTogglePerActionPerformed

    private void jToggleComerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleComerActionPerformed
            jButtonBajaClte.setEnabled(false);
            jButtonModCliente.setEnabled(false);
            jButListaComp.setEnabled(false);
    }//GEN-LAST:event_jToggleComerActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButListaComp;
    private javax.swing.JButton jButtomSaveChanges;
    private javax.swing.JButton jButtomSaveChanges1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonBajaClte;
    private javax.swing.JButton jButtonDescartar;
    private javax.swing.JButton jButtonDescartar1;
    private javax.swing.JButton jButtonModCliente;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanelModCliente;
    private javax.swing.JPanel jPanelModCliente1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablaClientes;
    private javax.swing.JTextField jTeTelClien;
    private javax.swing.JTextField jTeTelClien1;
    private javax.swing.JTextField jTeTelComer;
    private javax.swing.JTextField jTextApell;
    private javax.swing.JTextField jTextApell1;
    private javax.swing.JTextField jTextBuscarCliente;
    private javax.swing.JTextField jTextCuitClien;
    private javax.swing.JTextField jTextCuitClien1;
    private javax.swing.JTextField jTextCuitCom;
    private javax.swing.JTextField jTextDirecc;
    private javax.swing.JTextField jTextNomb;
    private javax.swing.JTextField jTextNomb1;
    private javax.swing.JTextField jTextnombreCom;
    private javax.swing.JToggleButton jToggleComer;
    private javax.swing.JToggleButton jTogglePer;
    // End of variables declaration//GEN-END:variables
}
