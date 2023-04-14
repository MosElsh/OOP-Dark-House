package OOP.ec22549.MP;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

class GUIVisitor_ec22549 extends JFrame implements Visitor {

    private static final char[] yOrN = { 'y', 'n'};
    
    private int row = 1;
    private int column = 1;
    private int purse;
    private char response = 'z';
    private final List<Item> items = new ArrayList<Item>();
    private final List<Component> allComponents = new ArrayList<Component>();
    private final JPanel mainPanel = new JPanel();
    private final JPanel panelContainer = new JPanel();
    private final JScrollPane scrollableMainPanel = new JScrollPane(mainPanel);
    private final JPanel locationDisplayPanelContainer = new JPanel();
    private final JPanel locationDisplayPanel = new JPanel();
    private final JPanel gridPanel = new JPanel();
    private final JPanel metaDataPanel = new JPanel();
    private final JPanel itemsPanel = new JPanel();
    private final JPanel goldPanel = new JPanel();
    private boolean lightsOn = false;
    private boolean clicked = false;
    
    public GUIVisitor_ec22549(String title, String[] roomUsernames) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Use the screen width and screen height to determine the minimum width and height of the window.
        setMinimumSize(new Dimension(Integer.parseInt("" + (int)(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width * 0.9)), Integer.parseInt("" + (int)(java.awt.Toolkit.getDefaultToolkit().getScreenSize().height * 0.75))));
        setVisible(true);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new GridBagLayout());
        setStyle(getContentPane());
        allComponents.add(getContentPane());

        panelContainer.setLayout(new BoxLayout(panelContainer, BoxLayout.Y_AXIS));
        metaDataPanel.setLayout(new FlowLayout());
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        goldPanel.setLayout(new BoxLayout(goldPanel, BoxLayout.Y_AXIS));
        locationDisplayPanel.setLayout(new BoxLayout(locationDisplayPanel, BoxLayout.Y_AXIS));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.weightx = 0.25;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.insets = new Insets(30, 0, 0, 20);

        // Meta Data Panel

        setStyle(metaDataPanel);
        add(metaDataPanel, constraints);
        allComponents.add(metaDataPanel);

        setStyle(goldPanel);

        JLabel goldAmountLabel = new JLabel("Gold Amount: " + purse);
        setStyle(goldAmountLabel);

        goldPanel.add(goldAmountLabel);
        allComponents.add(goldAmountLabel);

        metaDataPanel.add(goldPanel);
        allComponents.add(goldPanel);

        metaDataPanel.add(Box.createHorizontalStrut(20));

        setStyle(itemsPanel);

        JLabel textLabel = new JLabel("Items Acquired:");
        setStyle(textLabel);

        itemsPanel.add(textLabel);
        allComponents.add(textLabel);

        metaDataPanel.add(itemsPanel);
        allComponents.add(itemsPanel);

        // Main Panel

        constraints.anchor = GridBagConstraints.EAST;
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 0.5;
        constraints.insets = new Insets(30, 0, 0, 0);

        setStyle(mainPanel);
        setStyle(scrollableMainPanel);

        setStyle(scrollableMainPanel.getVerticalScrollBar());
        setStyle(scrollableMainPanel.getHorizontalScrollBar());
        allComponents.add(scrollableMainPanel.getVerticalScrollBar());
        allComponents.add(scrollableMainPanel.getHorizontalScrollBar());

        // Use the screen width and screen height to determine the minimum width and height of the window.
        scrollableMainPanel.setPreferredSize(new Dimension(Integer.parseInt("" + (int)(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width * 0.4)), Integer.parseInt("" + (int)(java.awt.Toolkit.getDefaultToolkit().getScreenSize().height * 0.5))));
        add(scrollableMainPanel, constraints);
        mainPanel.setLayout(new FlowLayout());
        allComponents.add(mainPanel);

        setStyle(panelContainer);
        mainPanel.add(panelContainer);
        allComponents.add(panelContainer);



        // Location Display Panel

        setStyle(locationDisplayPanelContainer);
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.25;
        constraints.weighty = 1.0;
        constraints.insets = new Insets(30, 20, 0, 0);

        add(locationDisplayPanelContainer, constraints);
        allComponents.add(locationDisplayPanelContainer);

        setStyle(locationDisplayPanel);
        locationDisplayPanelContainer.add(locationDisplayPanel);
        allComponents.add(locationDisplayPanel);
        

        JPanel titleContainer = new JPanel();
        titleContainer.setLayout(new FlowLayout());
        setStyle(titleContainer);
        locationDisplayPanel.add(titleContainer);
        allComponents.add(titleContainer);

        JLabel locationDisplayTitle = new JLabel("Your Current Location:");
        setStyle(locationDisplayTitle);
        titleContainer.add(locationDisplayTitle);
        allComponents.add(locationDisplayTitle);

        // Create the panel for the grid that shows where the user is in the house currently.

        gridPanel.setLayout(new GridLayout(3, 3));
        for (int x = 0; x < 9; x++) {
            JButton button = new JButton("Room " + roomUsernames[x]);
            button.setEnabled(false);
            setStyle(button);
            gridPanel.add(button);
        }

        setNewMarker();

        locationDisplayPanel.add(gridPanel);

        mainPanel.updateUI();
        metaDataPanel.updateUI();
        locationDisplayPanelContainer.updateUI();

        tell("Welcome to EC22549's Dark House!");

        repaint();

        purse = 0;
    }
    
       
    public void tell(String m) {
        panelContainer.add(Box.createVerticalStrut(5));

        JTextArea l = new JTextArea(m);
        setStyle(l);
        l.setEditable(false);
        l.setAlignmentY(CENTER_ALIGNMENT);
        l.setAlignmentY(CENTER_ALIGNMENT);
        panelContainer.add(l);
        allComponents.add(l);

        panelContainer.add(Box.createVerticalStrut(5));

        panelContainer.updateUI();
        repaint();
        return;
    }
    
    public char getChoice(String d, char[] a) {
        clicked = false;
        tell(d);
        panelContainer.updateUI();

        JPanel buttonChoices = new JPanel();
        setStyle(buttonChoices);
        buttonChoices.setLayout(new FlowLayout(FlowLayout.LEFT));

        for (int x = 0; x < a.length; x++) {
            JButton button = new JButton("" + a[x]);
            setStyle(button);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton clickedButton = (JButton)(e.getSource());
                    response = clickedButton.getText().charAt(0);
                    clicked = true;
                    return;
                }
            });
            buttonChoices.add(button);
            allComponents.add(button);
        }

        panelContainer.add(buttonChoices);
        panelContainer.updateUI();
        allComponents.add(buttonChoices);
        
        while (clicked == false) {repaint();}
        clicked = false;

        tell("");
        
        return response;
    }
    
    public boolean giveItem(Item x) {
        if (x != null) {
            tell("You are being offered a "+x.name + ".");
            if (getChoice("Would you like to accept the " + x.name + "?", yOrN) == 'y') {
                tell("You have accepted the " + x.name + ".");
                if (x.name.equals("Torch") || x.name.equals("Flashlight")) {
                    lightsOn = true;
                    checkLightsOn(panelContainer);
                }
                items.add(x);
                addItemToItemsPanel(x);
                return true;
            }
            tell("You haven't accepted the " + x.name + ".");
        }
        return false;
    }
    
    public boolean hasIdenticalItem(Item x) {
        for (int i=0; i<items.size();i++) 
            if (x == items.get(i)) 
                return true;
        return false;
    }
        
    public boolean hasEqualItem(Item x) {
        for (int i=0; i<items.size();i++) 
            if (x.equals(items.get(i))) 
                return true;
        return false;
    }
    
    public void giveGold(int n) {
        tell("You are given "+n+" gold pieces.");
        purse += n;
        tell("You now have "+purse+" pieces of gold.");
        updateGoldDisplay();
    }
        
    public int takeGold(int n) {
        
        if (n<0) {
            tell("You didn't have enough gold to give so you have as much as you can.");
            updateGoldDisplay();
            return 0;
        }
        int t = 0;
        if (n > purse) t = purse;
        else t = n;
        
        tell(t+" pieces of gold are taken from you.");
        purse -= t;
        tell("You now have "+purse+" pieces of gold.");
        
        updateGoldDisplay();
        return t;
    }

    private void setStyle(Component c) {
        c.setFont(c.getFont().deriveFont(12f));
        if (lightsOn) {
            c.setBackground(Color.white);
            c.setForeground(Color.black);
        }
        else {
            c.setBackground(Color.black);
            c.setForeground(Color.white);
        }
        repaint();
        return;
    }

    private void checkLightsOn(JPanel panel) {
        if (lightsOn) {
            for (int x = 0; x < allComponents.size(); x++) {
                setStyle(allComponents.get(x));
            }
        }
        panel.updateUI();
        repaint();
        return;
    }

    private void updateGoldDisplay() {
        goldPanel.removeAll();
        JLabel goldLabel = new JLabel("Gold Amount: " + purse);
        setStyle(goldLabel);
        goldPanel.add(goldLabel);
        allComponents.add(goldLabel);
        goldPanel.updateUI();
        return;
    }

    private void addItemToItemsPanel(Item item) {
        JLabel itemLabel = new JLabel("  - " + item.name);
        setStyle(itemLabel);
        itemsPanel.add(itemLabel);
        allComponents.add(itemLabel);
        itemsPanel.updateUI();
        return;
    }

    private void setNewMarker() {
        try {
            gridPanel.getComponent((row * 3) + column).setBackground(Color.red);
        }
        catch (Exception e) {}
        return;
    }

    public void removeOldMarker() {
        try {
            gridPanel.getComponent((row * 3) + column).setBackground(Color.black);;
        }
        catch (Exception e) {}
        return;
    }

    public void updateLocation(int r, int c) {
        removeOldMarker();
        row = r;
        column = c;
        setNewMarker();
        if (r == c && r == 2000) {
            JPanel textContainerPanel = new JPanel();
            setStyle(textContainerPanel);
            textContainerPanel.setLayout(new FlowLayout());
            JLabel endLabel = new JLabel("You have escaped the house!");
            setStyle(endLabel);
            textContainerPanel.add(endLabel);
            locationDisplayPanel.add(textContainerPanel);
        }
        locationDisplayPanel.updateUI();
        return;
    }
}
