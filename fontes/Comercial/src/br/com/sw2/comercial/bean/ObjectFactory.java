
package br.com.sw2.comercial.bean;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.com.sw2.comercial.bean package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.com.sw2.comercial.bean
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Fornecedor }
     * 
     */
    public Fornecedor createFornecedor() {
        return new Fornecedor();
    }

    /**
     * Create an instance of {@link ItemOrcamentoPK }
     * 
     */
    public ItemOrcamentoPK createItemOrcamentoPK() {
        return new ItemOrcamentoPK();
    }

    /**
     * Create an instance of {@link Orcamento }
     * 
     */
    public Orcamento createOrcamento() {
        return new Orcamento();
    }

    /**
     * Create an instance of {@link OrcamentoPK }
     * 
     */
    public OrcamentoPK createOrcamentoPK() {
        return new OrcamentoPK();
    }

    /**
     * Create an instance of {@link ItemOrcamento }
     * 
     */
    public ItemOrcamento createItemOrcamento() {
        return new ItemOrcamento();
    }

    /**
     * Create an instance of {@link Material }
     * 
     */
    public Material createMaterial() {
        return new Material();
    }

    /**
     * Create an instance of {@link Pedido }
     * 
     */
    public Pedido createPedido() {
        return new Pedido();
    }

    /**
     * Create an instance of {@link ItemPedido }
     * 
     */
    public ItemPedido createItemPedido() {
        return new ItemPedido();
    }

    /**
     * Create an instance of {@link ItemPedidoPK }
     * 
     */
    public ItemPedidoPK createItemPedidoPK() {
        return new ItemPedidoPK();
    }

    /**
     * Create an instance of {@link TipoMaterial }
     * 
     */
    public TipoMaterial createTipoMaterial() {
        return new TipoMaterial();
    }

}
