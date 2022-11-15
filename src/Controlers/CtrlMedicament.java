package Controlers;

import Entities.Consultation;
import Entities.Medicament;
import Tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CtrlMedicament
{
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public CtrlMedicament() {
        cnx = ConnexionBDD.getCnx();
    }

    public ArrayList<Medicament> GetAllMedicamentsByIdConsultations(int idConsultation)
    {
        ArrayList<Medicament> mesMedicaments = new ArrayList<>();

        try {
            ps = cnx.prepareStatement("SELECT medicament.idMedoc, medicament.nomMedoc, medicament.prixMedoc \n" +
                    "FROM prescrire\n" +
                    "INNER JOIN consultation ON consultation.idConsult = prescrire.numConsult\n" +
                    "INNER JOIN medicament ON medicament.idMedoc = prescrire.numMedoc\n" +
                    "WHERE consultation.idConsult = ?");
            ps.setInt(1, idConsultation);
            rs = ps.executeQuery();
            while(rs.next())
            {
                Medicament medicament = new Medicament(rs.getInt(1), rs.getString(2), rs.getFloat(3));
                mesMedicaments.add(medicament);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mesMedicaments;
    }
    public ArrayList<Medicament> getAllMedicaments()
    {
        ArrayList<Medicament> mesMedicaments = new ArrayList<>();

        try {
            ps = cnx.prepareStatement("SELECT idMedoc, nomMedoc, prixMedoc FROM `medicament`");
            rs = ps.executeQuery();

            while (rs.next()) {
                Medicament medicament = new Medicament(rs.getInt(1), rs.getString(2), rs.getDouble(3));
                mesMedicaments.add(medicament);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mesMedicaments;
    }
}
