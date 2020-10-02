package com.gyeong.practicesupportlibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn1;
    Button btn2;
    Button btn3;

    FragmentManager manager;
    OneFragment oneFragment;
    TwoFragment twoFragment;
    ThreeFragment threeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1=findViewById(R.id.main_btn1);
        btn2=findViewById(R.id.main_btn2);
        btn3=findViewById(R.id.main_btn3);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);

        manager=getSupportFragmentManager();
        oneFragment=new OneFragment();
        twoFragment=new TwoFragment();
        threeFragment=new ThreeFragment();

        FragmentTransaction tf=manager.beginTransaction();
        tf.addToBackStack(null);
        tf.add(R.id.main_container, oneFragment);
        tf.commit();

    }

    @Override
    public void onClick(View v) {
        if(v==btn1){
            if(!oneFragment.isVisible()){
                FragmentTransaction tf=manager.beginTransaction();
//                Fragment가 화면에 안보이는 순간 제거하지 않고 저장했다가 다시 이용
//                뒤로가기 버튼을 누르면 BackStack에 들어있던 Fragment가 다시 튀어나오는 형식
                tf.addToBackStack(null);
                tf.replace(R.id.main_container, oneFragment);
                tf.commit();
            }
        }else if(v==btn2){
            if(!twoFragment.isVisible()){
//                다이아로그 형태의 Fragment이기 때문에, 다른 Fragment처럼 add하는 것이 아닌, DialogFragment 클래스의 show()함수를 이용해 출력!!
                twoFragment.show(manager, null);
            }
        }else if(v==btn3){
            if(!threeFragment.isVisible()){
                Log.d("kkang","111111111111");
                FragmentTransaction tf=manager.beginTransaction();
                tf.addToBackStack(null);
                tf.replace(R.id.main_container, threeFragment);
                tf.commit();
            }
        }
    }
}
