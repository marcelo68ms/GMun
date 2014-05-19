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
@Table(name = "TBLFORNECEDOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fornecedor.findAll", query = "SELECT f FROM Fornecedor f"),
    @NamedQuery(name = "Fornecedor.findByNrcnpj", query = "SELECT f FROM Fornecedor f WHERE f.nrcnpj = :nrcnpj"),
    @NamedQuery(name = "Fornecedor.findByNmrazaosocial", query = "SELECT f FROM Fornecedor f WHERE f.nmrazaosocial = :nmrazaosocial"),
    @NamedQuery(name = "Fornecedor.findByDsendereco", query = "SELECT f FROM Fornecedor f WHERE f.dsendereco = :dsendereco"),
    @NamedQuery(name = "Fornecedor.findByDscidade", query = "SELECT f FROM Fornecedor f WHERE f.dscidade = :dscidade"),
    @NamedQuery(name = "Fornecedor.findByDsestado", query = "SELECT f FROM Fornecedor f WHERE f.dsestado = :dsestado"),
    @NamedQuery(name = "Fornecedor.findByDscep", query = "SELECT f FROM Fornecedor f WHERE f.dscep = :dscep"),
    @NamedQuery(name = "Fornecedor.findByDsinscestadual", query = "SELECT f FROM Fornecedor f WHERE f.dsinscestadual = :dsinscestadual"),
    @NamedQuery(name = "Fornecedor.findByDsinscmunicipal", query = "SELECT f FROM Fornecedor f WHERE f.dsinscmunicipal = :dsinscmunicipal"),
    @NamedQuery(name = "Fornecedor.findByDtcadastro", query = "SELECT f FROM Fornecedor f WHERE f.dtcadastro = :dtcadastro")})
public class Fornecedor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NRCNPJ", nullable = false, length = 14)
    private String nrcnpj;
    @Basic(optional = false)
    @Column(name = "NMRAZAOSOCIAL", nullable = false, length = 100)
    private String nmrazaosocial;
    @Column(name = "DSENDERECO", length = 100)
    private String dsendereco;
    @Column(name = "DSCIDADE", length = 60)
    private String dscidade;
    @Column(name = "DSESTADO", length = 2)
    private String dsestado;
    @Column(name = "DSCEP", length = 10)
    private String dscep;
    @Column(name = "DSINSCESTADUAL", length = 15)
    private String dsinscestadual;
    @Column(name = "DSINSCMUNICIPAL", length = 16)
    private String dsinscmunicipal;
    @Column(name = "DTCADASTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtcadastro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fornecedor")
    private List<Orcamento> orcamentoList;

    public Fornecedor() {
    }

    public Fornecedor(String nrcnpj) {
        this.nrcnpj = nrcnpj;
    }

    public Fornecedor(String nrcnpj, String nmrazaosocial) {
        this.nrcnpj = nrcnpj;
        this.nmrazaosocial = nmrazaosocial;
    }

    public String getNrcnpj() {
        return nrcnpj;
    }

    public void setNrcnpj(String nrcnpj) {
        this.nrcnpj = nrcnpj;
    }

    public String getNmrazaosocial() {
        return nmrazaosocial;
    }

    public void setNmrazaosocial(String nmrazaosocial) {
        this.nmrazaosocial = nmrazaosocial;
    }

    public String getDsendereco() {
        return dsendereco;
    }

    public void setDsendereco(String dsendereco) {
        this.dsendereco = dsendereco;
    }

    public String getDscidade() {
        return dscidade;
    }

    public void setDscidade(String dscidade) {
        this.dscidade = dscidade;
    }

    public String getDsestado() {
        return dsestado;
    }

    public void setDsestado(String dsestado) {
        this.dsestado = dsestado;
    }

    public String getDscep() {
        return dscep;
    }

    public void setDscep(String dscep) {
        this.dscep = dscep;
    }

    public String getDsinscestadual() {
        return dsinscestadual;
    }

    public void setDsinscestadual(String dsinscestadual) {
        this.dsinscestadual = dsinscestadual;
    }

    public String getDsinscmunicipal() {
        return dsinscmunicipal;
    }

    public void setDsinscmunicipal(String dsinscmunicipal) {
        this.dsinscmunicipal = dsinscmunicipal;
    }

    public Date getDtcadastro() {
        return dtcadastro;
    }

    public void setDtcadastro(Date dtcadastro) {
        this.dtcadastro = dtcadastro;
    }

    @XmlTransient
    public List<Orcamento> getOrcamentoList() {
        return orcamentoList;
    }

    public void setOrcamentoList(List<Orcamento> orcamentoList) {
        this.orcamentoList = orcamentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nrcnpj != null ? nrcnpj.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fornecedor)) {
            return false;
        }
        Fornecedor other = (Fornecedor) object;
        if ((this.nrcnpj == null && other.nrcnpj != null) || (this.nrcnpj != null && !this.nrcnpj.equals(other.nrcnpj))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sw2.comercial.bean.Fornecedor[ nrcnpj=" + nrcnpj + " ]";
    }
    
}
