package kr.teamcadi.calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText = (EditText) findViewById(R.id.editText);
        final TextView result_tv = (TextView) findViewById(R.id.result_tv);
        Button btn0 = (Button) findViewById(R.id.btn0);
        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        Button btn3 = (Button) findViewById(R.id.btn3);
        Button btn4 = (Button) findViewById(R.id.btn4);
        Button btn5 = (Button) findViewById(R.id.btn5);
        Button btn6 = (Button) findViewById(R.id.btn6);
        Button btn7 = (Button) findViewById(R.id.btn7);
        Button btn8 = (Button) findViewById(R.id.btn8);
        Button btn9 = (Button) findViewById(R.id.btn9);
        Button btn_c = (Button) findViewById(R.id.btn_c);
        Button btn_ce = (Button) findViewById(R.id.btn_ce);
        Button btn_left = (Button) findViewById(R.id.btn_left);
        Button btn_right = (Button) findViewById(R.id.btn_right);

        Button btn_add = (Button) findViewById(R.id.btn_add);
        Button btn_sub = (Button) findViewById(R.id.btn_sub);
        Button btn_mul = (Button) findViewById(R.id.btn_mul);
        Button btn_div = (Button) findViewById(R.id.btn_div);
        Button btn_rest = (Button) findViewById(R.id.btn_rest);
        Button btn_result = (Button) findViewById(R.id.btn_result);

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.append("0");
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.append("1");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.append("2");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.append("3");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.append("4");
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.append("5");
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.append("6");
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.append("7");
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.append("8");
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.append("9");
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.append("+");
            }
        });
        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.append("-");
            }
        });
        btn_mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.append("*");
            }
        });
        btn_rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.append("%");
            }
        });
        btn_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.append("/");
            }
        });
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.append("(");
            }
        });
        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.append(")");
            }
        });
        btn_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strTemp = editText .getText().toString();
                if(strTemp.length()==0) editText.setText("");
                else editText.setText(strTemp.substring(0,strTemp.length()-1));
            }
        });
        btn_ce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText("");
                result_tv.setText("");
            }
        });
        btn_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //here
                String infix = editText.getText().toString();
                Toast.makeText(getApplicationContext(), calculate(transform(infix))+" ", Toast.LENGTH_LONG).show();
                result_tv.setText(calculate(transform(infix))+" ");
            }
        });
    }

    private int precedence(char c){
        if(c=='*' || c=='/' || c=='%') return 2;
        else if(c=='+' || c=='-') return 1;
        else return 0;
    }

    public String transform(String inFix) {
        //45+2
        char[] f = inFix.toCharArray();
        Stack<Character> stack = new Stack<>();
        String postFix = "";

        for (char c : f) {
            if (c == '(') {
                stack.push(c);
            }
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postFix += (char) stack.pop();
                }
                stack.pop();

            } else if (c == '+' || c == '-' || c == '*' || c == '/' || c=='%') {
                while (!stack.isEmpty() && precedence((char) stack.peek()) >= precedence(c)) {
                    postFix += (char) stack.pop();
                }
                stack.push(c);

            } else if (c >= '0' && c <= '9')
                postFix += c;
        }

        while (!stack.isEmpty())
            postFix += (char) stack.pop();

        return postFix;
    }

    public int calculate(String postfix) {
        Stack<Integer> stack = new Stack<>();
        char[] f = postfix.toCharArray();
        Integer result = 0;

        for (char c : f) {
            if (c >= '0' && c <= '9') {
                stack.push(Integer.parseInt(String.valueOf(c)));
            } else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '%') {
                try {
                    int a = stack.pop();
                    int b = stack.pop();

                    if (c == '+') {
                        result = b + a;
                        stack.push(result);
                    }
                    if (c == '-') {
                        result = b - a;
                        stack.push(result);
                    }
                    if (c == '*') {
                        result = b * a;
                        stack.push(result);
                    }
                    if (c == '/') {
                        result = b / a;
                        stack.push(result);
                    }
                    if (c == '%') {
                        result = b % a;
                        stack.push(result);
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "연산할 수 없습니다", Toast.LENGTH_LONG).show();
                }
            }
        }
        return stack.pop();
    }
}