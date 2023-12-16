package org.example.view;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

//显示柱状图界面
public class BarChart {

    protected Shell shell;
//    private final int[] numArray = {100, 45, 67, 12, 90};

    public void open(int[] nowArr) {
        Display display = Display.getDefault();
        createContents(nowArr);
        // 设置窗口位置
        shell.setLocation(800, 100);
        shell.open();
        shell.layout();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }

    protected void createContents(int[] nowArray) {
        shell = new Shell();
        shell.setSize(800, 600);
        shell.setText("数组可视化柱状图");
        shell.setLayout(new FillLayout(SWT.HORIZONTAL));

        FigureCanvas canvas = new FigureCanvas(shell, SWT.NONE);
        canvas.setBackground(ColorConstants.white);
        canvas.setScrollBarVisibility(FigureCanvas.ALWAYS);

        BarChartFigure barChartFigure = new BarChartFigure(nowArray);
        canvas.setContents(barChartFigure);
    }

    public static void main(String[] args) {
        //新建一个窗口用来绘制柱状图
        BarChart window = new BarChart();
        int[] numArray = {10, 7, 4, 3, 5};
        window.open(numArray);
    }
}