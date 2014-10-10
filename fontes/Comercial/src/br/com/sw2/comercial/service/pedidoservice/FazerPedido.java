
package br.com.sw2.comercial.service.pedidoservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import br.com.sw2.comercial.bean.Pedido;


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
 *         &lt;element name="pedido" type="{http://www.sw2.com.br/comercial/bean}pedido"/>
 *         &lt;element name="nrcnpj" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "pedido",
    "nrcnpj"
})
@XmlRootElement(name = "fazerPedido")
public class FazerPedido {

    @XmlElement(required = true)
    protected Pedido pedido;
    @XmlElement(required = true)
    protected String nrcnpj;

    /**
     * Obtem o valor da propriedade pedido.
     * 
     * @return
     *     possible object is
     *     {@link Pedido }
     *     
     */
    public Pedido getPedido() {
        return pedido;
    }

    /**
     * Define o valor da propriedade pedido.
     * 
     * @param value
     *     allowed object is
     *     {@link Pedido }
     *     
     */
    public void setPedido(Pedido value) {
        this.pedido = value;
    }

    /**
     * Obtem o valor da propriedade fornecedor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNrcnpj() {
        return nrcnpj;
    }

    /**
     * Define o valor da propriedade nrcnpj.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNrcnpj(String value) {
        this.nrcnpj = value;
    }

}
