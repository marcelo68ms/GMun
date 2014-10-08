
package br.com.sw2.comercial.service.pedidoservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import br.com.sw2.comercial.bean.Orcamento;


/**
 * <p>Classe Java de anonymous complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteudo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="orcamento" type="{http://www.sw2.com.br/comercial/bean}orcamento"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "orcamento"
})
@XmlRootElement(name = "fazerPedidoResponse")
public class FazerPedidoResponse {

    @XmlElement(required = true)
    protected Orcamento orcamento;

    /**
     * Obtem o valor da propriedade orcamento.
     * 
     * @return
     *     possible object is
     *     {@link Orcamento }
     *     
     */
    public Orcamento getOrcamento() {
        return orcamento;
    }

    /**
     * Define o valor da propriedade orcamento.
     * 
     * @param value
     *     allowed object is
     *     {@link Orcamento }
     *     
     */
    public void setOrcamento(Orcamento value) {
        this.orcamento = value;
    }

}
