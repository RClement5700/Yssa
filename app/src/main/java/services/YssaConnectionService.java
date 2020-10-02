package services;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.unboundid.ldap.sdk.LDAPConnection;
import com.unboundid.ldap.sdk.LDAPException;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jxmpp.stringprep.XmppStringprepException;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import pojos.Employee;

public class YssaConnectionService extends Service {

    private static YssaConnectionService instance;
    private Connection sqlConnection;
    private LDAPConnection ldapConnection;
    private XMPPConnection openfireConnection;

    public static YssaConnectionService getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    sqlConnection = new ConnectToSqlDatabaseTask().execute().get();
                    ldapConnection = new ConnectToLDAPDirectoryTask().execute().get();
//                    openfireConnection = new ConnectToOpenfireTask().execute().get();

                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        runnable.run();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public Connection getSqlConnection() {
        return sqlConnection;
    }

    public LDAPConnection getLdapConnection() {
        return ldapConnection;
    }

    public XMPPConnection getOpenfireConnection() {
        return openfireConnection;
    }

    /*
        TODO:
            match username:employeeIDs in database to username:employeeIDs in directory
     */
    static class ConnectToSqlDatabaseTask extends AsyncTask<String, Void, Connection> {

        String url = "jdbc:mysql://10.0.2.2:8889/yssa";
        String username = "root";
        String password = "adminadmin";
        ArrayList<Employee> employees = new ArrayList<Employee>();
        Connection con;

        @Override
        protected Connection doInBackground(String... strings) {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = DriverManager.getConnection(url, username, password);
                System.err.println("Connecting to SQL database...");
                if (!con.isClosed()) {
                    System.err.println("SQL database connection complete");
                }
            }
            catch (IllegalAccessException | InstantiationException | SQLException |
                    ClassNotFoundException e) {
                e.printStackTrace();
            }
            return con;
        }
    }

    static class ConnectToLDAPDirectoryTask extends AsyncTask<String, Void, LDAPConnection> {

        int PORT = 389;
        LDAPConnection c;
        String address = "10.0.2.2";
        String dn = "cn=Manager,dc=catosystems,dc=com";
        String dnPassword = "uptown5700";

        @Override
        protected LDAPConnection doInBackground(String... strings) {
            try {
                c = new LDAPConnection(address, PORT, dn, dnPassword); //,DN,password);
                System.err.println("Connecting to directory...");
                if (c.isConnected()) {
                    System.err.println("LDAP Directory Connection Complete");
                }
            } catch (LDAPException | RuntimeException e) {
                System.err.println("Error connecting to directory");
                e.printStackTrace();
            }
            return c;
        }
    }

    //openfire admin console http://172.20.4.51:9090
//    static class ConnectToOpenfireTask extends AsyncTask<String, Void, XMPPTCPConnection> {

//        String username = "8993";
//        String password = "uptown5700";
//        @Override
//        protected XMPPTCPConnection doInBackground(String... strings) {
//            XMPPTCPConnection connection;
//            XMPPTCPConnectionConfiguration.Builder config = XMPPTCPConnectionConfiguration.builder();
//                config.setKeystoreType("BKS");
//                String str = System.getProperty("javax.net.ssl.trustStore");
//                if (str == null) {
//                    str = System.getProperty("java.home") + File.separator + "etc" + File.separator + "security"
//                            + File.separator + "cacerts.bks";
//                }
//                config.setKeystorePath(str);
//            config.setHost("10.0.2.2");
//            config.setUsernameAndPassword(username, password);
//            config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
//            config.setSendPresence(false);
//            try {
//            config.setHostAddress(InetAddress.getByName("10.0.2.2"));
//            config.setPort(5222);
////            config.setResource("myResource");
//            config.setDebuggerEnabled(true);
////            config.setXmppDomain("myDomain");
//            }
//            catch(UnknownHostException e) {
//                e.printStackTrace();
//            }
//            connection = new XMPPTCPConnection(config.build());
//            connection.setUseStreamManagement(false);
//            try {
//                connection.connect();
//            }
//            catch(IOException | SmackException | XMPPException | InterruptedException e) {
//                System.err.println("Error connecting to openfire...");
//                e.printStackTrace();
//            }
//            return connection;
//        }
//    }



}
