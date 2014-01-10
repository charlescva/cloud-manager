
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tamriel.cyrodiil.champion.thor.service;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.ConnectionMonitor;
import ch.ethz.ssh2.Session;

import ch.ethz.ssh2.StreamGobbler;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import javax.swing.SwingWorker;
import tamriel.cyrodiil.champion.thor.service.hadoop.HdfsConnector;

/**
 * @author Charles
 *
 *
 *
 */
public class HdfsSwingWorker extends SwingWorker<String, Integer> {

    private String uri;
    private String soureFile;
    private String targetFolder;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getSoureFile() {
        return soureFile;
    }

    public void setSoureFile(String soureFile) {
        this.soureFile = soureFile;
    }

    public String getTargetFolder() {
        return targetFolder;
    }

    public void setTargetFolder(String targetFolder) {
        this.targetFolder = targetFolder;
    }
    
    @Override
    protected String doInBackground() {

        HdfsConnector hdfs = null;
        try {
            URI hdfsUrl = new URI(uri);
            hdfs = new HdfsConnector(hdfsUrl.getHost(), hdfsUrl.getPort());
            hdfs.copyToHdfs(new File(soureFile), targetFolder);
            hdfs.destroy();

        } catch (Exception err) {
            err.printStackTrace();
            return "Failed.  See Log for details.";
        } finally {
            StringBuilder response = new StringBuilder("File Sent.");

            firePropertyChange("sendreport", "", response.toString());

            return response.toString();
        }

    }
}
