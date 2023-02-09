import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HtmlRead {
    public JFrame mainFrame;
    public JPanel panel;
    public JPanel bigPanel;
    public JTextArea urlSearch;
    public JTextArea termSearch;
    public JLabel urlText;
    public JLabel termText;
    public JTextArea results;
    public JButton searchButton;
    public JScrollPane scrollBar;

    public String currentLink;
    public String linkList;
    public String searchTerm;

    public static void main(String[] args) {
        HtmlRead html = new HtmlRead();
    }

    public HtmlRead() {
        prepareGUI();
    }

    public void searchLink(){
        linkList = "Results:\n";
        try {
            URL url = new URL(urlSearch.getText());
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;

            while ( (line = reader.readLine()) != null ) {
                if(line.contains("href=\"http")) {
                    currentLink = (line.substring((line.indexOf("href=\"http") + 6), (line.indexOf("\"", line.indexOf("href=\"http") + 6))));
                    if(currentLink.contains(searchTerm)) {
                        if(linkList.contains(currentLink)){} else { // no repeats of links
                            linkList = linkList.concat(currentLink + "\n");
                        }
                    }
                }
            }
            reader.close();

            if(linkList == "Results:\n"){
                results.setText("no results found");
            } else {
                results.setText(linkList);
            }
        } catch(Exception ex) {
            results.setText("please enter a valid link");
        }
    }

    public void prepareGUI(){
        mainFrame = new JFrame("Html Project");
        mainFrame.setLayout(new GridLayout(2,1));

        bigPanel = new JPanel();
        bigPanel.setLayout(new BorderLayout());
        panel = new JPanel();
        panel.setLayout(new GridLayout(2,2));

        urlText = new JLabel("URL:", JLabel.CENTER);
        termText = new JLabel("Term:", JLabel.CENTER);

        urlSearch = new JTextArea();
        urlSearch.setBounds(50,5,700,650);
        termSearch = new JTextArea();
        termSearch.setBounds(50,5,700,650);

        searchButton = new JButton("Search");
        searchButton.setActionCommand("Search");
        searchButton.addActionListener(new ButtonClickListener());

        results = new JTextArea();
        results.setLineWrap(true);
        scrollBar = new JScrollPane(results);
        scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        panel.add(urlText);
        panel.add(urlSearch);
        panel.add(termText);
        panel.add(termSearch);
        bigPanel.add(panel, BorderLayout.CENTER);
        bigPanel.add(searchButton, BorderLayout.SOUTH);
        mainFrame.add(bigPanel);
        mainFrame.add(scrollBar);
        mainFrame.setSize(800,700);
        mainFrame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("Search")) {
                System.out.println(urlSearch.getText());
                searchTerm = termSearch.getText();
                if(searchTerm.equals("")) {
                    results.setText("please enter a search term");
                } else {
                    searchLink();
                }
            }
        }
    }
}
