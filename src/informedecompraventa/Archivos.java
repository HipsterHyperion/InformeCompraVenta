/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package informedecompraventa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.TreeMap;
/**
 *
 * @author Lucho
 */
public class Archivos {
    private File arch;
    private final static String  archCl= "Clientes.txt";
    private final static String archComp = "Comprobantes.txt";
    private final static String extensionCSV = ".csv";
    private final static String extensionTXT = ".txt";
    
    
    
    
    private void abrirArchivo(String nombreArch){
        
        try{   
            arch=new File(nombreArch);
            if(!arch.exists()){             //si no existe creo un archivo vacio
                escribirArchivo("");
                cerrarArchivo();
            }
        }
        catch(Exception ex){
        }
    }
     
    private void cerrarArchivo(){
        
        try{
            FileWriter fw=new FileWriter(arch,false); //escribo a continuacion de lo anterior
            fw.close();
        }
        catch(IOException ex){
        }
    }
    
    public void borrarArchivo(){
        abrirArchivo(archCl);
        arch.delete();
        abrirArchivo(archComp);
        arch.delete();
    } 
            
    
    public TreeMap<String,Cliente> leerArchivoCliente () throws FileNotFoundException, IOException{
        TreeMap<String,Cliente> Tcl = new TreeMap<>();
        this.abrirArchivo(archCl);
        String linea="null";
        FileReader fr = new FileReader(arch);
        BufferedReader br=new BufferedReader(fr);
        String[] row = null; 
        try{
                   while((linea=br.readLine()) != null) {
                       switch(Integer.valueOf(linea)){
                           case 0:{
                               Cliente nuevo = new Cliente(br.readLine(),br.readLine(),br.readLine(),br.readLine());
                               Tcl.put(nuevo.getCuit(), nuevo);
                               break; 
                            }
                           case 1:{
                               ClienteComercio nuevo = new ClienteComercio(br.readLine(),br.readLine(),br.readLine(),br.readLine(),br.readLine(),br.readLine(),br.readLine(),br.readLine());
                               Tcl.put(nuevo.getCuitComercio(), nuevo);
                               break;
                            }
                       }
            }
            fr.close();
            br.close();
            
        }catch(Exception e){
            
        }
        return Tcl;
    }
    public HashSet<Comprobante> leerArchivoComprobante() throws FileNotFoundException, IOException{
        HashSet<Comprobante> comps = new HashSet<>();
        HashSet<Alicuota> alis= new HashSet<>();
        String linea,fecha;
        this.abrirArchivo(archComp);
        FileReader fr = new FileReader(arch);
        BufferedReader br = new BufferedReader (fr);
        try {
            while((linea=br.readLine())!=null){
            Comprobante c = new Comprobante(linea,Integer.valueOf(br.readLine()),br.readLine(),Integer.valueOf(br.readLine()),
                    br.readLine(),br.readLine(),Double.valueOf(br.readLine()),Double.valueOf(br.readLine()),Double.valueOf(br.readLine()),Double.valueOf(br.readLine()),
                    Double.valueOf(br.readLine()),Double.valueOf(br.readLine()),Double.valueOf(br.readLine()),Double.valueOf(br.readLine()),Double.valueOf(br.readLine()),
                    Integer.valueOf(br.readLine()),Double.valueOf(br.readLine()),Integer.valueOf(br.readLine()),Integer.valueOf(br.readLine()));
         for (int i = 0; i<c.getCantAlicuotas();i++){
             Alicuota al = new Alicuota (br.readLine(),Double.valueOf(br.readLine()),Integer.valueOf(br.readLine()),Double.valueOf(br.readLine()));
             alis.add(al);
         }
         c.setAlicuotas(alis);
         comps.add(c);
        }
            fr.close();
            br.close();
    }catch(IOException e) {
        
    }  
    return comps;
    }
    public void escribirArchivo(String cadena){
        try{
            FileWriter fw=new FileWriter(arch,true);                            //escribo a continuacion de lo anterior  
            BufferedWriter bw=new BufferedWriter(fw);                           //que pasa si ingreso un salto de linea
            bw.write(cadena);
            bw.newLine();
            bw.close();
            fw.close();
        }
        catch(Exception ex){
        }
    }
    
    public void guardarComprobante(Comprobante c){ 
        try{
            this.abrirArchivo(archComp);
            this.escribirArchivo(c.getFechaEmicion());
            this.escribirArchivo(String.valueOf(c.getTipo()));
            this.escribirArchivo(c.getNumeroComprobante());
            this.escribirArchivo(String.valueOf(c.getCodigoVendedor()));
            this.escribirArchivo(c.getNumeroId());
            this.escribirArchivo(c.getNombreVendedor());
            this.escribirArchivo(String.valueOf(c.getImporteTotal()));
            this.escribirArchivo(String.valueOf(c.getConceptosSinPrecionNetoGravado()));
            this.escribirArchivo(String.valueOf(c.getOperacionesExentas()));
            this.escribirArchivo(String.valueOf(c.getPagosAcuentaDeIva()));
            this.escribirArchivo(String.valueOf(c.getPagosAotrosImpuestosNacionales()));
            this.escribirArchivo(String.valueOf(c.getImpuestosBrutos()));
            this.escribirArchivo(String.valueOf(c.getImpuestoMunicipal()));
            this.escribirArchivo(String.valueOf(c.getImpuestosInternos()));
            this.escribirArchivo(String.valueOf(c.getOtrosTributos()));
            this.escribirArchivo(String.valueOf(c.getCodigoMoneda()));
            this.escribirArchivo(String.valueOf(c.getTipoCambio()));
            this.escribirArchivo(String.valueOf(c.getCodigoOperacion()));
            this.escribirArchivo(String.valueOf(c.getCantAlicuotas()));
                 for (Alicuota al: c.getAlicuotas()){
                     this.escribirArchivo(String.valueOf(al.getDescripcion()));
                     this.escribirArchivo(String.valueOf(al.getImporteNetoGravado()));
                     this.escribirArchivo(String.valueOf(al.getCodigoAlicuota()));
                     this.escribirArchivo(String.valueOf(al.getPorcentajeAsociado()));
            } 
        }catch(Exception e){
            
        }
    }
    public void guardarCliente (Cliente cl){
        this.abrirArchivo(archCl);
        if (cl instanceof ClienteComercio){
            this.escribirArchivo("1");
            ClienteComercio aux = (ClienteComercio) cl;
            this.escribirArchivo (aux.getNombre());
            this.escribirArchivo (aux.getApellido());
            this.escribirArchivo (aux.getCuit());
            this.escribirArchivo (aux.getTelefono());
            this.escribirArchivo (aux.getNombreComercio());
            this.escribirArchivo (aux.getDireComercio());
            this.escribirArchivo (aux.getCuitComercio());
            this.escribirArchivo (aux.getTelComercio());
        }else {
        this.escribirArchivo("0");
        this.escribirArchivo (cl.getNombre());
        this.escribirArchivo (cl.getApellido());
        this.escribirArchivo (cl.getCuit());
        this.escribirArchivo (cl.getTelefono());
        }
    }
public void exportarComprobantes(HashSet<Comprobante> comps,String nom){
    
    String cadena;
    cadena = "";
    this.escribirArchivo("Numero de Comprobante,Factura,Fecha de emicion,Nombre del Vendedor,"
            + "Codigo del vendedor,Cuit/Cuil Vendedor,Importe total,"
            + "Cantidad de Alicuotas,conceptos Sin Precio Neto Gravado,operaciones Exentas,pagos Acuenta De Iva,"
            + "pagos a Otros Impuestos Nacionales,Impuestos Brutos,Impuesto Municipal,Impuestos Internos,"
            + "Otros tributos,Moneda,Tipo de cambio,Codigo de operacion");
    for (Comprobante comp: comps){
        cadena = comp.getNumeroComprobante() +","+ comp.getFechaEmicion() +","+ comp.getNombreVendedor() +","+ comp.getCodigoVendedor()
                +","+ comp.getNumeroId()+","+comp.getImporteTotal()+","+comp.getCantAlicuotas()+","+comp.getConceptosSinPrecionNetoGravado()+","+
                comp.getOperacionesExentas()+","+comp.getPagosAcuentaDeIva()+","+comp.getPagosAotrosImpuestosNacionales()+","+
                comp.getImpuestosBrutos()+","+comp.getImpuestoMunicipal()+","+comp.getImpuestosInternos()+","+
                comp.getOtrosTributos()+","+comp.getCodigoMoneda()+","+comp.getTipoCambio()+","+comp.getCodigoOperacion();
        this.escribirArchivo(cadena);
    }
}

public void exportarAlicuotas(HashSet<Comprobante> comp, String nom){
    String cadena;
    this.escribirArchivo("Codigo de alicuota");
    
    for(Comprobante c: comp){
        for (Alicuota al: c.getAlicuotas()){
            cadena = ""+al.getCodigoAlicuota();   
            this.escribirArchivo(cadena);
        }
    }
}
    
}
