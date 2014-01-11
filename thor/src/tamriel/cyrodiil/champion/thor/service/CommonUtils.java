/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tamriel.cyrodiil.champion.thor.service;

import java.awt.Component;
import java.io.File;
import java.io.StringWriter;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;

/**
 *
 * @author Charles
 */
public class CommonUtils {
    
    public static String parseDomToXml(Document xml) {
        

        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer xform = factory.newTransformer();

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(xml), new StreamResult(writer));
            return writer.getBuffer().toString().replaceAll("\n|\r", "");
        }
        catch (Exception err) {
            err.printStackTrace();
            return null;
        }
    }
    
    
    public static void ChooseFile(Component parent, JTextField targetField) {
        final JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(parent);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            targetField.setText(file.getAbsolutePath());
        } else {
            //do nothing.
        }
    }
    
}
