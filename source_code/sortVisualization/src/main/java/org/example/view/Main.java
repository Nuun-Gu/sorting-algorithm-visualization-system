package org.example.view;

import org.example.controller.Persistence;
import org.example.model.*;
import org.example.controller.Sort;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    //定义全局列表，存放排序交换过程
    public List<int[]> resultList = new ArrayList<>();
//    //定义列表用来存储记录
//    public ArrayList<SortData> historyList = new ArrayList<>();

    InputStr inputStr = new InputStr();
    SortList sortList = new SortList();
    ResultArr resultArr = new ResultArr();

    public Main(){
        JFrame frame = new JFrame("排序算法可视化");
        // 创建文本输入框和按钮
        JTextField textField = new JTextField(20);
        JButton bubbleSortButton = new JButton("冒泡排序");
        JButton selectionSortButton = new JButton("选择排序");
        JButton insertionSortButton = new JButton("插入排序");
        JButton showButton = new JButton("显示数组变化图");
        JButton btnShowHistory = new JButton("查看历史记录");

        //历史记录下拉框
        JComboBox<String> historyComboBox;
        historyComboBox = new JComboBox<String>();
        historyComboBox.setBounds(134, 37, 290, 20);

        // 创建表格模型
        DefaultTableModel model = new DefaultTableModel();
        // 创建 JTable 并设置模型
        JTable table = new JTable(model);
        // 将 JTable 添加到滚动窗格中
        JScrollPane scrollPane = new JScrollPane(table);

        // 创建面板，并将各组件添加到面板中
        JPanel panel = new JPanel();
        panel.add(textField);
        panel.add(bubbleSortButton);
        panel.add(selectionSortButton);
        panel.add(insertionSortButton);
//        panel.add(quicksortButton);
        panel.add(scrollPane);
        panel.add(showButton, BorderLayout.SOUTH);
        panel.add(btnShowHistory, BorderLayout.SOUTH);

        // 将面板添加到主窗口中
        frame.add(panel);
        frame.setSize(500, 550);
        frame.setVisible(true);

        // 冒泡排序按钮的事件监听器
        bubbleSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 清空resultList和数据表
                resultList.clear();
                model.setRowCount(0);
                model.setColumnCount(0);
                //获取用户输入的字符串
                String text = textField.getText();

                // 通过Sort类，获取原始数组、包含排序交换过程的列表、排序结果
                Sort sort = new Sort();
                int[] numArray = sort.getArr(text);
                resultList = sort.getBubbleList(text);

                // 显示排序过程中数组变化
                // 为表格添加表头
                model.addColumn("冒泡排序交换次数");
                for (int i = 0; i < numArray.length; i++) {
                    model.addColumn("第" + (i + 1) +"个数");
                }
                // 为表格添加排序过程
                for (int[] arr : resultList) {
                    // 输出结果列表中的所有数组
                    System.out.println(Arrays.toString(arr));
                    // 向表格添加一行数据
                    Object[] row = new Object[arr.length];
                    for (int i = 0; i < arr.length; i++) {
                        row[i] = arr[i];
                    }
                    model.addRow(row);
                }

                //对象持久化，创建对象存储算法输入参数、算法执行过程、算法执行结果
                inputStr.set(text);
                sortList.set(resultList);
                //获取结果数组，删掉序号
                int[] lastArray = resultList.get(resultList.size() - 1);
                int[] newLastArray = new int[lastArray.length - 1];
                System.arraycopy(lastArray, 1, newLastArray, 0, lastArray.length - 1);
                // 创建对象存储算法执行结果
                resultArr.set(newLastArray);

                Persistence per = new Persistence();
                List<InputStr> historyStr = new ArrayList<>();
                historyStr = per.loadFromFileInputStr();
                historyStr.add(inputStr);
                per.writeToFileInputStr(historyStr);

                List<SortList> historyList = new ArrayList<>();
                historyList = per.loadFromFileSortList();
                historyList.add(sortList);
                per.writeToFileSortList(historyList);

                List<ResultArr> historyArr = new ArrayList<>();
                historyArr = per.loadFromFileResultArr();
                historyArr.add(resultArr);
                per.writeToFileResultArr(historyArr);
            }
        });

        // 为绘制柱状图按钮添加点击事件
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //新建一个窗口用来绘制柱状图
                BarChart window = new BarChart();
                //将每个数组中的交换次数序号删掉
                Sort sort = new Sort();
                List<int[]> newResultList = sort.getNewResultList(resultList);

                //将数组可视化
                for(int i = 0;i<newResultList.size();i++){
                    window.open(newResultList.get(i));
                }
            }
        });


        //展示历史记录
        btnShowHistory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //获取历史记录
                Persistence per = new Persistence();
                List<InputStr> historyStr = new ArrayList<>();
                //存放历史字符串的列表
                historyStr = per.loadFromFileInputStr();

//                for(int i = 0; i< historyStr.size();i++){
//                    System.out.println(historyStr.get(i).get());
//                }

                //将输入字符串转化成输入数组
                if (historyStr.size() > 0) {
                    String[] InputArr = new String[historyStr.size()];
                    for(int i=0; i<historyStr.size();i++){
                        InputArr[i] = historyStr.get(i).get();
                    }

                    historyComboBox.setModel(new DefaultComboBoxModel<String>(InputArr));
                    JOptionPane.showMessageDialog(frame, historyComboBox, "历史记录", JOptionPane.PLAIN_MESSAGE);

                    historyComboBox.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String selectedText = (String) historyComboBox.getSelectedItem();
                            //将输入框内容设置为选中字符串
                            textField.setText(selectedText);

                            //本次是第几个记录
                            int index = -1;
                            for (int i = 0; i < InputArr.length; i++) {
                                if (InputArr[i].equals(selectedText)) {
                                    index = i;
                                    break;
                                }
                            }

                            //存放排序列表的列表
                            List<SortList> historyList = new ArrayList<>();
                            historyList = per.loadFromFileSortList();
                            List<int[]> selectedList = new ArrayList<>();
                            selectedList = historyList.get(index).get();
                            //用于绘制柱状图
                            resultList = selectedList;

                            //存放排序结果
                            List<ResultArr> historyArr = new ArrayList<>();
                            historyArr = per.loadFromFileResultArr();
                            int[] selectedArr = new int[selectedText.split("，").length];
                            selectedArr = historyArr.get(index).get();


                            // 显示表格
                            model.setRowCount(0);
                            model.setColumnCount(0);
                            model.addColumn("排序交换次数");
                            for (int i = 0; i < selectedArr.length; i++) {
                                model.addColumn("第" + (i + 1) + "个数");
                            }
                            // 为表格添加排序过程
                            for (int[] arr : selectedList) {
                                // 输出结果列表中的所有数组
                                System.out.println(Arrays.toString(arr));
                                // 向表格添加一行数据
                                Object[] row = new Object[arr.length];
                                for (int i = 0; i < arr.length; i++) {
                                    row[i] = arr[i];
                                }
                                model.addRow(row);
                            }
                        }
                    });
                }
                else {
                    JOptionPane.showMessageDialog(frame, "暂无记录!");
                }

//                if (historyStr.size() > 0) {
//                    //创建数组存储字符串
//                    String[] historyTextArray = new String[historyList.size()];
//                    for(int i = 0; i< historyList.size();i++){
//                        historyTextArray[i] = historyList.get(i).getStr().get();
//                    }
//                    historyComboBox.setModel(new DefaultComboBoxModel<String>(historyTextArray));
//                    JOptionPane.showMessageDialog(frame, historyComboBox, "历史记录", JOptionPane.PLAIN_MESSAGE);
//
//                    historyComboBox.addActionListener(new ActionListener() {
//                        public void actionPerformed(ActionEvent e) {
//                            String selectedText = (String) historyComboBox.getSelectedItem();
//                            //将输入框内容设置为选中字符串
//                            textField.setText(selectedText);
//                            //查找选中字符串对应的列表和数组
//                            List<int[]> selectedList = new ArrayList<>();
//                            int[] selectedArr = new int[selectedText.split("，").length];
//                            for(SortData sd : historyList){
//                                if (sd.getStr().equals(selectedText)){
//                                    selectedList = sd.getList().get();
//                                    selectedArr = sd.getArr().get();
//                                }
//                            }
//                            resultList = selectedList;
//
//                            // 显示表格
//                            model.setRowCount(0);
//                            model.setColumnCount(0);
//                            model.addColumn("排序交换次数");
//                            for (int i = 0; i < selectedArr.length; i++) {
//                                model.addColumn("第" + (i + 1) +"个数");
//                            }
//                            // 为表格添加排序过程
//                            for (int[] arr : selectedList) {
//                                // 输出结果列表中的所有数组
//                                System.out.println(Arrays.toString(arr));
//                                // 向表格添加一行数据
//                                Object[] row = new Object[arr.length];
//                                for (int i = 0; i < arr.length; i++) {
//                                    row[i] = arr[i];
//                                }
//                                model.addRow(row);
//                            }
//                        }
//                    });
//                }
//                else {
//                    JOptionPane.showMessageDialog(frame, "暂无记录!");
//                }
            }
        });


        //选择排序按钮的事件监听器
        selectionSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 清空resultList和数据表
                resultList.clear();
                model.setRowCount(0);
                model.setColumnCount(0);
                //获取用户输入的字符串
                String text = textField.getText();

                // 通过Sort类，获取原始数组，及包含排序交换过程的列表
                Sort sort = new Sort();
                int[] numArray = sort.getArr(text);
                resultList = sort.getSelectionList(text);

                // 显示排序过程中数组变化
                // 为表格添加表头
                model.addColumn("选择排序交换次数");
                for (int i = 0; i < numArray.length; i++) {
                    model.addColumn("第" + (i + 1) +"个数");
                }
                // 为表格添加排序过程
                for (int[] arr : resultList) {
                    // 输出结果列表中的所有数组
                    System.out.println(Arrays.toString(arr));
                    // 向表格添加一行数据
                    Object[] row = new Object[arr.length];
                    for (int i = 0; i < arr.length; i++) {
                        row[i] = arr[i];
                    }
                    model.addRow(row);
                }


                //对象持久化，创建对象存储算法输入参数、算法执行过程、算法执行结果
                inputStr.set(text);
                sortList.set(resultList);
                //获取结果数组，删掉序号
                int[] lastArray = resultList.get(resultList.size() - 1);
                int[] newLastArray = new int[lastArray.length - 1];
                System.arraycopy(lastArray, 1, newLastArray, 0, lastArray.length - 1);
                // 创建对象存储算法执行结果
                resultArr.set(newLastArray);

                Persistence per = new Persistence();
                List<InputStr> historyStr = new ArrayList<>();
                historyStr = per.loadFromFileInputStr();
                historyStr.add(inputStr);
                per.writeToFileInputStr(historyStr);

                List<SortList> historyList = new ArrayList<>();
                historyList = per.loadFromFileSortList();
                historyList.add(sortList);
                per.writeToFileSortList(historyList);

                List<ResultArr> historyArr = new ArrayList<>();
                historyArr = per.loadFromFileResultArr();
                historyArr.add(resultArr);
                per.writeToFileResultArr(historyArr);
            }
        });


        //插入排序按钮的事件监听器
        insertionSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 清空resultList和数据表
                resultList.clear();
                model.setRowCount(0);
                model.setColumnCount(0);
                //获取用户输入的字符串
                String text = textField.getText();

                // 通过Sort类，获取原始数组，及包含排序交换过程的列表
                Sort sort = new Sort();
                int[] numArray = sort.getArr(text);
                resultList = sort.getInsertionList(text);

                // 显示排序过程中数组变化
                // 为表格添加表头
                model.addColumn("插入排序交换次数");
                for (int i = 0; i < numArray.length; i++) {
                    model.addColumn("第" + (i + 1) +"个数");
                }
                // 为表格添加排序过程
                for (int[] arr : resultList) {
                    // 输出结果列表中的所有数组
                    System.out.println(Arrays.toString(arr));
                    // 向表格添加一行数据
                    Object[] row = new Object[arr.length];
                    for (int i = 0; i < arr.length; i++) {
                        row[i] = arr[i];
                    }
                    model.addRow(row);
                }

                //对象持久化，创建对象存储算法输入参数、算法执行过程、算法执行结果
                inputStr.set(text);
                sortList.set(resultList);
                //获取结果数组，删掉序号
                int[] lastArray = resultList.get(resultList.size() - 1);
                int[] newLastArray = new int[lastArray.length - 1];
                System.arraycopy(lastArray, 1, newLastArray, 0, lastArray.length - 1);
                // 创建对象存储算法执行结果
                resultArr.set(newLastArray);

                Persistence per = new Persistence();
                List<InputStr> historyStr = new ArrayList<>();
                historyStr = per.loadFromFileInputStr();
                historyStr.add(inputStr);
                per.writeToFileInputStr(historyStr);

                List<SortList> historyList = new ArrayList<>();
                historyList = per.loadFromFileSortList();
                historyList.add(sortList);
                per.writeToFileSortList(historyList);

                List<ResultArr> historyArr = new ArrayList<>();
                historyArr = per.loadFromFileResultArr();
                historyArr.add(resultArr);
                per.writeToFileResultArr(historyArr);
            }
        });
    }

    public static void main(String[] args) {
        new Main();
    }
}
