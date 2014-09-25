/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.sw2.comercial.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "TBLORCAMENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orcamento.findAll", query = "SELECT o FROM Orcamento o"),
    @NamedQuery(name = "Orcamento.findByNrpedido", query = "SELECT o FROM Orcamento o WHERE o.orcamentoPK.nrpedido = :nrpedido"),
    @NamedQuery(name = "Orcamento.findByNrcnpj", query = "SELECT o FROM Orcamento o WHERE o.orcamentoPK.nrcnpj = :nrcnpj"),
    @NamedQuery(name = "Orcamento.findByDtenvio", query = "SELECT o FROM Orcamento o WHERE o.dtenvio = :dtenvio"),
    @NamedQuery(name = "Orcamento.findByDtretorno", query = "SELECT o FROM Orcamento o WHERE o.dtretorno = :dtretorno")})
public class Orcamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OrcamentoPK orcamentoPK;
    @Column(name = "DTENVIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtenvio;
    @Column(name = "DTRETORNO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtretorno;
    @JoinColumn(name = "NRPEDIDO", referencedColumnName = "NRPEDIDO", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pedido pedido;
    @JoinColumn(name = "NRCNPJ", referencedColumnName = "NRCNPJ", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Fornecedor fornecedor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orcamento")
    private List<ItemOrcamento> itemOrcamentoList;

    public Orcamento() {
    }

    public Orcamento(OrcamentoPK orcamentoPK) {
        this.orcamentoPK = orcamentoPK;
    }

    public Orcamento(int nrpedido, String nrcnpj) {
        this.orcamentoPK = new OrcamentoPK(nrpedido, nrcnpj);
    }

    public OrcamentoPK getOrcamentoPK() {
        return orcamentoPK;
    }

    public void setOrcamentoPK(OrcamentoPK orcamentoPK) {
        this.orcamentoPK = orcamentoPK;
    }

    public Date getDtenvio() {
        return dtenvio;
    }

    public void setDtenvio(Date dtenvio) {
        this.dtenvio = dtenvio;
    }

    public Date getDtretorno() {
        return dtretorno;
    }

    public void setDtretorno(Date dtretorno) {
        this.dtretorno = dtretorno;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    @XmlTransient
    public List<ItemOrcamento> getItemOrcamentoList() {
        return itemOrcamentoList;
    }

    public void setItemOrcamentoList(List<ItemOrcamento> itemOrcamentoList) {
        this.itemOrcamentoList = itemOrcamentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orcamentoPK != null ? orcamentoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orcamento)) {
            return false;
        }
        Orcamento other = (Orcamento) object;
        if ((this.orcamentoPK == null && other.orcamentoPK != null) || (this.orcamentoPK != null && !this.orcamentoPK.equals(other.orcamentoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sw2.comercial.bean.Orcamento[ orcamentoPK=" + orcamentoPK + " ]";
    }
    
}
