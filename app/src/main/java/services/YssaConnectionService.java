package services;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.unboundid.ldap.sdk.LDAPConnection;
import com.unboundid.ldap.sdk.LDAPException;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

import java.io.IOException;
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
                    openfireConnection = new ConnectToOpenfireTask().execute().get();

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
    static class ConnectToOpenfireTask extends AsyncTask<String, Void, AbstractXMPPConnection> {

        String username = "8993";
        String password = "uptown5700";
        @Override
        protected AbstractXMPPConnection doInBackground(String... strings) {

            XMPPTCPConnectionConfiguration.Builder configBuilder = XMPPTCPConnectionConfiguration.builder();
            configBuilder.setUsernameAndPassword(username, password);
            configBuilder.setHost("http://10.0.2.2:5554");
            configBuilder.setPort(5222);
//            configBuilder.setResource("SomeResource");
//            configBuilder.setXmppDomain("jabber.org");

            AbstractXMPPConnection connection = new XMPPTCPConnection(configBuilder.build());
// Connect to the server
            try {
                connection.connect();
//                connection.login();
            } catch (SmackException | IOException | XMPPException | InterruptedException e) {
                e.printStackTrace();
            }
// Log into the server
            return connection;
        }
    }



}
