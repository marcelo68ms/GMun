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
@Table(name = "TBLPEDIDOITEM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemPedido.findAll", query = "SELECT i FROM ItemPedido i"),
    @NamedQuery(name = "ItemPedido.findByNrpedido", query = "SELECT i FROM ItemPedido i WHERE i.itemPedidoPK.nrpedido = :nrpedido"),
    @NamedQuery(name = "ItemPedido.findByCdmaterial", query = "SELECT i FROM ItemPedido i WHERE i.itemPedidoPK.cdmaterial = :cdmaterial"),
    @NamedQuery(name = "ItemPedido.findByNrquant", query = "SELECT i FROM ItemPedido i WHERE i.nrquant = :nrquant")})
public class ItemPedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ItemPedidoPK itemPedidoPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "NRQUANT", precision = 7, scale = 3)
    private BigDecimal nrquant;
    @JoinColumn(name = "NRPEDIDO", referencedColumnName = "NRPEDIDO", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pedido pedido;
    @JoinColumn(name = "CDMATERIAL", referencedColumnName = "CDMATERIAL", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Material material;

    public ItemPedido() {
    }

    public ItemPedido(ItemPedidoPK itemPedidoPK) {
        this.itemPedidoPK = itemPedidoPK;
    }

    public ItemPedido(int nrpedido, String cdmaterial) {
        this.itemPedidoPK = new ItemPedidoPK(nrpedido, cdmaterial);
    }

    public ItemPedidoPK getItemPedidoPK() {
        return itemPedidoPK;
    }

    public void setItemPedidoPK(ItemPedidoPK itemPedidoPK) {
        this.itemPedidoPK = itemPedidoPK;
    }

    public BigDecimal getNrquant() {
        return nrquant;
    }

    public void setNrquant(BigDecimal nrquant) {
        this.nrquant = nrquant;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
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
        hash += (itemPedidoPK != null ? itemPedidoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemPedido)) {
            return false;
        }
        ItemPedido other = (ItemPedido) object;
        if ((this.itemPedidoPK == null && other.itemPedidoPK != null) || (this.itemPedidoPK != null && !this.itemPedidoPK.equals(other.itemPedidoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sw2.comercial.bean.ItemPedido[ itemPedidoPK=" + itemPedidoPK + " ]";
    }
    
}
