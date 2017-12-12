/*
    not our code, took it on the web
 */


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class ProteinLoader {


    private Connection con;
    private PreparedStatement proteinStmt;

    public ProteinLoader(String organism, String dbName, String dbDate) {
        // TODO: fill in your database settings
        con = null;/*Postgres.getConnection("localhost", "rosetta",
                "cs329e", "");*/

        // find the protein source ID and prepare the protein insertion
        // statement
        try {
            int sourceID = getSourceID(dbName, dbDate);
            proteinStmt = con.prepareStatement(
                    "INSERT INTO T_Protein(accessionNumber,name,organism,"
                            + "sequence, T_ProteinSource_ID) VALUES (?, ?, '"
                            + organism + "', ?, " + sourceID + ")");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Load the proteins from a Fasta file.
     */
    public void load(String fastaFile) {
        // load the proteins from the FASTA file into memory
        try {
            BufferedReader in =
                    new BufferedReader(new FileReader(fastaFile));
            String line;
            StringBuffer seq = new StringBuffer();
            String[] header = null; // header information: accID and name

            // read sequences from the fasta file
            while ((line = in.readLine()) != null) {
                if (line.startsWith(">")) {
                    // a new sequence found, so save the previous sequence
                    if (seq.length() > 0) {
                        insertProtein(header[0], header[1], seq.toString());
                        seq.setLength(0);
                    }
                    header = parseHeader(line.substring(1));
                } else {
                    seq.append(line.trim());
                }
            }

            // store the last sequence
            if (seq.length() > 0) {
                insertProtein(header[0], header[1], seq.toString());
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Extract information from the header of a FASTA sequence.
     * Return a string array of two elements. The first element is
     * the accession ID and the second is the sequence name.
     * Note: This method is very much specialized for headers in a certain
     * format. The better way is to retrieve the sequence information
     * from other sources.
     */
    private String[] parseHeader(String header) {
        // the header is separated by '|'
        String[] fields = header.split("\\|");

        String[] seqInfo = new String[2];
        // the accession ID is composed of the first two fields
        seqInfo[0] = fields[0] + "|" + fields[1];
        // the name is the first word of the last field
        seqInfo[1] = fields[fields.length-1].trim().split("\\s")[0];

        return seqInfo;
    }

    /**
     * Return the ID of the protein source with the given name and date.
     * If the source is not found, a new source is created and its ID
     * is returned.
     */
    private int getSourceID(String dbName, String dbDate) throws SQLException {
        // check if this protein source exists in the database
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
                "SELECT T_ProteinSource_ID FROM T_ProteinSource "
                        + "WHERE dbName = '" + dbName + "' AND date = '"
                        + dbDate + "'");
        if (rs.next()) {
            // return the ID since the protein source exists
            return rs.getInt(1);
        } else {
            // otherwise, create a new protein source and return its ID
            stmt.executeUpdate(
                    "INSERT INTO T_ProteinSource(dbName, date) VALUES ('"
                            + dbName + "', '" + dbDate + "')");
            rs = stmt.executeQuery(
                    "SELECT T_ProteinSource_ID FROM T_ProteinSource "
                            + "WHERE dbName = '" + dbName + "' AND date = '"
                            + dbDate + "'");
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                // the insertion failed
                throw new Error("Error inserting protein source");
            }
        }
    }

    private void insertProtein(String accID, String name, String seq)
            throws SQLException {
        proteinStmt.setString(1, accID);
        proteinStmt.setString(2, name);
        proteinStmt.setString(3, seq);
        proteinStmt.executeUpdate();
        System.out.println("Protein " + name + " inserted");
    }

    // Display all proteins belonging to a organism
    public void display(String organism) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT name, accessionNumber FROM T_Protein " +
                            "WHERE organism ='" + organism + "'");
            System.out.println(organism + " proteins:");
            while (rs.next()) {
                String name = rs.getString("name");
                String accID = rs.getString("accessionNumber");
                System.out.println(name + "\t" + accID);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("Protein Loader");
            System.out.println("Usage: java ProteinLoader FASTA-FILE ORGANISM-NAME DB-NAME DB-DATE\n");
            System.out.println("Example: java ProteinLoader data/ecoli-gb.txt EColi Genbank 2005-2-15");
            return;
        }

        ProteinLoader loader = new ProteinLoader(args[1], args[2], args[3]);
        loader.load(args[0]);
        loader.display(args[1]);
    }
}
