/*
참고사이트:
http://powerworker.tistory.com/entry/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-%EA%B0%95%EC%A2%8C-%EC%98%88%EC%A0%9C-%EB%94%B0%EB%9D%BC%ED%95%98%EA%B8%B0-5-%EA%B3%84%EC%82%B0%EA%B8%B0%EB%A7%8C%EB%93%A4%EA%B8%B0-%EA%B3%84%EC%82%B0%EB%B6%80%EB%B6%8422
 */
package com.example.kim.firstapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class cal extends Activity {
    //버튼 선언
    private Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btnAdd,btnSubtract,btnMultiply,btnDivide,btnResult,btnClear;
    //입력값(실제론 출력값) 선언(1st Value,2nd Value,Result)
    private EditText inputEdit;
    //아주 간단한 계산기 보여주기
    private TextView text, resultText;
    String res;
    //Operator List.
    ArrayList<String> operatorList = new ArrayList<String>();
    //+ =1,
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal);
        //버튼 할당
        btn0 = (Button)findViewById(R.id.btn0); btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2); btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4); btn5 = (Button)findViewById(R.id.btn5);
        btn6 = (Button)findViewById(R.id.btn6); btn7 = (Button)findViewById(R.id.btn7);
        btn8 = (Button)findViewById(R.id.btn8); btn9 = (Button)findViewById(R.id.btn9);
        btnAdd = (Button)findViewById(R.id.btnAdd); btnSubtract = (Button)findViewById(R.id.btnSubtract);
        btnMultiply = (Button)findViewById(R.id.btnMultiply); btnDivide = (Button)findViewById(R.id.btnDivide);
        btnResult = (Button)findViewById(R.id.btnResult); btnClear=(Button)findViewById(R.id.btnClear);

        //화면에서 출력해줄 텍스트 할당
        text = (TextView)findViewById(R.id.text);
        text.setText("연산 우선 순위 적용 안되는 간단한 계산기");
        resultText = (TextView)findViewById(R.id.resultText);
        //수입력 값이 보여질 영역. 디자인 때문에 EditText로 구현.
        inputEdit = (EditText)findViewById(R.id.inputEdit);

        OnClickListener cl = new OnClickListener() {
            @Override
            public void onClick(View v) {
               if(v.equals(btn0)){
                   inputEdit.setText(inputEdit.getText().toString()+0);
               }
                else if(v.equals(btn1)){
                   inputEdit.setText(inputEdit.getText().toString()+1);
               }
               else if(v.equals(btn2)){
                   inputEdit.setText(inputEdit.getText().toString() + 2);
               }
               else if(v.equals(btn3)){
                   inputEdit.setText(inputEdit.getText().toString() + 3);
               }
               else if(v.equals(btn4)){
                   inputEdit.setText(inputEdit.getText().toString() + 4);
               }
               else if(v.equals(btn5)){
                   inputEdit.setText(inputEdit.getText().toString() + 5);
               }
               else if(v.equals(btn6)){
                   inputEdit.setText(inputEdit.getText().toString() + 6);
               }
               else if(v.equals(btn7)){
                   inputEdit.setText(inputEdit.getText().toString() + 7);
               }
               else if(v.equals(btn8)){
                   inputEdit.setText(inputEdit.getText().toString() + 8);
               }
               else if(v.equals(btn9)){
                   inputEdit.setText(inputEdit.getText().toString() + 9);
               }
               //연산부분
                else if(v.equals(btnAdd)){
                   inputEdit.setText(inputEdit.getText().toString() + "+");
                   operatorList.add("+");
               }
               else if(v.equals(btnSubtract)){
                   inputEdit.setText(inputEdit.getText().toString() + "-");
                   operatorList.add("-");
               }
               else if(v.equals(btnMultiply)){
                   inputEdit.setText(inputEdit.getText().toString() + "*");
                   operatorList.add("*");
               }
               else if(v.equals(btnDivide)){
                   inputEdit.setText(inputEdit.getText().toString() + "/");
                   operatorList.add("/");
               }
                // = 버튼 누르면 계산 값이 나와야한다.!
                else if(v.equals(btnResult)){
                   res=calc(inputEdit.getText().toString().toString());
                   resultText.setText(res);
                   inputEdit.setText(res);
               }
                //Clear
                else if(v.equals(btnClear)){
                   inputEdit.setText("");
                   resultText.setText("");
               }
            }
        };
        btn0.setOnClickListener(cl); btn1.setOnClickListener(cl);
        btn2.setOnClickListener(cl); btn3.setOnClickListener(cl);
        btn4.setOnClickListener(cl); btn5.setOnClickListener(cl);
        btn6.setOnClickListener(cl); btn7.setOnClickListener(cl);
        btn8.setOnClickListener(cl); btn9.setOnClickListener(cl);
        btnAdd.setOnClickListener(cl); btnSubtract.setOnClickListener(cl);
        btnMultiply.setOnClickListener(cl); btnDivide.setOnClickListener(cl);
        btnResult.setOnClickListener(cl); btnClear.setOnClickListener(cl);
    }
    private String calc(String exp) {
        ArrayList<Double> numberList = new ArrayList<Double>();
        StringTokenizer st = new StringTokenizer(exp,"*/+-");
        while( st.hasMoreTokens() ) {
            double number = Double.parseDouble(st.nextToken());
            numberList.add( number );
            Log.d("aaa", String.valueOf(number));
        }
        double result = numberList.get(0);
        Log.d("bbb", String.valueOf(result) );
        for( int i = 0 ; i < operatorList.size() ; i++ ) {
            String operator = operatorList.get(i);
            if( "*".equals(operator)){
                result = ( result * numberList.get(i+1));
            }else if( "/".equals(operator)){
                result = ( result / numberList.get(i+1));
            }else if( "+".equals(operator)){
                result = ( result + numberList.get(i+1));
            }else if( "-".equals(operator)){
                result = ( result - numberList.get(i+1));
            }
        }
        operatorList.clear();
        numberList.clear();
        return String.valueOf(result);
    }
}
