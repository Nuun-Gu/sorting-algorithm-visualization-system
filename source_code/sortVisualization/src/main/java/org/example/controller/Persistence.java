package org.example.controller;

import org.example.model.InputStr;
import org.example.model.ResultArr;
import org.example.model.SortList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Persistence {
    //存储算法输入
    public void writeToFileInputStr(List<InputStr> historyStr) {
        try {
            FileOutputStream fos = new FileOutputStream("inputStr.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(historyStr);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<InputStr> loadFromFileInputStr() {
        try {
            FileInputStream fis = new FileInputStream("inputStr.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            List<InputStr> historyStr = (List<InputStr>) ois.readObject();
            ois.close();
            fis.close();
            return historyStr;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("未找到文件");
            return null;
        }
    }

    //算法过程
    public void writeToFileSortList(List<SortList> historyList) {
        try {
            FileOutputStream fos = new FileOutputStream("inputList.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(historyList);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<SortList> loadFromFileSortList() {
        try {
            FileInputStream fis = new FileInputStream("inputList.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            List<SortList> historyList = (List<SortList>) ois.readObject();
            ois.close();
            fis.close();
            return historyList;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("未找到文件");
            return null;
        }
    }

    //算法结果
    public void writeToFileResultArr(List<ResultArr> historyArr) {
        try {
            FileOutputStream fos = new FileOutputStream("inputArr.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(historyArr);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<ResultArr> loadFromFileResultArr() {
        try {
            FileInputStream fis = new FileInputStream("inputArr.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            List<ResultArr> historyArr = (List<ResultArr>) ois.readObject();
            ois.close();
            fis.close();
            return historyArr;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("未找到文件");
            return null;
        }
    }
}

//public class Persistence {
//    public void writeToFile(ArrayList<SortData> list) {
//        try {
//            FileOutputStream fos = new FileOutputStream("save.txt");
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            oos.writeObject(list);
//            oos.close();
//            fos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public ArrayList<SortData> loadFromFile() {
//        try {
//            FileInputStream fis = new FileInputStream("save.txt");
//            ObjectInputStream ois = new ObjectInputStream(fis);
//            ArrayList<SortData> historyList = (ArrayList<SortData>) ois.readObject();
//            ois.close();
//            fis.close();
//            return historyList;
//        } catch (IOException | ClassNotFoundException e) {
//            System.out.println("未找到文件");
//            return null;
//        }
//    }
//}
