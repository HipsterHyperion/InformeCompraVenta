/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package informedecompraventa;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Lucho
 */
public class Comprobante {
    private String fechaEmicion;//
    private int tipo;//Factura A, Factura B, Factura C.
    private String numeroComprobante;//5 digitos de punto de vente y seguido de 30 digitos numericos diferentes de '0'
    private int codigoVendedor;// 80(cuit), 82 (cuil) y 92 (en tramite).
    private String numeroId; //el cuit, cuil, o dni en caso de tramite 00-dni-0
    private String nombreVendedor; //si no es persona fisica con la razon social
    private double importeTotal; 
    private int cantAlicuotas; //alicuotas iva que posee el comprobante
    private HashSet<Alicuota> alicuotas; // el comprobante puede tener varias alicuotas
    private double conceptosSinPrecioNetoGravado; //importes que no estan en el echo imponible
    private double operacionesExentas;// caso de que en una operacion haya cosas exentas y gravadas, solo se pone el monto de la parte exenta.
    private double pagosAcuentaDeIva; // cobro de iva 
    private double pagosAotrosImpuestosNacionales;// cosas aparte ademas del iva
    private double impuestosBrutos; // percepciones de ingresos brutos 
    private double impuestoMunicipal;// 
    private double impuestosInternos;//
    private double otrosTributos;// 
    private int codigoMoneda;// PES (PESOS) DOL(DOLLAR) 060 (EUROS) // CON LA SOLAPITA
    private double tipoCambio; // 4ENTEROS, 6 DECIMALES 
    private int codigoOperacion;// LISTA DESPLEGABLE 
/* Código de Operación: se deberá seleccionar de la lista desplegable una opción distinta a NO
CORRESPONDE (valor por defecto) sólo si la operación que se informa incluye montos con
alícuotas de IVA en cero. Las opciones que se contemplan en este sistema son: 0 (No
Corresponde), A (No alcanzado), N (No gravado) y X (Importación del Exterior).*/    

    public Comprobante(String fechaEmicion, int tipo, String numeroComprobante, int codigoVendedor, String numeroId, String nombreVendedor, double importeTotal, int cantAlicuotas, HashSet<Alicuota> alicuotas, int codigoMoneda, double tipoCambio, int codigoOperacion) {
        this.fechaEmicion = fechaEmicion;
        this.tipo = tipo;
        this.numeroComprobante = numeroComprobante;
        this.codigoVendedor = codigoVendedor;
        this.numeroId = numeroId;
        this.nombreVendedor = nombreVendedor;
        this.importeTotal = importeTotal;
        this.cantAlicuotas = cantAlicuotas;
        alicuotas = new HashSet<>();
        this.codigoMoneda = codigoMoneda;
        this.tipoCambio = tipoCambio;
        this.codigoOperacion = codigoOperacion;
        this.conceptosSinPrecioNetoGravado = 0;
        this.operacionesExentas = 0;
        this.pagosAcuentaDeIva = 0;
        this.pagosAotrosImpuestosNacionales =0;
        this.impuestosBrutos =0;
        this.impuestoMunicipal = 0;
        this.impuestosInternos = 0;
        this.otrosTributos = 0;
    }

    

    public String getFechaEmicion() {
        return fechaEmicion;
    }

    public int getTipo() {
        return tipo;
    }

    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    public int getCodigoVendedor() {
        return codigoVendedor;
    }

    public String getNumeroId() {
        return numeroId;
    }

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public double getImporteTotal() {
        return importeTotal;
    }

    public int getCantAlicuotas() {
        return cantAlicuotas;
    }

    public HashSet<Alicuota> getAlicuotas() {
        return alicuotas;
    }

    public double getConceptosSinPrecionNetoGravado() {
        return conceptosSinPrecioNetoGravado;
    }

    public double getOperacionesExentas() {
        return operacionesExentas;
    }

    public double getPagosAcuentaDeIva() {
        return pagosAcuentaDeIva;
    }

    public double getPagosAotrosImpuestosNacionales() {
        return pagosAotrosImpuestosNacionales;
    }

    public double getImpuestosBrutos() {
        return impuestosBrutos;
    }

    public double getImpuestoMunicipal() {
        return impuestoMunicipal;
    }

    public double getImpuestosInternos() {
        return impuestosInternos;
    }

    public double getOtrosTributos() {
        return otrosTributos;
    }

    public int getCodigoMoneda() {
        return codigoMoneda;
    }

    public double getTipoCambio() {
        return tipoCambio;
    }

    public int getCodigoOperacion() {
        return codigoOperacion;
    }

    public void setFechaEmicion(String fechaEmicion) {
        this.fechaEmicion = fechaEmicion;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public void setCodigoVendedor(int codigoVendedor) {
        this.codigoVendedor = codigoVendedor;
    }

    public void setNumeroId(String numeroId) {
        this.numeroId = numeroId;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }

    public void setImporteTotal(double importeTotal) {
        this.importeTotal = importeTotal;
    }

    public void setCantAlicuotas(int cantAlicuotas) {
        this.cantAlicuotas = cantAlicuotas;
    }

    public void setAlicuotas(HashSet<Alicuota> alicuotas) {
        this.alicuotas = alicuotas;
    }

    public void setConceptosSinPrecionNetoGravado(double conceptosSinPrecionNetoGravado) {
        this.conceptosSinPrecioNetoGravado = conceptosSinPrecionNetoGravado;
    }

    public void setOperacionesExentas(double operacionesExentas) {
        this.operacionesExentas = operacionesExentas;
    }

    public void setPagosAcuentaDeIva(double pagosAcuentaDeIva) {
        this.pagosAcuentaDeIva = pagosAcuentaDeIva;
    }

    public void setPagosAotrosImpuestosNacionales(double pagosAotrosImpuestosNacionales) {
        this.pagosAotrosImpuestosNacionales = pagosAotrosImpuestosNacionales;
    }

    public void setImpuestosBrutos(double impuestosBrutos) {
        this.impuestosBrutos = impuestosBrutos;
    }

    public void setImpuestoMunicipal(double impuestoMunicipal) {
        this.impuestoMunicipal = impuestoMunicipal;
    }

    public void setImpuestosInternos(double impuestosInternos) {
        this.impuestosInternos = impuestosInternos;
    }

    public void setOtrosTributos(double otrosTributos) {
        this.otrosTributos = otrosTributos;
    }

    public void setCodigoMoneda(int codigoMoneda) {
        this.codigoMoneda = codigoMoneda;
    }

    public void setTipoCambio(double tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public void setCodigoOperacion(int codigoOperacion) {
        this.codigoOperacion = codigoOperacion;
    }
    
    public Comprobante(String fechaEmicion, int tipo, String numeroComprobante, int codigoVendedor, 
            String numeroId, String nombreVendedor, double importeTotal, double conceptosSinPrecionNetoGravado, double operacionesExentas, 
            double pagosAcuentaDeIva, double pagosAotrosImpuestosNacionales, double impuestosBrutos, 
            double impuestoMunicipal, double impuestosInternos, double otrosTributos, int codigoMoneda, 
            double tipoCambio, int codigoOperacion, int cantAlicuotas, 
            HashSet<Alicuota> alicuotas) {
        this.fechaEmicion = fechaEmicion;
        this.tipo = tipo;
        this.numeroComprobante = numeroComprobante;
        this.codigoVendedor = codigoVendedor;
        this.numeroId = numeroId;
        this.nombreVendedor = nombreVendedor;
        this.importeTotal = importeTotal;
        this.cantAlicuotas = cantAlicuotas;
        this.alicuotas = alicuotas;
        this.conceptosSinPrecioNetoGravado = conceptosSinPrecionNetoGravado;
        this.operacionesExentas = operacionesExentas;
        this.pagosAcuentaDeIva = pagosAcuentaDeIva;
        this.pagosAotrosImpuestosNacionales = pagosAotrosImpuestosNacionales;
        this.impuestosBrutos = impuestosBrutos;
        this.impuestoMunicipal = impuestoMunicipal;
        this.impuestosInternos = impuestosInternos;
        this.otrosTributos = otrosTributos;
        this.codigoMoneda = codigoMoneda;
        this.tipoCambio = tipoCambio;
        this.codigoOperacion = codigoOperacion;
    }

    public Comprobante(String fechaEmicion, int tipo, String numeroComprobante, int codigoVendedor, 
            String numeroId, String nombreVendedor, double importeTotal, double conceptosSinPrecionNetoGravado, double operacionesExentas, 
            double pagosAcuentaDeIva, double pagosAotrosImpuestosNacionales, double impuestosBrutos, 
            double impuestoMunicipal, double impuestosInternos, double otrosTributos, int codigoMoneda, 
            double tipoCambio, int codigoOperacion, int cantAlicuotas) {
        this.fechaEmicion = fechaEmicion;
        this.tipo = tipo;
        this.numeroComprobante = numeroComprobante;
        this.codigoVendedor = codigoVendedor;
        this.numeroId = numeroId;
        this.nombreVendedor = nombreVendedor;
        this.importeTotal = importeTotal;
        this.cantAlicuotas = cantAlicuotas;
        this.conceptosSinPrecioNetoGravado = conceptosSinPrecionNetoGravado;
        this.operacionesExentas = operacionesExentas;
        this.pagosAcuentaDeIva = pagosAcuentaDeIva;
        this.pagosAotrosImpuestosNacionales = pagosAotrosImpuestosNacionales;
        this.impuestosBrutos = impuestosBrutos;
        this.impuestoMunicipal = impuestoMunicipal;
        this.impuestosInternos = impuestosInternos;
        this.otrosTributos = otrosTributos;
        this.codigoMoneda = codigoMoneda;
        this.tipoCambio = tipoCambio;
        this.codigoOperacion = codigoOperacion;
        this.alicuotas = new HashSet<>();
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.numeroComprobante);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Comprobante other = (Comprobante) obj;
        return Objects.equals(this.numeroComprobante, other.numeroComprobante);
    }
    
}
