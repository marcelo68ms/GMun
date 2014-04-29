/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.sw2.comercial.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author marcelo
 */
@Entity
@Table(name="TblProduto")
public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="CodProduto")
    private String codProduto;
    
    @Column(name="DsNomeProduto")
    private String nomeProduto;
    
    @Column(name="NrQtdMinima")
    private Long qtdMinima;
    
    @Column(name="NrQtdMaxima")
    private Long qtdMaxima;
    
    @ManyToOne
    @JoinColumn(name="CodTipoProduto")
    private TipoProduto tipoProduto;

    public String getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(String codProduto) {
        this.codProduto = codProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Long getQtdMinima() {
        return qtdMinima;
    }

    public void setQtdMinima(Long qtdMinima) {
        this.qtdMinima = qtdMinima;
    }

    public Long getQtdMaxima() {
        return qtdMaxima;
    }

    public void setQtdMaxima(Long qtdMaxima) {
        this.qtdMaxima = qtdMaxima;
    }

    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codProduto != null ? codProduto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.codProduto == null && other.codProduto != null) || 
                (this.codProduto != null && !this.codProduto.equals(other.codProduto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sw2.comercial.bean.Produto[ codProduto=" + codProduto + " ]";
    }
    
}
