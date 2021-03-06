//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.28 at 01:45:55 AM EST 
//


package tamriel.cyrodiil.champion.thor.jaxb.accumulo;

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
 *         &lt;element ref="{}servers"/>
 *         &lt;element ref="{}masterGoalState"/>
 *         &lt;element ref="{}masterState"/>
 *         &lt;element ref="{}badTabletServers"/>
 *         &lt;element ref="{}loggers"/>
 *         &lt;element ref="{}tabletServersShuttingDown"/>
 *         &lt;element ref="{}unassignedTablets"/>
 *         &lt;element ref="{}deadTabletServers"/>
 *         &lt;element ref="{}deadLoggers"/>
 *         &lt;element ref="{}tables"/>
 *         &lt;element ref="{}totals"/>
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
    "servers",
    "masterGoalState",
    "masterState",
    "badTabletServers",
    "loggers",
    "tabletServersShuttingDown",
    "unassignedTablets",
    "deadTabletServers",
    "deadLoggers",
    "tables",
    "totals"
})
@XmlRootElement(name = "stats")
public class Stats {

    @XmlElement(required = true)
    protected Servers servers;
    @XmlElement(required = true)
    protected String masterGoalState;
    @XmlElement(required = true)
    protected String masterState;
    @XmlElement(required = true)
    protected String badTabletServers;
    @XmlElement(required = true)
    protected Loggers loggers;
    @XmlElement(required = true)
    protected String tabletServersShuttingDown;
    protected byte unassignedTablets;
    @XmlElement(required = true)
    protected String deadTabletServers;
    @XmlElement(required = true)
    protected String deadLoggers;
    @XmlElement(required = true)
    protected Tables tables;
    @XmlElement(required = true)
    protected Totals totals;

    /**
     * Gets the value of the servers property.
     * 
     * @return
     *     possible object is
     *     {@link Servers }
     *     
     */
    public Servers getServers() {
        return servers;
    }

    /**
     * Sets the value of the servers property.
     * 
     * @param value
     *     allowed object is
     *     {@link Servers }
     *     
     */
    public void setServers(Servers value) {
        this.servers = value;
    }

    /**
     * Gets the value of the masterGoalState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMasterGoalState() {
        return masterGoalState;
    }

    /**
     * Sets the value of the masterGoalState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMasterGoalState(String value) {
        this.masterGoalState = value;
    }

    /**
     * Gets the value of the masterState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMasterState() {
        return masterState;
    }

    /**
     * Sets the value of the masterState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMasterState(String value) {
        this.masterState = value;
    }

    /**
     * Gets the value of the badTabletServers property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBadTabletServers() {
        return badTabletServers;
    }

    /**
     * Sets the value of the badTabletServers property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBadTabletServers(String value) {
        this.badTabletServers = value;
    }

    /**
     * Gets the value of the loggers property.
     * 
     * @return
     *     possible object is
     *     {@link Loggers }
     *     
     */
    public Loggers getLoggers() {
        return loggers;
    }

    /**
     * Sets the value of the loggers property.
     * 
     * @param value
     *     allowed object is
     *     {@link Loggers }
     *     
     */
    public void setLoggers(Loggers value) {
        this.loggers = value;
    }

    /**
     * Gets the value of the tabletServersShuttingDown property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTabletServersShuttingDown() {
        return tabletServersShuttingDown;
    }

    /**
     * Sets the value of the tabletServersShuttingDown property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTabletServersShuttingDown(String value) {
        this.tabletServersShuttingDown = value;
    }

    /**
     * Gets the value of the unassignedTablets property.
     * 
     */
    public byte getUnassignedTablets() {
        return unassignedTablets;
    }

    /**
     * Sets the value of the unassignedTablets property.
     * 
     */
    public void setUnassignedTablets(byte value) {
        this.unassignedTablets = value;
    }

    /**
     * Gets the value of the deadTabletServers property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeadTabletServers() {
        return deadTabletServers;
    }

    /**
     * Sets the value of the deadTabletServers property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeadTabletServers(String value) {
        this.deadTabletServers = value;
    }

    /**
     * Gets the value of the deadLoggers property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeadLoggers() {
        return deadLoggers;
    }

    /**
     * Sets the value of the deadLoggers property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeadLoggers(String value) {
        this.deadLoggers = value;
    }

    /**
     * Gets the value of the tables property.
     * 
     * @return
     *     possible object is
     *     {@link Tables }
     *     
     */
    public Tables getTables() {
        return tables;
    }

    /**
     * Sets the value of the tables property.
     * 
     * @param value
     *     allowed object is
     *     {@link Tables }
     *     
     */
    public void setTables(Tables value) {
        this.tables = value;
    }

    /**
     * Gets the value of the totals property.
     * 
     * @return
     *     possible object is
     *     {@link Totals }
     *     
     */
    public Totals getTotals() {
        return totals;
    }

    /**
     * Sets the value of the totals property.
     * 
     * @param value
     *     allowed object is
     *     {@link Totals }
     *     
     */
    public void setTotals(Totals value) {
        this.totals = value;
    }

}
