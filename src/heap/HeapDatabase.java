/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Supun Athukorala
 * @param <T>
 */
public class HeapDatabase<T extends Comparable<T>> implements IntFace {

    static final String DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/HeapStorage";
    static final String USERNAME = "root";
    static final String PASSWORD = "08coding";

    static int count = 0;

    static Connection conn = null;
    static Statement stmt = null;
    ResultSet rs = null;

    public HeapDatabase() {

        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Database connected");
            stmt = conn.createStatement();

            String sql = "truncate heaptable";
            stmt.executeUpdate(sql);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(HeapDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    boolean isRoot(int index) {
        return (index == 0);
    }

    int leftChild(int index) {
        return 2 * index + 1;
    }

    int parent(int index) {
        return (int) ((index - 1) / 2);
    }

    int rightChild(int index) {
        return 2 * index + 2;
    }

    T myParent(int index) {
        return getValue(parent(index));
    }

    boolean hasLeftChild(int i) {
        return leftChild(i) < count;
    }

    boolean hasRightChild(int i) {
        return rightChild(i) < count;
    }

    @Override
    public void add(Comparable value) {

        String sql;
        try {
            sql = "INSERT INTO heaptable VALUES (" + count + "," + value + ");";

            stmt.executeUpdate(sql);

            count++;
        } catch (SQLException ex) {
            Logger.getLogger(HeapDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }

        bubbleUp();

    }

    @Override
    public Comparable remove() {
        T res = getValue(0);
        try {

            String sql = "DELETE FROM heaptable WHERE indexo=0;";
            stmt.executeUpdate(sql);
            count--;

            sql = "UPDATE heaptable SET indexo=0 WHERE indexo=" + count + "; ";
            stmt.executeUpdate(sql);

            bubbleDown();

            return res;
        } catch (SQLException ex) {
            Logger.getLogger(HeapDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        bubbleDown();
        return res;
    }

    @Override
    public boolean isEmpty() {
        String sql = "SELECT indexo,value FROM heaptable";

        try {
            rs = stmt.executeQuery(sql);
            return !rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(HeapDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;

    }

    @Override
    public void show() {
        try {
            String sql = "SELECT indexo,value FROM heaptable ORDER BY indexo";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {

                int inexval = rs.getInt("indexo");
                String element = rs.getString("value");
                System.out.print(element + " ");
            }

        } catch (SQLException ex) {
            Logger.getLogger(HeapDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("=======");
    }

    public T getValue(int index) {
        T element = null;
        try {
            String sql = "SELECT value FROM heaptable WHERE indexo=" + index + ";";

            rs = stmt.executeQuery(sql);
            rs.next();
            element = (T) rs.getString("value");
        } catch (SQLException ex) {
            Logger.getLogger(HeapDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return element;
    }

    private void bubbleDown() {

        int index = 0;

        while (true) {

            if (hasLeftChild(index) && hasRightChild(index)) {

                if (getValue(leftChild(index)).compareTo(getValue(rightChild(index))) >= 0) {

                    swap(rightChild(index), index);
                    index = rightChild(index);
                } else {

                    swap(leftChild(index), index);
                    index = leftChild(index);
                }

            } else if (!hasRightChild(index) && hasLeftChild(index)) {

                swap(leftChild(index), index);
                index = leftChild(index);

            } else {
                break;
            }

        }
    }

    public void bubbleUp() {
        if (count == 0) {
            throw new IllegalStateException("Shape error");
        }
        int index = count - 1;
        while (!isRoot(index)) {
            if (myParent(index).compareTo(getValue(index)) <= 0) {
                break;
            }
            /* else part */
            swap(parent(index), index);
            index = parent(index);
        }
    }

    private void swap(int a, int b) {
        try {
            T tmpa = getValue(a);
            T tmpb = getValue(b);

            String sql = "UPDATE heaptable SET value=" + tmpb + " WHERE indexo=" + a + "; "; //  array[a] = array[b];
            stmt.executeUpdate(sql);

            sql = "UPDATE heaptable SET value=" + tmpa + " WHERE indexo=" + b + "; "; // array[b] = tmp;
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(HeapDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
