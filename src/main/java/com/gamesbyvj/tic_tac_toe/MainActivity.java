package com.gamesbyvj.tic_tac_toe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int toggleNumber;
    private String numberOfPlayers;
    private Random rand;
    // if toggleCharacter is 0 then the move is 'X' or the move is 'O'
    private int[] arr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        rand = new Random();
        numberOfPlayers = intent.getStringExtra("numberOfPlayers");
        toggleNumber = 0;
        arr = new int[9];
        putIDs();
        buttonsText();
        Log.v("testing","mainFunc");
        if(numberOfPlayers.equals("one")){
            Toast.makeText(getApplicationContext(), "You go first!",
                    Toast.LENGTH_LONG).show();
        }
    }
    private void putIDs(){
        arr[0] = R.id.button_1;
        arr[1] = R.id.button_2;
        arr[2] = R.id.button_3;
        arr[3] = R.id.button_4;
        arr[4] = R.id.button_5;
        arr[5] = R.id.button_6;
        arr[6] = R.id.button_7;
        arr[7] = R.id.button_8;
        arr[8] = R.id.button_9;

    }
    private void putCharacter(int id){
        Button butt = (Button) findViewById(id);
        if(toggleNumber == 0){
            butt.setText("X");
            toggleNumber = 1;
        }
        else{
            butt.setText("O");
            toggleNumber = 0;
        }
    }
    private boolean checkWinner(int id){
        Log.v("testing","checkwinner");
        int x = id;
        int position=0;
        for(int i=0;i<arr.length;i++) {
            if (x == arr[i]) {
                position = i;
                break;
            }
        }
        return checkMatch(position);
    }
    private boolean checkMatch(int position){
        Log.v("testing","checkmatch");
        boolean result = false;
        if(position==0){
            return checkLeftRight(position, 1) || checkTopBottom(position, 1) || check145Degree(position, 1);
        }
        else if(position==1){
            return checkLeftRight(position, 2) || checkTopBottom(position, 1);
        }
        else if(position==2){
            return checkLeftRight(position, 3) || checkTopBottom(position, 1) || check45Degree(position, 3);
        }
        else if(position==3){
            return checkLeftRight(position, 1) || checkTopBottom(position, 2);
        }
        else if(position==4){
            return checkLeftRight(position, 2) || checkTopBottom(position, 2) || check145Degree(position, 2) || check45Degree(position, 2);
        }
        else if(position==5){
            return checkLeftRight(position, 3) || checkTopBottom(position, 2);
        }
        else if(position==6){
            return checkLeftRight(position, 1) || checkTopBottom(position, 3) || check45Degree(position, 1);
        }
        else if(position==7){
            return checkLeftRight(position, 2) || checkTopBottom(position, 3);
        }
        else{
            return checkLeftRight(position, 3) || checkTopBottom(position, 3) || check145Degree(position, 3);
        }
    }
    /**
     * first parameter corresponds to the clicked button
     * second parameter corresponds to position of the clicked button w.r.t the given function
     * for example for checkLeftRight, buttons in first row corresponds to i=1,
     * similarly for second and third row.*/
    private boolean checkLeftRight(int position, int i) {
        Button button1 = (Button) findViewById(arr[position+(1-i)]);
        String str1 = button1.getText().toString();
        Button button2 = (Button) findViewById(arr[position+(2-i)]);
        String str2 = button2.getText().toString();
        Button button3 = (Button) findViewById(arr[position+(3-i)]);
        String str3 = button3.getText().toString();
        if(str1.equals(" ") || str2.equals(" ") || str3.equals(" "))
            return false;
        //Log.v("testing","checkLeftR");
        if(toggleNumber==1)
            return str1.equals("X") && str2.equals("X") && str3.equals("X");
        else
            return str1.equals("O") && str2.equals("O") && str3.equals("O");
    }
    private boolean checkTopBottom(int position, int i) {

        Button button1 = (Button) findViewById(arr[position+(1-i)*3]);
        String str1 = button1.getText().toString();
        Button button2 = (Button) findViewById(arr[position+(2-i)*3]);
        String str2 = button2.getText().toString();
        Button button3 = (Button) findViewById(arr[position+(3-i)*3]);
        String str3 = button3.getText().toString();
        //Log.v("testing", String.valueOf(str2.equals(" ")));
        if(str1.equals(" ") || str2.equals(" ") || str3.equals(" "))
            return false;
        //Log.v("testing","checkTopB");
        return str1.equals(str2) && str2.equals(str3);
    }
    private boolean check145Degree(int position, int i) {
        Button button1 = (Button) findViewById(arr[position+(1-i)*4]);
        String str1 = button1.getText().toString();
        Button button2 = (Button) findViewById(arr[position+(2-i)*4]);
        String str2 = button2.getText().toString();
        Button button3 = (Button) findViewById(arr[position+(3-i)*4]);
        String str3 = button3.getText().toString();
        if(str1.equals(" ") || str2.equals(" ") || str3.equals(" "))
            return false;
        //Log.v("testing","check145D");
        return str1.equals(str2) && str2.equals(str3);
    }
    private boolean check45Degree(int position, int i) {

        Button button1 = (Button) findViewById(arr[position+(1-i)*-2]);
        String str1 = button1.getText().toString();
        Button button2 = (Button) findViewById(arr[position+(2-i)*-2]);
        String str2 = button2.getText().toString();
        Button button3 = (Button) findViewById(arr[position+(3-i)*-2]);
        String str3 = button3.getText().toString();
        //Log.v("testing","check45D");

        return str1.equals(str2) && str2.equals(str3);
    }
    private void buttonsClicability(boolean bool){
        for(int i=0;i<arr.length;i++){
            Button button = (Button) findViewById(arr[i]);
            button.setClickable(bool);
        }
    }
    private void buttonsText(){
        for(int i=0;i<arr.length;i++){
            Button button = (Button) findViewById(arr[i]);
            button.setText(" ");
        }
    }
    private int getNewID(){
        while(true){
            int tempID = rand.nextInt(9);
            Button button = (Button) findViewById(arr[tempID]);
            String str = button.getText().toString();
            if(str.equals(" "))
                return arr[tempID];
        }
    }
    private boolean allClicked(){
        for(int i=0;i<arr.length;i++){
            Button button = (Button) findViewById(arr[i]);
            String str = button.getText().toString();
            if(str.equals(" "))
                return false;
        }
        return true;
    }
    public void playGame(View view) {
        int flag = 0;
        Log.v("testing","playGame");
        int id = view.getId();
        Button butt = (Button)findViewById(id);
        butt.setClickable(false);
        putCharacter(id);
        if(numberOfPlayers.equals("one")){
            Log.v("testing","true");
        }
        else{
            Log.v("testing","false");
        }
        Log.v("testing","before method of one player");
        if(checkWinner(id) && numberOfPlayers.equals("two")){ //
            Log.v("testing","two players");
            if(toggleNumber==1){
                TextView gameResult = (TextView)findViewById(R.id.result_id);
                gameResult.setText("Player 1 has won!!!");
            }
            else{
                TextView gameResult = (TextView)findViewById(R.id.result_id);
                gameResult.setText("Player 2 has won!!!");
            }
            buttonsClicability(false);
        }
//        else if(allClicked()){
//            Toast.makeText(getApplicationContext(), "It's a tie!!!",
//                    Toast.LENGTH_LONG).show();
//        }
        else if(numberOfPlayers.equals("one")){
            Log.v("testing","one player");
            if(checkWinner(id)){
                TextView gameResult = (TextView)findViewById(R.id.result_id);
                gameResult.setText("You won!!!");
                buttonsClicability(false);
                flag = 1;
            }
            if(!allClicked()) {
                int newID = getNewID();
                Button button = (Button) findViewById(newID);
                button.setClickable(false);
                putCharacter(newID);
                if (checkWinner(newID)) {
                    TextView gameResult = (TextView) findViewById(R.id.result_id);
                    gameResult.setText("Computer won!!!");
                    buttonsClicability(false);
                    flag = 1;
                }
            }
            if(flag == 0 && allClicked()){
                Toast.makeText(getApplicationContext(), "It's a tie!!!",
                        Toast.LENGTH_LONG).show();
            }
        }
        if(flag == 0 && allClicked()){
            Toast.makeText(getApplicationContext(), "It's a tie!!!",
                    Toast.LENGTH_LONG).show();
        }
    }
    public void newGame(View view) {
        toggleNumber = 0;
        buttonsClicability(true);
        buttonsText();
        TextView gameResult = (TextView)findViewById(R.id.result_id);
        gameResult.setText("");
    }

    public void goToMainMenu(View view) {
        finish();
    }
}
