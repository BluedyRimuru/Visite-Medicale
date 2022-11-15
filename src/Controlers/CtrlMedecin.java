package Controlers;

import Tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CtrlMedecin
{
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public CtrlMedecin() {
        cnx = ConnexionBDD.getCnx();
    }

    public ArrayList<String> getAllMedecins()
    {
        ArrayList<String> mesMedecins = new ArrayList<>();

        try {
            ps = cnx.prepareStatement("SELECT nomMedecin FROM `medecin`");
            rs = ps.executeQuery();

            while (rs.next()) {
                String medecin = rs.getString(1);
                mesMedecins.add(medecin);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mesMedecins;
    }

    public int getIdMedecinByName(String nomMed)
    {
        try {
            ps = cnx.prepareStatement("SELECT idMedecin FROM `medecin` WHERE ?");
            ps.setString(1,nomMed);
            rs = ps.executeQuery();
            int idMedecin;
            idMedecin = rs.getInt(1);
            return idMedecin;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
