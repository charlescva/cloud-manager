//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.28 at 01:45:55 AM EST 
//


package tamriel.cyrodiil.champion.thor.jaxb.accumulo;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}ingestrate"/>
 *         &lt;element ref="{}queryrate"/>
 *         &lt;element ref="{}diskrate"/>
 *         &lt;element ref="{}numentries"/>
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
    "ingestrate",
    "queryrate",
    "diskrate",
    "numentries"
})
@XmlRootElement(name = "totals")
public class Totals {

    protected double ingestrate;
    @XmlElement(required = true)
    protected BigDecimal queryrate;
    @XmlElement(required = true)
    protected BigDecimal diskrate;
    protected int numentries;

    /**
     * Gets the value of the ingestrate property.
     * 
     */
    public double getIngestrate() {
        return ingestrate;
    }

    /**
     * Sets the value of the ingestrate property.
     * 
     */
    public void setIngestrate(double value) {
        this.ingestrate = value;
    }

    /**
     * Gets the value of the queryrate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getQueryrate() {
        return queryrate;
    }

    /**
     * Sets the value of the queryrate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setQueryrate(BigDecimal value) {
        this.queryrate = value;
    }

    /**
     * Gets the value of the diskrate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDiskrate() {
        return diskrate;
    }

    /**
     * Sets the value of the diskrate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDiskrate(BigDecimal value) {
        this.diskrate = value;
    }

    /**
     * Gets the value of the numentries property.
     * 
     */
    public int getNumentries() {
        return numentries;
    }

    /**
     * Sets the value of the numentries property.
     * 
     */
    public void setNumentries(int value) {
        this.numentries = value;
    }

}