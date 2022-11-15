package Vues;

import Controlers.*;
import Tools.ModelJTable;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Objects;

public class FrmPrescrire extends JFrame
{
    private JPanel pnlRoot;
    private JLabel lblTitre;
    private JLabel lblNumero;
    private JLabel lblDate;
    private JLabel lblNomMedecin;
    private JTextField txtNumeroConsultation;
    private JComboBox<String> cboPatients;
    private JComboBox<String> cboMedecins;
    private JButton btnInserer;
    private JTable tblMedicaments;
    private JPanel pnlDate;
    private JLabel lblNomPatient;
    private JLabel lblMedicaments;
    private JDateChooser dcDateConsultation;
    private CtrlConsultation ctrlConsultation;
    private CtrlPatient ctrlPatient;
    private CtrlMedecin ctrlMedecin;
    private CtrlMedicament ctrlMedicament;
    private ModelJTable mdl;

    public FrmPrescrire()
    {
        this.setTitle("Prescrire");
        this.setContentPane(pnlRoot);
        this.pack();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);
                dcDateConsultation = new JDateChooser();
                dcDateConsultation.setDateFormatString("yyyy-MM-dd");
                pnlDate.add(dcDateConsultation);
                ctrlConsultation = new CtrlConsultation();
                ctrlPatient = new CtrlPatient();
                ctrlMedecin = new CtrlMedecin();
                ctrlMedicament = new CtrlMedicament();
                mdl = new ModelJTable();

                try {
                    txtNumeroConsultation.setText(String.valueOf(ctrlConsultation.getLastNumberOfConsultation()));
                    cboPatients.setModel(new DefaultComboBoxModel<>(ctrlPatient.getAllPatients().toArray(new String[0])));
                    cboMedecins.setModel(new DefaultComboBoxModel<>(ctrlMedecin.getAllMedecins().toArray(new String[0])));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                mdl.LoadDatasMedicaments(ctrlMedicament.getAllMedicaments());
                tblMedicaments.setModel(mdl);

                // A vous de jouer

            }
        });
        btnInserer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // A vous de jouer
                if (Objects.equals(txtNumeroConsultation.getText(), "")) {
                    JOptionPane.showMessageDialog(null, "Aucun numéro n'a été saisi", "Erreur Système", JOptionPane.WARNING_MESSAGE);
                }
                else if(cboPatients.getSelectedItem()==null)
                {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner un patient", "Erreur de sélection", JOptionPane.WARNING_MESSAGE);
                }
                else if(cboMedecins.getSelectedItem()==null)
                {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner un médecin", "Erreur de sélection", JOptionPane.WARNING_MESSAGE);
                }
//                else if(pnlDate == 0) {
//                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner une date", "Erreur de sélection", JOptionPane.WARNING_MESSAGE);
//                }
                // Lorsque toutes les conditions sont remplies, effectuer une requête SQL pour ajouter la consultation.

            }
        });
    }
}
