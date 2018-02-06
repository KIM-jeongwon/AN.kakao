package com.bitcamp.app.kakao;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Member;

public class Index extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);
        final Context context = Index.this;
        final EditText id = findViewById(R.id.input_id);
        final EditText pass = findViewById(R.id.input_pass);
        final MemberExist exist = new MemberExist(context);


        findViewById(R.id.login_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            new Intro.LoginService() {
                @Override
                public void execute() {
                    if(exist.execute(String.valueOf(id.getText()),String.valueOf(pass.getText()))){
                        Toast.makeText(context,"로그인 성공",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(context,MemberList.class));
                    }else{
                        Toast.makeText(context,"로그인 실패",Toast.LENGTH_LONG).show();
                        id.setText("");
                        pass.setText("");
                    }
                }
            }.execute();
            }
        });
    }
    private class LoginQuery extends Intro.QueryFactory{
        SQLiteOpenHelper helper;
        public LoginQuery(Context context) {
            super(context);
            helper = new Intro.SQLiteHelper(context);
        }

        @Override
        public SQLiteDatabase getDatabase() {
            return helper.getReadableDatabase();
        }
    }
    private class MemberExist extends LoginQuery{

        public MemberExist(Context context) {
            super(context);
        }
        public boolean execute(String id,String pass){
            return super.getDatabase().rawQuery(
                    String.format("SELECT * FROM %s WHERE  " +
                            " %s = '%s' AND " +
                            " %s = '%s'",
                            Intro.TABLE_MEMBER,
                            Intro.MEMBER_1,
                            id,
                            Intro.MEMBER_2,
                            pass
                            ), null)
                    .moveToNext();
        }
    }
}
