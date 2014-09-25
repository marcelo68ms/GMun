/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.sw2.comercial.bean;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author marcelo.santos
 */
@Entity
@Table(name = "TBLTIPOMATERIAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoMaterial.findAll", query = "SELECT t FROM TipoMaterial t"),
    @NamedQuery(name = "TipoMaterial.findByCdtipomaterial", query = "SELECT t FROM TipoMaterial t WHERE t.cdtipomaterial = :cdtipomaterial"),
    @NamedQuery(name = "TipoMaterial.findByDstipomaterial", query = "SELECT t FROM TipoMaterial t WHERE t.dstipomaterial = :dstipomaterial")})
public class TipoMaterial implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CDTIPOMATERIAL", nullable = false)
    private Integer cdtipomaterial;
    @Basic(optional = false)
    @Column(name = "DSTIPOMATERIAL", nullable = false, length = 100)
    private String dstipomaterial;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cdtipomaterial")
    private List<Material> materialList;

    public TipoMaterial() {
    }

    public TipoMaterial(Integer cdtipomaterial) {
        this.cdtipomaterial = cdtipomaterial;
    }

    public TipoMaterial(Integer cdtipomaterial, String dstipomaterial) {
        this.cdtipomaterial = cdtipomaterial;
        this.dstipomaterial = dstipomaterial;
    }

    public Integer getCdtipomaterial() {
        return cdtipomaterial;
    }

    public void setCdtipomaterial(Integer cdtipomaterial) {
        this.cdtipomaterial = cdtipomaterial;
    }

    public String getDstipomaterial() {
        return dstipomaterial;
    }

    public void setDstipomaterial(String dstipomaterial) {
        this.dstipomaterial = dstipomaterial;
    }

    @XmlTransient
    public List<Material> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(List<Material> materialList) {
        this.materialList = materialList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdtipomaterial != null ? cdtipomaterial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoMaterial)) {
            return false;
        }
        TipoMaterial other = (TipoMaterial) object;
        if ((this.cdtipomaterial == null && other.cdtipomaterial != null) || (this.cdtipomaterial != null && !this.cdtipomaterial.equals(other.cdtipomaterial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sw2.comercial.bean.TipoMaterial[ cdtipomaterial=" + cdtipomaterial + " ]";
    }
    
}
