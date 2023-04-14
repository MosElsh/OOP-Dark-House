package OOP.ec22549.MP;

import javax.swing.*;
public class SwingConsole {
    public static void run(final JFrame f, final int width, final int height) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                f.setTitle(f.getClass().getSimpleName());
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setSize(width, height);
                f.setExtendedState(JFrame.MAXIMIZED_BOTH);
                f.setLocationRelativeTo(null);
                f.setVisible(true);
            }
        });
    }
}
