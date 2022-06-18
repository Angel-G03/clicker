import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class Main {
    
    private static JPanel panel;
    private static boolean on = false;
    private static JFrame frame;
    private static int level1a = 0;
    private static JButton button1;
    private static JButton button1a;
    private static JButton button1u;
    private static int cost1u = 500;
    private static int level1u = 0; 
    private static int level2a = 0;
    private static JButton button2;
    private static JButton button2a;
    private static JButton button2u;
    private static int level2u = 0;
    private static int cost2u = 5000;
    private static int level3a = 0;
    private static JButton button3;
    private static JButton button3a;
    private static JButton button3u;
    private static int cost3u = 10000;
    private static int level3u = 0;
    private static JLabel titleLabel;
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
        frame.setPreferredSize(new Dimension(700,700));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);
        frame.setResizable(false);

        ImageIcon bgImg = new ImageIcon("mineNow.png");
        JLabel bgLabel = new JLabel();
        bgLabel.setIcon(bgImg);
        bgLabel.setLayout(null);
        frame.setContentPane(bgLabel);

        File bgMusic = new File("music.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(bgMusic);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        while(on) {
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            break;
        }

        titleLabel = new JLabel();
        ImageIcon titleImg = new ImageIcon("clicker32.png");
        titleLabel.setBounds(310, 60, 90,60);
        titleLabel.setIcon(titleImg);
        bgLabel.add(titleLabel);

        scoreLabel = new JLabel();
        scoreLabel.setText(String.valueOf(score));
        scoreLabel.setBounds(325,190,500,50);
        scoreLabel.setFont(new Font("Times New Roman",Font.BOLD,45));
        bgLabel.add(scoreLabel);

        scoreLabel2 = new JLabel();
        scoreLabel2.setText("Your Score: ");
        scoreLabel2.setFont(new Font("Times New Roman",Font.ITALIC,30));
        scoreLabel2.setBounds(290,115,200,100);
        bgLabel.add(scoreLabel2);

        button1 = new JButton("Click | " + increment1);
        button1.setBounds(200,450,175,50);
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
                    bgLabel.add(button2);
                    bgLabel.add(button2a);
                    bgLabel.add(button2u);
                    bgLabel.revalidate();
                    bgLabel.repaint();
                    
                }
            }
        });
        bgLabel.add(button1);
       
        button1a = new JButton("Automate | LVL" + level1a);
        button1a.setEnabled(false);
        button1a.setBounds(200,500,150,25);
        button1a.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                level1a++;
                switch(level1a) {
                    case 1:
                    ActionListener al = new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            button1.doClick();
                        }
                    };
                    Timer timer = new Timer(1500, al);
                    timer.start();
                    button1a.setText("Automate | LVL" + level1a);
                    break;
                    case 2:
                    ActionListener al2 = new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            button1.doClick();
                        }
                    };
                    Timer timer2 = new Timer(1000, al2);
                    timer2.start();
                    button1a.setText("Automate | LVL" + level1a);
                    break;
                    case 3:
                    ActionListener al3 = new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            button1.doClick();
                        }
                    };
                    Timer timer3 = new Timer(500, al3);
                    timer3.start();
                    button1a.setText("Automate | LVL" + level1a);
                    break;
                }
            }
        });
        bgLabel.add(button1a); 

        button1u = new JButton("Upgrade | Cost: " + cost1u);
        button1u.setBounds(350, 505, 150, 15);
        button1u.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (score >= cost1u && level1u < 3) {
                    score -= cost1u;
                    level1u++;
                    cost1u += 1000;
                    button1a.setEnabled(true);
                    button1a.doClick();
                    button1a.setEnabled(false);
                    button1u.setText("Upgrade | Cost: " + cost1u);
                }
                if(level1u == 3) {
                    button1u.setEnabled(false);
                    button1u.setText("Upgrade | MAX");
                }
            }
        });
        bgLabel.add(button1u);
        
        button2 = new JButton("Click | " + increment2);
        button2.setBounds(200,375,175,50);
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
                    bgLabel.add(button3);
                    bgLabel.add(button3a);
                    bgLabel.add(button3u);
                    bgLabel.revalidate();
                    bgLabel.repaint();
                }        
            }
        });

        button2a = new JButton("Automate | LVL" + level2a);
        button2a.setEnabled(false);
        button2a.setBounds(200,425,150,25);
        button2a.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                level2a++;
                switch(level2a) {
                    case 1:
                    ActionListener al = new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            button2.doClick();
                        }
                    };
                    Timer timer = new Timer(1500, al);
                    timer.start();
                    button2a.setText("Automate | LVL" + level2a);
                    break;
                    case 2:
                    ActionListener al2 = new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            button2.doClick();
                        }
                    };
                    Timer timer2 = new Timer(1000, al2);
                    timer2.start();
                    button2a.setText("Automate | LVL" + level2a);
                    break;
                    case 3:
                    ActionListener al3 = new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            button2.doClick();
                        }
                    };
                    Timer timer3 = new Timer(500, al3);
                    timer3.start();
                    button2a.setText("Automate | LVL" + level2a);
                    break;
                }
            }
        });

        button2u = new JButton("Upgrade | Cost: " + cost2u);
        button2u.setBounds(350, 430, 150, 15);
        button2u.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (score >= cost2u && level2u < 3) {
                    score -= cost2u;
                    level2u++;
                    cost2u += 2000;
                    button2a.setEnabled(true);
                    button2a.doClick();
                    button2a.setEnabled(false);
                    button2u.setText("Upgrade | Cost: " + cost2u);
                }
                if(level2u == 3) {
                    button2u.setEnabled(false);
                    button2u.setText("Upgrade | MAX");
                }
            }
        });

        button3 = new JButton("Click | " + increment3);
        button3.setBounds(200,300,175,50);
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
                    bgLabel.revalidate();
                    bgLabel.repaint();
                }
            }
        });

        button3a = new JButton("Automate | LVL" + level3a);
        button3a.setEnabled(false);
        button3a.setBounds(200,350,150,25);
        button3a.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                level3a++;
                switch(level3a) {
                    case 1:
                    ActionListener al = new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            button3.doClick();
                        }
                    };
                    Timer timer = new Timer(1500, al);
                    timer.start();
                    button3a.setText("Automate | LVL" + level3a);
                    break;
                    case 2:
                    ActionListener al2 = new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            button3.doClick();
                        }
                    };
                    Timer timer2 = new Timer(1000, al2);
                    timer2.start();
                    button3a.setText("Automate | LVL" + level3a);
                    break;
                    case 3:
                    ActionListener al3 = new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            button3.doClick();
                        }
                    };
                    Timer timer3 = new Timer(500, al3);
                    timer3.start();
                    button3a.setText("Automate | LVL" + level3a);
                    break;
                }
            }
        });    
        
        button3u = new JButton("Upgrade | Cost: " + cost3u);
        button3u.setBounds(350, 355, 150, 15);
        button3u.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (score >= cost3u && level3u < 3) {
                    score -= cost3u;
                    level3u++;
                    cost3u += 5000;
                    button3a.setEnabled(true);
                    button3a.doClick();
                    button3a.setEnabled(false);
                    button3u.setText("Upgrade | Cost: " + cost3u);
                }
                if(level3u == 3) {
                    button3u.setEnabled(false);
                    button3u.setText("Upgrade | MAX");
                }
            }
        });
        
        frame.pack();
        frame.setVisible(true);      
    }
}
