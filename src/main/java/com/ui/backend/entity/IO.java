package com.ui.backend.entity;
import java.io.*;

public class IO {
    /**
     * read file from disk
     * @param fileName
     * @return the content of that file
     * @throws IOException
     */
    public static String read(String fileName) throws IOException {
        File directory = new File("");// 参数为空
        String courseFile;
        courseFile = directory.getCanonicalPath();
        System.out.println(courseFile);

        try (
                FileReader fr = new FileReader(courseFile+"/" + fileName);
                BufferedReader br = new BufferedReader(fr)
        ) {
            StringBuilder sb = new StringBuilder();
            String temp = "";
            while ((temp = br.readLine()) != null) {
                sb.append(temp + "\n");
            }

            return sb.toString();
        }
    }

    /**
     * write to disk
     * @param fileName
     * @param str content of file
     * @throws IOException
     */
    protected static void write(String fileName, String str) throws IOException {
        File directory = new File("");// 参数为空
        String courseFile;
        courseFile = directory.getCanonicalPath();
        System.out.println(courseFile);


        File file = new File(courseFile +"/"+ fileName);
        PrintStream ps = new PrintStream(new FileOutputStream(file));
        ps.println(str);
        ps.close();
    }
}

