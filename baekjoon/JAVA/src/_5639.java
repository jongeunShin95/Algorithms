import java.awt.print.Pageable;
import java.io.*;
import java.util.*;

public class _5639 {
    static class Node {
        Node left;
        Node right;
        int data;
        Node(int data) {
            this.data = data;
        }
    }
    static StringBuilder sb = new StringBuilder();

    static void makeTree(Node root, Node child) {
        if (root.data > child.data) {
            if (root.left == null) root.left = child;
            else makeTree(root.left, child);
        } else if (root.data < child.data) {
            if (root.right == null) root.right = child;
            else makeTree(root.right, child);
        }
    }

    static void postPrint(Node root) {
        if (root == null) return;
        postPrint(root.left);
        postPrint(root.right);
        sb.append(root.data + "\n");
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        // 루트 노드 입력 받기
        Node root = new Node(Integer.parseInt(in.readLine()));

        // 노드 입력 받기
        String s;
        while ((s = in.readLine()) != null && s.length() != 0) {
            Node tempNode = new Node(Integer.parseInt(s));
            makeTree(root, tempNode);
        }

        postPrint(root);

        System.out.println(sb);
    }

}