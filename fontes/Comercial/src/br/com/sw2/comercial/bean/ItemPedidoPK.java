/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.sw2.comercial.bean;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author marcelo.santos
 */
@Embeddable
public class ItemPedidoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "NRPEDIDO", nullable = false)
    private int nrpedido;
    @Basic(optional = false)
    @Column(name = "CDMATERIAL", nullable = false, length = 13)
    private String cdmaterial;

    public ItemPedidoPK() {
    }

    public ItemPedidoPK(int nrpedido, String cdmaterial) {
        this.nrpedido = nrpedido;
        this.cdmaterial = cdmaterial;
    }

    public int getNrpedido() {
        return nrpedido;
    }

    public void setNrpedido(int nrpedido) {
        this.nrpedido = nrpedido;
    }

    public String getCdmaterial() {
        return cdmaterial;
    }

    public void setCdmaterial(String cdmaterial) {
        this.cdmaterial = cdmaterial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) nrpedido;
        hash += (cdmaterial != null ? cdmaterial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemPedidoPK)) {
            return false;
        }
        ItemPedidoPK other = (ItemPedidoPK) object;
        if (this.nrpedido != other.nrpedido) {
            return false;
        }
        if ((this.cdmaterial == null && other.cdmaterial != null) || (this.cdmaterial != null && !this.cdmaterial.equals(other.cdmaterial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sw2.comercial.bean.ItemPedidoPK[ nrpedido=" + nrpedido + ", cdmaterial=" + cdmaterial + " ]";
    }
    
}
