/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.sw2.comercial.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marcelo.santos
 */
@Entity
@Table(name = "TBLORCAITEM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemOrcamento.findAll", query = "SELECT i FROM ItemOrcamento i"),
    @NamedQuery(name = "ItemOrcamento.findByNrpedido", query = "SELECT i FROM ItemOrcamento i WHERE i.itemOrcamentoPK.nrpedido = :nrpedido"),
    @NamedQuery(name = "ItemOrcamento.findByNrcnpj", query = "SELECT i FROM ItemOrcamento i WHERE i.itemOrcamentoPK.nrcnpj = :nrcnpj"),
    @NamedQuery(name = "ItemOrcamento.findByCdmaterial", query = "SELECT i FROM ItemOrcamento i WHERE i.itemOrcamentoPK.cdmaterial = :cdmaterial"),
    @NamedQuery(name = "ItemOrcamento.findByVlmaterial", query = "SELECT i FROM ItemOrcamento i WHERE i.vlmaterial = :vlmaterial")})
public class ItemOrcamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ItemOrcamentoPK itemOrcamentoPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VLMATERIAL", precision = 10, scale = 2)
    private BigDecimal vlmaterial;
    @JoinColumns({
        @JoinColumn(name = "NRPEDIDO", referencedColumnName = "NRPEDIDO", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "NRCNPJ", referencedColumnName = "NRCNPJ", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Orcamento orcamento;
    @JoinColumn(name = "CDMATERIAL", referencedColumnName = "CDMATERIAL", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Material material;

    public ItemOrcamento() {
    }

    public ItemOrcamento(ItemOrcamentoPK itemOrcamentoPK) {
        this.itemOrcamentoPK = itemOrcamentoPK;
    }

    public ItemOrcamento(int nrpedido, String nrcnpj, String cdmaterial) {
        this.itemOrcamentoPK = new ItemOrcamentoPK(nrpedido, nrcnpj, cdmaterial);
    }

    public ItemOrcamentoPK getItemOrcamentoPK() {
        return itemOrcamentoPK;
    }

    public void setItemOrcamentoPK(ItemOrcamentoPK itemOrcamentoPK) {
        this.itemOrcamentoPK = itemOrcamentoPK;
    }

    public BigDecimal getVlmaterial() {
        return vlmaterial;
    }

    public void setVlmaterial(BigDecimal vlmaterial) {
        this.vlmaterial = vlmaterial;
    }

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemOrcamentoPK != null ? itemOrcamentoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemOrcamento)) {
            return false;
        }
        ItemOrcamento other = (ItemOrcamento) object;
        if ((this.itemOrcamentoPK == null && other.itemOrcamentoPK != null) || (this.itemOrcamentoPK != null && !this.itemOrcamentoPK.equals(other.itemOrcamentoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sw2.comercial.bean.ItemOrcamento[ itemOrcamentoPK=" + itemOrcamentoPK + " ]";
    }
    
}
