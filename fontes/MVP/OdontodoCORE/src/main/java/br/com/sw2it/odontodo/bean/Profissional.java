/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sw2it.odontodo.bean;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author marcelo
 */
@Entity
@Table(name = "TblProfissional")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profissional.findAll", query = "SELECT p FROM Profissional p"),
    @NamedQuery(name = "Profissional.findByDSemail", query = "SELECT p FROM Profissional p WHERE p.dSemail = :dSemail"),
    @NamedQuery(name = "Profissional.findByDSNome", query = "SELECT p FROM Profissional p WHERE p.dSNome = :dSNome"),
    @NamedQuery(name = "Profissional.findByDSSenha", query = "SELECT p FROM Profissional p WHERE p.dSSenha = :dSSenha"),
    @NamedQuery(name = "Profissional.findByDtCadastro", query = "SELECT p FROM Profissional p WHERE p.dtCadastro = :dtCadastro")})
public class Profissional implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "DSemail", nullable = false, length = 100)
    private String dSemail;
    @Basic(optional = false)
    @Column(name = "DSNome", nullable = false, length = 60)
    private String dSNome;
    @Basic(optional = false)
    @Column(name = "DSSenha", nullable = false, length = 10)
    private String dSSenha;
    @Column(name = "dtCadastro")
    @Temporal(TemporalType.DATE)
    private Date dtCadastro;
    @ManyToMany(mappedBy = "profissionalCollection")
    private Collection<Paciente> pacienteCollection;

    public Profissional() {
    }

    public Profissional(String dSemail) {
        this.dSemail = dSemail;
    }

    public Profissional(String dSemail, String dSNome, String dSSenha) {
        this.dSemail = dSemail;
        this.dSNome = dSNome;
        this.dSSenha = dSSenha;
    }

    public String getDSemail() {
        return dSemail;
    }

    public void setDSemail(String dSemail) {
        this.dSemail = dSemail;
    }

    public String getDSNome() {
        return dSNome;
    }

    public void setDSNome(String dSNome) {
        this.dSNome = dSNome;
    }

    public String getDSSenha() {
        return dSSenha;
    }

    public void setDSSenha(String dSSenha) {
        this.dSSenha = dSSenha;
    }

    public Date getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Date dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    @XmlTransient
    public Collection<Paciente> getPacienteCollection() {
        return pacienteCollection;
    }

    public void setPacienteCollection(Collection<Paciente> pacienteCollection) {
        this.pacienteCollection = pacienteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dSemail != null ? dSemail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profissional)) {
            return false;
        }
        Profissional other = (Profissional) object;
        if ((this.dSemail == null && other.dSemail != null) || (this.dSemail != null && !this.dSemail.equals(other.dSemail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sw2it.odontodo.bean.Profissional[ dSemail=" + dSemail + " ]";
    }
    
}
