/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package informedecompraventa;

import java.util.HashSet;
import java.util.Set;

/**
 Un cliente puede ser una persona física o un comercio.
Si es una persona se registran su nombre, cuit y teléfono. Si es un comercio, además de los
datos del propietario, los cuales coinciden con los de una persona física, se registra la
siguiente información: nombre del comercio, dirección, cuit y teléfono del comercio.
 */
public class Cliente implements Comparable<Cliente>{
    private String nombre;
    private String apellido;
    private String cuit;
    private String telefono;
    private Set<Comprobante> comprobante;

    public Cliente(String nombre, String apellido, String cuit, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cuit = cuit;
        this.telefono = telefono;
        comprobante = new  HashSet<Comprobante>();
    }

    public Set<Comprobante> getComprobante() {
        return comprobante;
    }

    public void setComprobante(Set<Comprobante> comprobante) {
        this.comprobante = comprobante;
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCuit() {
        return cuit;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Cliente{" + "nombre=" + nombre + ", apellido=" + apellido + ", cuit=" + cuit + ", telefono=" + telefono + '}';
    }

    @Override
    public int compareTo(Cliente c2) {
        if (this.getNombre().compareTo(c2.getNombre())!=0)
            return this.getNombre().compareTo(c2.getNombre());
        else
        {
            return this.getApellido().compareTo(c2.getApellido());
        }
       }
    /*public boolean compararCliente(String nombre, String apellido, Integer cuit, Integer telefono){
        return (this.getTelefono()==telefono && this.getNombre().compareTo(nombre)==0
                && this.getApellido().compareTo(apellido)==0 && (this.getCuit()== cuit));
    }*/

    
    //Para Que no haya dos cuit/cuil iguales en los set
  /*  @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (int) (Integer.getInteger(this.cuit) ^ (Integer.getInteger(this.cuit) >>> 32));
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
        final Cliente other = (Cliente) obj;
        return this.cuit == other.cuit;
    }
    */
}
