//Information Retrieval
//Butas, Marinduque, Meguillo, Varona
package irframe;

public class IRFrame extends javax.swing.JFrame {
    String input;
    InfoRetrievalCMSC176 project = new InfoRetrievalCMSC176();
    //constructor
    public IRFrame() {
        initComponents();
        setTitle("Information Retrieval");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TITLE = new javax.swing.JLabel();
        searchField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        result = new javax.swing.JScrollPane();
        resultField = new javax.swing.JTextArea();
        reset = new javax.swing.JButton();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(512, 386));
        setPreferredSize(new java.awt.Dimension(512, 300));
        setResizable(false);
        getContentPane().setLayout(null);

        TITLE.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        TITLE.setForeground(new java.awt.Color(204, 204, 204));
        TITLE.setText("INFORMATION RETRIEVAL");
        getContentPane().add(TITLE);
        TITLE.setBounds(150, 10, 217, 70);

        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });
        getContentPane().add(searchField);
        searchField.setBounds(140, 80, 140, 30);

        searchButton.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        searchButton.setText("SEARCH");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });
        getContentPane().add(searchButton);
        searchButton.setBounds(290, 80, 80, 30);

        result.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        result.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        resultField.setEditable(false);
        resultField.setColumns(20);
        resultField.setRows(5);
        resultField.setAutoscrolls(false);
        result.setViewportView(resultField);

        getContentPane().add(result);
        result.setBounds(140, 126, 230, 120);

        reset.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        reset.setText("RESET");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });
        getContentPane().add(reset);
        reset.setBounds(140, 250, 90, 19);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/irframe/bg.jpg"))); // NOI18N
        getContentPane().add(background);
        background.setBounds(0, 0, 530, 290);

        pack();
    }// </editor-fold>//GEN-END:initComponents
//action event when search button is entered
    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        input = searchField.getText();
        
//        System.out.println(input);
//        String result = "result itetch";
        resultField.setText(project.search(input));
    }//GEN-LAST:event_searchButtonActionPerformed

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchFieldActionPerformed

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        resultField.setText("");
        searchField.setText("");
    }//GEN-LAST:event_resetActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new IRFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TITLE;
    private javax.swing.JLabel background;
    private javax.swing.JButton reset;
    private javax.swing.JScrollPane result;
    private javax.swing.JTextArea resultField;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchField;
    // End of variables declaration//GEN-END:variables
}
