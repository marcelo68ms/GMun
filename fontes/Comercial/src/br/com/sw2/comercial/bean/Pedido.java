/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.sw2.comercial.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author marcelo.santos
 */
@Entity
@Table(name = "TBLPEDIDO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p"),
    @NamedQuery(name = "Pedido.findByNrpedido", query = "SELECT p FROM Pedido p WHERE p.nrpedido = :nrpedido"),
    @NamedQuery(name = "Pedido.findByDspedido", query = "SELECT p FROM Pedido p WHERE p.dspedido = :dspedido")})
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NRPEDIDO", nullable = false)
    private Integer nrpedido;
    @Column(name = "DSPEDIDO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dspedido;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
    private List<Orcamento> orcamentoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
    private List<ItemPedido> itemPedidoList;

    public Pedido() {
    }

    public Pedido(Integer nrpedido) {
        this.nrpedido = nrpedido;
    }

    public Integer getNrpedido() {
        return nrpedido;
    }

    public void setNrpedido(Integer nrpedido) {
        this.nrpedido = nrpedido;
    }

    public Date getDspedido() {
        return dspedido;
    }

    public void setDspedido(Date dspedido) {
        this.dspedido = dspedido;
    }

    @XmlTransient
    public List<Orcamento> getOrcamentoList() {
        return orcamentoList;
    }

    public void setOrcamentoList(List<Orcamento> orcamentoList) {
        this.orcamentoList = orcamentoList;
    }

    @XmlTransient
    public List<ItemPedido> getItemPedidoList() {
        return itemPedidoList;
    }

    public void setItemPedidoList(List<ItemPedido> itemPedidoList) {
        this.itemPedidoList = itemPedidoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nrpedido != null ? nrpedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.nrpedido == null && other.nrpedido != null) || (this.nrpedido != null && !this.nrpedido.equals(other.nrpedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sw2.comercial.bean.Pedido[ nrpedido=" + nrpedido + " ]";
    }
    
}
