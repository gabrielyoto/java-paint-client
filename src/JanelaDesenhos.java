import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaDesenhos {
  private final JList list;
  private final JLabel label;
  private JButton okButton, cancelButton;
  private ActionListener okEvent, cancelEvent;
  private JDialog dialog;

  public JanelaDesenhos(String message, JList listToDisplay){
    list = listToDisplay;
    label = new JLabel(message);
    createAndDisplayOptionPane();
  }

  public JanelaDesenhos(String title, String message, JList listToDisplay){
    this(message, listToDisplay);
    dialog.setTitle(title);
  }

  private void createAndDisplayOptionPane(){
    setupButtons();
    JPanel pane = layoutComponents();
    JOptionPane optionPane = new JOptionPane(pane);
    optionPane.setOptions(new Object[]{okButton, cancelButton});
    dialog = optionPane.createDialog("Selecione um desenho");
  }

  private void setupButtons(){
    okButton = new JButton("Ok");
    okButton.addActionListener(this::handleOkButtonClick);

    cancelButton = new JButton("Cancel");
    cancelButton.addActionListener(this::handleCancelButtonClick);
  }

  private JPanel layoutComponents(){
    centerListElements();
    JPanel panel = new JPanel(new BorderLayout(5,5));
    panel.add(label, BorderLayout.NORTH);
    panel.add(list, BorderLayout.CENTER);
    return panel;
  }

  private void centerListElements(){
    DefaultListCellRenderer renderer = (DefaultListCellRenderer) list.getCellRenderer();
    renderer.setHorizontalAlignment(SwingConstants.CENTER);
  }

  public void setOnOk(ActionListener event){ okEvent = event; }

  public void setOnClose(ActionListener event){
    cancelEvent  = event;
  }

  private void handleOkButtonClick(ActionEvent e){
    if(okEvent != null){ okEvent.actionPerformed(e); }
    hide();
  }

  private void handleCancelButtonClick(ActionEvent e){
    if(cancelEvent != null){ cancelEvent.actionPerformed(e);}
    hide();
  }

  public void show(){ dialog.setVisible(true); }

  private void hide(){ dialog.setVisible(false); }

  public Object getSelectedItem(){ return list.getSelectedValue(); }
}