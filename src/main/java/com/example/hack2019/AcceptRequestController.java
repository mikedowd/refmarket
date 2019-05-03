package com.example.hack2019;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// @todo need to wire this in to spring so a thread is created with a connection.....
public class AcceptRequestController {

    @Value("${hack2019.accepted_request.listener_channel}")
    private String listenerChannel;
    @Autowired
    private DataSource dataSource;

    class Listener extends Thread {

        private Connection conn;
        private org.postgresql.PGConnection pgconn;

        Listener(Connection conn) throws SQLException {
            this.conn = conn;
            this.pgconn = (org.postgresql.PGConnection)conn;
            Statement stmt = conn.createStatement();
            stmt.execute("LISTEN " + listenerChannel);
            stmt.close();
        }

        public void run() {
            while (true) {
                try {
                    // issue a dummy query to contact the backend
                    // and receive any pending notifications.
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT 1");
                    rs.close();
                    stmt.close();

                    org.postgresql.PGNotification notifications[] = pgconn.getNotifications();
                    if (notifications != null) {
                        for (int i=0; i<notifications.length; i++) {
                            System.out.println("Got notification: " + notifications[i].getName());
                        }
                        // @todo
                        // While there are row in AcceptedRequests
                        // In a transaction
                        // - Select row from AcceptedRequests table
                        // - Send row to appropriate Salesforce org
                        // - Delete row from AcceptedRequests
                    }

                    // wait a while before checking again for new
                    // notifications
                    Thread.sleep(500);
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }

    }

}
