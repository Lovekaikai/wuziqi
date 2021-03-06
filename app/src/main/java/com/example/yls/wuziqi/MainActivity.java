package com.example.yls.wuziqi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    private WuziqiPanel mPanel;
    private Button mRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPanel= (WuziqiPanel) findViewById(R.id.id_panel);
        mRestart= (Button) findViewById(R.id.id_restart);
//        mRestart.setBackgroundColor(Integer.parseInt("#000000"));
        mPanel.setListener(new ResultListener() {
            @Override
            public void showResult(int result) {
                String text=(result==WuziqiPanel.DRAW)?("和棋!"):(result==WuziqiPanel.WHITE_WON?"恭喜白棋胜利!":"恭喜黑棋胜利!");
//                Toast.makeText(MainActivity.this,text,Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setIcon(android.R.drawable.ic_dialog_info);
                builder.setTitle("游戏结果:");
                builder.setMessage(text);

                builder.setNegativeButton("重来", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPanel.restart();
                    }
                });
                builder.setPositiveButton("退出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.show();
            }
        });
        mRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPanel.restart();
            }
        });
    }
}
