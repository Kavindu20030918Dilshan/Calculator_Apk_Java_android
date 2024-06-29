package lk.javainstitute.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    TextView resultTv,solutionTv;
    MaterialButton buttonC,buttonSQ,buttonPc;
    MaterialButton buttonDivide,buttonMulti,buttonPlus,buttonMinus,buttonEqual;
    MaterialButton b0,b1,b2,b3,b4,b5,b6,b7,b8,b9;
    MaterialButton buttonAc,buttonDot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        int c, n;
        double d;

        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);

        assignId(buttonC,R.id.button_c);
        assignId(buttonSQ,R.id.button_sqrt);
        assignId(buttonPc,R.id.button_percentage);
        assignId(buttonDivide,R.id.button_divide);
        assignId(b7,R.id.button_7);
        assignId(b8,R.id.button_8);
        assignId(b9,R.id.button_9);
        assignId(buttonMulti,R.id.button_multipliy);
        assignId(b4,R.id.button_4);
        assignId(b5,R.id.button_5);
        assignId(b6,R.id.button_6);
        assignId(buttonPlus,R.id.button_addition);
        assignId(b1,R.id.button_1);
        assignId(b2,R.id.button_2);
        assignId(b3,R.id.button_3);
        assignId(buttonMinus,R.id.button_subscraction);
        assignId(buttonAc,R.id.button_AC);
        assignId(b0,R.id.button_0);
        assignId(buttonDot,R.id.button_dot);
        assignId(buttonEqual,R.id.button_equal);

    }

    void assignId(MaterialButton btn,int id){
        btn  = findViewById(id);
        btn.setOnClickListener(this);
    }


    public void onClick(View view){
        MaterialButton button  = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();

        if (buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }

        if (buttonText.equals("sq")){


            return;
        }

        if (buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }

        if(buttonText.equals("C")){

            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);

        }else{
            dataToCalculate = dataToCalculate + buttonText;
        }


        solutionTv.setText(dataToCalculate);
        String finalResult = getResult(dataToCalculate);
        if (!finalResult.equals("Error")){
            resultTv.setText(finalResult);
        }

    }

    String getResult(String data){
        try {
            Context  context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable  scriptable = context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if (finalResult.endsWith(".0")){
               finalResult =  finalResult.replace(".0","");
            }
            return  finalResult;
        }catch (Exception e){
            return "Error";
        }
    }
}