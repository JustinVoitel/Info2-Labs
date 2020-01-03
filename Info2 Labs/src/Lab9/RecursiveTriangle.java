package Lab9;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RecursiveTriangle {

    private JFrame frame;
    private Dimension res;
    private int length;
    private int maxLevel;
    public ArrayList<Color> colors= new ArrayList<>();


    public RecursiveTriangle() {
        res = new Dimension(600,600);
        length = 600;
        maxLevel =7;
        initColors();
        draw();
    }

    public void initColors(){
        colors.add(Color.BLACK);
        colors.add(Color.BLUE);
        colors.add(Color.YELLOW);
        colors.add(Color.RED);
        colors.add(Color.PINK);
    }

    public JPanel initPanel(){
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLUE);
                divide(g, frame.getWidth()/2 - frame.getWidth()/2, frame.getHeight()/2+ frame.getWidth()/3, frame.getWidth()-20, 1, maxLevel);
            }

            @Override
            public Dimension getPreferredSize() {
                return frame.getSize();
            }

            public Color pickColor(int depth){
                int randomNum = 0 + (int)(Math.random() * ((colors.size()-1 - 0) + 1));
            	
                return colors.get(randomNum);
            }

            public void divide(Graphics g, float x, float y, float l, int lvl, int max){
                if(lvl == max) {
                    drawTriangle(g, x, y, l);
                } else {
                    g.setColor(this.pickColor(lvl));
                    divide(g, x, y, l/2, lvl + 1, max);
                    divide(g,x + l/2, y, l/2, lvl + 1, max);
                    divide(g, x + l/4, y - (float)Math.sin(Math.PI/3) * l/2, l/2, lvl + 1, max);
                }
            }

            public void drawTriangle(Graphics g, float x, float y, float l){
                g.fillPolygon(
                        new int[]{(int)x,   Math.round(x+l),    Math.round(x + l/2)},
                        new int[]{(int)y,   (int)y,             (int)Math.round(y-(Math.sin(Math.PI/3)*l))},
                        3
                );
            }
        };
    }

    private void draw() {
        frame = new JFrame();
        frame.setSize(this.res);
        frame.setBackground(Color.BLACK);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Component c = (Component)e.getSource();
                frame.add(initPanel());
            }
        });
        frame.add(this.initPanel());
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new RecursiveTriangle();
    }
}