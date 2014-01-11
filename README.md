cloud-manager
=============

For screenshots, installation instructions and more, visit the wiki first!
https://github.com/charlescva/cloud-manager/wiki


Description:

Netbeans Platform Module for management of open source distributed software.  The intention is to be able to compile an application someone without java experience can use for any platform, as well as provide a starting point for anyone interested in building their own distributed architecture and would like to use java to customize and automate administration procedures. 


-----------------

Update: 1/10/2014

You can grab a sample of this application here:
https://github.com/charlescva/cloud-manager/releases

This is what i'm calling version 0.1

Been adding features as time permits.  Now you can send files to an HDFS filesystem from the client.  I plan to implement a 'file manager' type interface with a split view of client FS and HDFS.  And of course, drag and drop capability!  Currently the progress bar is not working for HDFS transfers so be patient and do not click the Send button multiple times.  Will clean up when i get a chance, but the core code is in place :)

Added 4 JAR files.  Didn't want to, but I wanted to get it working.  Since the client code is open sourced, at some point I will go through and pick out the classes and methods needed so that it can all be compiled in netbeans.


-----------------
Update: 12/28/2013

The idea behind this tool is to provide a Plugin for Netbeans that allows easier management of open source distributed stacks.  

Some of the targeted software platforms are:

Apache Accumulo 1.4/1.5
Cloudera CDH3u6 and/or 4u5
Storm 0.8.2 (nathanmarz)
Zookeeper (cdh)
RPMs for CentOS 6.x
anything else...

Currently I'm the only one working on it in my spare time.  At this point you can build a list
of Accumulo tables by specifying host name, add a Storm Nimbus Server to a saved list of servers, deploy a storm topology, and quick browse to the storm ui.
