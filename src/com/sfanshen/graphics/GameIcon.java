package com.sfanshen.graphics;

import com.sfanshen.main.Const;

import javax.swing.*;
import java.awt.*;

public class GameIcon {
    int x, y, width, height;


    public static final double ARC_OFFSET = 0.09;
    public static final double TOP_OFFSET_Y = 0.35;
    public static final double TRAPEZOID_HEIGHT = 0.02;
    public static final double TRAPEZOID_TOP_BOTTOM_OFFSET = 0.025;
    public static final double ICON_OFFSET_X = 0.3;
    public static final double I_WIDTH = 0.03;
    public static final double I_OFFSET_X = 0.05;
    public static final double I_OFFSET_Y = 0.025;

    GameIcon(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics2D g) {
        drawOutline(g);
        int[] xPoints = {(int) (this.x + this.width * ICON_OFFSET_X), (int) (this.x + this.width * (1 - ICON_OFFSET_X)), (int) (this.x + this.width * (1 - ICON_OFFSET_X - TRAPEZOID_TOP_BOTTOM_OFFSET)), (int) (this.x + this.width * (ICON_OFFSET_X + TRAPEZOID_TOP_BOTTOM_OFFSET))};
        int[] yPoints = {(int) (this.y + this.height * TOP_OFFSET_Y), (int) (this.y + this.height * TOP_OFFSET_Y), (int) (this.y + this.height * (TOP_OFFSET_Y + TRAPEZOID_HEIGHT)), (int) (this.y + this.height * (TOP_OFFSET_Y + TRAPEZOID_HEIGHT))};
        int[] yPoints2 = {(int) (this.y + this.height * (1 - TOP_OFFSET_Y)), (int) (this.y + this.height * (1 - TOP_OFFSET_Y)), (int) (this.y + this.height * (1 - TOP_OFFSET_Y - TRAPEZOID_HEIGHT)), (int) (this.y + this.height * (1 - TOP_OFFSET_Y - TRAPEZOID_HEIGHT))};

        Polygon trapezoid = new Polygon(xPoints, yPoints, 4);
        Polygon trapezoid2 = new Polygon(xPoints, yPoints2, 4);
        Rectangle rectangle = new Rectangle((int) (this.x + this.width * (ICON_OFFSET_X + TRAPEZOID_TOP_BOTTOM_OFFSET + I_OFFSET_X)), (int) (this.y + this.height * (TOP_OFFSET_Y + TRAPEZOID_HEIGHT + I_OFFSET_Y)), (int) (this.width * I_WIDTH), (int) (this.height * (1 - (2 * TOP_OFFSET_Y + 2 * TRAPEZOID_HEIGHT + 2 * I_OFFSET_Y))));
        Rectangle rectangle2 = new Rectangle((int) (this.x + this.width * (ICON_OFFSET_X + TRAPEZOID_TOP_BOTTOM_OFFSET + I_OFFSET_X)), (int) (this.y + this.height * (TOP_OFFSET_Y + TRAPEZOID_HEIGHT + I_OFFSET_Y)), (int) (this.width * I_WIDTH), (int) (this.height * (1 - (2 * TOP_OFFSET_Y + 2 * TRAPEZOID_HEIGHT + 2 * I_OFFSET_Y))));
        g.fillPolygon(trapezoid);
        g.drawPolygon(trapezoid);
        g.fillPolygon(trapezoid2);
        g.drawPolygon(trapezoid2);
        g.fill(rectangle);
        g.draw(rectangle);

    }


    public void drawOutline(Graphics2D g) {
        Stroke oldStroke = g.getStroke();
        g.setStroke(new BasicStroke((float) (0.01 * this.width)));
        g.drawOval(this.x, this.y, this.width, this.height);
        g.setStroke(new BasicStroke((float) (0.008 * this.width)));
        g.drawArc((int) (this.x + this.width * ARC_OFFSET), (int) (this.y + this.height * ARC_OFFSET), (int) (this.width * (1 - 2 * ARC_OFFSET)), (int) (this.height * (1 - 2 * ARC_OFFSET)),
                20, 50);
        g.drawArc((int) (this.x + this.width * ARC_OFFSET), (int) (this.y + this.height * ARC_OFFSET), (int) (this.width * (1 - 2 * ARC_OFFSET)), (int) (this.height * (1 - 2 * ARC_OFFSET)),
                110, 50);
        g.drawArc((int) (this.x + this.width * ARC_OFFSET), (int) (this.y + this.height * ARC_OFFSET), (int) (this.width * (1 - 2 * ARC_OFFSET)), (int) (this.height * (1 - 2 * ARC_OFFSET)),
                200, 50);
        g.drawArc((int) (this.x + this.width * ARC_OFFSET), (int) (this.y + this.height * ARC_OFFSET), (int) (this.width * (1 - 2 * ARC_OFFSET)), (int) (this.height * (1 - 2 * ARC_OFFSET)),
                290, 50);


        g.drawArc((int) (this.x + this.width * 2 * ARC_OFFSET), (int) (this.y + this.height * 2 * ARC_OFFSET), (int) (this.width * (1 - 4 * ARC_OFFSET)), (int) (this.height * (1 - 4 * ARC_OFFSET)),
                20, 140);
        g.drawArc((int) (this.x + this.width * 2 * ARC_OFFSET), (int) (this.y + this.height * 2 * ARC_OFFSET), (int) (this.width * (1 - 4 * ARC_OFFSET)), (int) (this.height * (1 - 4 * ARC_OFFSET)),
                200, 140);
        g.setStroke(oldStroke);
    }
}
