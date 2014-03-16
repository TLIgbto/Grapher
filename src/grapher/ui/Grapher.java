package grapher.ui;

import grapher.fc.Function;
import grapher.fc.FunctionFactory;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.Vector;

import static java.lang.Math.*;

public class Grapher extends JPanel {

    static final int MARGIN = 40;
    static final int STEP = 5;
    static final Color defaultColor = Color.black;
    static final BasicStroke dash = new BasicStroke(1, BasicStroke.CAP_ROUND,
            BasicStroke.JOIN_ROUND,
            1.f,
            new float[]{4.f, 4.f},
            0.f);

    protected int W = 400;
    protected int H = 300;

    protected double xmin, xmax;
    protected double ymin, ymax;

    protected Vector<Object[]> functions;
    private LeftPane leftPane;

    public Grapher() {
        xmin = -PI / 2.;
        xmax = 3 * PI / 2;
        ymin = -1.5;
        ymax = 1.5;
        functions = new Vector< Object[]>();

        MyListener mia = new MyListener();
        addMouseListener(mia);
        addMouseMotionListener(mia);
        addMouseWheelListener(mia);
    }

    public void highLightGraph(Function f, Color c) {
        Graphics2D g = (Graphics2D) getGraphics();
        Rectangle r = g.getClipBounds();

        final int N = (W / this.STEP) + 1;
        final double dx = dx(STEP);
        double[] xs = new double[N];
        int Xs[] = new int[N];
        int Ys[] = new int[N];
        for (int i = 0; i < N; i++) {
            double x = this.xmin + i * dx;
            xs[i] = x;
            Xs[i] = X(x);
            Ys[i] = Y(f.y(xs[i]));
        }

        for (Object[] function : functions) {
            if (f.toString().compareTo(function[0].toString()) == 0) {
                function[1] = c;
            }
        }

        g.setStroke(new BasicStroke(1.5f));
        g.setColor(c);
        g.drawPolyline(Xs, Ys, N);
        repaint();

    }

    public void add(String expression) {
        Object[] tab = {FunctionFactory.createFunction(expression), defaultColor};
        add(tab);
    }

    public void remove(String expression) {
        for (Object[] function : functions) {
            if (function[0] == expression) {
                remove(function);
                break;
            }
        }
    }

    public void add(Object[] tab) {
        functions.add(tab);
        repaint();
    }

    public void remove(Object[] function) {
        functions.remove(function);
        repaint();
    }

    public Dimension getPreferredSize() {
        return new Dimension(W, H);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        W = getWidth();
        H = getHeight();

        Graphics2D g2 = (Graphics2D) g;

        // background
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, W, H);

        g2.setColor(Color.BLACK);

        // box
        g2.translate(MARGIN, MARGIN);
        W -= 2 * MARGIN;
        H -= 2 * MARGIN;
        if (W < 0 || H < 0) {
            return;
        }
        //dessin du rectangle
        g2.drawRect(0, 0, W, H);

        //ecriture de x et y sur le graphe
        g2.drawString("x", W, H + 10);
        g2.drawString("y", -10, 0);

        // plot
        g2.clipRect(0, 0, W, H);
        g2.translate(-MARGIN, -MARGIN);

        // x values
        final int N = W / STEP + 1;
        final double dx = dx(STEP);
        double xs[] = new double[N];
        int Xs[] = new int[N];
        for (int i = 0; i < N; i++) {
            double x = xmin + i * dx;
            xs[i] = x;
            Xs[i] = X(x);
        }

        for (Object[] f : functions) {
            // y values
            int Ys[] = new int[N];
            for (int i = 0; i < N; i++) {
                Ys[i] = Y(((Function) f[0]).y(xs[i]));
            }
            g2.setColor((Color) f[1]);
            g2.drawPolyline(Xs, Ys, N);
        }
        g2.setColor(Color.BLACK);
        g2.setClip(null);

        // axes
        drawXTick(g2, 0);
        drawYTick(g2, 0);

        double xstep = unit((xmax - xmin) / 10);
        double ystep = unit((ymax - ymin) / 10);

        g2.setStroke(dash);
        for (double x = xstep; x < xmax; x += xstep) {
            drawXTick(g2, x);
        }
        for (double x = -xstep; x > xmin; x -= xstep) {
            drawXTick(g2, x);
        }
        for (double y = ystep; y < ymax; y += ystep) {
            drawYTick(g2, y);
        }
        for (double y = -ystep; y > ymin; y -= ystep) {
            drawYTick(g2, y);
        }
    }

    protected double dx(int dX) {
        return (double) ((xmax - xmin) * dX / W);
    }

    protected double dy(int dY) {
        return -(double) ((ymax - ymin) * dY / H);
    }

    protected double x(int X) {
        return xmin + dx(X - MARGIN);
    }

    protected double y(int Y) {
        return ymin + dy((Y - MARGIN) - H);
    }

    protected int X(double x) {
        int Xs = (int) round((x - xmin) / (xmax - xmin) * W);
        return Xs + MARGIN;
    }

    protected int Y(double y) {
        int Ys = (int) round((y - ymin) / (ymax - ymin) * H);
        return (H - Ys) + MARGIN;
    }

    protected void drawXTick(Graphics2D g2, double x) {
        if (x > xmin && x < xmax) {
            final int X0 = X(x);
            g2.drawLine(X0, MARGIN, X0, H + MARGIN);
            g2.drawString((new Double(x)).toString(), X0, H + MARGIN + 15);
        }
    }

    protected void drawYTick(Graphics2D g2, double y) {
        if (y > ymin && y < ymax) {
            final int Y0 = Y(y);
            g2.drawLine(0 + MARGIN, Y0, W + MARGIN, Y0);
            g2.drawString((new Double(y)).toString(), 5, Y0);
        }
    }

    protected static double unit(double w) {
        double scale = pow(10, floor(log10(w)));
        w /= scale;
        if (w < 2) {
            w = 2;
        } else if (w < 5) {
            w = 5;
        } else {
            w = 10;
        }
        return w * scale;
    }

    protected void translate(int dX, int dY) {
        double dx = dx(dX);
        double dy = dy(dY);
        xmin -= dx;
        xmax -= dx;
        ymin -= dy;
        ymax -= dy;
        repaint();
    }

    protected void zoom(Point center, int dz) {
        double x = x(center.x);
        double y = y(center.y);
        double ds = exp(dz * .01);
        xmin = x + (xmin - x) / ds;
        xmax = x + (xmax - x) / ds;
        ymin = y + (ymin - y) / ds;
        ymax = y + (ymax - y) / ds;
        repaint();
    }

    protected void zoom(Point p0, Point p1) {
        double x0 = x(p0.x);
        double y0 = y(p0.y);
        double x1 = x(p1.x);
        double y1 = y(p1.y);
        xmin = min(x0, x1);
        xmax = max(x0, x1);
        ymin = min(y0, y1);
        ymax = max(y0, y1);
        repaint();
    }

    public LeftPane getLeftPane() {
        return leftPane;
    }

    public void setLeftPane(LeftPane leftPane) {
        this.leftPane = leftPane;
    }

    class MyListener extends MouseInputAdapter {

        int x = 0;
        int y = 0;
        Point p0, p1;
        boolean gauche, milieu, droite, droitedragged, gauchedragged = false;

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            // TODO Auto-generated method stub
            super.mouseWheelMoved(e);
            int n = e.getWheelRotation();
            if (n < 0) {
                zoom(e.getPoint(), 5);
            } else {
                zoom(e.getPoint(), -5);
            }

        }

        public void mouseDragged(MouseEvent e) {

            if (gauche) {
                gauchedragged = true;
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                translate((x - e.getX()) / 50, (y - e.getY()) / 50);
            } else if (milieu) {

                p1 = e.getPoint();
                if (p0.x > p1.x) {
                    zoom(p0, -5);
                } else if (p0.x < p1.x) {
                    zoom(p0, 5);
                }

            } else if (droite) {
                droitedragged = true;
                p1 = e.getPoint();
            }

        }

        @Override
        public void mousePressed(MouseEvent e) {
            x = e.getX();
            y = e.getY();
            p0 = new Point(x, y);
            if (e.getButton() == MouseEvent.BUTTON1) {
                gauche = true;

            } else if (e.getButton() == MouseEvent.BUTTON2) {
                milieu = true;

            } else if (e.getButton() == MouseEvent.BUTTON3) {
                droite = true;
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

            if (droitedragged) {
                zoom(p0, p1);
            } else if (gauchedragged) {
                //aucun traitement
            } else if (gauche) {
                zoom(p0, 5);
            } else if (droite) {
                zoom(p0, -5);
            }

            gauche = false;
            droite = false;
            droitedragged = false;
            gauchedragged = false;
            milieu = false;
        }
    }
}
