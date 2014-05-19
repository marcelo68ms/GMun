/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.sw2.comercial.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author marcelo.santos
 */
@Entity
@Table(name = "TBLMATERIAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Material.findAll", query = "SELECT m FROM Material m"),
    @NamedQuery(name = "Material.findByCdmaterial", query = "SELECT m FROM Material m WHERE m.cdmaterial = :cdmaterial"),
    @NamedQuery(name = "Material.findByDsmaterial", query = "SELECT m FROM Material m WHERE m.dsmaterial = :dsmaterial"),
    @NamedQuery(name = "Material.findByQtdminima", query = "SELECT m FROM Material m WHERE m.qtdminima = :qtdminima"),
    @NamedQuery(name = "Material.findByQtdmaxima", query = "SELECT m FROM Material m WHERE m.qtdmaxima = :qtdmaxima"),
    @NamedQuery(name = "Material.findByDslocal", query = "SELECT m FROM Material m WHERE m.dslocal = :dslocal")})
public class Material implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CDMATERIAL", nullable = false, length = 13)
    private String cdmaterial;
    @Column(name = "DSMATERIAL", length = 100)
    private String dsmaterial;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "QTDMINIMA", precision = 7, scale = 3)
    private BigDecimal qtdminima;
    @Column(name = "QTDMAXIMA", precision = 7, scale = 3)
    private BigDecimal qtdmaxima;
    @Column(name = "DSLOCAL", length = 100)
    private String dslocal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "material")
    private List<ItemPedido> itemPedidoList;
    @JoinColumn(name = "CDTIPOMATERIAL", referencedColumnName = "CDTIPOMATERIAL", nullable = false)
    @ManyToOne(optional = false)
    private TipoMaterial cdtipomaterial;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "material")
    private List<ItemOrcamento> itemOrcamentoList;

    public Material() {
    }

    public Material(String cdmaterial) {
        this.cdmaterial = cdmaterial;
    }

    public String getCdmaterial() {
        return cdmaterial;
    }

    public void setCdmaterial(String cdmaterial) {
        this.cdmaterial = cdmaterial;
    }

    public String getDsmaterial() {
        return dsmaterial;
    }

    public void setDsmaterial(String dsmaterial) {
        this.dsmaterial = dsmaterial;
    }

    public BigDecimal getQtdminima() {
        return qtdminima;
    }

    public void setQtdminima(BigDecimal qtdminima) {
        this.qtdminima = qtdminima;
    }

    public BigDecimal getQtdmaxima() {
        return qtdmaxima;
    }

    public void setQtdmaxima(BigDecimal qtdmaxima) {
        this.qtdmaxima = qtdmaxima;
    }

    public String getDslocal() {
        return dslocal;
    }

    public void setDslocal(String dslocal) {
        this.dslocal = dslocal;
    }

    @XmlTransient
    public List<ItemPedido> getItemPedidoList() {
        return itemPedidoList;
    }

    public void setItemPedidoList(List<ItemPedido> itemPedidoList) {
        this.itemPedidoList = itemPedidoList;
    }

    public TipoMaterial getCdtipomaterial() {
        return cdtipomaterial;
    }

    public void setCdtipomaterial(TipoMaterial cdtipomaterial) {
        this.cdtipomaterial = cdtipomaterial;
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
        hash += (cdmaterial != null ? cdmaterial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Material)) {
            return false;
        }
        Material other = (Material) object;
        if ((this.cdmaterial == null && other.cdmaterial != null) || (this.cdmaterial != null && !this.cdmaterial.equals(other.cdmaterial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sw2.comercial.bean.Material[ cdmaterial=" + cdmaterial + " ]";
    }
    
}
