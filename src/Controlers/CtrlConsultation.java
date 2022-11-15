package Controlers;

import Entities.Consultation;
import Tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CtrlConsultation
{
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public CtrlConsultation() {
        cnx = ConnexionBDD.getCnx();
    }

    public ArrayList<Consultation> GetAllConsultations()
    {
        ArrayList<Consultation> mesConsultations = new ArrayList<>();

        try {
            ps = cnx.prepareStatement("SELECT idConsult, dateConsult, numPatient, numMedecin FROM `consultation`");
            rs = ps.executeQuery();

            while (rs.next()) {
                Consultation consultation = new Consultation(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 1);
                mesConsultations.add(consultation);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mesConsultations;
    }
    public int getLastNumberOfConsultation() throws SQLException {
        ps = cnx.prepareStatement("SELECT MAX(idConsult) FROM `consultation`");
        rs = ps.executeQuery();
        rs.next();
        int value = rs.getInt(1);
        value = value + 1;
        return value;
    }
    public void InsertConsultation(int idConsult, String dateConsultation, int numPatient,int numMedecin)
    {

    }
}
