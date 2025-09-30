package klase;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Controller {
    @FXML
    private TextField ivedimoID;
    @FXML
    private ListView<String> laukasID;
    @FXML
    private Label allid, doneid,undoneid;

    @FXML
    protected void addtask(){

        if(ivedimoID.getText().isEmpty()){

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Tusčias Įvedimo laukas");
            alert.showAndWait();

        }
        else {
            int total = laukasID.getItems().size() + 1;
            String text = ivedimoID.getText();


            laukasID.getItems().add(text);
            ivedimoID.setText("");
            allid.setText("Total: " + total);
            updatedoneid();
            int kiekis = updatedoneid();
            updateUndone(kiekis, total);
            save();
        }
    }
    @FXML
    protected void deletetask(){
        String text = laukasID.getSelectionModel().getSelectedItem();
        laukasID.getItems().remove(text);
        updateTotal();
        updatedoneid();
        int kiekis = updatedoneid();
        int total = laukasID.getItems().size();
        updateUndone(kiekis, total);
        save();
    }
    @FXML
    protected void deleteall(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Ar tikrai norite ištrinti visas užduotis?");
        alert.showAndWait();
        if(alert.getResult() == ButtonType.OK){
            laukasID.getItems().clear();
            updateTotal();
            updatedoneid();
            int kiekis = updatedoneid();
            int total = laukasID.getItems().size();
            updateUndone(kiekis, total);
            save();
        }
    }

    @FXML
    protected void edit(){
        String text = laukasID.getSelectionModel().getSelectedItem();
        ivedimoID.setText(text);
        laukasID.getItems().remove(text);
        updatedoneid();
        updateTotal();
        int kiekis = updatedoneid();
        int total = laukasID.getItems().size();
        updateUndone(kiekis, total);
        save();

    }
    @FXML
    protected void save(){
        try {
            FileWriter writer = new FileWriter("tasks.txt");

            for (Object item : laukasID.getItems()) {
                writer.write(item.toString() + "\n");
            }

            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void initialize() {
        try {
            laukasID.getItems().clear();


            List<String> lines = Files.readAllLines(Path.of("tasks.txt"));


            laukasID.getItems().addAll(lines);
            allid.setText("Total: "+laukasID.getItems().size());
            updatedoneid();
            int kiekis = updatedoneid();
            int total = laukasID.getItems().size();
            updateUndone(kiekis, total);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 @FXML
    protected void done(){
        String text = laukasID.getSelectionModel().getSelectedItem();
        int index = laukasID.getSelectionModel().getSelectedIndex();
        if(laukasID.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nepasirinkta Užduotis");
            alert.showAndWait();


        }
        else {
            laukasID.getItems().set(index, text + " ✔");
            updatedoneid();
            int kiekis = updatedoneid();
            int total = laukasID.getItems().size();
            updateUndone(kiekis, total);


            save();
        }


    }
    @FXML
    protected int updatedoneid(){
        int kiekis = 0;
        for(String item : laukasID.getItems()){
            if(item.contains(" ✔")){
                kiekis++;
            }
        }
        doneid.setText("Done: " + kiekis);
        save();
       return kiekis;


    }
    @FXML
    protected int updateTotal(){
        int total = laukasID.getItems().size();
        allid.setText("Total: " + total);
        save();
        return total;

    }
    @FXML
    protected void updateUndone(int kiekis,int total){
        String undone;
        undone = String.valueOf(total - kiekis);
        undoneid.setText("Undone: " +undone);


    }







}
