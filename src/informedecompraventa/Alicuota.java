/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package informedecompraventa;

/**
 *
 * @author Lucho
 */
public class Alicuota {
    private String descripcion;
    private double importeNetoGravado;
    private int codigoAlicuota;
    private double porcentajeAsociado; // esta porcentaje varia segun el codigo ingresado de la alicuota

    public Alicuota(String descripcion, double importeNetoGravado, int codigoAlicuota, double porcentajeAsociado) {
        this.descripcion = descripcion;
        this.importeNetoGravado = importeNetoGravado;
        this.codigoAlicuota = codigoAlicuota;
        this.porcentajeAsociado = porcentajeAsociado;
    }

    public Alicuota() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public double getImporteNetoGravado() {
        return importeNetoGravado;
    }

    public int getCodigoAlicuota() {
        return codigoAlicuota;
    }

    public double getPorcentajeAsociado() {
        return porcentajeAsociado;
    }

    public void setImporteNetoGravado(double importeNetoGravado) {
        this.importeNetoGravado = importeNetoGravado;
    }

    public void setCodigoAlicuota(int codigoAlicuota) {
        this.codigoAlicuota = codigoAlicuota;
    }

    public void setPorcentajeAsociado(double porcentajeAsociado) {
        this.porcentajeAsociado = porcentajeAsociado;
    }

    /**
     * @return the Descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param Descripcion the Descripcion to set
     */
    public void setDescripcion(String Descripcion) {
        this.descripcion = Descripcion;
    }
    
}
