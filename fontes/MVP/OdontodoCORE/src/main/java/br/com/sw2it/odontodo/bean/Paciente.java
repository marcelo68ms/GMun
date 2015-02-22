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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "TblPaciente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paciente.findAll", query = "SELECT p FROM Paciente p"),
    @NamedQuery(name = "Paciente.findByCdPaciente", query = "SELECT p FROM Paciente p WHERE p.cdPaciente = :cdPaciente"),
    @NamedQuery(name = "Paciente.findByDsNome", query = "SELECT p FROM Paciente p WHERE p.dsNome = :dsNome"),
    @NamedQuery(name = "Paciente.findByDtNascimento", query = "SELECT p FROM Paciente p WHERE p.dtNascimento = :dtNascimento"),
    @NamedQuery(name = "Paciente.findByTpSexo", query = "SELECT p FROM Paciente p WHERE p.tpSexo = :tpSexo"),
    @NamedQuery(name = "Paciente.findByDsEndereco", query = "SELECT p FROM Paciente p WHERE p.dsEndereco = :dsEndereco"),
    @NamedQuery(name = "Paciente.findByDsBairro", query = "SELECT p FROM Paciente p WHERE p.dsBairro = :dsBairro"),
    @NamedQuery(name = "Paciente.findByDsCidade", query = "SELECT p FROM Paciente p WHERE p.dsCidade = :dsCidade"),
    @NamedQuery(name = "Paciente.findByDsCEP", query = "SELECT p FROM Paciente p WHERE p.dsCEP = :dsCEP"),
    @NamedQuery(name = "Paciente.findByDsEstado", query = "SELECT p FROM Paciente p WHERE p.dsEstado = :dsEstado"),
    @NamedQuery(name = "Paciente.findByDsRG", query = "SELECT p FROM Paciente p WHERE p.dsRG = :dsRG"),
    @NamedQuery(name = "Paciente.findByDsCPF", query = "SELECT p FROM Paciente p WHERE p.dsCPF = :dsCPF"),
    @NamedQuery(name = "Paciente.findByDsFoneFixo", query = "SELECT p FROM Paciente p WHERE p.dsFoneFixo = :dsFoneFixo"),
    @NamedQuery(name = "Paciente.findByDsFoneCelular1", query = "SELECT p FROM Paciente p WHERE p.dsFoneCelular1 = :dsFoneCelular1"),
    @NamedQuery(name = "Paciente.findByDsFoneCelular2", query = "SELECT p FROM Paciente p WHERE p.dsFoneCelular2 = :dsFoneCelular2"),
    @NamedQuery(name = "Paciente.findByDsResponsavel", query = "SELECT p FROM Paciente p WHERE p.dsResponsavel = :dsResponsavel"),
    @NamedQuery(name = "Paciente.findByDtCadastro", query = "SELECT p FROM Paciente p WHERE p.dtCadastro = :dtCadastro")})
public class Paciente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cdPaciente", nullable = false)
    private Long cdPaciente;
    @Column(name = "dsNome", length = 60)
    private String dsNome;
    @Column(name = "dtNascimento")
    @Temporal(TemporalType.DATE)
    private Date dtNascimento;
    @Column(name = "tpSexo")
    private Character tpSexo;
    @Column(name = "dsEndereco", length = 100)
    private String dsEndereco;
    @Column(name = "dsBairro", length = 60)
    private String dsBairro;
    @Column(name = "dsCidade", length = 60)
    private String dsCidade;
    @Column(name = "dsCEP", length = 10)
    private String dsCEP;
    @Column(name = "dsEstado", length = 2)
    private String dsEstado;
    @Column(name = "dsRG", length = 14)
    private String dsRG;
    @Column(name = "dsCPF", length = 14)
    private String dsCPF;
    @Column(name = "dsFoneFixo", length = 14)
    private String dsFoneFixo;
    @Column(name = "dsFoneCelular1", length = 14)
    private String dsFoneCelular1;
    @Column(name = "dsFoneCelular2", length = 14)
    private String dsFoneCelular2;
    @Column(name = "dsResponsavel", length = 60)
    private String dsResponsavel;
    @Column(name = "dtCadastro")
    @Temporal(TemporalType.DATE)
    private Date dtCadastro;
    @JoinTable(name = "TblProfissionalxPaciente", joinColumns = {
        @JoinColumn(name = "cdPaciente", referencedColumnName = "cdPaciente", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "DSemail", referencedColumnName = "DSemail", nullable = false)})
    @ManyToMany
    private Collection<Profissional> profissionalCollection;

    public Paciente() {
    }

    public Paciente(Long cdPaciente) {
        this.cdPaciente = cdPaciente;
    }

    public Long getCdPaciente() {
        return cdPaciente;
    }

    public void setCdPaciente(Long cdPaciente) {
        this.cdPaciente = cdPaciente;
    }

    public String getDsNome() {
        return dsNome;
    }

    public void setDsNome(String dsNome) {
        this.dsNome = dsNome;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public Character getTpSexo() {
        return tpSexo;
    }

    public void setTpSexo(Character tpSexo) {
        this.tpSexo = tpSexo;
    }

    public String getDsEndereco() {
        return dsEndereco;
    }

    public void setDsEndereco(String dsEndereco) {
        this.dsEndereco = dsEndereco;
    }

    public String getDsBairro() {
        return dsBairro;
    }

    public void setDsBairro(String dsBairro) {
        this.dsBairro = dsBairro;
    }

    public String getDsCidade() {
        return dsCidade;
    }

    public void setDsCidade(String dsCidade) {
        this.dsCidade = dsCidade;
    }

    public String getDsCEP() {
        return dsCEP;
    }

    public void setDsCEP(String dsCEP) {
        this.dsCEP = dsCEP;
    }

    public String getDsEstado() {
        return dsEstado;
    }

    public void setDsEstado(String dsEstado) {
        this.dsEstado = dsEstado;
    }

    public String getDsRG() {
        return dsRG;
    }

    public void setDsRG(String dsRG) {
        this.dsRG = dsRG;
    }

    public String getDsCPF() {
        return dsCPF;
    }

    public void setDsCPF(String dsCPF) {
        this.dsCPF = dsCPF;
    }

    public String getDsFoneFixo() {
        return dsFoneFixo;
    }

    public void setDsFoneFixo(String dsFoneFixo) {
        this.dsFoneFixo = dsFoneFixo;
    }

    public String getDsFoneCelular1() {
        return dsFoneCelular1;
    }

    public void setDsFoneCelular1(String dsFoneCelular1) {
        this.dsFoneCelular1 = dsFoneCelular1;
    }

    public String getDsFoneCelular2() {
        return dsFoneCelular2;
    }

    public void setDsFoneCelular2(String dsFoneCelular2) {
        this.dsFoneCelular2 = dsFoneCelular2;
    }

    public String getDsResponsavel() {
        return dsResponsavel;
    }

    public void setDsResponsavel(String dsResponsavel) {
        this.dsResponsavel = dsResponsavel;
    }

    public Date getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Date dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    @XmlTransient
    public Collection<Profissional> getProfissionalCollection() {
        return profissionalCollection;
    }

    public void setProfissionalCollection(Collection<Profissional> profissionalCollection) {
        this.profissionalCollection = profissionalCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdPaciente != null ? cdPaciente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paciente)) {
            return false;
        }
        Paciente other = (Paciente) object;
        if ((this.cdPaciente == null && other.cdPaciente != null) || (this.cdPaciente != null && !this.cdPaciente.equals(other.cdPaciente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sw2it.odontodo.bean.Paciente[ cdPaciente=" + cdPaciente + " ]";
    }
    
}
