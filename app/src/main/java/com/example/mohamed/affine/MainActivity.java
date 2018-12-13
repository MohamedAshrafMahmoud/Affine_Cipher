package com.example.mohamed.affine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;

public class MainActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

    EditText plaintext, M, Key, N, ciphertext, newlanguage;
    TextView new_plaintext, new_ciphertext;
    Spinner spinner;

    String[] styles = {"a  2  z", "A  2  Z", "A a", "_ A a", "new language"};


    //             0    1    2    3    4    5    6    7    8    9   10   11   12   13   14   15   16   17   18   19   20   21   22   23   24   25   26   27   28   29   30   31   32   33   34   35   36   37   38   39   40   41   42   43   44   45   46   47   48   49   50   51  52
    char sma[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    char cap[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    char cAs[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    char spA[] = {' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    char newLang[];

    char newArr[];

    BigInteger Mminus, res, n;

    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        spinner.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, styles);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);

    }

    private void init() {
        plaintext = (EditText) findViewById(R.id.plaintext);
        Key = (EditText) findViewById(R.id.Key);
        M = (EditText) findViewById(R.id.M);
        N = (EditText) findViewById(R.id.N);
        ciphertext = (EditText) findViewById(R.id.ciphertext);
        newlanguage = (EditText) findViewById(R.id.newlang);
        new_plaintext = (TextView) findViewById(R.id.newplaintext);
        new_ciphertext = (TextView) findViewById(R.id.newcipher);
        spinner = (Spinner) findViewById(R.id.spinner1);
    }


    public void Encrypt(View view) {

        if (flag == 1) {
            newLang = new char[newlanguage.getText().toString().length()];
            newLang = newlanguage.getText().toString().toCharArray();

        }

        int x = getGCD(Integer.parseInt(M.getText().toString()), Integer.parseInt(N.getText().toString()));
        if (x != 1) {
            Toast.makeText(this, "gcd btween M and N not equal 1", Toast.LENGTH_SHORT).show();
        } else {
            Encrypt(plaintext.getText().toString());

        }

    }

    public void Deccrypt(View view) {

        if (flag == 1) {
            newLang = new char[newlanguage.getText().toString().length()];
            newLang = newlanguage.getText().toString().toCharArray();

        }

        int x = getGCD(Integer.parseInt(M.getText().toString()), Integer.parseInt(N.getText().toString()));
        if (x != 1) {
            Toast.makeText(this, "gcd btween M and N not equal 1", Toast.LENGTH_SHORT).show();
        } else {
            Deccrypt(ciphertext.getText().toString());

        }


    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        if (styles[position] == "a  2  z") {
            newArr = sma;
            flag = 0;
        } else if (styles[position] == "A  2  Z") {
            newArr = cap;
            flag = 0;
        } else if (styles[position] == "A a") {
            newArr = cAs;
            flag = 0;
        } else if (styles[position] == "_ A a") {
            newArr = spA;
            flag = 0;
        } else if (styles[position] == "new language") {
            flag = 1;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
    }


    // encryption method
    private void Encrypt(String msg) {

        //to get chars from the msg
        char[] arr = msg.toCharArray();

        String new_msg = "";


        for (int x = 0; x < arr.length; x++) {
            for (int p = 0; p < newArr.length; p++) {

                if (arr[x] == newArr[p]) {

                    new_msg = new_msg + newArr[(((Integer.parseInt(M.getText().toString()) * p) + Integer.parseInt(Key.getText().toString())) % Integer.parseInt(N.getText().toString()))];
                } else {
                    Toast.makeText(this, "your plain text is not from your language", Toast.LENGTH_SHORT).show();
                    new_ciphertext.setText("");
                    break;
                }

                new_ciphertext.setText(new_msg);
            }
        }

    }


    // decryption method
    private void Deccrypt(String msg) {

        char[] arr = msg.toCharArray();

        String new_msg = "";

        int new_index = 0;
        int new_met = 0;

        Mminus = new BigInteger(M.getText().toString());
        n = new BigInteger(N.getText().toString());
        res = Mminus.modInverse(n);


        for (int x = 0; x < arr.length; x++) {
            for (int c = 0; c < newArr.length; c++) {

                if (arr[x] == newArr[c]) {

                    new_index = c - Integer.parseInt(Key.getText().toString());
                    new_met = new_index * res.intValue();

                    while (new_met < 0) {
                        new_met += Integer.parseInt(N.getText().toString());
                    }

                    new_msg = new_msg + newArr[new_met % Integer.parseInt(N.getText().toString())];
                    new_index = 0;
                    new_met = 0;
                }
                new_plaintext.setText(new_msg);
            }
        }

    }


    static int getGCD(int x, int y) {
        BigInteger x2 = BigInteger.valueOf(x);
        BigInteger y2 = BigInteger.valueOf(y);
        BigInteger gcd = x2.gcd(y2);
        return gcd.intValue();
    }


}
