package com.test.dsalg.dfs;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FileListBfs {

    private File root;

    FileListBfs(File root) {
        this.root = root;
    }

    public static void main(String[] args) {
        FileListBfs fileListDfs = new FileListBfs(new File("C:\\Intel"));
        List<File> listFiles = fileListDfs.listAllFiles();

        listFiles.forEach(file -> System.out.println(file.getAbsoluteFile()));
    }

    public List<File> listAllFiles() {
        List<File> listFiles = new ArrayList<>();
        Queue<File> queueDirs = new LinkedList<>();
        queueDirs.add(root);
        while (!queueDirs.isEmpty()) {
            File dir = queueDirs.poll();
            File[] listOfFilesAndDirectory = dir.listFiles();
            if (listOfFilesAndDirectory != null) {
                for (File file : listOfFilesAndDirectory) {
                    if (file.isDirectory()) {
                        queueDirs.add(file);
                    } else {
                        listFiles.add(file);
                    }
                }
            }
        }
        return listFiles;
    }

}
