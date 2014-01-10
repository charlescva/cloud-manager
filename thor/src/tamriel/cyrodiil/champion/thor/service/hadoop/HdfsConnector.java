/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamriel.cyrodiil.champion.thor.service.hadoop;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

/**
 *
 * @author Charles
 */
public class HdfsConnector {

    private FileSystem fileSystem;

    private String hadoopServer;

    private Integer hadoopPort;

    private boolean fileNameEscaped = true;

    private int chunkSize = 1024;

    public HdfsConnector(String host, Integer port) {
        if (host == null) {
            throw new IllegalArgumentException(
                    "Hadoop server must be specified");
        }
        StringBuilder defaultName = new StringBuilder("hdfs://");
        defaultName.append(host);
        if (port != null) {
            defaultName.append(':').append(port);
        }

        Configuration conf = new Configuration();
        conf.set("fs.default.name", defaultName.toString());

        try {
            fileSystem = FileSystem.newInstance(conf);
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    /**
     * Return true if the given file exists on HDFS
     *
     * @param hdfsPath
     * @return
     * @throws IOException
     */
    public boolean exists(String hdfsPath) throws IOException {
        return fileSystem.exists(new Path(hdfsPath));
    }

    /**
     * Copies a local file to HDFS
     *
     * @param file file to copy
     * @param hdfsPath HDFS folder to copy into
     * @return
     * @throws IOException
     */
    public boolean copyToHdfs(File file, String hdfsPath) throws IOException {
        return copyToHdfs(file, hdfsPath, file.getName());
    }

    /**
     * Copies a local file to HDFS
     *
     * @param file file to copy
     * @param hdfsPath HDFS folder to copy into
     * @param hdfsFileName file name to copy to
     * @return
     * @throws IOException
     */
    public boolean copyToHdfs(File file, String hdfsPath, String hdfsFileName)
            throws IOException {
        return copyToHdfs(file, hdfsPath, hdfsFileName, true);
    }

    /**
     * Copies a local file to HDFS
     *
     * @param file file to copy
     * @param hdfsPath HDFS folder to copy into
     * @param hdfsFileName file name to copy to
     * @param overwrite
     * @return
     * @throws IOException
     */
    public boolean copyToHdfs(File file, String hdfsPath, String hdfsFileName,
            boolean overwrite) throws IOException {

        if (!hdfsPath.endsWith("/")) {
            hdfsPath = hdfsPath + '/';
        }
        if (fileNameEscaped) {
            hdfsFileName = hdfsFileName.replaceAll("\\{|\\}|\\s+", "");
        }

        Path path = new Path(hdfsPath + hdfsFileName);

        if (!overwrite && fileSystem.exists(path)) {
            return false;
        }

        FileInputStream fis = new FileInputStream(file);
        OutputStream out = fileSystem.create(path);
        try {
            IOUtils.copyBytes(fis, out, chunkSize);
        } finally {
            out.close();
            fis.close();
        }

        return true;
    }

    /**
     * Copies a file out of HDFS to local file system
     *
     * @param hdfsPath
     * @param file
     * @return
     * @throws IOException
     */
    public boolean copyFromHdfs(String hdfsPath, File file) throws IOException {
        return copyFromHdfs(hdfsPath, file, true);
    }

    /**
     * Copies a file out of HDFS to local file system
     *
     * @param hdfsPath
     * @param file
     * @param overwrite
     * @return
     * @throws IOException
     */
    public boolean copyFromHdfs(String hdfsPath, File file, boolean overwrite)
            throws IOException {
        Path path = new Path(hdfsPath);

        if ((!overwrite && file.exists()) || !fileSystem.exists(path)) {
            return false;
        }

        FileOutputStream fos = new FileOutputStream(file);
        InputStream is = fileSystem.open(path);
        try {
            IOUtils.copyBytes(is, fos, chunkSize);
        } finally {
            fos.close();
            is.close();
        }

        return true;
    }

    /**
     * Reads a file from HDFS
     *
     * @param hdfsPath
     * @return
     * @throws IOException
     */
    public byte[] readFromHdfs(String hdfsPath) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputStream is = fileSystem.open(new Path(hdfsPath));
        try {
            IOUtils.copyBytes(is, out, chunkSize);
            return out.toByteArray();
        } finally {
            out.close();
            is.close();
        }
    }

    /**
     * Writes a file to HDFS
     *
     * @param hdfsPath
     * @return
     * @throws IOException
     */
    public void writeToHdfs(String hdfsPath, byte[] data) throws IOException {
        try (ByteArrayInputStream in = new ByteArrayInputStream(data)) {
            writeToHdfs(hdfsPath, in);
        }
    }

    public void mkdirs(String hdfsPath) throws IOException {
        fileSystem.mkdirs(new Path(hdfsPath));
    }

    /**
     * Writes a file to HDFS
     *
     * @param hdfsPath
     * @return
     * @throws IOException
     */
    public void writeToHdfs(String hdfsPath, InputStream data)
            throws IOException {
        try (OutputStream os = fileSystem.create(new Path(hdfsPath))) {
            IOUtils.copyBytes(data, os, chunkSize);
        }
    }

    /**
     * @return the hadoopServer
     */
    public String getHadoopServer() {
        return hadoopServer;
    }

    /**
     * @param hadoopServer the hadoopServer to set
     */
    public void setHadoopServer(String hadoopServer) {
        this.hadoopServer = hadoopServer;
    }

    /**
     * @return the hadoopPort
     */
    public Integer getHadoopPort() {
        return hadoopPort;
    }

    /**
     * @param hadoopPort the hadoopPort to set
     */
    public void setHadoopPort(Integer hadoopPort) {
        this.hadoopPort = hadoopPort;
    }

    /**
     * @return the fileNameEscaped
     */
    public boolean isFileNameEscaped() {
        return fileNameEscaped;
    }

    /**
     * @param fileNameEscaped the fileNameEscaped to set
     */
    public void setFileNameEscaped(boolean fileNameEscaped) {
        this.fileNameEscaped = fileNameEscaped;
    }

    /**
     * @return the chunkSize
     */
    public int getChunkSize() {
        return chunkSize;
    }

    /**
     * @param chunkSize the chunkSize to set
     */
    public void setChunkSize(int chunkSize) {
        this.chunkSize = chunkSize;
    }

    public void destroy() {
        try {
            if (fileSystem != null) {
                fileSystem.close();

            }
        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
