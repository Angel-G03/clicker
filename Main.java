import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class Main {
    
    private static JPanel panel;
    private static boolean on;
    private static JFrame frame;
    private static int screenWidth = 700;
    private static int screenHeight = 700;
    private static int automationLevel = 0;
    private static JButton button1;
    private static int button1YPos = 550;
    private static int buttonWidth = 175;
    private static JButton automateButton;
    private static int automateButtonYPos = 600;
    private static int automationRate = 750;
    private static int automationRate2 = 500;
    private static int automationRate3 = 250;
    private static int automateButtonWidth = 150;
    private static int automateButtonHeight = 25;
    private static JButton upgradeButton;
    private static int upgradeButtonYPos = 600;
    private static int upgradeButtonWidth = 175;
    private static int upgradeButtonHeight = 25;
    private static int upgradeCost = 500;
    private static int upgradeLevel = 0; 
    private static int automationLevel2 = 0;
    private static JButton button2;
    private static JButton automateButton2;
    private static JButton upgradeButton2;
    private static int upgradeLevel2 = 0;
    private static int upgradeCost2 = 5000;
    private static int automationLevel3 = 0;
    private static JButton button3;
    private static JButton automateButton3;
    private static JButton upgradeButton3;
    private static int upgradeCost3 = 10000;
    private static int upgradeLevel3 = 0;
    private static JLabel logoLabel;
    private static JLabel scoreLabel;
    private static JLabel scoreLabel2;
    private static int score = 0;
    private static int increment1 = 1;
    private static int increment2 = 16;
    private static int increment3 = 64;

    public static void main(String[] args) throws Exception {

        on = true;
        frame = new JFrame("Clicker");
        panel = new JPanel();
        frame.setPreferredSize(new Dimension(screenWidth, screenHeight));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);
        frame.setResizable(false);

        ImageIcon backgroundImage = new ImageIcon("images/mineNow.png");
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(backgroundImage);
        backgroundLabel.setLayout(null);
        frame.setContentPane(backgroundLabel);

        File backgroundMusic = new File("music/music.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(backgroundMusic);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        while(on) {
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            break;
        }

        logoLabel = new JLabel();
        ImageIcon titleLogo = new ImageIcon("images/clicker32.png");
        logoLabel.setBounds((screenWidth/2) - 45, 60, 90,60);
        logoLabel.setIcon(titleLogo);
        backgroundLabel.add(logoLabel);

        scoreLabel = new JLabel();
        scoreLabel.setText(String.valueOf(score));
        scoreLabel.setBounds(325,190,500,50);
        scoreLabel.setFont(new Font("Times New Roman",Font.BOLD,45));
        backgroundLabel.add(scoreLabel);

        scoreLabel2 = new JLabel();
        scoreLabel2.setText("Your Score: ");
        scoreLabel2.setFont(new Font("Times New Roman",Font.ITALIC,30));
        scoreLabel2.setBounds(280,115,200,100);
        backgroundLabel.add(scoreLabel2);

        button1 = new JButton("Click | " + increment1);
        button1.setBounds((screenWidth/2) - (buttonWidth/2), button1YPos, buttonWidth, 50);
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                score += increment1;
                scoreLabel.setText(String.valueOf(score));

                if (score >= 50 && increment1 < 2) {
                    increment1 *= 2;
                    button1.setText("Click | " + increment1);
                }
                else if (score >= 150 && increment1 < 4) {
                    increment1 *= 2;
                    button1.setText("Click | " + increment1);
                }
                else if (score >= 300 && increment1 < 8) {
                    increment1 *= 2;
                    button1.setText("Click | " + increment1);
                }
                else if (score >= 500 && increment1 < 16) {
                    increment1 *= 2;
                    button1.setText("Click | " + increment1 + " | MAX");
                }

                if (increment1 == 16) {
                    backgroundLabel.add(button2);
                    backgroundLabel.add(automateButton2);
                    backgroundLabel.add(upgradeButton2);
                    backgroundLabel.revalidate();
                    backgroundLabel.repaint();
                }
            }
        });
        backgroundLabel.add(button1);
       
        automateButton = new JButton("Automate | LVL" + automationLevel);
        automateButton.setEnabled(false);
        automateButton.setBounds(200, automateButtonYPos, automateButtonWidth, automateButtonHeight);
        automateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                automationLevel++;
                switch(automationLevel) {
                    case 1:
                    ActionListener actionListener = new ActionListener() {
                        public void actionPerformed(ActionEvent event) {
                            button1.doClick();
                        }
                    };
                    Timer timer = new Timer(automationRate, actionListener);
                    timer.start();
                    automateButton.setText("Automate | LVL" + automationLevel);
                    break;
                    case 2:
                    ActionListener actionListener2 = new ActionListener() {
                        public void actionPerformed(ActionEvent event) {
                            button1.doClick();
                        }
                    };
                    Timer timer2 = new Timer(automationRate2, actionListener2);
                    timer2.start();
                    automateButton.setText("Automate | LVL" + automationLevel);
                    break;
                    case 3:
                    ActionListener actionListener3 = new ActionListener() {
                        public void actionPerformed(ActionEvent event) {
                            button1.doClick();
                        }
                    };
                    Timer timer3 = new Timer(automationRate3, actionListener3);
                    timer3.start();
                    automateButton.setText("Automate | LVL" + automationLevel);
                    break;
                }
            }
        });
        backgroundLabel.add(automateButton); 

        upgradeButton = new JButton("Upgrade | Cost: " + upgradeCost);
        upgradeButton.setBounds(350, upgradeButtonYPos, upgradeButtonWidth, upgradeButtonHeight);
        upgradeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (score >= upgradeCost && upgradeLevel < 3) {
                    score -= upgradeCost;
                    upgradeLevel++;
                    upgradeCost += 1000;
                    automateButton.setEnabled(true);
                    automateButton.doClick();
                    automateButton.setEnabled(false);
                    upgradeButton.setText("Upgrade | Cost: " + upgradeCost);
                }
                if(upgradeLevel == 3) {
                    upgradeButton.setEnabled(false);
                    upgradeButton.setText("Upgrade | MAX");
                }
            }
        });
        backgroundLabel.add(upgradeButton);
        
        button2 = new JButton("Click | " + increment2);
        button2.setBounds((screenWidth/2) - (buttonWidth/2), button1YPos - 100, buttonWidth, 50);
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                score += increment2;
                scoreLabel.setText(String.valueOf(score));
                if (score >= 1500 && increment2 < 17) {
                    increment2 += 8;
                    button2.setText("Click | " + increment2);
                }
                else if (score >= 2500 && increment2 < 25) {
                    increment2 += 8;
                    button2.setText("Click | " + increment2);
                }
                else if (score >= 5000 && increment2 < 33) {
                    increment2 += 8;
                    button2.setText("Click | " + increment2);
                }
                else if (score >= 10000 && increment2 < 41) {
                    increment2 += 8;
                    button2.setText("Click | " + increment2 + " | MAX");
                }
                if (increment2 == 48) {
                    backgroundLabel.add(button3);
                    backgroundLabel.add(automateButton3);
                    backgroundLabel.add(upgradeButton3);
                    backgroundLabel.revalidate();
                    backgroundLabel.repaint();
                }        
            }
        });

        automateButton2 = new JButton("Automate | LVL" + automationLevel2);
        automateButton2.setEnabled(false);
        automateButton2.setBounds(200, automateButtonYPos - 100, automateButtonWidth, automateButtonHeight);
        automateButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                automationLevel2++;
                switch(automationLevel2) {
                    case 1:
                    ActionListener actionListener = new ActionListener() {
                        public void actionPerformed(ActionEvent event) {
                            button2.doClick();
                        }
                    };
                    Timer timer = new Timer(automationRate, actionListener);
                    timer.start();
                    automateButton2.setText("Automate | LVL" + automationLevel2);
                    break;
                    case 2:
                    ActionListener actionListener2 = new ActionListener() {
                        public void actionPerformed(ActionEvent event) {
                            button2.doClick();
                        }
                    };
                    Timer timer2 = new Timer(automationRate2, actionListener2);
                    timer2.start();
                    automateButton2.setText("Automate | LVL" + automationLevel2);
                    break;
                    case 3:
                    ActionListener actionListener3 = new ActionListener() {
                        public void actionPerformed(ActionEvent event) {
                            button2.doClick();
                        }
                    };
                    Timer timer3 = new Timer(automationRate3, actionListener3);
                    timer3.start();
                    automateButton2.setText("Automate | LVL" + automationLevel2);
                    break;
                }
            }
        });

        upgradeButton2 = new JButton("Upgrade | Cost: " + upgradeCost2);
        upgradeButton2.setBounds(350, upgradeButtonYPos - 100, upgradeButtonWidth, upgradeButtonHeight);
        upgradeButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (score >= upgradeCost2 && upgradeLevel2 < 3) {
                    score -= upgradeCost2;
                    upgradeLevel2++;
                    upgradeCost2 += 2000;
                    automateButton2.setEnabled(true);
                    automateButton2.doClick();
                    automateButton2.setEnabled(false);
                    upgradeButton2.setText("Upgrade | Cost: " + upgradeCost2);
                }
                if(upgradeLevel2 == 3) {
                    upgradeButton2.setEnabled(false);
                    upgradeButton2.setText("Upgrade | MAX");
                }
            }
        });

        button3 = new JButton("Click | " + increment3);
        button3.setBounds((screenWidth/2) - (buttonWidth/2), upgradeButtonYPos - 250, buttonWidth, 50);
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                score += increment3;
                scoreLabel.setText(String.valueOf(score));
                if (score >= 17000 && increment3 < 65) {
                    increment3 += 16;
                    button3.setText("Click | " + increment3);
                }
                else if (score >= 30000 && increment3 < 81) {
                    increment3 += 16;
                    button3.setText("Click | " + increment3);
                }
                else if (score >= 50000 && increment3 < 97) {
                    increment3 += 16;
                    button3.setText("Click | " + increment3);
                }
                else if (score >= 100000 && increment3 < 113) {
                    increment3 += 16;
                    button3.setText("Click | " + increment3 + " | MAX");
                    backgroundLabel.revalidate();
                    backgroundLabel.repaint();
                }
            }
        });

        automateButton3 = new JButton("Automate | LVL" + automationLevel3);
        automateButton3.setEnabled(false);
        automateButton3.setBounds(200, automateButtonYPos - 200, automateButtonWidth, automateButtonHeight);
        automateButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                automationLevel3++;
                switch(automationLevel3) {
                    case 1:
                    ActionListener actionListener = new ActionListener() {
                        public void actionPerformed(ActionEvent event) {
                            button3.doClick();
                        }
                    };
                    Timer timer = new Timer(automationRate, actionListener);
                    timer.start();
                    automateButton3.setText("Automate | LVL" + automationLevel3);
                    break;
                    case 2:
                    ActionListener actionListener2 = new ActionListener() {
                        public void actionPerformed(ActionEvent event) {
                            button3.doClick();
                        }
                    };
                    Timer timer2 = new Timer(automationRate2, actionListener2);
                    timer2.start();
                    automateButton3.setText("Automate | LVL" + automationLevel3);
                    break;
                    case 3:
                    ActionListener actionListener3 = new ActionListener() {
                        public void actionPerformed(ActionEvent event) {
                            button3.doClick();
                        }
                    };
                    Timer timer3 = new Timer(automationRate3, actionListener3);
                    timer3.start();
                    automateButton3.setText("Automate | LVL" + automationLevel3);
                    break;
                }
            }
        });    
        
        upgradeButton3 = new JButton("Upgrade | Cost: " + upgradeCost3);
        upgradeButton3.setBounds(350, upgradeButtonYPos - 200, upgradeButtonWidth, upgradeButtonHeight);
        upgradeButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (score >= upgradeCost3 && upgradeLevel3 < 3) {
                    score -= upgradeCost3;
                    upgradeLevel3++;
                    upgradeCost3 += 5000;
                    automateButton3.setEnabled(true);
                    automateButton3.doClick();
                    automateButton3.setEnabled(false);
                    upgradeButton3.setText("Upgrade | Cost: " + upgradeCost3);
                }
                if(upgradeLevel3 == 3) {
                    upgradeButton3.setEnabled(false);
                    upgradeButton3.setText("Upgrade | MAX");
                }
            }
        });
        
        frame.pack();
        frame.setVisible(true);      
    }
}
