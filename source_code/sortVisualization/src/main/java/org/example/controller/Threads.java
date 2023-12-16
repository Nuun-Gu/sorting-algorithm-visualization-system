package org.example.controller;


import org.example.view.Main;

import java.util.Scanner;

public class Threads {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input;

        // 循环等待用户输入
        while (true) {
            System.out.print("请输入命令: ");
            input = scanner.nextLine();

            // 如果用户输入 "quit"，则退出程序
            if (input.equals("quit")) {
                break;
            }

            // 如果用户输入 "aaa"，则启动一个新线程
            if (input.equals("aaa")) {
                // 创建一个新线程
                Thread t = new Thread(new Runnable() {
                    public void run() {
                        // 启动一个新的界面
                        new Main();
                    }
                });

                // 启动线程
                t.start();
            }
        }

        // 关闭扫描器
        scanner.close();
    }
}
