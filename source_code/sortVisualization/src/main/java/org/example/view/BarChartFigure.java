package org.example.view;

import org.eclipse.draw2d.*;
import org.eclipse.draw2d.geometry.Rectangle;

//绘制柱状图
public class BarChartFigure extends Figure {
    private final int[] numArray;
    // 柱子宽度
    private final int barWidth = 50;
    // 柱子间隔
    private final int gap = 20;

    public BarChartFigure(int[] numArray) {
        this.numArray = numArray;
    }

    @Override
    protected void paintFigure(Graphics graphics) {
        super.paintFigure(graphics);

        // 获取父级Figure的边界矩形
        Rectangle parentBounds = getParent().getBounds();

        int x = gap;
        for(int i=0; i<numArray.length; i++) {
            // 计算柱子高度
            int barHeight = numArray[i] * 10;

            // 一个矩形区域
            Rectangle rect = new Rectangle(x, parentBounds.bottom() - barHeight, barWidth, barHeight);
            // 表示一个矩形图形
            RectangleFigure rectangleFigure = new RectangleFigure();
            //图形的背景颜色为蓝色,边框黑色
            rectangleFigure.setBackgroundColor(ColorConstants.blue);
            rectangleFigure.setForegroundColor(ColorConstants.black);
            rectangleFigure.setBounds(rect);
            add(rectangleFigure);

            // 在柱子上添加标签，显示数字
            Label label = new Label(String.valueOf(numArray[i]));
            // 标签的字体颜色为红色
            label.setBackgroundColor(ColorConstants.red);
            label.setForegroundColor(ColorConstants.red);
            // 标签的位置在柱子正上方
            label.setBounds(new Rectangle(x, parentBounds.bottom() - barHeight - 20, barWidth, 20));
            add(label);

            // 更新柱子的横坐标
            x += barWidth + gap;
        }
    }
}
