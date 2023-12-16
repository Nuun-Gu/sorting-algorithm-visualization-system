package org.example.controller;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sort {

    //将字符串分割成数组
    public int[] getArr(String text){
        String[] strArray = text.split("，");
        //得到数字序列
        int[] numArray = new int[strArray.length];
        for (int i = 0; i < strArray.length; i++) {
            numArray[i] = Integer.parseInt(strArray[i]);
        }
        return numArray;
    }

    //将初始数组加上序号，加入列表
    public List<int[]> getInitialList(int[] numArray) {
        //创建存放数组的列表
        List<int[]> initialList = new ArrayList<>();
        // 在numArray第0个位置插入新的数字
        int[] numArrayTimes = new int[numArray.length + 1];
        System.arraycopy(numArray, 0, numArrayTimes, 1, numArray.length);
        numArrayTimes[0] = 0;
        //将原数组添加到列表
        initialList.add(numArrayTimes);
        return initialList;
    }

    //将每个数组中的交换次数序号删掉
    public List<int[]> getNewResultList(List<int[]> resultList){
        List<int[]> newResultList = new ArrayList<>();
        for (int[] arr : resultList) {
            int[] newArr = new int[arr.length - 1];
            for (int i = 1; i < arr.length; i++) {
                newArr[i - 1] = arr[i];
            }
            newResultList.add(newArr);
        }
        return newResultList;
    }


    //冒泡排序
    public List<int[]> getBubbleList(String text){
        // 通过InputProcess类，处理输入的字符串，获取原始数组
        int[] numArray = getArr(text);

        //创建列表
        List<int[]> resultList = getInitialList(numArray);
        //开始排序
        //创建排序交换标志
        boolean swapped = true;
        //标记排序交换次数
        int sortTime = 1;
        while (swapped) {
            swapped = false;
            for (int i = 0; i < numArray.length - 1; i++) {
                if (numArray[i] > numArray[i + 1]) {
                    //交换数字
                    int temp = numArray[i];
                    numArray[i] = numArray[i + 1];
                    numArray[i + 1] = temp;
                    //本轮进行了交换，所以还需进行下一轮排序循环
                    swapped = true;
                    //在数组第0个位置插入新的数字
                    int[] newArr = new int[numArray.length + 1];
                    System.arraycopy(numArray, 0, newArr, 1, numArray.length);
                    newArr[0] = sortTime;
                    sortTime++;
                    //将数组添加到列表
                    resultList.add(newArr);
//                            resultList.add(Arrays.copyOf(numArray, numArray.length));
                }
            }
        }
        return resultList;
    }

    //选择排序
    public List<int[]> getSelectionList(String text){
        // 通过InputProcess类，处理输入的字符串，获取原始数组
        int[] numArray = getArr(text);
        //创建列表
        List<int[]> resultList = getInitialList(numArray);
        //开始排序
        //标记排序交换次数
        int sortTime = 1;
        //开始排序
        for (int i = 0; i < numArray.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < numArray.length; j++) {
                if (numArray[j] < numArray[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                // 交换数组中的元素
                int temp = numArray[i];
                numArray[i] = numArray[minIndex];
                numArray[minIndex] = temp;
                //在数组第0个位置插入新的数字
                int[] newArr = new int[numArray.length + 1];
                System.arraycopy(numArray, 0, newArr, 1, numArray.length);
                newArr[0] = sortTime;
                sortTime++;
                //将数组添加到列表
                resultList.add(newArr);
            }
        }
        return resultList;
    }

    //插入排序
    public List<int[]> getInsertionList(String text){
        // 通过InputProcess类，处理输入的字符串，获取原始数组
        int[] numArray = getArr(text);
        //创建列表
        List<int[]> resultList = getInitialList(numArray);

        //标记排序交换次数
        int sortTime = 1;

        for (int i = 1; i < numArray.length; i++) {
            int key = numArray[i];
            int j = i - 1;
            while (j >= 0 && numArray[j] > key) {
                numArray[j + 1] = numArray[j];
                j--;
            }
            numArray[j + 1] = key;
            //在数组第0个位置插入新的数字
            int[] newArr = new int[numArray.length + 1];
            System.arraycopy(numArray, 0, newArr, 1, numArray.length);
            newArr[0] = sortTime;
            sortTime++;
            //将数组添加到列表
            resultList.add(newArr);
        }
        return resultList;

    }
}