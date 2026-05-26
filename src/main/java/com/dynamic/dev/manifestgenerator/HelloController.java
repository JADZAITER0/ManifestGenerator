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
    protected void onHelloButtonClick() throws NoSuchAlgorithmException, IOException {
        welcomeText.setText("Welcome to JavaFX Application!");
        Stack<File> sub_folder = new Stack<>();
        String TARGET_DIRECTORY = "C:\\Users\\HP\\Desktop\\university\\machine vision\\Vision8";
        Path path = Path.of(TARGET_DIRECTORY);
        boolean INCLUDE_HIDDEN_FILES = false;

        File root = new File(TARGET_DIRECTORY);

        if (!root.exists() || !root.isDirectory())
            return;

        sub_folder.push(root);


        //TODO NEED COMPLEXITY IMPROVEMENT I THINK
        while (!sub_folder.isEmpty()){
            File temp_file = sub_folder.pop();
            for (File file : temp_file.listFiles()){
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
                System.out.println("PATH: " + file.getPath().replace(TARGET_DIRECTORY,""));
                System.out.println("DIGEST: " + HexFormat.of().formatHex(result));
                System.out.println("SIZE: " + file.length());
                System.out.println("}");
                System.out.println();

                md.reset();


            }
        }


    }

    @FXML
    protected void onHelloButtonClick2(String TARGET_DIRECTORY, Boolean IsHidden) throws NoSuchAlgorithmException, IOException {
        welcomeText.setText("Welcome to JavaFX Application!");
        Stack<File> sub_folder = new Stack<>();
        Path path = Path.of(TARGET_DIRECTORY);
        boolean INCLUDE_HIDDEN_FILES = false;

        File root = new File(TARGET_DIRECTORY);

        if (!root.exists() || !root.isDirectory())
            return;

        sub_folder.push(root);


        //TODO NEED COMPLEXITY IMPROVEMENT I THINK
        while (!sub_folder.isEmpty()){
            File temp_file = sub_folder.pop();
            for (File file : temp_file.listFiles()){
                System.out.println(file.toPath());
                if (!INCLUDE_HIDDEN_FILES){
                    if (IsHidden) continue;
                }

                if (file.isDirectory()){
                    sub_folder.push(file);
                }

                byte[] read_file_result = Files.readAllBytes(file.toPath());

                byte[] result = md.digest(read_file_result);

                System.out.println("{");
                System.out.println("PATH: " + file.getPath().replace(TARGET_DIRECTORY,""));
                System.out.println("DIGEST: " + HexFormat.of().formatHex(result));
                System.out.println("SIZE: " + file.length());
                System.out.println("}");
                System.out.println();

                md.reset();


            }
        }


    }
}
