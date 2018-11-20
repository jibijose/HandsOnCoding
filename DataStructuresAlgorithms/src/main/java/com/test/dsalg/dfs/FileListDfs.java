package com.test.dsalg.dfs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileListDfs {

    private File root;

    FileListDfs(File root) {
        this.root = root;
    }

    public static void main(String[] args) {
        FileListDfs fileListDfs = new FileListDfs(new File("C:\\Intel"));
        List<File> listFiles = fileListDfs.listAllFiles();

        listFiles.forEach(file -> System.out.println(file.getAbsoluteFile()));
    }

    public List<File> listAllFiles() {
        List<File> listFiles = new ArrayList<>();
        listAllFilesUtil(root, listFiles);
        return listFiles;
    }

    private void listAllFilesUtil(File root, List<File> listFiles) {
        if (root.isDirectory()) {
            File[] listOfFilesAndDirectory = root.listFiles();
            if (listOfFilesAndDirectory != null) {
                for (File file : listOfFilesAndDirectory) {
                    listAllFilesUtil(file, listFiles);
                }
            }
        } else {
            listFiles.add(root);
        }
    }

}
