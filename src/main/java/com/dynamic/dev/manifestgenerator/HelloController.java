package com.dynamic.dev.manifestgenerator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.Stack;


public class HelloController {

    private static  MessageDigest md;

    static {
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() throws IOException {
        welcomeText.setText("Welcome to JavaFX Application!");
        Stack<File> sub_folder = new Stack<>();
        String TARGET_DIRECTORY = "C:\\Users\\lenovo\\Downloads\\";
        boolean INCLUDE_HIDDEN_FILES = false;

        File root = new File(TARGET_DIRECTORY);

        if (!root.exists() || !root.isDirectory())
            return;

        sub_folder.push(root);

        System.out.println("Conflict????");
        //TODO NEED COMPLEXITY IMPROVEMENT I THINK
        while (!sub_folder.isEmpty()){
            File temp_files = sub_folder.pop();
            for (File file : temp_files.listFiles()){
                System.out.println(file.toPath());
                if (!INCLUDE_HIDDEN_FILES){
                    if (file.isHidden()) continue;
                }

                if (file.isDirectory()){
                    sub_folder.push(file);
                }

                byte[] read_file_result = Files.readAllBytes(file.toPath());

                byte[] result = md.digest(read_file_result);

                System.out.println("{");
                System.out.println("PATHS: " + file.getPath().replace(TARGET_DIRECTORY,""));
                System.out.println("DIGESTS: " + HexFormat.of().formatHex(result));
                System.out.println("SIZES: " + file.length());
                System.out.println("}");
                System.out.println();

                md.reset();


            }
        }


    }


}
