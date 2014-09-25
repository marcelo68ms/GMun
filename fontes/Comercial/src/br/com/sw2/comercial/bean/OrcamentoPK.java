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
public class OrcamentoPK implements Serializable {

	private static final long serialVersionUID = 457116736282425626L;
	
	@Basic(optional = false)
    @Column(name = "NRPEDIDO", nullable = false)
    private int nrpedido;
    @Basic(optional = false)
    @Column(name = "NRCNPJ", nullable = false, length = 14)
    private String nrcnpj;

    public OrcamentoPK() {
    }

    public OrcamentoPK(int nrpedido, String nrcnpj) {
        this.nrpedido = nrpedido;
        this.nrcnpj = nrcnpj;
    }

    public int getNrpedido() {
        return nrpedido;
    }

    public void setNrpedido(int nrpedido) {
        this.nrpedido = nrpedido;
    }

    public String getNrcnpj() {
        return nrcnpj;
    }

    public void setNrcnpj(String nrcnpj) {
        this.nrcnpj = nrcnpj;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) nrpedido;
        hash += (nrcnpj != null ? nrcnpj.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrcamentoPK)) {
            return false;
        }
        OrcamentoPK other = (OrcamentoPK) object;
        if (this.nrpedido != other.nrpedido) {
            return false;
        }
        if ((this.nrcnpj == null && other.nrcnpj != null) || (this.nrcnpj != null && !this.nrcnpj.equals(other.nrcnpj))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sw2.comercial.bean.OrcamentoPK[ nrpedido=" + nrpedido + ", nrcnpj=" + nrcnpj + " ]";
    }
    
}
