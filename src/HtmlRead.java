import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HtmlRead {

    public JFrame mainFrame;
    public JTextArea urlSearch;
    public JTextArea termSearch;
    public JLabel urlText;
    public JLabel termText;
    public JLabel statusLabel;
    public JLabel results;
    public JButton searchButton;


    public static void main(String[] args) {
        HtmlRead html = new HtmlRead();
    }

    public HtmlRead() {
        prepareGUI();

        /*
        try {
            String word = "schema";
            System.out.println();
            System.out.print("hello \n");
            URL url = new URL("https://www.milton.edu/");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream())
            );
            String line;
            while ( (line = reader.readLine()) != null ) {
                if(line.contains("href")) {
                    //if(line.indexOf(word) != -1) {
                        System.out.println(line);
                    //}
                }

            }
            reader.close();
        } catch(Exception ex) {
            System.out.println(ex);
        }

         */

    }

    public void prepareGUI(){
        mainFrame = new JFrame("Html Project");
        mainFrame.setLayout(new GridLayout(10,1));

        urlText = new JLabel("URL:", JLabel.CENTER);
        termText = new JLabel("Term:", JLabel.CENTER);
        statusLabel = new JLabel("", JLabel.CENTER);
        results = new JLabel("");

        urlSearch = new JTextArea();
        urlSearch.setBounds(50,5,700,650);
        termSearch = new JTextArea();
        termSearch.setBounds(50,5,700,650);

        searchButton = new JButton("Search");
        searchButton.setActionCommand("Search");
        searchButton.addActionListener(new ButtonClickListener());


        mainFrame.add(urlText);
        mainFrame.add(urlSearch);
        mainFrame.add(termText);
        mainFrame.add(termSearch);
        mainFrame.add(searchButton);
        mainFrame.add(statusLabel);
        mainFrame.add(results);
        mainFrame.setSize(800,700);
        mainFrame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("Search")) {
                System.out.println(urlSearch.getText());
                if(urlSearch.getText().contains(".com")){
                    statusLabel.setText("URL Found");
                }
                else {
                    statusLabel.setText("Invalid URL");
                }
            }
        }
    }

}
