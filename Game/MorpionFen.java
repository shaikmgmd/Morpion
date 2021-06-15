package Game;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import javax.swing.*;
import com.intellij.ui.components.JBScrollPane;
import net.miginfocom.layout.Grid;

public class MorpionFen extends JFrame implements ActionListener{

    JFrame Fen = new JFrame();
    JFrame FenAide = new JFrame();
    JFrame FenCredits = new JFrame();
    //JFrame FenVictoire = new JFrame();

    //Tous les pannels
    JPanel jeuHeader = new JPanel();
    JPanel jeuFooter = new JPanel();
    JPanel jeuInterface = new JPanel();
    JPanel jeuEast = new JPanel();
    JPanel jeuWest = new JPanel();

    //TOus les textes
    JLabel LabelTxtNorth = new JLabel();
    JLabel LabelTxtSouth = new JLabel();
    //JLabel VictoryX = new JLabel("VICTOIRE EQUIPE CROIX");
    //JLabel VictoryO = new JLabel("VICTOIRE EQUIPE ROND");
    //JLabel LabelTxtEast = new JLabel();
    //JLabel LabelTxtWest = new JLabel();
    JTextArea AideTexte = new JTextArea("Pour jouer une partie de morpion \n, " +
            "il suffit de tracer sur une feuille blanche une grille de 3 cases sur 3 \n" +
            "(selon les variantes, il est possible d’augmenter le nombre de cases). \n" +
            "Le but du jeu est d’aligner avant son adversaire 3 symboles identiques horizontalement, \n" +
            "verticalement ou en diagonale.\n" +
            "Chaque joueur a donc son propre symbole, \n" +
            "généralement une croix pour l’un et un rond pour l’autre. \n" +
            "La partie se termine quand l’un des joueurs à aligner \n" +
            "3 symboles ou quand la grille est complétée sans vainqueur. \n" +
            "Il y a alors égalité.\n");
    JTextArea CreditsTexte = new JTextArea("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\n" + "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.\n" + "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.\n" + "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");

    //Tous les bouttons
    JButton[] jeuButton = new JButton[9];
    JButton btnSauver;
    JButton btnCharger;
    JButton btnPlay;

    //Pour le jeu
    boolean tour;
    Random random = new Random();

    //Pour la Menu Bar
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenu CreditsMenu;
    JMenu helpMenu;
    JMenuItem loadItem;
    JMenuItem saveItem;
    JMenuItem exitItem;
    JMenuItem creditsItem;
    JMenuItem helpItem;

    MorpionFen(){
        Fen.setTitle("Jeu du morpion - Tic-Tac-Toe Game");
        Fen.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Fen.setSize(650,650);
        //Fen.getContentPane().setBackground(new Color(50,50,50));
        Fen.setLayout(new BorderLayout());
        Fen.setVisible(true);
        Fen.setLocationRelativeTo(null);
        //centrer();
        Fen.add(header(),BorderLayout.NORTH);
        Fen.add(footer(),BorderLayout.SOUTH);
        Fen.add(east(), BorderLayout.EAST);
        Fen.add(west(), BorderLayout.WEST);
        //this.Ecouteurs();
        Fen.add(game());
        MenuBar();
    
    }


    public void MenuBar() {
        menuBar = new JMenuBar();

        fileMenu = new JMenu("File");
        CreditsMenu = new JMenu("Credits");
        helpMenu = new JMenu("Help");

        loadItem = new JMenuItem("Load");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");
        creditsItem = new JMenuItem("Crédits");
        helpItem = new JMenuItem("Aide");

        loadItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);
        creditsItem.addActionListener(this);
        helpItem.addActionListener(this);

        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);
        CreditsMenu.add(creditsItem);
        helpMenu.add(helpItem);

        menuBar.add(fileMenu);
        menuBar.add(CreditsMenu);
        menuBar.add(helpMenu);

        Fen.setJMenuBar(menuBar);
    }

    public JPanel east() {
        return jeuEast;
    }

    public JPanel west() {
        return jeuWest;
    }

    public JPanel header() {
        LabelTxtNorth.setFont(new Font("Forte",Font.PLAIN,25));
        LabelTxtNorth.setHorizontalAlignment(JLabel.CENTER);
        LabelTxtNorth.setText("Jeu du morpion");
        //LabelTxtNorth.setBackground();
        LabelTxtNorth.setForeground(new Color(0, 0, 0));
        //----- shit

        jeuHeader.setBackground(new Color(255, 255, 255));
        jeuHeader.setLayout(new BorderLayout());
        jeuHeader.add(LabelTxtNorth, BorderLayout.CENTER);

        return jeuHeader;
    }


    public JPanel footer() {
        LabelTxtSouth.setFont(new Font("Forte",Font.PLAIN,25));
        LabelTxtSouth.setHorizontalAlignment(JLabel.CENTER);
        LabelTxtSouth.setText("Sauver, (re)commencez ou charger une partie");
        LabelTxtSouth.setBackground(new Color(0, 0, 0));
        LabelTxtSouth.setForeground(new Color(0, 0, 0));
        JPanel pan = new JPanel();
        //JPanel pan1 = new JPanel();
        //JPanel pan2 = new JPanel();
        pan.setLayout(new FlowLayout());

        pan.setBackground(new Color(255, 255, 255));

        jeuFooter.setBackground(new Color(255, 255, 255));
        jeuFooter.setLayout(new BorderLayout());
        jeuFooter.add(LabelTxtSouth, BorderLayout.NORTH);

        /*
        btnSauver = new JButton("Save");
        btnSauver.setFont(new Font("Dubai",Font.BOLD,20));
        btnSauver.setBackground(new Color(0, 0, 0));
        btnSauver.setForeground(new Color(255 , 255, 255));
        */

        btnPlay = new JButton("Play");
        btnPlay.setFont(new Font("Forte",Font.PLAIN,20));
        btnPlay.setBackground(new Color(0, 0, 0));
        btnPlay.setForeground(new Color(255 , 255, 255));
        /*
        btnCharger = new JButton("Load");
        btnCharger.setFont(new Font("Dubai",Font.BOLD,20));
        btnCharger.setBackground(new Color(0, 0, 0));
        btnCharger.setForeground(new Color(255 , 255, 255));
        */

        //pan.add(btnSauver);
        pan.add(btnPlay);
        //pan.add(btnCharger);

        jeuFooter.add(pan, BorderLayout.CENTER);

        return jeuFooter;
    }

    public JPanel game() {
        jeuInterface.setLayout(new GridLayout(3,3));
        //System.out.println("test game 1");
        jeuInterface.setBackground(new Color(0, 0, 0));
        //System.out.println("test game 2");
        int i=0;
        while (i<jeuButton.length) {
            jeuButton[i] = new JButton();
            jeuInterface.add(jeuButton[i]);
            jeuButton[i].setBackground(new Color(0, 0, 0));
            jeuButton[i].setFont(new Font("MV Boli",Font.PLAIN,85));
            jeuButton[i].addActionListener(this);
            jeuButton[i].setFocusPainted(false);
            jeuButton[i].setText("");
            jeuButton[i].setEnabled(false);
            i++;
        }

        //btnCharger.addActionListener(this);
        //btnCharger.setFocusPainted(false);
        btnPlay.addActionListener(this);
        btnPlay.setFocusPainted(false);
        //btnSauver.addActionListener(this);
        //btnSauver.setFocusPainted(false);


        return jeuInterface;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        int i=0;
        while (i< jeuButton.length) {

            if(e.getSource()==jeuButton[i]) {
                if(tour) {
                    if(jeuButton[i].getText().equals("")) {
                        System.out.println("--- Appui boutton équipe X");
                        jeuButton[i].setText("X");
                        jeuButton[i].setForeground(new Color(0, 0, 0));
                        jeuButton[i].setBackground(new Color(255, 255, 255));
                        tour=false;
                        LabelTxtNorth.setText(" Tour équipe ROND ");
                        LabelTxtNorth.setFont(new Font("Forte",Font.PLAIN,25));
                        verifCroixRond();
                    }
                }
                else {
                    if(jeuButton[i].getText().equals("")) {
                        System.out.println("--- Appui boutton équipe O");
                        jeuButton[i].setText("O");
                        jeuButton[i].setForeground(new Color(255, 255,255));
                        tour=true;
                        LabelTxtNorth.setText(" Tour équipe CROiX ");
                        LabelTxtNorth.setFont(new Font("Forte",Font.PLAIN,25));
                        verifCroixRond();
                    }
                }
            }


        i++;
        }

        if (e.getSource()==btnPlay) {
            System.out.println(" --- Play ! ---");
            tourRandom();
            i=0;
            while (i<jeuButton.length) {
                if (jeuButton[i].getText().equals("")) {
                    jeuButton[i].setEnabled(true);
                }
                if (jeuButton[i].getText().equals("X") || jeuButton[i].getText().equals("O") || jeuButton[i].getText().equals("-")) {
                    jeuButton[i].setText("");
                    jeuButton[i].setBackground(new Color(0, 0, 0));
                    jeuButton[i].setEnabled(true);
                }
                i++;
            }
        }
        if (e.getSource()==btnCharger) {
            JFileChooser FileChooser = new JFileChooser("./");
            FileChooser.setDialogTitle("Quel fichier charger ?");
            int test = FileChooser.showOpenDialog(null);
            File fileToLoad = FileChooser.getSelectedFile();
            System.out.println("Chargé depuis : " + fileToLoad.getAbsolutePath());
            String nom = fileToLoad.getPath();
            try {
                charger(nom);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        if (e.getSource()==btnSauver) {
            JFileChooser fileChooser = new JFileChooser("./");
            fileChooser.setDialogTitle("Où sauvegarder le fichier ?");
            int userSelection = fileChooser.showSaveDialog(null);
            File fileToSave = fileChooser.getSelectedFile();
            System.out.println("Sauvegardé dans : " + ((File) fileToSave).getAbsolutePath());
            String fileToSaveString = fileToSave.toString()+".txt";
            sauver(fileToSaveString);
        }

        if(e.getSource()==loadItem) {
            JFileChooser FileChooser = new JFileChooser("./");
            FileChooser.setDialogTitle("Quel fichier charger ?");
            int test = FileChooser.showOpenDialog(null);
            File fileToLoad = FileChooser.getSelectedFile();
            System.out.println("Chargé depuis : " + fileToLoad.getAbsolutePath());
            String nom = fileToLoad.getPath();
            try {
                charger(nom);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        if(e.getSource()==saveItem) {
            JFileChooser fileChooser = new JFileChooser("./");
            fileChooser.setDialogTitle("Où sauvegarder le fichier ?");
            int userSelection = fileChooser.showSaveDialog(null);
            File fileToSave = fileChooser.getSelectedFile();
            System.out.println("Sauvegardé dans : " + ((File) fileToSave).getAbsolutePath());
            String fileToSaveString = fileToSave.toString()+".txt";
            sauver(fileToSaveString);
        }
        if(e.getSource()==exitItem) {
            System.exit(0);
        }

        if(e.getSource()==creditsItem) {
            System.out.println("--- Crédits !");
            FenCredits.setTitle("Credits : Jeu du morpion");
            FenCredits.setDefaultCloseOperation(HIDE_ON_CLOSE);
            FenCredits.setSize(400,200);
            FenCredits.setLocationRelativeTo(null);
            //Fen.getContentPane().setBackground(new Color(50,50,50));
            FenCredits.setLayout(new BorderLayout());
            FenCredits.setVisible(true);

            JPanel pan = new JPanel();
            CreditsTexte.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(CreditsTexte);
            pan.setLayout(new BorderLayout());
            pan.add(scrollPane);


            FenCredits.add(pan);
        }

        if(e.getSource()==helpItem) {
            System.out.println("--- Help/Aide !");
            FenAide.setTitle("Aide : Jeu du morpion");
            FenAide.setDefaultCloseOperation(HIDE_ON_CLOSE);
            FenAide.setSize(400,200);
            FenAide.setLocationRelativeTo(null);
            //Fen.getContentPane().setBackground(new Color(50,50,50));
            FenAide.setLayout(new BorderLayout());
            FenAide.setVisible(true);

            JPanel pan = new JPanel();
            AideTexte.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(AideTexte);
            pan.setLayout(new BorderLayout());
            pan.add(scrollPane);


            FenAide.add(pan);
        }

    }

    public void tourRandom() {


        if(random.nextInt(2)==0) {
            tour=true;
            LabelTxtNorth.setText("Tour équipe CROiX");
        }
        else {
            tour=false;
            LabelTxtNorth.setText("Tour équipe ROND");
        }
    }

    public void verifCroixRond() {
        //check X win conditions
        if(
                (jeuButton[0].getText().equals("X")) && (jeuButton[1].getText().equals("X")) && (jeuButton[2].getText().equals("X"))
        ) {
            Croix(0,1,2);
        }
        if(
                (jeuButton[3].getText().equals("X")) && (jeuButton[4].getText().equals("X")) && (jeuButton[5].getText().equals("X"))
        ) {
            Croix(3,4,5);
        }
        if(
                (jeuButton[6].getText().equals("X")) && (jeuButton[7].getText().equals("X")) && (jeuButton[8].getText().equals("X"))
        ) {
            Croix(6,7,8);
        }
        if(
                (jeuButton[0].getText().equals("X")) && (jeuButton[3].getText().equals("X")) && (jeuButton[6].getText().equals("X"))
        ) {
            Croix(0,3,6);
        }
        if(
                (jeuButton[1].getText().equals("X")) && (jeuButton[4].getText().equals("X")) && (jeuButton[7].getText().equals("X"))
        ) {
            Croix(1,4,7);
        }
        if(
                (jeuButton[2].getText().equals("X")) && (jeuButton[5].getText().equals("X")) && (jeuButton[8].getText().equals("X"))
        ) {
            Croix(2,5,8);
        }
        if(
                (jeuButton[0].getText().equals("X")) && (jeuButton[4].getText().equals("X")) && (jeuButton[8].getText().equals("X"))
        ) {
            Croix(0,4,8);
        }
        if(
                (jeuButton[2].getText().equals("X")) && (jeuButton[4].getText().equals("X")) && (jeuButton[6].getText().equals("X"))
        ) {
            Croix(2,4,6);
        }
        //check O win conditions
        if(
                (jeuButton[0].getText().equals("O")) && (jeuButton[1].getText().equals("O")) && (jeuButton[2].getText().equals("O"))
        ) {
            Rond(0,1,2);
        }
        if(
                (jeuButton[3].getText().equals("O")) && (jeuButton[4].getText().equals("O")) && (jeuButton[5].getText().equals("O"))
        ) {
            Rond(3,4,5);
        }
        if(
                (jeuButton[6].getText().equals("O")) && (jeuButton[7].getText().equals("O")) && (jeuButton[8].getText().equals("O"))
        ) {
            Rond(6,7,8);
        }
        if(
                (jeuButton[0].getText().equals("O")) && (jeuButton[3].getText().equals("O")) && (jeuButton[6].getText().equals("O"))
        ) {
            Rond(0,3,6);
        }
        if(
                (jeuButton[1].getText().equals("O")) && (jeuButton[4].getText().equals("O")) && (jeuButton[7].getText().equals("O"))
        ) {
            Rond(1,4,7);
        }
        if(
                (jeuButton[2].getText().equals("O")) && (jeuButton[5].getText().equals("O")) && (jeuButton[8].getText().equals("O"))
        ) {
            Rond(2,5,8);
        }
        if(
                (jeuButton[0].getText().equals("O")) && (jeuButton[4].getText().equals("O")) && (jeuButton[8].getText().equals("O"))
        ) {
            Rond(0,4,8);
        }
        if(
                (jeuButton[2].getText().equals("O")) && (jeuButton[4].getText().equals("O")) && (jeuButton[6].getText().equals("O"))
        ) {
            Rond(2,4,6);
        }
    }

    public void VerifEgalite() {

    }

    public void Croix(int a,int b,int c) {
        jeuButton[a].setBackground(new Color(189, 24, 243));
        jeuButton[b].setBackground(new Color(189, 24, 243));
        jeuButton[c].setBackground(new Color(189, 24, 243));

        int i=0;
        while (i< jeuButton.length) {
            if (i != a || i != b || i != c) {
                jeuButton[i].setEnabled(false);
            }
            i++;
        }

        System.out.println("--- Victoire équipe CROiX");
        LabelTxtNorth.setText("Victoire CROiX");
    }
    public void Rond(int a,int b,int c) {
        jeuButton[a].setBackground(new Color(189, 24, 243));
        jeuButton[b].setBackground(new Color(189, 24, 243));
        jeuButton[c].setBackground(new Color(189, 24, 243));

        int i=0;
        while (i< jeuButton.length) {
            if (i != a || i != b || i != c) {
                jeuButton[i].setEnabled(false);
            }
            i++;
        }

        System.out.println("--- Victoire équipe ROND");
        LabelTxtNorth.setText("Victoire ROND");
    }


    public String convertToTxt() {
        String str = new String();
        int i=0;
        while (i< jeuButton.length) {
            if (jeuButton[i].getText().equals("")) {
                //if (str.length()==0) {
                    //str=str+"-";
                //}
                str=str+","+"-";
            }
            else if (jeuButton[i].getText().equals("X") || jeuButton[i].getText().equals("O")) {
                //if (str.length()==0) {
                    //str=str+jeuButton[i].getText();
                //}
                str=str+","+jeuButton[i].getText();
            }
            i++;
        }
        System.out.println(str);
        return str;
    }

    public void sauver(String nomDuFichier) {

        try {
            BufferedWriter test = Files.newBufferedWriter(Paths.get(nomDuFichier));
            test.write(this.convertToTxt());
            test.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void charger(String nomDuFichier) throws IOException {
    try {
        //System.out.println(nomDuFichier);
        BufferedReader br = Files.newBufferedReader(Paths.get(nomDuFichier));
        String ligne;
        ligne = br.readLine();
        //System.out.println(ligne);
        br.close();
        String[] tab= ligne.split(",");

        System.out.println("--- Taille du tableau de base ! "+jeuButton.length);
        int i=1;
        while (i< jeuButton.length+1) {
            System.out.println("--- Valeure à rentrer : "+tab[i]+" pour le bouton numéro "+String.valueOf(i));
            if (tab[i].equals("-")) {
                jeuButton[i-1].setText("");
                jeuButton[i-1].setBackground(new Color(0, 0, 0));
            }
            else if (tab[i].equals("X")) {
                jeuButton[i-1].setText(tab[i]);
                jeuButton[i-1].setForeground(new Color(0, 0, 0));
                jeuButton[i-1].setBackground(new Color(255, 255, 255));
            }
            else {
                jeuButton[i-1].setText(tab[i]);
                jeuButton[i-1].setForeground(new Color(255, 255, 255));
                jeuButton[i-1].setBackground(new Color(0, 0, 0));
            }
            i++;
        }
    } catch (IOException e) {
        System.err.println(e);
    }

    }

    /*
    public void centrer() {

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();
        int largeur = (int) (dim.width);
        int longueur = (int) (dim.height);
        Fen.setBounds((dim.width - largeur) / 2, (dim.height - longueur) / 2, largeur, longueur);

        //Fen.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        Fen.setSize(width/2, height/2);

    }
    */
}