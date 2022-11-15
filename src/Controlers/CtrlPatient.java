package Controlers;

import Entities.Consultation;
import Tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CtrlPatient
{
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public CtrlPatient() {
        cnx = ConnexionBDD.getCnx();
    }

    public ArrayList<String> getAllPatients()
    {
        ArrayList<String> mesPatients = new ArrayList<>();

        try {
            ps = cnx.prepareStatement("SELECT nomPatient FROM `patient`");
            rs = ps.executeQuery();

            while (rs.next()) {
                String patient = rs.getString(1);
                mesPatients.add(patient);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mesPatients;
    }
    public int getIdPatientByName(String nomPat)
    {
        try {
            ps = cnx.prepareStatement("SELECT idPatient FROM `patient` WHERE ?");
            ps.setString(1,nomPat);
            rs = ps.executeQuery();
            int idPatient;
            idPatient = rs.getInt(1);
            return idPatient;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
