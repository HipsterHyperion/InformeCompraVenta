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
public class ClienteComercio extends Cliente {
    private String nombreComercio;
    private String direComercio;
    private String cuitComercio;
    private String telComercio;

    public ClienteComercio(String nombreComercio, String direComercio, String cuitComercio, String telComercio, String nombre, String apellido, String cuit, String telefono) {
        super(nombre, apellido, cuit, telefono);
        this.nombreComercio = nombreComercio;
        this.direComercio = direComercio;
        this.cuitComercio = cuitComercio;
        this.telComercio = telComercio;
    }



    public void setNombreComercio(String nombreComercio) {
        this.nombreComercio = nombreComercio;
    }

    public void setDireComercio(String direComercio) {
        this.direComercio = direComercio;
    }

    public void setCuitComercio(String cuitComercio) {
        this.cuitComercio = cuitComercio;
    }

    public void setTelComercio(String telComercio) {
        this.telComercio = telComercio;
    }

    public String getNombreComercio() {
        return nombreComercio;
    }

    public String getDireComercio() {
        return direComercio;
    }

    public String getCuitComercio() {
        return cuitComercio;
    }

    public String getTelComercio() {
        return telComercio;
    }
    

    @Override
    public String toString() {
        return "ClienteComercio{" + "nombreComercio=" + nombreComercio + ", direComercio=" + direComercio + ", cuitComercio=" + cuitComercio + ", telComercio=" + telComercio + '}';
    }
/*public boolean compararClienteComercio(String nombreComercio, String direComercio, int cuitComercio, int telComercio, String nombre, String apellido, int cuit, int telefono) {
        return super.compararCliente(nombre, apellido, cuit, telefono)&&
        this.nombreComercio.compareTo(nombreComercio)==0 &&
        this.direComercio.compareTo(direComercio)==0 &&
        this.cuitComercio==cuitComercio &&
        this.telComercio ==telComercio;
}*/
/*
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (int) (this.cuitComercio ^ (this.cuitComercio >>> 32));
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
        final ClienteComercio other = (ClienteComercio) obj;
        if (this.cuitComercio != other.cuitComercio) {
            return false;
        }
        return true;
    }

*/
}